/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.service.api.utils;

import org.springframework.stereotype.Component;

/**
 *
 * @author Chakkapong
 */

@Component
public class ObjectValidatorUtils {

    public boolean validateMandatory(String str) {
        return !(null == str || "".equals(str.trim()) || str.trim().length() == 0 || "null".equalsIgnoreCase(str));
    }

}
