package haurane.escape.server.controllers;

import haurane.escape.server.dto.StaticObjectDTO;
import haurane.escape.server.repositories.StaticObjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/staticobjects")
public class StaticObjectsController {

    Logger logger = LoggerFactory.getLogger(StaticObjectsController.class);
    private final StaticObjectRepository staticObjectRepository;

    public StaticObjectsController(StaticObjectRepository repo) {
        this.staticObjectRepository = repo;
    }

    @GetMapping("/{roomId}/contained")
    public List<StaticObjectDTO> getByRoomContaining(@PathVariable("roomId") String roomId) {
        List<StaticObjectDTO> l = staticObjectRepository.findByContainingRoom(roomId);
        logger.error(l.toString());
        return staticObjectRepository.findByContainingRoom(roomId);
    }

    @GetMapping("/{id}")
    public StaticObjectDTO getByUUID(@PathVariable("id") String id) {
        return staticObjectRepository.findByUuid(id);
    }

}
