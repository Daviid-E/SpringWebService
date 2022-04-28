package com.springwebservice.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springwebservice.entity.User;
import com.springwebservice.service.UserService;
import com.springwebservice.service.UserValidator;



@Controller
@RequestMapping("/admin")
public class AdminController {
	
	private UserService userService;
	private UserValidator userValidator;
	
	public AdminController(UserService userService, UserValidator userValidator) {
		this.userService=userService;
		this.userValidator=userValidator;
	}
	
	@RequestMapping("/")
	public String adminPage(Principal principal, Model model) {
	     String username = principal.getName();
	     model.addAttribute("currentUser", userService.findByUsername(username));
	     return "adminPage.html";
	 }
	 @GetMapping("/adduser")
	    public String signupform(@Valid @ModelAttribute("user") User user) {
	        return "signup";
	    }
	 @PostMapping("/adduser")
	public String adduser(@Valid @ModelAttribute("user") User user, BindingResult result, Model model, HttpSession session) {
		userValidator.validate(user, result);
		 if (result.hasErrors()) {
            return "signup";
        }
        userService.saveWithUserRole(user);
        return "redirect:/userlist";
    }
} 
