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


    Logger logger = LoggerFactory.getLogger(haurane.escape.server.services.StaticObjectService.class);

    @Autowired
    public StaticObjectServiceImpl(StaticObjectRepository staticObjectRepository) {
        this.staticObjectRepository = staticObjectRepository;

    }

    // TODO: Error messages should be in an external file to support translation
    @Override
    public List<ItemDTO> unlockStaticObject(String uuid, UnlockDTO unlockDTO) throws UnlockFailException {
        if (!Objects.equals(uuid, unlockDTO.getUuid())) {
            throw new UnlockFailException("IDs do not match");
        }
        StaticObject staticObject = staticObjectRepository.findByuuid(uuid);
        String[] combinations = staticObject.getCombination();
        List<String> requiredItems = staticObject.getRequiredItems()
                .stream().map(Item::getUuid).toList();

        // TODO: Maybe cover in tests

        if (combinations.length != unlockDTO.getCombination().length) {
            throw new UnlockFailException("Combination amount does not match\n"
                    + (combinations.length < unlockDTO.getCombination().length ?
                    "Too many combinations given in Answer" :
                    "Too few combinations given in Answer"));
        }

        // TODO: Maybe cover in tests
        if (requiredItems.size() != unlockDTO.getItems().length) {
            throw new UnlockFailException("Item amount does not match\n"
                    + (requiredItems.size() < unlockDTO.getItems().length ?
                    "Too many items given in Answer" :
                    "Too few items given in Answer"));
        }

        for (String comb : combinations) {
            if (!Arrays.asList(unlockDTO.getCombination()).contains(comb)) {
                throw new UnlockFailException("Combination not found " + comb);
            }
        }

        for (String itemId : requiredItems) {
            if (!Arrays.stream(unlockDTO.getItems()).map(ItemDTO::getUuid).toList().contains(itemId)) {
                throw new UnlockFailException("Item not found " + itemId);
            }
        }
        List<ItemDTO> returnItems = new ArrayList<>();
        for (Item item : staticObject.getHeldItems()) {
            returnItems.add(DTOMapper.ItemToDTO(item));
        }
        return returnItems;
    }

    @Override
    public StaticObjectDTO getByUUID(String id) {
        StaticObject obj = staticObjectRepository.findByuuid(id);
        if (obj.isLocked()) {
            return DTOMapper.StaticObjectToDTO(obj);
        } else {
            return DTOMapper.StaticObjectToFullDTO(staticObjectRepository.findByuuid(id));
        }
    }

    @Override
    public List<StaticObjectDTO> getByContainingRoom(String roomId) {
        List<StaticObject> staticObjectList = staticObjectRepository.findByContainingRoom(roomId);
        List<StaticObjectDTO> dtoList = new ArrayList<>();
        for (StaticObject obj : staticObjectList) {
            if (obj.isLocked()) {
                dtoList.add(DTOMapper.StaticObjectToDTO(obj));
            } else {
                dtoList.add(DTOMapper.StaticObjectToFullDTO(obj));
            }
        }
        return dtoList;

    }


}
