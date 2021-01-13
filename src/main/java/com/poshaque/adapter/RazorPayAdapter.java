package com.poshaque.adapter;

import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Refund;
import com.razorpay.Utils;

@Component
public class RazorPayAdapter {

	public Order createOrder(JSONObject json){
		Order order = null;
		try {
			RazorpayClient client = new RazorpayClient("rzp_test_1eo3r4LGkOpo75", "5R46Yn6t865BaOcjJNGDVZbQ");
			 order = client.Orders.create(json);
		} catch (RazorpayException e) {
			e.printStackTrace();
		}
		return order;
	}
	

	public Refund refundOrder(JSONObject json){
		Refund refund = null;
		try {
			RazorpayClient client = new RazorpayClient("rzp_test_1eo3r4LGkOpo75", "5R46Yn6t865BaOcjJNGDVZbQ");
			refund = client.Payments.refund(json);
		} catch (RazorpayException e) {
			e.printStackTrace();
		}
		return refund;
	}

	public boolean verifySigneture(Map<String, Object> map) {
		boolean resp = false;
		try {
			JSONObject options = new JSONObject();
			options.put("razorpay_order_id", map.get("order_id").toString());
			options.put("razorpay_payment_id",map.get("payment_id").toString());
			options.put("razorpay_signature", map.get("sign_id").toString());
			resp = Utils.verifyPaymentSignature(options, "5R46Yn6t865BaOcjJNGDVZbQ");
		} catch (RazorpayException | JSONException e) {
			e.printStackTrace();
		}
		return resp;
	}
}
