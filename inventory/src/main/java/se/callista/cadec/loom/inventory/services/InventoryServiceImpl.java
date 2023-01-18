package se.callista.cadec.loom.inventory.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import se.callista.cadec.loom.inventory.model.InventoryValue;
import se.callista.cadec.loom.inventory.model.InventoryValueMapper;
import se.callista.cadec.loom.inventory.multitenancy.repository.InventoryRepository;

@Component
public class InventoryServiceImpl implements InventoryService {

  private final InventoryRepository inventoryRepository;

  @Autowired
  public InventoryServiceImpl(InventoryRepository inventoryRepository) {
    this.inventoryRepository = inventoryRepository;
  }

  @Override
  @Transactional(readOnly = true)
  public InventoryValue getInventory(String sku) {
    return inventoryRepository.findBySku(sku)
        .map(InventoryValueMapper::fromEntity)
        .orElseThrow(() -> new EntityNotFoundException("Inventory with sku " + sku + " not found"));
  }

}
