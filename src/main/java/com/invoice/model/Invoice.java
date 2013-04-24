package com.invoice.model;


import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name = "INVOICE")
public class Invoice implements Serializable {

		
		public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

		private static final long serialVersionUID = 1L;

		public Timestamp getDueDate() {
			return dueDate;
		}

		public void setDueDate(Timestamp dueDate) {
			this.dueDate = dueDate;
		}

		public Timestamp getCreatedDate() {
			return createdDate;
		}

		public void setCreatedDate(Timestamp createdDate) {
			this.createdDate = createdDate;
		}

		public Timestamp getPaidDate() {
			return paidDate;
		}

		public void setPaidDate(Timestamp paidDate) {
			this.paidDate = paidDate;
		}

		public Timestamp getSendDate() {
			return sendDate;
		}

		public void setSendDate(Timestamp sendDate) {
			this.sendDate = sendDate;
		}

		@Id
		@Column(name = "INVOICE_ID")
		private int invoiceId;
		
		@Column(name = "INVOICE_PARENT_ID")
		private int invoiceParentId;

		@Column(name = "AMOUNT", nullable = false)
		private int amount;

		@Column(name = "DUE_DATE", nullable = false)
		private Timestamp dueDate;
		
		@Column(name = "CREATED_DT", nullable = false)
		private Timestamp createdDate;
		
		@Column(name = "PAID_DATE", nullable = true)
		private Timestamp paidDate;
		
		@Column(name = "SEND_DATE", nullable = true)
		private Timestamp sendDate;
		


		public int getInvoiceParentId() {
			return invoiceParentId;
		}

		public void setInvoiceParentId(int invoiceParentId) {
			this.invoiceParentId = invoiceParentId;
		}

		@Column(name = "TAX", nullable = false)
		private float tax;
		
		@Column(name = "STATUS", nullable = false)
		private String status;
		
		@Column(name = "RECURRING", nullable = false)
		private short recurring;
		
		@ManyToOne(fetch = FetchType.EAGER)
		@JoinColumn(name = "USER_ID", referencedColumnName="USER_ID")
		@JsonIgnore
		private User user;
		
		
		
		public int getInvoiceId() {
			return invoiceId;
		}

		public void setInvoiceId(int invoiceId) {
			this.invoiceId = invoiceId;
		}

		@ManyToOne(fetch = FetchType.EAGER)
		@JoinColumn(name = "TENANT_ID", referencedColumnName="TENANT_ID")
		@JsonIgnore
		private Tenant tenant;
		
		
		public Tenant getTenant() {
			return tenant;
		}

		public void setTenant(Tenant tenant) {
			this.tenant = tenant;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}

		@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
		@JoinColumn(name = "CLIENT_ID", referencedColumnName="CLIENT_ID")
		@JsonIgnore
		private Client client;


		
		@Column(name = "ENABLE", nullable = false)
		private short enable;
		
		public short getEnable() {
			return enable;
		}

		public void setEnable(short enable) {
			this.enable = enable;
		}

		@Column(name = "CNAME", nullable = false)
		private String cname;
		
		
		public String getCname() {
			return cname;
		}

		public void setCname(String cname) {
			this.cname = cname;
		}

		@OneToMany(fetch = FetchType.EAGER )
		@JoinColumn(name = "INVOICE_ID", referencedColumnName="INVOICE_ID")
		@JsonIgnore
		private Set<InvoiceDetail> invoiceHistory;
		
		
		
		
		
		public Set<InvoiceDetail> getInvoiceHistory() {
			return invoiceHistory;
		}

		public void setInvoiceHistory(Set<InvoiceDetail> invoiceHistory) {
			this.invoiceHistory = invoiceHistory;
		}

		//		
//		public Set<Item> getItems() {
//			return items;
//		}
//		
//		public void setItems(Set<Item> items) {
//			this.items = items;
//		}
//
//		
//		@ManyToMany(targetEntity = com.invoice.model.Item.class, fetch = FetchType.EAGER)
//		@JoinTable(name = "INVOICEITEM", joinColumns = @JoinColumn(name = "INVOICE_ID"), inverseJoinColumns = @JoinColumn(name = "ID"))
//		private Set<Item> items = new HashSet<Item>(0);
//		
		/*public Client getClient() {
			return client;
		}

		public void setClient(Client client) {
			this.client = client;
		}
*/
		

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}


		public float getTax() {
			return tax;
		}

		public void setTax(float tax) {
			this.tax = tax;
		}

		

		public short getRecurring() {
			return recurring;
		}

		public void setRecurring(short recurring) {
			this.recurring = recurring;
		}

		public void setAmount(int amount) {
			this.amount = amount;
		}

		

		public int getAmount() {
			return amount;
		}

	

		

		
		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}	
		
		
		
	
}



