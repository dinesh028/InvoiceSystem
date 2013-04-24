package com.invoice.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "INVOICE_DETAIL")
public class InvoiceDetail {

		@Id
		@Column(name = "DETAIL_ID")
		@SequenceGenerator(name = "DETAIL_GEN", sequenceName = "DETAIL_SEQ")
		@GeneratedValue(generator = "DETAIL_GEN")
		private int detailId;
		
		
		
		@ManyToOne(fetch = FetchType.EAGER)
		@JoinColumn(name = "INVOICE_ID", referencedColumnName="INVOICE_ID")		
		private Invoice invoice;
		
		
		@Column(name = "QUANTITY", nullable = false)
		private int quantity;

		
		@Column(name = "AMOUNT", nullable = false)
		private int amount;
		
		
		@Column(name = "PRICE", nullable = false)
		private int price;
		

		@Column(name = "ITEM", nullable = false)
		private String item;






		public int getDetailId() {
			return detailId;
		}


		public void setDetailId(int detailId) {
			this.detailId = detailId;
		}


		public Invoice getInvoice() {
			return invoice;
		}


		public void setInvoice(Invoice invoice) {
			this.invoice = invoice;
		}


		public int getQuantity() {
			return quantity;
		}


		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}


		public int getAmount() {
			return amount;
		}


		public void setAmount(int amount) {
			this.amount = amount;
		}


		public int getPrice() {
			return price;
		}


		public void setPrice(int price) {
			this.price = price;
		}


		public String getItem() {
			return item;
		}


		public void setItem(String item) {
			this.item = item;
		}


		
	
}



