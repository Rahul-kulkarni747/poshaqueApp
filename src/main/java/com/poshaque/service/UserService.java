package com.poshaque.service;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.poshaque.dao.AddressRepository;
import com.poshaque.dao.PinRepository;
import com.poshaque.dao.UserRepository;
import com.poshaque.dto.AddressDTO;
import com.poshaque.dto.UserDTO;
import com.poshaque.exception.PoshaqueBussinessException;
import com.poshaque.model.Address;
import com.poshaque.model.Pin;
import com.poshaque.model.User;

@Service
public class UserService extends AbstractBaseService implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private AddressRepository addressRepo;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private EmailServiceImpl emailService;

	@Autowired
	private PinRepository pinRepo;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		loggedInUser = userRepo.findByUsername(userName);
		if (loggedInUser == null)
			throw new UsernameNotFoundException("Username does not exist");

		return new UserPrincipal(loggedInUser);
	}

	public boolean signUpUser(UserDTO dto) {
		User dbUser = userRepo.findByUsername(dto.getEmail());
		
		if (dbUser != null)
			throw new PoshaqueBussinessException("User with this email already exists.");
		
		if (!dto.getPassword().equals(dto.getConfirmPassword()))
			throw new PoshaqueBussinessException("Passwords do not match.");
		
		Integer pin = (int) (Math.random() * (99999999 - 10000000 + 1) + 10000000);
		Pin pinObj = pinRepo.findByUserEmail(dto.getEmail());
		int id = (pinObj == null)?0:pinObj.getId();
		pinObj = new Pin(id,pin.toString(), (new Date()), dto.getEmail());
		pinObj = pinRepo.save(pinObj);
		setTimerToDeletePin(pinObj);
		
		emailService.sendSimpleMessage(dto.getEmail(), "Welcome to Poshaque",
				"Hi "+dto.getFirstName()+" "+dto.getLastName()+", Welcome to poshaque, please verify your email address. Verification code is : " + pin);
		
		return true;
	}
	
	public boolean saveUser(UserDTO dto){
		Pin pinObj = pinRepo.findByUserEmail(dto.getEmail());
		
		if(pinObj.getPinValue().equals(dto.getOTP())){
			User user = new User(0,dto.getFirstName(), dto.getLastName(), ACTIVE, 1, dto.getPhone(),
					dto.getEmail(), bCryptPasswordEncoder.encode(dto.getPassword()),(new Date().toString()),(new Date().toString()));
			
			User savedUser = userRepo.save(user);
			
			for (AddressDTO addressDTO : dto.getAddress()) {
				Address address = new Address(addressDTO.getAddressLine1(), addressDTO.getAddressLine2(),
						addressDTO.getState(), addressDTO.getCity(), addressDTO.getZip(), addressDTO.getLandmark(),
						savedUser.getId());
				addressRepo.save(address);
			}
			this.pinRepo.delete(pinObj);
		}else{
			throw new PoshaqueBussinessException("Pin is incorrect or expired.");
		}
		return true;
	}
	
	public void setTimerToDeletePin(Pin pin){
		Timer t = new Timer();  
		TimerTask tt = new TimerTask() {  
		    @Override  
		    public void run() {  
		        pinRepo.delete(pin);
		    };  
		};
		
		Calendar date = Calendar.getInstance();
		long timeInMills= date.getTimeInMillis();
		Date afterFiveMins=new Date(timeInMills + (5 * 60000));
		t.schedule(tt,afterFiveMins);  
	}

}
