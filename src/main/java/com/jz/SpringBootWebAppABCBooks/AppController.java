package com.jz.SpringBootWebAppABCBooks;


import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
public class AppController {
	
	@RequestMapping("/test")
	public String test() {
		
		System.out.println("AppController->wellcome()");
		
		return "test";
	}
	
	@RequestMapping("/books_list")
	public String listContact(Model model) {
		
		ContactBusiness business = new ContactBusiness();
		List<Contact> contactList = business.getContactList();
		
		model.addAttribute("contacts", contactList);
		
		return "contact";
		
	}
}
