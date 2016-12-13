package org.launchcode.dessertz.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.launchcode.dessertz.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AuthenticationController extends AbstractController {
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signupForm() {
		return "signup";
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(HttpServletRequest request, Model model) {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String verify = request.getParameter("verify");
		if (User.isValidUsername(username) == false){
			model.addAttribute("username_error", "This username is not valid.");
			return "signup";
		}
		if (User.isValidPassword(password) == false){
			model.addAttribute("password_error", "This password is not valid.");
			return "signup";
		}
		if (password.equals(verify) == false){
			model.addAttribute("verify_error", "The passwords do not match.");
			return "signup";
		}
		User newUser= new User(username, password);
		userDao.save(newUser);
		HttpSession session = request.getSession();
		this.setUserInSession(session, newUser);
		return "redirect:allrecipes/newrecipe";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginForm() {
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, Model model) {
		
		// TODO - implement login
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = userDao.findByUsername(username);
		if (user == null){
			model.addAttribute("error", "Username not found.");
			return "login";
		}
		if (user.isMatchingPassword(password) == false){
			model.addAttribute("error", "Incorrect password.");
			return "login";
		}
		HttpSession session = request.getSession();
		this.setUserInSession(session, user);
		return "redirect:allrecipes/newrecipe";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request){
        request.getSession().invalidate();
		return "redirect:/";
	}
}
