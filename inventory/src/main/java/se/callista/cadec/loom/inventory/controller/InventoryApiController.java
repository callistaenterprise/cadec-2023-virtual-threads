package se.callista.cadec.loom.inventory.controller;

import jakarta.persistence.EntityNotFoundException;
import java.time.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.callista.cadec.loom.inventory.model.InventoryValue;
import se.callista.cadec.loom.inventory.services.InventoryService;

@RestController
@RequestMapping("/")
public class InventoryApiController {

  private final InventoryService inventoryService;
  @Value("${inventory.delay}")
  private Duration delay;

  @Autowired
  public InventoryApiController(InventoryService inventoryService) {
    this.inventoryService = inventoryService;
  }

  @GetMapping(value = "/inventory/{sku}", produces = {"application/json"})
  public ResponseEntity<InventoryValue> getInventory(@PathVariable("sku") String sku) {
    try {
      InventoryValue inventory = inventoryService.getInventory(sku);
      artitraryDelay();
      return new ResponseEntity<>(inventory, HttpStatus.OK);
    } catch (EntityNotFoundException e) {
      throw new NotFoundException(e.getMessage());
    }
  }

  private void artitraryDelay() {
    try {
      Thread.sleep(delay.toMillis());
    } catch (InterruptedException e) {
      // Never mind
    }
  }

}
