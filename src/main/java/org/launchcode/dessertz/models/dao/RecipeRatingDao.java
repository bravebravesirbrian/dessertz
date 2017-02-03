package org.launchcode.dessertz.models.dao;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.launchcode.dessertz.models.RecipeRating;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface RecipeRatingDao extends CrudRepository<RecipeRating, Integer>{

	List<RecipeRating> findByRecipeId(int recipeId);
	
}
