package com.service.api.service.impl;

import com.service.api.dao.InventoryDao;
import com.service.api.domain.Inventory;
import com.service.api.domain.vo.InventoryVO;
import com.service.api.model.request.InventoryRequest;
import com.service.api.model.response.InventoryResponse;
import com.service.api.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Chakkapong
 */
@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryDao inventoryDao;

    @Override
    public InventoryResponse addInventory(InventoryRequest request) throws Exception {
        InventoryResponse response = new InventoryResponse();
        Inventory updateInventory = new Inventory();
        Date currentDate = new Date();
        try {
            Inventory inventory = inventoryDao.findById(request.getId());
            Integer inStock = inventory.getQuantity();
            Integer toAdd = inStock + request.getAmount();

            updateInventory.setUpdateDate(currentDate);
            updateInventory.setUpdateBy("admin");
            updateInventory.setProductId(request.getId());
            updateInventory.setQuantity(toAdd);

            inventoryDao.update(updateInventory);

            response.setSuccess(true);
            response.setMessage("Update inventory success.");
            response.setProductId(request.getId());
        } catch (Exception e) {
            throw e;
        }
        return response;
    }

    @Override
    public InventoryResponse updateInventory(List<InventoryRequest> request) throws Exception {
        InventoryResponse response = new InventoryResponse();
        Date currentDate = new Date();
        try {
            for (InventoryRequest i : request) {
                Inventory inventoryDB = inventoryDao.findById(i.getId());
                if(i.getAmount() > inventoryDB.getQuantity()) {
                    response.setMessage("???????????????????????????????????????????????? ?????????????????????????????????");
                    response.setSuccess(false);
                    response.setProductId(i.getId());
                    return response;
                } else {
                    Integer toUpdate = inventoryDB.getQuantity() - i.getAmount();
                    Inventory updateObject = new Inventory();
                    updateObject.setUpdateDate(currentDate);
                    updateObject.setUpdateBy("admin");
                    updateObject.setProductId(i.getId());
                    updateObject.setQuantity(toUpdate);
                    inventoryDao.update(updateObject);

                    response.setProductId(null);
                    response.setSuccess(true);
                    response.setMessage("???????????????????????????????????????????????????");
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return response;
    }

    @Override
    public InventoryVO findById(Integer id) throws Exception {
        InventoryVO response = new InventoryVO();
        try {
            Inventory inventoryDB = inventoryDao.findById(id);
            response.setProductId(inventoryDB.getProductId());
            response.setQuantity(inventoryDB.getQuantity());
        } catch (Exception e) {
            throw e;
        }
        return response;
    }
}
