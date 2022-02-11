package com.service.api.service;

import com.service.api.model.request.InventoryRequest;
import com.service.api.model.response.InventoryResponse;

/**
 * @author Chakkapong
 */
public interface InventoryService {
    InventoryResponse updateInventory(InventoryRequest request) throws Exception;
}
