package org.launchcode.dessertz.controllers;

import javax.servlet.http.HttpSession;

import org.launchcode.dessertz.models.User;
import org.launchcode.dessertz.models.dao.RecipeDao;
import org.launchcode.dessertz.models.dao.UserDao;
import org.launchcode.dessertz.models.dao.RecipeRatingDao;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractController {

	@Autowired
    protected UserDao userDao;
	
	@Autowired
	protected RecipeDao recipeDao;
	
	@Autowired
	protected RecipeRatingDao recipeRatingDao;

    public static final String userSessionKey = "user_id";

    protected User getUserFromSession(HttpSession session) {
    	
        Integer userId = (Integer) session.getAttribute(userSessionKey);
        return userId == null ? null : userDao.findByUid(userId);
    }
    
    protected void setUserInSession(HttpSession session, User user) {
    	session.setAttribute(userSessionKey, user.getUid());
    }
	
}
