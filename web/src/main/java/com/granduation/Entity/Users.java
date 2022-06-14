package com.granduation.Entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@NotEmpty(message = "Username k đc trống!")
	@Column(unique = true)
	private String username;
	@NotEmpty(message = "Họ đệm k đc trống!")
	@NotNull
	private String firstname;
	@NotEmpty(message = "Tên k đc trống!")
	@NotNull
	private String lastname;
	@Pattern(regexp = "^[a-zA-Z][\\w]+@([\\w]+\\.[\\w]+|[\\w]+\\.[\\w]+.[\\w]+)$", message = "Định dạng email k đúng")
	@NotNull
	@Column(unique = true)
	private String email;
	@Pattern(regexp = "^0[1-9]{1}[0-9]{8}$", message = "Định dạng sdt chưa đúng")
	@NotNull
	@Column(unique = true)
	private String phone;
	@NotEmpty(message = "Mật khẩu k đc trống!")
	@NotNull
	private String password;
	@NotEmpty(message = "Địa chỉ k đc trống!")
	@Column(nullable = false)
	private String address;
	@NotEmpty(message = "Xã k đc trống!")
	@NotNull
	private String ward;
	@NotEmpty(message = "Huyện k đc trống!")
	@NotNull
	private String district;
	@NotEmpty(message = "Tỉnh k đc trống!")
	@NotNull
	private String province;
	@NotNull
	private boolean enable;

	@Column(name = "reset_password_token")
	private String resetPasswordToken;

	@Enumerated(EnumType.STRING)
	private ProviderAuth provider;

	public ProviderAuth getProvider() {
		return provider;
	}

	public void setProvider(ProviderAuth provider) {
		this.provider = provider;
	}

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Bill> bill;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "auth_id", nullable = false, referencedColumnName = "id")
	private Authorization authorization;

	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	private List<Cart> cart;

	public Users() {
		super();
	}

	public Users(@NotNull String username, @NotNull String firstname, @NotNull String lastname, @NotNull String email,
			@NotNull String phone, @NotNull String password, String address, @NotNull String ward,
			@NotNull String district, @NotNull String province, @NotNull boolean enable, String resetPasswordToken,
			ProviderAuth provider, List<Bill> bill, Authorization authorization) {
		super();
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.address = address;
		this.ward = ward;
		this.district = district;
		this.province = province;
		this.enable = enable;
		this.resetPasswordToken = resetPasswordToken;
		this.provider = provider;
		this.bill = bill;
		this.authorization = authorization;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public String getResetPasswordToken() {
		return resetPasswordToken;
	}

	public void setResetPasswordToken(String resetPasswordToken) {
		this.resetPasswordToken = resetPasswordToken;
	}

	public List<Bill> getBill() {
		return bill;
	}

	public void setBill(List<Bill> bill) {
		this.bill = bill;
	}

	public Authorization getAuthorization() {
		return authorization;
	}

	public void setAuthorization(Authorization authorization) {
		this.authorization = authorization;
	}

	public List<Cart> getCart() {
		return cart;
	}

	public void setCart(List<Cart> cart) {
		this.cart = cart;
	}

}
