package org.launchcode.dessertz.models.dao;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.launchcode.dessertz.models.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface RecipeDao extends CrudRepository<Recipe, Integer> {
	
Recipe findByTitle(String title);
	
	Recipe findByUid(int uid);
	
	Recipe findByCreated(Date created);
	
	Recipe findByModified(Date modified);
	
	List<Recipe> findByCategory(String category);
	
	List<Recipe> findAll();
    
    List<Recipe> findByAuthor(int authorId);
	
}
