package org.launchcode.dessertz.controllers;

import java.util.List;

import org.launchcode.dessertz.models.Recipe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AllRecipesController extends AbstractController {

	@RequestMapping(value = "/")
	public String index(){
		return "index";
	}
	
	@RequestMapping(value = "/allrecipes")
	public String recipeIndex(Model model) {
		
		List<Recipe> recipes = recipeDao.findAll();
		model.addAttribute("recipes", recipes);
		return "allRecipes";
	}
	
}
