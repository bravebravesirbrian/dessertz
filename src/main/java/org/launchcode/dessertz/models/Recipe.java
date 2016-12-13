package org.launchcode.dessertz.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "recipe")
public class Recipe extends AbstractEntity {

	private String title;
	private String inst;
	private String ingr;
	private User author;
	private String prep;
	private String total;
	private String serve;
	public double rate;
	public double rating;
	private String category;
	private Date created;
	private Date modified;
	
	public Recipe() {}
	
	public Recipe(String title, String inst, String ingr, User author, String prep, String total, String serve, double rate, double rating, String category) {
		
		super();
		
		this.title = title;
		this.inst = inst;
		this.ingr = ingr;
		this.author = author;
		this.prep = prep;
		this.total = total;
		this.serve = serve;
		this.rate = rate;
		this.rating = rating;
		this.category = category;
		this.created = new Date();
		this.updated();
		
		author.addRecipe(this);
		
	}
	
	@NotNull
    @Column(name = "title")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
		this.updated();
	}

	@NotNull
    @Column(name = "inst")
	public String getInst() {
		return inst;
	}

	public void setInst(String inst) {
		this.inst = inst;
		this.updated();
	}
	
	@NotNull
    @Column(name = "ingr")
	public String getIngr() {
		return ingr;
	}

	public void setIngr(String ingr) {
		this.ingr = ingr;
		this.updated();
	}
	
	@NotNull
    @Column(name = "prep")
	public String getPrep() {
		return prep;
	}

	public void setPrep(String prep) {
		this.prep = prep;
		this.updated();
	}
	
	@NotNull
    @Column(name = "total")
	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
		this.updated();
	}
	
	@NotNull
    @Column(name = "serve")
	public String getServe() {
		return serve;
	}

	public void setServe(String serve) {
		this.serve = serve;
		this.updated();
	}
	
	@NotNull
    @Column(name = "rate")
	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
		this.updated();
	}
	
	@NotNull
    @Column(name = "rating")
	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
		this.updated();
	}
	
	@NotNull
    @Column(name = "category")
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
		this.updated();
	}
	
	@ManyToOne
	public User getAuthor() {
		return author;
	}
	
	@SuppressWarnings("unused")
	private void setAuthor(User author) {
		this.author = author;
	}
	
	@NotNull
	@OrderColumn
	@Column(name = "created")
	public Date getCreated() {
		return created;
	}
	
	@SuppressWarnings("unused")
	private void setCreated(Date created) {
		this.created = created;
	}
	
	@NotNull
	@Column(name = "modified")
	public Date getModified() {
		return modified;
	}
	
	@SuppressWarnings("unused")
	private void setModified(Date modified) {
		this.modified = modified;
	}
	
	private void updated() {
		this.modified = new Date();
	}
	
}
