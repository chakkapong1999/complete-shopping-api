package com.service.api.dao;

import com.service.api.domain.Inventory;
import com.service.api.model.request.InventoryRequest;

/**
 * @author Chakkapong
 */
public interface InventoryDao {
    Inventory findById(Integer id) throws Exception;
    void update(Inventory updateObject) throws Exception;
    void insert(Inventory insertObject) throws Exception;
    void delete (Integer id) throws Exception;
}
