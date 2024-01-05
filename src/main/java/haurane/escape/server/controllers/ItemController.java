package haurane.escape.server.controllers;

import haurane.escape.server.dto.ItemDTO;
import haurane.escape.server.models.Item;
import haurane.escape.server.services.ItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/items")
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/{id}")
    public ItemDTO getById(@PathVariable("id") String id) {
        return itemService.getByUUID(id);
    }

    @GetMapping("/{staticObjectId}/held")
    public List<ItemDTO> getByStaticObjectHolding(@PathVariable("staticObjectId") String staticObjectId) {
        return itemService.getByHoldingStaticObject(staticObjectId);
    }

    @GetMapping("/{staticObjectId}/required")
    public List<ItemDTO> getByStaticObjectRequiring(@PathVariable("staticObjectId") String staticObjectId) {
        return itemService.getByRequiringStaticObject(staticObjectId);
    }
}
