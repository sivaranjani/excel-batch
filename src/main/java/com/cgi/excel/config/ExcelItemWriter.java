package com.cgi.excel.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cgi.excel.entity.Item;
import com.cgi.excel.entity.ItemDetails;
import com.cgi.excel.repo.ItemDetailRepository;
import com.cgi.excel.repo.ItemRepository;

@Component
public class ExcelItemWriter implements ItemWriter<Item>{

	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private ItemDetailRepository itemDetailRepository;
	
	@Override
	public void write(List<? extends Item> items) throws Exception {
		Map<String, Map<Item,List<ItemDetails>>> finalMap= new HashMap<>();
		for (Item itemVal : items)
		{
			persitData(finalMap, itemVal);
		}
		//persist logic
		for(Map.Entry<String, Map<Item,List<ItemDetails>>> entry : finalMap.entrySet()) {
			
			for(Map.Entry<Item,List<ItemDetails>> internalEntry : finalMap.get(entry.getKey()).entrySet()) {
				Item item = internalEntry.getKey();
				item.setItemDetailList(null);
				final Item item2 = itemRepository.save(item);
				internalEntry.getValue().forEach(val->
				{
					val.setItem(item2);
				});
				itemDetailRepository.saveAll(internalEntry.getValue());
			}
		}
		
	}
	
	private void persitData(Map<String, Map<Item,List<ItemDetails>>> finalMap,Item itemVal )
	{
		if(itemVal.getItemName() == null)
		{
			return;
		}
		Map<Item,List<ItemDetails>> internalMap = new HashMap<>();
		if(finalMap.containsKey(itemVal.getItemName()))
		{
			for(Map.Entry<Item,List<ItemDetails>> entry : finalMap.get(itemVal.getItemName()).entrySet()) {
				List<ItemDetails> existingList = new ArrayList<>(entry.getValue());
				existingList.addAll(itemVal.getItemDetailList());
				entry.setValue(existingList);
			}
		}
		else if(null != itemVal)
		{
			internalMap.put(itemVal,itemVal.getItemDetailList());
			finalMap.put(itemVal.getItemName(),internalMap);
		}
	}

}
