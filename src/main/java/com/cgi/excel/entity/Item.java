/**
 * 
 */
package com.cgi.excel.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author vranj
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "tbl_item")
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", unique= true,nullable=false)
	private Long id;
	
	@Column(name="item_name")
	private String itemName;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="item",cascade = CascadeType.ALL)
	List<ItemDetails> itemDetailList = new ArrayList<>();


	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
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
	 * @return the itemDetailList
	 */
	public List<ItemDetails> getItemDetailList() {
		return itemDetailList;
	}

	/**
	 * @param itemDetailList the itemDetailList to set
	 */
	public void setItemDetailList(List<ItemDetails> itemDetailList) {
		this.itemDetailList = itemDetailList;
	}

	
}
