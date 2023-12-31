package haurane.escape.server.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import haurane.escape.server.dto.*;
import haurane.escape.server.services.StaticObjectService;
import haurane.escape.server.services.impl.UnlockFailException;
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

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = StaticObjectController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class StaticObjectControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private StaticObjectService staticObjectService;
    @Autowired
    private ObjectMapper objectMapper;

    private StaticObjectDTO staticObjectDTO;
    private FullStaticObjectDTO fullStaticObjectDTO;

    @BeforeEach
    public void init() {
        staticObjectDTO = StaticObjectDTO.builder()
                .name("Test Static Object 1")
                .description("Test Static Object Description")
                .uuid("AAA")
                .isLocked(true)
                .build();
        fullStaticObjectDTO = FullStaticObjectDTO.builder()
                .name("Test Static Object 2")
                .description("Test Static Object Description")
                .uuid("BBB")
                .isLocked(false)
                .heldItems(List.of(
                        Summary.builder().name("Test Item 1").uuid("CCC").build(),
                        Summary.builder().name("Test Item 2").uuid("DDD").build()
                ))
                .build();
    }

    @Test
    public void StaticObjectController_GetByUUIDUnlocked_ReturnFullDTO() throws Exception {
        when(staticObjectService.getByUUID("BBB")).thenReturn(fullStaticObjectDTO);

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/staticobjects/BBB")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(fullStaticObjectDTO.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.uuid", CoreMatchers.is(fullStaticObjectDTO.getUuid())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.heldItems.size()", CoreMatchers.is(fullStaticObjectDTO.getHeldItems().size())));
    }

    @Test
    public void StaticObjectController_GetByUUIDLocked_ReturnSimpleDTO() throws Exception {
        when(staticObjectService.getByUUID("AAA")).thenReturn(staticObjectDTO);

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/staticobjects/AAA")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(staticObjectDTO.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.uuid", CoreMatchers.is(staticObjectDTO.getUuid())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.heldItems").doesNotExist());
    }

    @Test
    public void StaticObjectController_GetByRoomContaining_ReturnMixedDTOs() throws Exception {
        List<StaticObjectDTO> list = List.of(staticObjectDTO, fullStaticObjectDTO);
        when(staticObjectService.getByContainingRoom(Mockito.anyString())).thenReturn(list);

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/staticobjects/QQQ/contained"));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(list.size())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].heldItems").doesNotExist())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].heldItems").exists());
    }

    @Test
    public void StaticObjectController_UnlockByUUID_ReturnListOfItems() throws Exception {
        List<ItemDTO> list = List.of(ItemDTO.builder().name("Test Item 1").uuid("RRR").description("Test Item 1").build(),
                ItemDTO.builder().name("Test Item 2").uuid("TTT").description("Test Item 2").build());
        UnlockDTO unlockDTO = UnlockDTO.builder().uuid("AAA").build();

        when(staticObjectService.unlockStaticObject("AAA", unlockDTO)).thenReturn(list);

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/staticobjects/AAA/unlock")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(unlockDTO)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(list.size())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].name", CoreMatchers.is(list.get(0).getName())));
    }

    @Test
    public void StaticObjectController_UnlockByUUIDIncorrect_Returns422() throws Exception {
        List<ItemDTO> list = List.of(ItemDTO.builder().name("Test Item 1").uuid("RRR").description("Test Item 1").build(),
                ItemDTO.builder().name("Test Item 2").uuid("TTT").description("Test Item 2").build());
        UnlockDTO unlockDTO = UnlockDTO.builder().uuid("AAA").build();

        when(staticObjectService.unlockStaticObject("BBB", unlockDTO)).thenThrow(new UnlockFailException("IDs do not match"));

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/staticobjects/BBB/unlock")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(unlockDTO)));

        response.andExpect(MockMvcResultMatchers.status().is(422))
                .andExpect(MockMvcResultMatchers.status().reason(containsString("IDs do not match")));
    }


}