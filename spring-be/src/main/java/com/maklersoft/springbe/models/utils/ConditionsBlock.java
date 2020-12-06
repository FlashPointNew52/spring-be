package com.maklersoft.springbe.models.utils;

import lombok.Data;

import java.util.Objects;

@Data
public class ConditionsBlock {
    private boolean complete;
    private boolean livingRoomFurniture;
    private boolean kitchenFurniture;
    private boolean couchette;
    private boolean bedding;
    private boolean dishes;
    private boolean refrigerator;
    private boolean washer;
    private boolean microwaveOven;
    private boolean airConditioning;
    private boolean dishwasher;
    private boolean tv;
    private boolean withAnimals;
    private boolean withChildren;
}
