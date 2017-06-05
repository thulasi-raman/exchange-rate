package com.exrate.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.exrate.dto.UserFormDTO;
import com.exrate.entity.User;
import com.exrate.service.UserServiceImpl;
import com.exrate.validator.UserValidator;

@Controller
public class UserController {
	
	@Autowired
	public UserValidator userValidator;
	
	@Autowired
	public UserServiceImpl userService;
	
	@RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registrationForm(Model model) {
        model.addAttribute("userFormData", new UserFormDTO());
        return "registration";
    }
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String handleRegistration(@Valid @ModelAttribute("userFormData") UserFormDTO userForm, 
    		BindingResult result, Model model) {
		userValidator.validate(userForm, result);
		model.addAttribute("userFormData", userForm);
		if(result.hasErrors()) return "registration";
		User user = userService.registerNewUser(userForm);
		//if(null != user) System.out.println("User registered with email : " + user.getEmail() + " Password: " + user.getEncryptedPassword());
		return "redirect:/login";
	}

}
