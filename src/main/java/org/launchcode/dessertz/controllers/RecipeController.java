package org.launchcode.dessertz.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.launchcode.dessertz.models.Recipe;
import org.launchcode.dessertz.models.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RecipeController extends AbstractController {

	@RequestMapping(value = "/allrecipes/newrecipe", method = RequestMethod.GET)
	public String newRecipeForm() {
		return "newrecipe";
	}
	
	@RequestMapping(value = "/allrecipes/newrecipe", method = RequestMethod.POST)
	public String newRecipe(HttpServletRequest request, Model model) {
		
		String title = request.getParameter("title");
		String inst = request.getParameter("inst");
		String ingr = request.getParameter("ingr");
		String image = request.getParameter("image");
		String prep = request.getParameter("prep");
		String total = request.getParameter("total");
		String serve = request.getParameter("serve");
		User author = this.getUserFromSession(request.getSession());
		if (title == ""){
			title = "No Title";
		}
		double rate = 0;
		double rating = 0;
		String category = request.getParameter("category");
		if (category == null){
			category = "other";
		}
		Recipe newRecipe = new Recipe(title, inst, ingr, image, author, prep, total, serve, rate, rating, category);
		recipeDao.save(newRecipe);
		int uid = newRecipe.getUid();
		String str = "redirect:" + author.getUsername() + "/" + uid;
		return str; 		
	}
	
	@RequestMapping(value = "/allrecipes/{username}/{uid}", method = RequestMethod.GET)
	public String singleRecipe(HttpServletRequest request, @PathVariable String username, @PathVariable int uid, Model model) {
		
		Recipe recipe = recipeDao.findByUid(uid);
		System.out.println(request.getSession());
		boolean loggedIn = true;
		int userId = -1;
		try { 
			userId = this.getUserFromSession(request.getSession()).getUid();
		} catch (Exception e) {
			loggedIn = false;
			System.out.println(e);
		}
		List<RecipeRating> recipeRatings = recipeRatingDao.findByRecipeId(uid);
		boolean rated = false;
		for (int i=0; i < recipeRatings.size(); i++){
			if (userId == recipeRatings.get(i).getUserId()){
				rated = true;
			}
		}
		model.addAttribute("recipe", recipe);
		model.addAttribute("rated", rated);
		model.addAttribute("loggedIn", loggedIn);
		return "recipe";
	}
	
	@RequestMapping(value = "/allrecipes/{username}", method = RequestMethod.GET)
	public String userRecipes(@PathVariable String username, Model model) {
		
		User user = userDao.findByUsername(username);
		List<Recipe> recipes = user.getRecipes();
		model.addAttribute("recipes", recipes);
		return "allRecipes";
	}
	
	@RequestMapping(value = "/allrecipes/{username}/{uid}", method = RequestMethod.POST)
	public String singleRecipeRated(HttpServletRequest request, @PathVariable String username, @PathVariable int uid, Model model){
		
		Recipe recipe = recipeDao.findByUid(uid);
		recipe.rate = recipe.rate + 1;
		recipe.rating = recipe.rating + Double.parseDouble(request.getParameter("rating"));
		recipeDao.save(recipe);
		int recipeId = uid;
		int userId = this.getUserFromSession(request.getSession()).getUid();
		double rating = Double.parseDouble(request.getParameter("rating"));
		RecipeRating newRecipeRating = new RecipeRating(recipeId, userId, rating);
		recipeRatingDao.save(newRecipeRating);
		model.addAttribute("recipe", recipe);
		return "recipe";
	}
	
	@RequestMapping(value = "/allrecipes/categories", method = RequestMethod.GET)
	public String categoryRecipes() {
		return "categories";
	}
	
	@RequestMapping(value = "/allrecipes/categories/{category}", method = RequestMethod.GET)
	public String singleCategory(@PathVariable String category, Model model) {
		
		List<Recipe> recipes = recipeDao.findByCategory(category);
		model.addAttribute("recipes", recipes);
		return "allRecipes";
	}
}
