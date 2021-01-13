package com.poshaque.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poshaque.adapter.RazorPayAdapter;
import com.poshaque.dao.CartRepository;
import com.poshaque.dao.OrdersRepository;
import com.poshaque.enums.OrderStatus;
import com.poshaque.exception.PoshaqueBussinessException;
import com.poshaque.model.Cart;
import com.poshaque.model.Orders;
import com.poshaque.util.JsonAdapter;
import com.razorpay.Order;

@Service
public class PaymentService {
	
	@Autowired
	RazorPayAdapter rpAdapter;
	
	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	OrdersRepository ordersRepository;
	
	@Autowired
	JsonAdapter jsonAdapter;

	public Map<?, ?> createRPOrder(UserPrincipal principal, int addressId){
		List<Orders> orderList =ordersRepository.findByUserIdAndStatus(principal.getId(), OrderStatus.PENDING.toString());
		
		if(orderList.size() > 0)
			throw new PoshaqueBussinessException("Transaction has already begun. Please complete the transaction or wait for 5mins for a new one.");
		
		Cart cart = cartRepository.findByUserId(principal.getId());
		Order rporder = null;
		Map<?, ?> retMap = null;
		try {
			String cartTotal = findTotal(cart.getCartData());
			Orders order = new Orders(principal.getId(), cart.getCartData(), cartTotal, new Date(), false, true, OrderStatus.PENDING.toString(),addressId);
			order = ordersRepository.save(order);
			JSONObject options = new JSONObject();
			options.put("amount", cartTotal);
			options.put("currency", "INR");
			options.put("receipt", "PSHQ_"+order.getId());
			rporder = rpAdapter.createOrder(options);
			order.setClientOrderId(rporder.get("id"));
			order.setReceiptId(rporder.get("receipt"));
			order = ordersRepository.save(order);
			retMap = jsonAdapter.marshal(rporder.toString());
			setTimerForOrderTransaction(order);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retMap;
	}
	
	public void setTimerForOrderTransaction(Orders order){
		Timer t = new Timer();  
		TimerTask tt = new TimerTask() {  
		    @Override  
		    public void run() {  
		    	ordersRepository.updateOrderStatus(order.getUserId());
		    };  
		};
		
		Calendar date = Calendar.getInstance();
		long timeInMills= date.getTimeInMillis();
		Date afterFiveMins=new Date(timeInMills + (5 * 60000));
		t.schedule(tt,afterFiveMins);  
	}

	public Orders updateOrder(Map<String, Object> map, UserPrincipal principal) {
		Optional<Orders> optorder = ordersRepository.findByClientOrderId(map.get("order_id").toString());
		Orders order = optorder.get();
		
		if(order == null)
			throw new PoshaqueBussinessException("Internal Server Error. Try again later.");
		
		order.setHasActiveTransaction(false);
		if(((boolean)map.get("status")) == true){
			if(rpAdapter.verifySigneture(map)){
				order.setClientPaymentId(map.get("payment_id").toString());
				order.setClientSignature(map.get("sign_id").toString());
				order.setPaymentStatus(true);
				order.setStatus("ACCEPTED");
				cartRepository.removeByUserId(principal.getId());
			}else
				throw new PoshaqueBussinessException("Couldn't verify signeture. Try again later.");
		}else{
			order.setStatus("FAILED");
		}
		
		return ordersRepository.save(order);
	}
	
	
	public String findTotal(String cartData) throws JSONException{
		Integer price = 0;
		JSONObject cartDetails = new JSONObject(cartData);
		JSONArray indexOfObjs =  cartDetails.getJSONArray("productIndexes");
		JSONObject products = cartDetails.getJSONObject("products");
		for (int i = 0; i < indexOfObjs.length(); i++) {
			JSONObject product = products.getJSONObject(indexOfObjs.getString(i));
			price += product.getInt("quantity") * product.getInt("price");
		}
		return (price.toString() + "00");
	}
}
