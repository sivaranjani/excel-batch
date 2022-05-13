/**
 * 
 */
package com.cgi.excel.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author vranj
 *
 */
public class ItemVo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ItemVo()
	{

	}

	public ItemVo(long itemId,String itemName,double itemPrice,Date itemPriceDate) {
		this.itemId =itemId;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.itemPriceDate = itemPriceDate;

	}

	public ItemVo(long itemId,String itemName,double itemPrice,Date itemPriceDate,String itemPriceDateStr) {
		this.itemId =itemId;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.itemPriceDate = itemPriceDate;
		this.itemPriceDateStr = itemPriceDateStr;
	}

	private long itemId;
	private String itemName;
	private double itemPrice;
	private Date itemPriceDate;
	private String itemPriceDateStr;
	/**
	 * @return the itemId
	 */
	public long getItemId() {
		return itemId;
	}

	/**
	 * @param itemId the itemId to set
	 */
	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	/**
	 * @return the itemName
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * @param itemName the itemName to set
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * @return the itemPrice
	 */
	public double getItemPrice() {
		return itemPrice;
	}

	/**
	 * @param itemPrice the itemPrice to set
	 */
	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}

	/**
	 * @return the itemPriceDate
	 */
	public Date getItemPriceDate() {
		return itemPriceDate;
	}

	/**
	 * @param itemPriceDate the itemPriceDate to set
	 */
	public void setItemPriceDate(Date itemPriceDate) {
		this.itemPriceDate = itemPriceDate;
	}

	/**
	 * @return the itemPriceDateStr
	 */
	public String getItemPriceDateStr() {
		return itemPriceDateStr;
	}

	/**
	 * @param string the itemPriceDateStr to set
	 */
	public void setItemPriceDateStr(String string) {
		this.itemPriceDateStr = string;
	}
}
