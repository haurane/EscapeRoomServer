package haurane.escape.server.services;

import haurane.escape.server.dto.ItemDTO;
import haurane.escape.server.dto.StaticObjectDTO;
import haurane.escape.server.dto.UnlockDTO;
import haurane.escape.server.services.impl.UnlockFailException;

import java.util.*;


public interface StaticObjectService {
    StaticObjectDTO getByUUID(String uuid);

    List<StaticObjectDTO> getByContainingRoom(String roomId);

    List<ItemDTO> unlockStaticObject(String id, UnlockDTO unlockDTO) throws UnlockFailException;
}
