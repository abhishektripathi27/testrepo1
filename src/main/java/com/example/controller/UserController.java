package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.model.Users;
import com.example.repos.UsersRepository;

@Controller
public class UserController {
	
	@Autowired
	private UsersRepository usersRepository;

	@RequestMapping("/adduser")
	@ResponseBody
	public String test(@RequestParam(value="firstName") String firstName) {
	    Users user = new Users();
	    user.setFirstName(firstName);
//	    person.setLastName("Test");
	    usersRepository.save(user);
	    return "hello "+firstName;
	}

}
