package haurane.escape.server.controllers;

import haurane.escape.server.dto.ItemDTO;
import haurane.escape.server.dto.StaticObjectDTO;
import haurane.escape.server.dto.UnlockDTO;
import haurane.escape.server.services.StaticObjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/staticobjects")
public class StaticObjectController {

    Logger logger = LoggerFactory.getLogger(StaticObjectController.class);
    private final StaticObjectService staticObjectService;

    public StaticObjectController(StaticObjectService staticObjectService) {
        this.staticObjectService = staticObjectService;
    }

    @GetMapping("/{roomId}/contained")
    public List<StaticObjectDTO> getByRoomContaining(@PathVariable("roomId") String roomId) {
        return staticObjectService.findByContainingRoom(roomId);
    }

    @GetMapping("/{id}")
    public StaticObjectDTO getByUUID(@PathVariable("id") String id) {
        return staticObjectService.getByUUID(id);
    }

    @PostMapping("/{id}/unlock")
    public List<ItemDTO> unlockByUUID(@PathVariable("id") String id, @RequestBody UnlockDTO unlockDTO) {
        return staticObjectService.unlockStaticObject(id, unlockDTO);
    }
}
