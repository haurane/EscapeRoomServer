package haurane.escape.server.services;

import haurane.escape.server.dto.RoomDTO;

public interface RoomService {
    public RoomDTO getByUUID(String uuid);
}
