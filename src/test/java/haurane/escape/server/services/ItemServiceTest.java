package haurane.escape.server.services;

import haurane.escape.server.dto.ItemDTO;
import haurane.escape.server.models.Item;
import haurane.escape.server.repositories.ItemRepository;
import haurane.escape.server.services.impl.ItemServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ItemServiceTest {
    @Mock
    ItemRepository repository;
    @InjectMocks
    ItemServiceImpl itemService;

    @Test
    public void ItemService_getByUUID_ReturnDTO() {
        Item item = Item.builder()
                .uuid("A")
                .name("Test Item")
                .description("Test Item Description")
                .build();

        when(repository.findByuuid(Mockito.anyString())).thenReturn(item);

        ItemDTO dto = itemService.getByUUID(item.getUuid());

        Assertions.assertEquals(item.getName(), dto.getName());
        Assertions.assertEquals(item.getDescription(), dto.getDescription());
        Assertions.assertEquals(item.getUuid(), dto.getUuid());
    }

    @Test
    public void ItemService_getByHoldingStaticObject_ReturnsNonEmptyList() {
        Item item = Item.builder()
                .name("Test Item")
                .description("Test Item Description")
                .build();
        List<Item> list = new ArrayList<>();
        list.add(item);

        when(repository.findByHoldingStaticObject(Mockito.anyString())).thenReturn(list);

        List<ItemDTO> dtos = itemService.getByHoldingStaticObject("a");

        Assertions.assertNotNull(dtos);
        Assertions.assertNotEquals(0, dtos.size());
        Assertions.assertEquals(item.getName(), dtos.get(0).getName());
    }

    @Test
    public void ItemService_getByRequiringStaticObject_ReturnsNonEmptyList() {
        Item item = Item.builder()
                .name("Test Item")
                .description("Test Item Description")
                .build();
        List<Item> list = new ArrayList<>();
        list.add(item);

        when(repository.findByRequiringStaticObject(Mockito.anyString())).thenReturn(list);

        List<ItemDTO> dtos = itemService.getByRequiringStaticObject("a");

        Assertions.assertNotNull(dtos);
        Assertions.assertNotEquals(0, dtos.size());
        Assertions.assertEquals(item.getName(), dtos.get(0).getName());
    }
}
