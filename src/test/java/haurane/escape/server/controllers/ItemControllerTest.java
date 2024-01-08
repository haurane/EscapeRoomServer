package haurane.escape.server.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import haurane.escape.server.dto.ItemDTO;
import haurane.escape.server.models.Item;
import haurane.escape.server.models.StaticObject;
import haurane.escape.server.services.ItemService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(controllers = ItemController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class ItemControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private ItemService itemService;

    private Item item;
    private ItemDTO itemDTO;

    private StaticObject staticObject;

    @BeforeEach
    public void init() {
        item = Item.builder()
                .uuid("AAA")
                .name("Test Item")
                .description("Test Item Description")
                .build();

        itemDTO = ItemDTO.builder()
                .uuid("AAA")
                .name("Test Item")
                .description("Test Item Description")
                .build();
    }

    @Test
    public void ItemController_GetById_ReturnDTO() throws Exception {
        when(itemService.getByUUID(Mockito.anyString())).thenReturn(itemDTO);

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/items/AAA")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is("Test Item")));
    }

    @Test
    public void ItemController_GetByStaticObjectHolding_ReturnDTOs() throws Exception {
        List<ItemDTO> list = List.of(itemDTO);
        when(itemService.getByHoldingStaticObject(Mockito.anyString())).thenReturn(list);

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/items/AAA/held")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(list.size())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].name", CoreMatchers.is(item.getName())))
                .andDo(print());
    }

    @Test
    public void ItemController_GetByStaticObjectRequiring_ReturnDTOs() throws Exception {
        List<ItemDTO> list = List.of(itemDTO);
        when(itemService.getByRequiringStaticObject(Mockito.anyString())).thenReturn(list);

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/items/AAA/required")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(list.size())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].name", CoreMatchers.is(item.getName())))
                .andDo(print());
    }
}