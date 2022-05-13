package com.cgi.excel.config;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.batch.extensions.excel.RowMapper;
import org.springframework.batch.extensions.excel.support.rowset.RowSet;

import com.cgi.excel.vo.ItemVo;

public class ItemCustomRowMapper implements RowMapper<ItemVo>{
	SimpleDateFormat dateFormat = new SimpleDateFormat("d/M/yy");
	@Override
	public ItemVo mapRow(RowSet rs) throws Exception {
		
		ItemVo vo = new ItemVo();
		String[] row = rs.getCurrentRow();
		String itemName = row[1];
		String price = row[3];
		String priceDate = row[2];
		if(validateRow(itemName, price, priceDate))
		{
			vo.setItemName(itemName);
			vo.setItemPrice(Double.valueOf(price));
			Date priceDt = dateFormat.parse(priceDate);
			vo.setItemPriceDate(priceDt);
		}
		
		return vo;
	}
	
	private boolean validateRow(String itemName, String price, String priceDate)
	{
		if(null != itemName && null != price && null != priceDate &&
				!itemName.equalsIgnoreCase("NULL") && !price.equalsIgnoreCase("NULL") && !priceDate.equalsIgnoreCase("NULL"))
		{
			try{
				Double.valueOf(price);
				new SimpleDateFormat("d/M/yy").parse(priceDate);
			}
			catch(Exception e)
			{
				return false;
			}
			return true;
		}
		return false;
	}

}
