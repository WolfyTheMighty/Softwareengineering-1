package system.impl;

import datamodel.Order;
import system.InventoryManager;

public class InventoryManagaerMOCK implements InventoryManager {
    @Override
    public boolean isFillable(Order order) {
        return true;
    }
}
