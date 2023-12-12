package haurane.escape.server.controllers;

import haurane.escape.server.dto.StaticObjectDTO;
import haurane.escape.server.repositories.StaticObjectRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RequestMapping("/staticobjects")
public class StaticObjectsController {
    private final StaticObjectRepository staticObjectRepository;
    public StaticObjectsController(StaticObjectRepository repo){
        this.staticObjectRepository = repo;
    }

    /*@GetMapping("{roomId}/contained")
    public List<StaticObjectDTO> getByRoomContaining(@PathVariable("RoomId") String roomId){
        return staticObjectRepository.findByContainingRoom(roomId);
    }*/

    @GetMapping("/{id}")
    public StaticObjectDTO getByUUID(@PathVariable("id") String id){
        return staticObjectRepository.findByUuid(id);
    }

}
