package org.launchcode.dessertz.controllers;

import java.util.List;

import org.launchcode.dessertz.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController extends AbstractController {
	
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String userForm(Model model) {
		
		List<User> users = userDao.findAll();
		model.addAttribute("users", users);
		return "user";
	}

}
