package com.granduation.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Product_Images {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private String product_image_name;

	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false, referencedColumnName = "id")
	private Product product;

	public Product_Images() {
		super();
	}

	public Product_Images(String product_image_name, Product product) {
		super();
		this.product_image_name = product_image_name;
		this.product = product;
	}

	public String getProduct_image_name() {
		return product_image_name;
	}

	public void setProduct_image_name(String product_image_name) {
		this.product_image_name = product_image_name;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
