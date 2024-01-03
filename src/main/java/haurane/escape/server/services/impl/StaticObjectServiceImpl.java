package haurane.escape.server.services.impl;

import haurane.escape.server.dto.ItemDTO;
import haurane.escape.server.dto.StaticObjectDTO;
import haurane.escape.server.dto.UnlockDTO;
import haurane.escape.server.models.Item;
import haurane.escape.server.models.StaticObject;
import haurane.escape.server.repositories.ItemRepository;
import haurane.escape.server.repositories.StaticObjectRepository;
import haurane.escape.server.services.StaticObjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class StaticObjectServiceImpl implements StaticObjectService {
    private final StaticObjectRepository staticObjectRepository;
    private final ItemRepository itemRepository;

    Logger logger = LoggerFactory.getLogger(haurane.escape.server.services.StaticObjectService.class);

    @Autowired
    public StaticObjectServiceImpl(StaticObjectRepository staticObjectRepository, ItemRepository itemRepository) {
        this.staticObjectRepository = staticObjectRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    public List<ItemDTO> unlockStaticObject(String uuid, UnlockDTO unlockDTO) {
        if (!Objects.equals(uuid, unlockDTO.getUuid())) {
            throw new IllegalArgumentException("IDs do not match");
        }
        StaticObject staticObject = staticObjectRepository.findByuuid(uuid);
        String[] combinations = staticObject.getCombination();
        List<String> requiredItems = staticObject.getHeldItems()
                .stream().map(Item::getUuid).toList();
        List<String> heldItems = staticObject.getHeldItems()
                .stream().map(Item::getUuid).toList();

        for (String comb : combinations) {
            if (!Arrays.asList(unlockDTO.getCombination()).contains(comb)) {
                throw new IllegalArgumentException("Combination not found " + comb);
            }
        }

        for (String itemId : requiredItems) {
            if (!Arrays.stream(unlockDTO.getItems()).map(ItemDTO::getUuid).toList().contains(itemId)) {
                throw new IllegalArgumentException("Item not found " + itemId);
            }
        }
        logger.debug("Yeah");
        List<ItemDTO> returnItems = new ArrayList<>();
        for (String itemId : heldItems) {
            // TODO: Map items to itemDTO
            // returnItems.add(itemRepository.findByUUID(itemId));
        }
        return returnItems;
    }

    @Override
    public StaticObjectDTO getByUUID(String id) {
        return DTOMapper.StaticObjectToDTO(staticObjectRepository.findByuuid(id));
    }

    @Override
    public List<StaticObjectDTO> findByContainingRoom(String roomId) {
        return staticObjectRepository.findByContainingRoom(roomId).stream()
                .map(DTOMapper::StaticObjectToDTO).toList();

    }


}
