package haurane.escape.server.services.impl;

import haurane.escape.server.dto.RoomDTO;
import haurane.escape.server.repositories.RoomRepository;
import haurane.escape.server.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public RoomDTO getRoomByUUID(String uuid) {
        return DTOMapper.RoomToDTO(roomRepository.findByuuid(uuid));
    }


}