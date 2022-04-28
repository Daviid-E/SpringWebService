package com.springwebservice.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.springwebservice.entity.User;
import com.springwebservice.repository.UserRepository;


@Controller
public class CrudController {
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/userlist")
	public String showUserList(Model model) {
	    model.addAttribute("users", userRepository.findAll());
	    return "userlistpage";
	}
	
	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
	    User user = userRepository.findById(id)
	      .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
	    
	    model.addAttribute("user", user);
	    return "editPage";
	}
	@PostMapping("/update/{id}")
	public String updateUser(@PathVariable("id") long id, @Valid User user, 
	  BindingResult result, Model model) {
	    if (result.hasErrors()) {
	        user.setId(id);
	        return "editPage";
	    }
	        
	    userRepository.save(user);
	    return "redirect:/userlist";
	}
	
	
}