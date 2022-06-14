package com.granduation.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@Table(name = "ward")
public class Ward {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String _name;
	private String _prefix;

	@ManyToOne
	@JoinColumn(name = "_district_id", referencedColumnName = "id")
	private District district;

	public Ward() {
		super();
	}

	public Ward(String _name, String _prefix, District district) {
		super();
		this._name = _name;
		this._prefix = _prefix;
		this.district = district;
	}

	public String get_name() {
		return _name;
	}

	public void set_name(String _name) {
		this._name = _name;
	}

	public String get_prefix() {
		return _prefix;
	}

	public void set_prefix(String _prefix) {
		this._prefix = _prefix;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
