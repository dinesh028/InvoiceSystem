package com.invoice.model;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name = "CLIENT_INFO")
public class Client {

	
	
	@Id
	@Column(name = "CLIENT_ID")
	private int clientId;

	public int getClientId() {
		return clientId;
	}


	public void setClientId(int clientId) {
		this.clientId = clientId;
	}


	@Column(name = "EMAIL")
	private String emailId;

	@Column(name = "INVOICE_EMAIL")
	private String invoiceEmailId;

	
	public String getInvoiceEmailId() {
		return invoiceEmailId;
	}

	@Column(name = "ENABLE", nullable = false)
	private short enable;

	public short getEnable() {
		return enable;
	}


	public void setEnable(short enable) {
		this.enable = enable;
	}


	public void setInvoiceEmailId(String invoiceEmailId) {
		this.invoiceEmailId = invoiceEmailId;
	}


	@Column(name = "COMPANY_NAME", length = 100)
	private String companyName;

	
	@Column(name = "NAME", nullable = false)
	@Size(min=1,max = 45, message = "Name should not be empty")
	private String name;

	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "TENANT_ID", referencedColumnName="TENANT_ID")
	@JsonIgnore
	private Tenant tenant;


	@OneToMany(fetch = FetchType.EAGER,  cascade = CascadeType.ALL)//LAZY
	@JoinColumn(name = "CLIENT_ID", referencedColumnName="CLIENT_ID")
	private List<Invoice> invoice;
	
	
	
	
	public List<Invoice> getInvoice() {
		return invoice;
	}


	public void setInvoice(List<Invoice> invoice) {
		this.invoice = invoice;
	}


	public String getEmailId() {
		return emailId;
	}


	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}


	public Tenant getTenant() {
		return tenant;
	}


	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}





	public String getCompanyName() {
		return companyName;
	}


	public void setcompanyName(String companyName) {
		this.companyName = companyName;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	
}



