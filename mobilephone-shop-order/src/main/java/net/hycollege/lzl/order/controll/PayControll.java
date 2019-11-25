package net.hycollege.lzl.order.controll;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import net.hycollege.message.bean.ali.AliForm;

@RestController
public class PayControll {

	
	@PostMapping("/ali/pay")
	public void createForm(AliForm aliForm) {
		
	}

}
