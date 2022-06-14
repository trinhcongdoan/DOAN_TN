package com.granduation.Entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.sun.istack.NotNull;

@Entity
@Table
public class Supplier {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull
	@NotEmpty(message = "Tên danh mục không được trống!")
	private String name;
	@NotEmpty(message = "Địa chỉ không được trống!")
	@NotNull
	private String address;
	@NotEmpty(message = "Chưa chọn xã!")
	@NotNull
	private String ward;
	@NotEmpty(message = "Chưa chọn huyện!")
	@NotNull
	private String district;
	@Pattern(regexp = "^0[1-9]{1}[0-9]{8}$", message = "Định dạng sdt chưa đúng")
	@NotNull
	private String phone;
	@Pattern(regexp = "^[a-zA-Z][\\w]+@([\\w]+\\.[\\w]+|[\\w]+\\.[\\w]+.[\\w]+)$", message = "Định dạng email k đúng")
	@NotNull
	private String email;
	@NotEmpty(message = "Chưa chọn tỉnh!")
	@NotNull
	private String province;
	@NotNull
	private String nation;

	@OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
	private List<Product> product;

	public Supplier() {
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getWard() {
		return ward;
	}

	public void setWard(String ward) {
		this.ward = ward;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public List<Product> getProduct() {
		return product;
	}

	public void setProduct(List<Product> product) {
		this.product = product;
	}

}
