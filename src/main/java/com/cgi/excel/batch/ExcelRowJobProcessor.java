package com.cgi.excel.batch;

import org.springframework.batch.item.ItemProcessor;
import com.cgi.excel.entity.Item;
import com.cgi.excel.entity.ItemDetails;
import com.cgi.excel.vo.ItemVo;

public class ExcelRowJobProcessor implements ItemProcessor<ItemVo, Item>{

	
	@Override
	public Item process(ItemVo itemVo) throws Exception {
		if(itemVo == null)
		{
			return null;
		}
		Item item = new Item();
		item.setItemName(itemVo.getItemName());
		ItemDetails detail = new ItemDetails();
		detail.setPrice(itemVo.getItemPrice());
		detail.setPriceDt(itemVo.getItemPriceDate());
		detail.setItem(item);
		item.getItemDetailList().add(detail);
		return item;
	}

}
	