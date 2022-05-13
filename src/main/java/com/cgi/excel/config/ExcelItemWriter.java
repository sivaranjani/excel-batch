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
import com.cgi.excel.repo.ItemRepository;

@Component
public class ExcelItemWriter implements ItemWriter<Item>{

	@Autowired
	private ItemRepository itemRepository;

	@Override
	public void write(List<? extends Item> items) throws Exception {
		Map<String, Map<Item,List<ItemDetails>>> finalMap= new HashMap<>();
		System.out.println("items list-->"+items.size());
		for (Item itemVal : items)
		{
			persitData(finalMap, itemVal);
		}
		//persist logic
		System.out.println("finalMap list-->"+finalMap.size());
		for(Map.Entry<String, Map<Item,List<ItemDetails>>> entry : finalMap.entrySet()) {
			
			for(Map.Entry<Item,List<ItemDetails>> internalEntry : finalMap.get(entry.getKey()).entrySet()) {
				Item item = internalEntry.getKey();
				itemRepository.save(item);
			}
		}
		
	}
	
	private void persitData(Map<String, Map<Item,List<ItemDetails>>> finalMap,Item itemVal )
	{
		Map<Item,List<ItemDetails>> internalMap = new HashMap<>();
		if(finalMap.containsKey(itemVal.getItemName()))
		{
			for(Map.Entry<Item,List<ItemDetails>> entry : finalMap.get(itemVal.getItemName()).entrySet()) {
				List<ItemDetails> existingList = new ArrayList<>(entry.getValue());
				existingList.addAll(itemVal.getItemDetailList());
			}
		}
		else if(null != itemVal)
		{
			internalMap.put(itemVal,itemVal.getItemDetailList());
			finalMap.put(itemVal.getItemName(),internalMap);
		}
	}

}
