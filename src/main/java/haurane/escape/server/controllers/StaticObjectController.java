package haurane.escape.server.controllers;

import haurane.escape.server.dto.ItemDTO;
import haurane.escape.server.dto.StaticObjectDTO;
import haurane.escape.server.dto.UnlockDTO;
import haurane.escape.server.services.StaticObjectService;
import haurane.escape.server.services.impl.UnlockFailException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
        return staticObjectService.getByContainingRoom(roomId);
    }

    @GetMapping("/{id}")
    public StaticObjectDTO getByUUID(@PathVariable("id") String id) {
        return staticObjectService.getByUUID(id);
    }

    @PostMapping("/{id}/unlock")
    public List<ItemDTO> unlockByUUID(@PathVariable("id") String id, @RequestBody UnlockDTO unlockDTO) {
        try {
            return staticObjectService.unlockStaticObject(id, unlockDTO);
        } catch (UnlockFailException exc) {
            throw new ResponseStatusException(
                    HttpStatus.UNPROCESSABLE_ENTITY, exc.getMessage(), exc);
        }
    }
}
