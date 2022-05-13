/**
 * 
 */
package com.cgi.excel.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cgi.excel.entity.ItemDetails;


/**
 * @author vranj
 *
 */
@Repository
public interface ItemDetailRepository extends JpaRepository<ItemDetails,Long>{
	
}
