package haurane.escape.server.services.impl;

import haurane.escape.server.dto.*;
import haurane.escape.server.models.*;


public class DTOMapper {
    public static FullStaticObjectDTO StaticObjectToFullDTO(StaticObject object) {
        FullStaticObjectDTO dto = FullStaticObjectDTO.builder()
                .name(object.getName())
                .description(object.getDescription())
                .isLocked(object.isLocked())
                .heldItems(object.getHeldItems().stream().map(DTOMapper::SummarizableToDTO).toList())
                .build();
        return dto;
    }

    public static StaticObjectDTO StaticObjectToDTO(StaticObject object) {
        StaticObjectDTO dto = StaticObjectDTO.builder()
                .name(object.getName())
                .description(object.getDescription())
                .isLocked(object.isLocked())
                .build();
        return dto;
    }

    public static StoryDTO StoryToDTO(Story story) {
        StoryDTO dto = StoryDTO.builder()
                .uuid(story.getUuid())
                .title(story.getTitle())
                .description(story.getDescription())
                .intro(story.getIntro())
                .startingRoomId(story.getStartingRoom() != null ? story.getStartingRoom().getUuid() : "")
                .build();
        return dto;
    }

    public static RoomDTO RoomToDTO(Room room) {
        RoomDTO dto = RoomDTO.builder()
                .uuid(room.getUuid())
                .name(room.getName())
                .description(room.getDescription())
                .containedObjects(room.getContainedObjects().stream().map(DTOMapper::SummarizableToDTO).toList())
                .build();
        return dto;
    }

    public static ItemDTO ItemToDTO(Item item) {
        ItemDTO dto = ItemDTO.builder()
                .uuid(item.getUuid())
                .name(item.getName())
                .description(item.getDescription())
                .build();
        return dto;
    }

    public static Summary SummarizableToDTO(Summarizable s) {
        return Summary.builder()
                .name(s.getName())
                .uuid(s.getUuid())
                .build();
    }
}
