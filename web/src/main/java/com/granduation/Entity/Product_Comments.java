package com.granduation.Entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table
public class Product_Comments {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String email;
	private String content;
	private int rank;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@CreationTimestamp
	private Date create_at;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date update_at;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id", nullable = false, referencedColumnName = "id")
	private Product product;

	public Product_Comments() {
		super();
	}

	public Product_Comments(String comment_name, String comment_email, String comment_content, int rank,
			Product product) {
		super();
		this.name = comment_name;
		this.email = comment_email;
		this.content = comment_content;
		this.rank = rank;
		this.product = product;
	}

	public String getName() {
		return name;
	}

	public void setName(String comment_name) {
		this.name = comment_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String comment_email) {
		this.email = comment_email;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String comment_content) {
		this.content = comment_content;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public Date getCreate_at() {
		return create_at;
	}

	public void setCreate_at(Date create_at) {
		this.create_at = create_at;
	}

	public Date getUpdate_at() {
		return update_at;
	}

	public void setUpdate_at(Date update_at) {
		this.update_at = update_at;
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
