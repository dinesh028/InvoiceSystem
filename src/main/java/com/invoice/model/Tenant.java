package com.invoice.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name = "TENANT_INFO")
public class Tenant implements Serializable {


	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "TENANT_ID", referencedColumnName="TENANT_ID")
	private Set<User> users;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "TENANT_ID", referencedColumnName="TENANT_ID")
	private Set<Invoice> invoices;
	
	

	public Set<Invoice> getInvoices() {
		return invoices;
	}


	public void setInvoices(Set<Invoice> invoices) {
		this.invoices = invoices;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "TENANT_ID", referencedColumnName="TENANT_ID")
	private Set<Item> items;

	
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "TENANT_ID", referencedColumnName="TENANT_ID")
	@JsonIgnore
	private Set<Client> client;

	
	public Set<Client> getClient() {
		return client;
	}


	public void setClient(Set<Client> client) {
		this.client = client;
	}

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@Column(name = "TENANT_ID")
	private String tenantId;

	
	

	@Column(name = "COMPANY_NAME", nullable = false)
	private String companyName;
	
	@Column(name = "CONTACT_NUMBER", nullable = false)
	private long contactNumber;
	
	@Column(name = "CITY", nullable = false)
	private String city;
	
	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}



	@Column(name = "STATE", nullable = false)
	private String state;
	
	@Column(name = "COUNTRY", nullable = false)
	private String country;
	
	
	@Column(name = "ADDRESS", nullable = false)
	private String address;
	

	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}



	@Column(name = "DATE_FORMAT", nullable = false)
	private String dateFormat;
	
	public String getCompanyName() {
		return companyName;
	}


	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}


	public long getContactNumber() {
		return contactNumber;
	}


	public void setContactNumber(long contactNumber) {
		this.contactNumber = contactNumber;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}



	@Column(name = "CURRENCY_FORMAT", nullable = false)
	private String currencyFormat;
	
	
	
	
	public String getCurrencyFormat() {
		return currencyFormat;
	}


	public void setCurrencyFormat(String currencyFormat) {
		this.currencyFormat = currencyFormat;
	}


	public String getDateFormat() {
		return dateFormat;
	}


	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}



	@Column(name = "EMAIL", nullable = false)
	private String emailId;
	
	
	@Column(name = "REMINDER", nullable = false)
	private short reminder;
	
	@Column(name = "THANK_YOU", nullable = false)
	private short thankyou;
	
	


	public short getReminder() {
		return reminder;
	}


	public void setReminder(short reminder) {
		this.reminder = reminder;
	}


	public short getThankyou() {
		return thankyou;
	}


	public void setThankyou(short thankyou) {
		this.thankyou = thankyou;
	}



	@Column(name = "PASSWORD", nullable = false)
	private String password;
	
	@Column(name = "ROLE", nullable = false)
	private String role;

	
	@Column(name = "ENABLE", nullable = false)
	private int enable;
	
	
	
	
	
	
	
	
	
	


	public String getTenantId() {
		return tenantId;
	}


	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}


	public String getEmailId() {
		return emailId;
	}


	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public int getEnable() {
		return enable;
	}


	public void setEnable(int enable) {
		this.enable = enable;
	}


	public Set<Item> getItems() {
		return items;
	}


	
	public void setItems(Set<Item> items) {
		this.items = items;
	}



	public Set<User> getUsers() {
		return users;
	}
	


	public void setUsers(Set<User> users) {
		this.users = users;
	}

	


	
}
