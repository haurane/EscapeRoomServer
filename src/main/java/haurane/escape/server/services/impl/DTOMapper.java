package haurane.escape.server.services.impl;

import haurane.escape.server.dto.ItemDTO;
import haurane.escape.server.dto.RoomDTO;
import haurane.escape.server.dto.StaticObjectDTO;
import haurane.escape.server.dto.StoryDTO;
import haurane.escape.server.models.Item;
import haurane.escape.server.models.Room;
import haurane.escape.server.models.StaticObject;
import haurane.escape.server.models.Story;

public class DTOMapper {
    static StaticObjectDTO StaticObjectToDTO(StaticObject object) {
        StaticObjectDTO dto = StaticObjectDTO.builder()
                .name(object.getName())
                .description(object.getDescription())
                .isLocked(object.isLocked())
                .combination(object.getCombination())
                .requiredItems(object.getRequiredItems().stream().map(DTOMapper::ItemToDTO).toList())
                .heldItems(object.getHeldItems().stream().map(DTOMapper::ItemToDTO).toList())
                .build();
        return dto;
    }

    static StoryDTO StoryToDTO(Story story) {
        StoryDTO dto = StoryDTO.builder()
                .uuid(story.getUuid())
                .title(story.getTitle())
                .description(story.getDescription())
                .intro(story.getIntro())
                .startingRoomId(story.getStartingRoom() != null ? story.getStartingRoom().getUuid() : "")
                .build();
        return dto;
    }

    static RoomDTO RoomToDTO(Room room) {
        RoomDTO dto = RoomDTO.builder()
                .uuid(room.getUuid())
                .name(room.getName())
                .description(room.getDescription())
                .containedObjects(room.getContainedObjects().stream().map(DTOMapper::StaticObjectToDTO).toList())
                .build();
        return dto;
    }

    static ItemDTO ItemToDTO(Item item) {
        ItemDTO dto = ItemDTO.builder()
                .uuid(item.getUuid())
                .name(item.getName())
                .description(item.getDescription())
                .build();
        return dto;
    }
}
