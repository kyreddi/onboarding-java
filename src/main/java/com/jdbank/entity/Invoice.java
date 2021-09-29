package com.jdbank.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "invoice")
public class Invoice {

	@Id
	private String id;
	
	@Pattern(regexp="[A-Za-z ]+")
	@NotBlank(message = "Name is mandatory")
	@Column(name="name")	
	private String name;
	

	@Column(name="age")
	private int age;
	
	@Email(message = "Enter valid email id")
	@NotBlank(message = "EmailId is mandatory")
	@Column(name="email_id")
	private String emailId;
	
	@Size(min=10, max=10)
	@NotBlank(message = "Phone number is mandatory")
	@Column(name="phone_number")
	private String phNo;
	
	
	@NotBlank(message = "address is mandatory")
	@Column(name="address")
	private String address;
	
	@Size(min=10, max=10)
	@Pattern(regexp="[A-Z]{5}[0-9]{4}[A-Z]{1}")
	@NotBlank(message = "Pan details is mandatory")
	@Column(name="pan_number")
	private String panNumber;

	
	
	@Pattern(regexp="^\\d{4}\\s\\d{4}\\s\\d{4}$")
	@NotBlank(message = "Aadhar number is mandatory")
	@Column(name="aadhar_number")
	private String aadharNumber;
	
	@NotBlank(message = "Certification Number is mandatory")
	@Column(name="certificate_no")
	private String certNo;
	
	public Invoice() {
		
	}
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPhNo() {
		return phNo;
	}

	public void setPhNo(String phNo) {
		this.phNo = phNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPanNumber() {
		return panNumber;
	}

	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}

	public String getAadharNumber() {
		return aadharNumber;
	}

	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}

	public String getCertNo() {
		return certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	public Invoice(String id, String name, int age, String emailId, String phNo, String address, String panNumber,
			String aadharNumber, String certNo) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.emailId = emailId;
		this.phNo = phNo;
		this.address = address;
		this.panNumber = panNumber;
		this.aadharNumber = aadharNumber;
		this.certNo = certNo;
	}

	@Override
	public String toString() {
		return "Invoice [id=" + id + ", name=" + name + ", age=" + age + ", emailId=" + emailId + ", phNo=" + phNo
				+ ", address=" + address + ", panNumber=" + panNumber + ", aadharNumber=" + aadharNumber + ", certNo="
				+ certNo + "]";
	}
	

	
	
}
