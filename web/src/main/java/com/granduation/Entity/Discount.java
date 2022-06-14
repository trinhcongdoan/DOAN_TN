package com.granduation.Entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table
public class Discount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull(message = "Không được để trống!")
	@NotEmpty(message = "Thông tin khuyến mãi không được trống!")
	private String name;
	@NotNull(message = "Không được để trống!")
	@Range(min = 0, message = "Discount > 0!")
	private Double discount;
	private int time;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date create_at;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date update_at;

	@NotNull(message = "Số lượng không được trống!")
	@Range(min = 1, message = "Số lượng sản phẩm ít nhất 1!")
	private int quantity;

	@NotNull(message = "Không tìm thấy sản phẩm nào!")
	@OneToOne
	@JoinColumn(name = "product_id", nullable = false, referencedColumnName = "id")
	private Product product;

	public Discount() {
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

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
