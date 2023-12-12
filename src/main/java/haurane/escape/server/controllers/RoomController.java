package haurane.escape.server.controllers;

import haurane.escape.server.dto.RoomDTO;
import haurane.escape.server.dto.StaticObjectDTO;
import haurane.escape.server.repositories.RoomRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/rooms")
public class RoomController {
    private final RoomRepository roomRepository;

    public RoomController(RoomRepository repo){
        this.roomRepository = repo;
    }

    @GetMapping("/{id}")
    public RoomDTO getRoomByUUid(@PathVariable("id") String id){
        return roomRepository.findByUuid(id);
    }
    /*
    @GetMapping("{id}/staticObjects")
    public List<StaticObjectDTO> getRoomObjects(@PathVariable("id") String id){
        return roomRepository.getContainedObjects(id);
    }
    */
}
