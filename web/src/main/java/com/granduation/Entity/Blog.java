package com.granduation.Entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.sun.istack.NotNull;

@Entity
@Table
public class Blog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotEmpty(message = "Title k đc trống!")
	@NotNull
	private String title;
	@NotEmpty(message = "Slug k đc trống!")
	@Column(nullable = false, unique = true)
	private String slug;
	@NotEmpty(message = "Mô tả k đc trống!")
	@NotNull
	private String description;
	@NotEmpty(message = "Nội dung k đc trống!")
	@NotNull
	private String content;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@CreationTimestamp
	private Date create_at;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date update_at;
	
	private String image;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "material", joinColumns = @JoinColumn(name = "blog_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
	private List<Product> product;

	public Blog() {
		super();
	}

	public Blog(String blog_food_title, String blog_food_slug, String blog_food_description, String blog_food_content,
			List<Product> product) {
		super();
		this.title = blog_food_title;
		this.slug = blog_food_slug;
		this.description = blog_food_description;
		this.content = blog_food_content;
		this.product = product;
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

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public List<Product> getProduct() {
		return product;
	}

	public void setProduct(List<Product> product) {
		this.product = product;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
