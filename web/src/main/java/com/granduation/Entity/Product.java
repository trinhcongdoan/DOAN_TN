package com.granduation.Entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotEmpty(message = "Tên k được trống!")
	@Column(nullable = false, unique = true)
	private String name;
	@NotEmpty(message = "Slug k được trống!")
	@Column(nullable = false, unique = true)
	private String slug;
	@Range(min = 0, message = "Giá phải lớn hơn 0!")
	@NotNull(message = "Giá phải lớn hơn 0!")
	private Double price;
	private Double price_discount = 0.0;
	@NotEmpty(message = "Unit k được trống!")
	@NotNull
	private String unit;
	@Range(min = 1, message = "Số lượng phải lớn hơn 0!")
	@NotNull(message = "Số lượng phải lớn hơn 0!")
	private int quantity;
	@NotNull
	private Boolean topfeatured;
	private String pimage;
	@NotNull
	private int view;
	private String description;
	@NotNull(message = "Không được trống")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date product_MFG;
	@NotNull(message = "Không được trống")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date product_EXP;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date create_at;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date update_at;

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<Product_Images> product_images;

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<Product_Comments> product_comment;
	
	@NotNull(message = "Bạn phải thêm loại hàng trước!")
	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false, referencedColumnName = "id")
	private Category category;

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<BillInfo> billInfo;

	@ManyToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<Blog> blog_food;

	@NotNull(message = "Chưa có NCC!")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "supplier_id", nullable = false, referencedColumnName = "id")
	private Supplier supplier;

	public Product() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getPrice_discount() {
		return price_discount;
	}

	public void setPrice_discount(Double price_discount) {
		this.price_discount = price_discount;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Boolean getTopfeatured() {
		return topfeatured;
	}

	public void setTopfeatured(Boolean topfeatured) {
		this.topfeatured = topfeatured;
	}

	public String getPimage() {
		return pimage;
	}

	public void setPimage(String pimage) {
		this.pimage = pimage;
	}

	public int getView() {
		return view;
	}

	public void setView(int view) {
		this.view = view;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getProduct_MFG() {
		return product_MFG;
	}

	public void setProduct_MFG(Date product_MFG) {
		this.product_MFG = product_MFG;
	}

	public Date getProduct_EXP() {
		return product_EXP;
	}

	public void setProduct_EXP(Date product_EXP) {
		this.product_EXP = product_EXP;
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

	public List<Product_Images> getProduct_images() {
		return product_images;
	}

	public void setProduct_images(List<Product_Images> product_images) {
		this.product_images = product_images;
	}

	public List<Product_Comments> getProduct_comment() {
		return product_comment;
	}

	public void setProduct_comment(List<Product_Comments> product_comment) {
		this.product_comment = product_comment;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public List<BillInfo> getBillInfo() {
		return billInfo;
	}

	public void setBillInfo(List<BillInfo> billInfo) {
		this.billInfo = billInfo;
	}

	public List<Blog> getBlog_food() {
		return blog_food;
	}

	public void setBlog_food(List<Blog> blog_food) {
		this.blog_food = blog_food;
	}

	public int getSold() {
		int sum=0;
		for (BillInfo b : billInfo) {
			sum+=b.getQuantity();
		}
		return sum;
	}
}
