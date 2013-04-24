package com.invoice.model;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.codehaus.jackson.annotate.JsonIgnore;

@SuppressWarnings("serial")
@Entity
@Table(name = "ITEM_INFO")
public class Item implements Serializable {

		@Id
		@Column(name = "ITEM_ID", nullable = false)
		private int itemId;

	
		public int getItemId() {
			return itemId;
		}

		public void setItemId(int itemId) {
			this.itemId = itemId;
		}

		@Column(name = "NAME", nullable = false)
		@Size(max = 45)
		private String name;
		
		
		

		@Column(name = "PRICE", nullable = false)
		private int price;
		
		@ManyToOne(fetch = FetchType.EAGER)
		@JoinColumn(name = "TENANT_ID", referencedColumnName="TENANT_ID")
		@JsonIgnore
		private Tenant tenant;
		
		
		
//		@ManyToMany(mappedBy = "items")
//		private Set<Invoice> invoices = new HashSet<Invoice>(0);
		
	

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getPrice() {
			return price;
		}

		public void setPrice(int price) {
			this.price = price;
		}
		
		public Tenant getTenant() {
			return tenant;
		}

		public void setTenant(Tenant tenant) {
			this.tenant = tenant;
		}
		
		
		public short getEnable() {
			return enable;
		}

		public void setEnable(short enable) {
			this.enable = enable;
		}

		@Column(name = "ENABLE", nullable = false)
		private short enable;
		
		
		
	
}



