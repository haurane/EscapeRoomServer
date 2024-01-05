package haurane.escape.server.services.impl;

import haurane.escape.server.dto.ItemDTO;
import haurane.escape.server.repositories.ItemRepository;
import haurane.escape.server.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    private ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public ItemDTO getByUUID(String uuid) {
        return DTOMapper.ItemToDTO(itemRepository.findByuuid(uuid));
    }

    @Override
    public List<ItemDTO> getByHoldingStaticObject(String staticObjectId) {
        return itemRepository.findByHoldingStaticObject(staticObjectId).stream().map(DTOMapper::ItemToDTO).toList();
    }

    @Override
    public List<ItemDTO> getByRequiringStaticObject(String staticObjectId) {
        return itemRepository.findByRequiringStaticObject(staticObjectId).stream().map(DTOMapper::ItemToDTO).toList();
    }
}
