package org.launchcode.dessertz.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table (name = "recipeRating")
public class RecipeRating extends AbstractEntity {
	
	private int recipeId;
	private int userId;
	private double rating;
	
	public RecipeRating() {}
	
	public RecipeRating(int recipeId, int userId, double rating) {
		
		super();
		
		this.setRecipeId(recipeId);
		this.setUserId(userId);
		this.setRating(rating);
		
		
	}
	
	@NotNull
	@Column(name = "recipeId")
	public int getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(int recipeId) {
		this.recipeId = recipeId;
	}

	@NotNull
	@Column(name = "userId")
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@NotNull
	@Column(name = "rating")
	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

}
