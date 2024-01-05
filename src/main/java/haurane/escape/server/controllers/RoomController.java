package haurane.escape.server.controllers;

import haurane.escape.server.dto.RoomDTO;
import haurane.escape.server.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/rooms")
public class RoomController {
    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService service) {
        this.roomService = service;
    }

    @GetMapping("/{id}")
    public RoomDTO getRoomByUUID(@PathVariable("id") String id) {
        return roomService.getByUUID(id);
    }
    /*
    @GetMapping("{id}/staticObjects")
    public List<StaticObjectDTO> getRoomObjects(@PathVariable("id") String id){
        return roomRepository.getContainedObjects(id);
    }
    */
}
