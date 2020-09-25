/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pawnshop.itemmgmt.model;

public class Item {

    private int itemId;
    private String itemType;
    private double weight;
    private int karat;
    private float value;
    private String status;
    private String description;
    private String nic;

    public Item() {
    }

    public Item(int itemId, String itemType, double weight, float value, String status, String description, String nic, int karat) {
        this.itemId = itemId;
        this.itemType = itemType;
        this.weight = weight;
        this.value = value;
        this.status = status;
        this.description = description;
        this.nic = nic;
        this.karat = karat;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public int getKarat() {
        return karat;
    }

    public void setKarat(int karat) {
        this.karat = karat;
    }

}
