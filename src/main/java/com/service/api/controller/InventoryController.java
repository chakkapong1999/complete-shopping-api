package com.service.api.controller;

import com.service.api.domain.vo.InventoryVO;
import com.service.api.model.request.InventoryRequest;
import com.service.api.model.response.InventoryResponse;
import com.service.api.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Chakkapong
 */

@CrossOrigin( origins = "*")
@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @PostMapping("/update")
    public Object updateInventory(@RequestBody InventoryRequest request) throws Exception {
        InventoryResponse response = inventoryService.addInventory(request);
        return response;
    }

    @PostMapping("/confirm")
    public Object updateInventory(@RequestBody List<InventoryRequest> request) throws Exception {
        InventoryResponse response = inventoryService.updateInventory(request);
        return response;
    }

    @GetMapping("/{id}")
    public Object findById(@PathVariable Integer id) throws Exception {
        InventoryVO response = inventoryService.findById(id);
        return response;
    }
}
