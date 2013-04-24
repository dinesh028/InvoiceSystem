package com.invoice.model;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name = "USER_INFO")
@XmlTransient
public class User {

		
		@Column(name = "EMAIL")
		private String emailId;

		public String getEmailId() {
			return emailId;
		}


		public void setEmailId(String emailId) {
			this.emailId = emailId;
		}


		public List<Invoice> getInvoice() {
			return invoice;
		}


		public void setInvoice(List<Invoice> invoice) {
			this.invoice = invoice;
		}

		@Column(name = "ENABLE", nullable = false)
		private short enable;
		
		

		@Id
		@Column(name = "USER_ID")
		private String userId;
				
		
		public String getUserId() {
			return userId;
		}


		public void setUserId(String userId) {
			this.userId = userId;
		}

		@ManyToOne(fetch = FetchType.EAGER)
		@JoinColumn(name = "TENANT_ID", referencedColumnName="TENANT_ID")
		@JsonIgnore
		private Tenant tenant;
		
		@OneToMany(fetch = FetchType.EAGER)
		@JoinColumn(name = "USER_ID", referencedColumnName="USER_ID")
		private List<Invoice> invoice;
			

		


		/*public List<Invoice> getInvoice() {
			return invoice;
		}


		public void setInvoice(List<Invoice> invoice) {
			this.invoice = invoice;
		}*/


		@Column(name = "PASSWORD", updatable = false)
		private String password;

		
		@Column(name = "NAME", nullable = false)
		@Size(min=1,max = 45, message = "Name should not be empty")
		private String name;

		
		@Column(name = "ROLE", nullable = false)
		private String role;

		
				

		public String getPassword() {
			return password;
		}


		public void setPassword(String password) {
			this.password = password;
		}


		public String getName() {
			return name;
		}


		public Tenant getTenant() {
			return tenant;
		}


		public void setTenant(Tenant tenant) {
			this.tenant = tenant;
		}
		
		public void setName(String name) {
			this.name = name;
		}


		public String getRole() {
			return role;
		}


		public void setRole(String role) {
			this.role = role;
		}


		public short getEnable() {
			return enable;
		}


		public void setEnable(short enable) {
			this.enable = enable;
		}


		
	
}



