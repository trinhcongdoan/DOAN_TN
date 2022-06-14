package com.granduation.Entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.sun.istack.NotNull;

@Entity
@Table
public class Bill {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull
	@NotEmpty(message = "Không được bỏ trống")
	private String receiver_name;
	@Pattern(regexp = "^[a-zA-Z][\\w]+@([\\w]+\\.[\\w]+|[\\w]+\\.[\\w]+.[\\w]+)$", message = "Định dạng email k đúng")
	@NotNull
	private String receiver_email;
	@Pattern(regexp = "^0[1-9]{1}[0-9]{8}$", message = "Định dạng sdt chưa đúng")
	@NotNull
	private String receiver_phone;
	@NotEmpty(message = "Không được bỏ trống")
	@NotNull
	private String receiver_address;
	@NotEmpty(message = "Chưa chọn xã!")
	@NotNull
	private String receiver_ward;
	@NotEmpty(message = "Chưa chọn huyện!")
	@NotNull
	private String receiver_district;
	@NotEmpty(message = "Chưa chọn tỉnh!")
	@NotNull
	private String receiver_province;
	private String note;
	@NotNull
	private double total;
	@NotEmpty(message = "Chưa chọn hình thức thanh toán!")
	@NotNull
	private String payment;
	@NotNull
	private String status;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull
	@CreationTimestamp
	private Date create_at;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date update_at;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
	private Users user;

	@OneToOne
	@JoinColumn(name = "tax_id", nullable = false, referencedColumnName = "id")
	private Tax tax;

	@OneToMany(mappedBy = "bill", cascade = CascadeType.ALL)
	private List<BillInfo> billInfo;
	

	public Bill() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getReceiver_name() {
		return receiver_name;
	}

	public void setReceiver_name(String receiver_name) {
		this.receiver_name = receiver_name;
	}

	public String getReceiver_email() {
		return receiver_email;
	}

	public void setReceiver_email(String receiver_email) {
		this.receiver_email = receiver_email;
	}

	public String getReceiver_phone() {
		return receiver_phone;
	}

	public void setReceiver_phone(String receiver_phone) {
		this.receiver_phone = receiver_phone;
	}

	public String getReceiver_address() {
		return receiver_address;
	}

	public void setReceiver_address(String receiver_address) {
		this.receiver_address = receiver_address;
	}

	public String getReceiver_ward() {
		return receiver_ward;
	}

	public void setReceiver_ward(String receiver_ward) {
		this.receiver_ward = receiver_ward;
	}

	public String getReceiver_district() {
		return receiver_district;
	}

	public void setReceiver_district(String receiver_district) {
		this.receiver_district = receiver_district;
	}

	public String getReceiver_province() {
		return receiver_province;
	}

	public void setReceiver_province(String receiver_province) {
		this.receiver_province = receiver_province;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Tax getTax() {
		return tax;
	}

	public void setTax(Tax tax) {
		this.tax = tax;
	}

	public List<BillInfo> getBillInfo() {
		return billInfo;
	}

	public void setBillInfo(List<BillInfo> billInfo) {
		this.billInfo = billInfo;
	}
	
}
