package com.service.api.service;

import com.service.api.model.request.InventoryRequest;
import com.service.api.model.response.InventoryResponse;

import java.util.List;

/**
 * @author Chakkapong
 */
public interface InventoryService {
    InventoryResponse addInventory(InventoryRequest request) throws Exception;
    InventoryResponse updateInventory(List<InventoryRequest> request) throws Exception;
}
