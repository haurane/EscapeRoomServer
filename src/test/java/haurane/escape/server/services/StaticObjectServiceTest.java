package haurane.escape.server.services;

import haurane.escape.server.dto.FullStaticObjectDTO;
import haurane.escape.server.dto.ItemDTO;
import haurane.escape.server.dto.StaticObjectDTO;
import haurane.escape.server.dto.UnlockDTO;
import haurane.escape.server.models.Item;
import haurane.escape.server.models.StaticObject;
import haurane.escape.server.repositories.StaticObjectRepository;
import haurane.escape.server.services.impl.StaticObjectServiceImpl;
import haurane.escape.server.services.impl.UnlockFailException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StaticObjectServiceTest {
    @Mock
    private StaticObjectRepository repository;
    @InjectMocks
    private StaticObjectServiceImpl staticObjectService;
    private Logger log = LoggerFactory.getLogger(StaticObjectServiceTest.class);

    @Test
    public void StaticObjectService_getByUUIDLocked_ReturnSimpleDTO() {
        StaticObject staticObject = StaticObject.builder()
                .name("Test Static Object")
                .description("Test Static Object Description")
                .isLocked(true)
                .combination(new String[]{"123"})
                .heldItems(Collections.singleton(Item.builder().name("Test Held Item").build()))
                .requiredItems(Collections.singleton(Item.builder().name("Test Required Item").build()))
                .build();

        when(repository.findByuuid(Mockito.anyString())).thenReturn(staticObject);
        StaticObjectDTO dto = staticObjectService.getByUUID("1");

        Assertions.assertEquals(staticObject.getUuid(), dto.getUuid());
        Assertions.assertEquals(staticObject.getName(), dto.getName());
        Assertions.assertEquals(staticObject.getDescription(), dto.getDescription());
        Assertions.assertEquals(staticObject.isLocked(), dto.isLocked());
    }

    @Test
    public void StaticObjectService_getByUUIDUnlocked_ReturnFullDTO() {
        StaticObject staticObject = StaticObject.builder()
                .name("Test Static Object")
                .description("Test Static Object Description")
                .isLocked(false)
                .combination(new String[]{"123"})
                .heldItems(Collections.singleton(Item.builder().name("Test Held Item").build()))
                .requiredItems(Collections.singleton(Item.builder().name("Test Required Item").build()))
                .build();

        when(repository.findByuuid(Mockito.anyString())).thenReturn(staticObject);
        FullStaticObjectDTO dto = (FullStaticObjectDTO) staticObjectService.getByUUID("1");

        Assertions.assertEquals(FullStaticObjectDTO.class, dto.getClass());
        Assertions.assertEquals(staticObject.getUuid(), dto.getUuid());
        Assertions.assertEquals(staticObject.getName(), dto.getName());
        Assertions.assertEquals(staticObject.getDescription(), dto.getDescription());
        Assertions.assertEquals(staticObject.isLocked(), dto.isLocked());
        Assertions.assertNotNull(dto.getHeldItems());
        Assertions.assertNotEquals(0, dto.getHeldItems().size());
        Assertions.assertEquals(staticObject.getHeldItems().stream().toList().get(0).getUuid(), dto.getHeldItems().get(0).getUuid());
    }

    @Test
    public void StaticObjectService_UnlockStaticObjectWithHeldItems_ReturnNonEmptyListOfItems() {
        Item heldItem = Item.builder().uuid("B").name("Test Held Item").description("Test Held Item Description").build();
        Item requiredItem = Item.builder().uuid("A").name("Test Required Item").build();
        StaticObject staticObject = StaticObject.builder()
                .uuid("C")
                .name("Test Static Object")
                .description("Test Static Object Description")
                .isLocked(false)
                .combination(new String[]{"123"})
                .heldItems(Collections.singleton(heldItem))
                .requiredItems(Collections.singleton(requiredItem))
                .build();


        UnlockDTO unlockDTO = UnlockDTO.builder()
                .uuid("C")
                .combination(new String[]{"123"})
                .items(new ItemDTO[]{ItemDTO.builder().uuid("A").name("Test Required Item").build()})
                .build();

        when(repository.findByuuid(Mockito.anyString())).thenReturn(staticObject);

        List<ItemDTO> itemDTOS = Assertions.assertDoesNotThrow(() -> staticObjectService.unlockStaticObject("C", unlockDTO));

        Assertions.assertNotNull(itemDTOS);
        Assertions.assertNotEquals(0, itemDTOS.size());
        Assertions.assertEquals("B", itemDTOS.get(0).getUuid());
    }

    @Test
    public void StaticObjectService_UnlockStaticObjectWithoutHeldItems_ReturnEmptyListOfItems() {
        Item heldItem = Item.builder().uuid("B").name("Test Held Item").description("Test Held Item Description").build();
        Item requiredItem = Item.builder().uuid("A").name("Test Required Item").build();
        StaticObject staticObject = StaticObject.builder()
                .uuid("C")
                .name("Test Static Object")
                .description("Test Static Object Description")
                .isLocked(false)
                .combination(new String[]{"123"})
                .requiredItems(Collections.singleton(requiredItem))
                .heldItems(Collections.emptySet())
                .build();


        UnlockDTO unlockDTO = UnlockDTO.builder()
                .uuid("C")
                .combination(new String[]{"123"})
                .items(new ItemDTO[]{ItemDTO.builder().uuid("A").name("Test Required Item").build()})
                .build();

        when(repository.findByuuid(Mockito.anyString())).thenReturn(staticObject);

        List<ItemDTO> itemDTOS = Assertions.assertDoesNotThrow(() -> staticObjectService.unlockStaticObject("C", unlockDTO));
        log.debug(itemDTOS.toString());

        Assertions.assertNotNull(itemDTOS);
        Assertions.assertEquals(0, itemDTOS.size());
    }

    @Test
    public void StaticObjectService_UnlockStaticObjectWrongID_ThrowsUnlockException() {
        Item heldItem = Item.builder().uuid("B").name("Test Held Item").description("Test Held Item Description").build();
        Item requiredItem = Item.builder().uuid("A").name("Test Required Item").build();
        StaticObject staticObject = StaticObject.builder()
                .uuid("C")
                .name("Test Static Object")
                .description("Test Static Object Description")
                .isLocked(false)
                .combination(new String[]{"123"})
                .heldItems(Collections.singleton(heldItem))
                .requiredItems(Collections.singleton(requiredItem))
                .build();


        UnlockDTO unlockDTO = UnlockDTO.builder()
                .uuid("C")
                .combination(new String[]{"123"})
                .items(new ItemDTO[]{ItemDTO.builder().uuid("A").name("Test Required Item").build()})
                .build();

        // Service won't check repository if IDs are mismatched
        // when(repository.findByuuid(Mockito.anyString())).thenReturn(staticObject);

        Exception e = Assertions.assertThrows(UnlockFailException.class, () -> staticObjectService.unlockStaticObject("D", unlockDTO));
        Assertions.assertEquals("IDs do not match", e.getMessage());
    }

    @Test
    public void StaticObjectService_UnlockStaticObjectWrongCombination_ThrowsUnlockException() {
        Item heldItem = Item.builder().uuid("B").name("Test Held Item").description("Test Held Item Description").build();
        Item requiredItem = Item.builder().uuid("A").name("Test Required Item").build();
        StaticObject staticObject = StaticObject.builder()
                .uuid("C")
                .name("Test Static Object")
                .description("Test Static Object Description")
                .isLocked(false)
                .combination(new String[]{"123"})
                .heldItems(Collections.singleton(heldItem))
                .requiredItems(Collections.singleton(requiredItem))
                .build();


        UnlockDTO unlockDTO = UnlockDTO.builder()
                .uuid("C")
                .combination(new String[]{"456"})
                .items(new ItemDTO[]{ItemDTO.builder().uuid("A").name("Test Required Item").build()})
                .build();

        when(repository.findByuuid(Mockito.anyString())).thenReturn(staticObject);

        Exception e = Assertions.assertThrows(UnlockFailException.class, () -> staticObjectService.unlockStaticObject("C", unlockDTO));
        Assertions.assertEquals("Combination not found " + staticObject.getCombination()[0], e.getMessage());
    }

    @Test
    public void StaticObjectService_UnlockStaticObjectWrongItem_ThrowsUnlockException() {
        Item heldItem = Item.builder().uuid("B").name("Test Held Item").description("Test Held Item Description").build();
        Item requiredItem = Item.builder().uuid("A").name("Test Required Item").build();
        StaticObject staticObject = StaticObject.builder()
                .uuid("C")
                .name("Test Static Object")
                .description("Test Static Object Description")
                .isLocked(false)
                .combination(new String[]{"123"})
                .heldItems(Collections.singleton(heldItem))
                .requiredItems(Collections.singleton(requiredItem))
                .build();


        UnlockDTO unlockDTO = UnlockDTO.builder()
                .uuid("C")
                .combination(new String[]{"123"})
                .items(new ItemDTO[]{ItemDTO.builder().uuid("D").name("Test Required Item").build()})
                .build();

        when(repository.findByuuid(Mockito.anyString())).thenReturn(staticObject);

        Exception e = Assertions.assertThrows(UnlockFailException.class, () -> staticObjectService.unlockStaticObject("C", unlockDTO));
        Assertions.assertEquals("Item not found " + staticObject.getRequiredItems().stream().toList().get(0).getUuid(), e.getMessage());
    }


}
