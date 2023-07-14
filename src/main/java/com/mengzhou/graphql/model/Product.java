package com.mengzhou.graphql.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
public class Product {
	  @Id
	  @GeneratedValue
	  private int id;

	  private String title;

	  private boolean isOnSale;

	  private float weight;

	private BigDecimal price;
	private LocalDateTime dateCreated;

	  public Product() {

	  }

	  public Product(String title, boolean isOnSale, float weight, BigDecimal price, LocalDateTime dateCreated) {

		  this.title = title;
		  this.isOnSale = isOnSale;
		  this.weight = weight;
		  this.price = price;
		  this.dateCreated = dateCreated;
	  }

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}



	public boolean isOnSale() {
		return isOnSale;
	}

	public void setOnSale(boolean onSale) {
		isOnSale = onSale;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "Product{" +
				"id=" + id +
				", title='" + title + '\'' +
				", isOnSale=" + isOnSale +
				", weight=" + weight +
				'}';
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public LocalDateTime getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(LocalDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}
}
