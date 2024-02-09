package haurane.escape.server.services;

import haurane.escape.server.dto.*;
import haurane.escape.server.models.Item;
import haurane.escape.server.models.Room;
import haurane.escape.server.models.StaticObject;
import haurane.escape.server.models.Story;
import haurane.escape.server.services.impl.DTOMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

@SpringBootTest
public class DTOMapperTest {

    @Test
    public void DTOMapperTest_StoryToDTO_ReturnDTO() {
        Room startingRoom = Room.builder().build();
        Story story = Story.builder()
                .uuid("AAA")
                .title("Test Story Title")
                .intro("Test Story Intro")
                .description("Test Story Description")
                .startingRoom(startingRoom)
                .build();

        StoryDTO dto = DTOMapper.StoryToDTO(story);

        Assertions.assertEquals(story.getUuid(), dto.getUuid());
        Assertions.assertEquals(story.getTitle(), dto.getTitle());
        Assertions.assertEquals(story.getIntro(), dto.getIntro());
        Assertions.assertEquals(story.getStartingRoom().getUuid(), dto.getStartingRoomId());
    }

    @Test
    public void DTOMapper_ItemToDTO_ReturnDTO() {
        Item item = Item.builder()
                .uuid("AAA")
                .name("Test Item")
                .description("Test Item Description")
                .build();

        ItemDTO dto = DTOMapper.ItemToDTO(item);

        Assertions.assertEquals(item.getUuid(), dto.getUuid());
        Assertions.assertEquals(item.getName(), dto.getName());
        Assertions.assertEquals(item.getDescription(), dto.getDescription());
    }

    @Test
    public void DTOMapper_StaticObjectToDTO_ReturnDTO() {
        StaticObject staticObject = StaticObject.builder()
                .uuid("AAA")
                .name("Test Stat Obj")
                .description("Test Stat Obj Description")
                .combination(new String[]{"123"})
                .isLocked(false)
                .requiredItems(Collections.singleton(Item.builder().name("Test Stat Obj required Item").build()))
                .heldItems(Collections.singleton(Item.builder().name("Test Stat Obj held Item").build()))
                .build();

        StaticObjectDTO dto = DTOMapper.StaticObjectToDTO(staticObject);

        Assertions.assertEquals(staticObject.getUuid(), dto.getUuid());
        Assertions.assertEquals(staticObject.getName(), dto.getName());
        Assertions.assertEquals(staticObject.getDescription(), dto.getDescription());
        Assertions.assertEquals(staticObject.isLocked(), dto.isLocked());

    }

    @Test
    public void DTOMapper_StaticObjectToFullDTO_ReturnDTO() {
        StaticObject staticObject = StaticObject.builder()
                .uuid("AAA")
                .name("Test Stat Obj")
                .description("Test Stat Obj Description")
                .combination(new String[]{"123"})
                .isLocked(false)
                .requiredItems(Collections.singleton(Item.builder().name("Test Stat Obj required Item").build()))
                .heldItems(Collections.singleton(Item.builder().name("Test Stat Obj held Item").build()))
                .build();

        FullStaticObjectDTO dto = DTOMapper.StaticObjectToFullDTO(staticObject);

        Assertions.assertEquals(staticObject.getUuid(), dto.getUuid());
        Assertions.assertEquals(staticObject.getName(), dto.getName());
        Assertions.assertEquals(staticObject.getDescription(), dto.getDescription());
        Assertions.assertEquals(staticObject.isLocked(), dto.isLocked());
        Assertions.assertNotEquals(0, dto.getHeldItems().size());
        Assertions.assertEquals(staticObject.getHeldItems().stream().toList().get(0).getUuid(), dto.getHeldItems().get(0).getUuid());
        Assertions.assertEquals(staticObject.getHeldItems().stream().toList().get(0).getName(), dto.getHeldItems().get(0).getName());

    }

    @Test
    public void DTOMapper_RoomToDTO_ReturnDTO() {
        Room room = Room.builder()
                .uuid("AAA")
                .name("Test Room")
                .description("Test Room Description")
                .containedObjects(Collections.singleton(StaticObject.builder().name("Test Room Contained Object").build()))
                .build();

        RoomDTO dto = DTOMapper.RoomToDTO(room);

        Assertions.assertEquals(room.getUuid(), dto.getUuid());
        Assertions.assertEquals(room.getName(), dto.getName());
        Assertions.assertEquals(room.getDescription(), dto.getDescription());
        Assertions.assertNotEquals(0, dto.getContainedObjects().size());
        Assertions.assertEquals(room.getContainedObjects().stream().toList().get(0).getUuid(), dto.getContainedObjects().get(0).getUuid());
        Assertions.assertEquals(room.getContainedObjects().stream().toList().get(0).getName(), dto.getContainedObjects().get(0).getName());
    }
}
