/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pawnshop.itemmgmt.controller;

import com.pawnshop.itemmgmt.model.Item;
import java.util.ArrayList;

public interface IItemDAO {
    void saveItem(Item item);
    Item viewItem(int itemId);
    void updateItem(Item item);
    void deleteItem(int itemId);
    ArrayList<Item> getAllItems();
}
