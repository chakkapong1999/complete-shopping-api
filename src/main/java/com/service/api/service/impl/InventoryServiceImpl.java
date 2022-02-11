package com.service.api.service.impl;

import com.service.api.dao.InventoryDao;
import com.service.api.domain.Inventory;
import com.service.api.model.request.InventoryRequest;
import com.service.api.model.response.InventoryResponse;
import com.service.api.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Chakkapong
 */
@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryDao inventoryDao;

    @Override
    public InventoryResponse updateInventory(InventoryRequest request) throws Exception {
        InventoryResponse response = new InventoryResponse();
        Inventory updateInventory = new Inventory();
        Date currentDate = new Date();
        try {
            Inventory inventory = inventoryDao.findById(request.getId());
            Integer inStock = inventory.getQuantity();
            Integer toAdd = inStock + request.getQuantity();

            updateInventory.setUpdateDate(currentDate);
            updateInventory.setUpdateBy("admin");
            updateInventory.setProductId(request.getId());
            updateInventory.setQuantity(toAdd);

            inventoryDao.update(updateInventory);

            response.setSuccess(true);
            response.setMessage("Update inventory success.");
            response.setProductId(request.getId());
        } catch (Exception e) {
            response.setProductId(request.getId());
            response.setSuccess(false);
            response.setMessage("Error while update inventory.");
        }
        return response;
    }
}
