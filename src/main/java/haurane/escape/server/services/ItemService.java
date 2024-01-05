package haurane.escape.server.services;

import haurane.escape.server.dto.ItemDTO;

import java.util.List;

public interface ItemService {
    public ItemDTO getByUUID(String uuid);

    public List<ItemDTO> getByHoldingStaticObject(String staticObjectId);

    public List<ItemDTO> getByRequiringStaticObject(String staticObjectId);
}
