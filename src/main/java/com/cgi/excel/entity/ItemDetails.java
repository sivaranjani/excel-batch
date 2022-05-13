/**
 * 
 */
package com.cgi.excel.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Ranjani
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "tbl_item_details")
public class ItemDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="detail_id", unique= true,nullable=false)
	private Long detailId;

	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name="item_id")
	private Item item;
	
	@Column(name="price")
	private double price;
	@Column(name="price_dt")
	private Date priceDt;
	
	/**
	 * @return the item
	 */
	public Item getItem() {
		return item;
	}
	/**
	 * @return the detailId
	 */
	public Long getDetailId() {
		return detailId;
	}
	/**
	 * @param detailId the detailId to set
	 */
	public void setDetailId(Long detailId) {
		this.detailId = detailId;
	}
	/**
	 * @param item the item to set
	 */
	public void setItem(Item item) {
		this.item = item;
	}
	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	/**
	 * @return the priceDt
	 */
	public Date getPriceDt() {
		return priceDt;
	}
	/**
	 * @param priceDt the priceDt to set
	 */
	public void setPriceDt(Date priceDt) {
		this.priceDt = priceDt;
	}
}
