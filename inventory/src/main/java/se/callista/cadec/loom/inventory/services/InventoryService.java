package se.callista.cadec.loom.inventory.services;


import se.callista.cadec.loom.inventory.model.InventoryValue;

public interface InventoryService {

  InventoryValue getInventory(String sku);

}
