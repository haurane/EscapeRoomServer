package haurane.escape.server.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import haurane.escape.server.dto.StoryDTO;
import haurane.escape.server.models.Room;
import haurane.escape.server.models.Story;
import haurane.escape.server.services.StoryService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(controllers = StoryController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class StoryControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private StoryService storyService;
    @Autowired
    private ObjectMapper objectMapper;


    private Story story;
    private Room room;
    private StoryDTO storyDto;

    @BeforeEach
    public void init() {
        room = Room.builder()
                .name("Test Room")
                .uuid("AAA")
                .build();

        story = Story.builder()
                .title("Test Story")
                .description("Test Story Description")
                .intro("Test Story Intro")
                .startingRoom(room)
                .build();

        storyDto = StoryDTO.builder()
                .title("Test Story")
                .description("Test Story Description")
                .intro("Test Story Intro")
                .startingRoomId("AAA")
                .build();
    }

    @Test
    public void StoryController_GetStories_ReturnDTO() throws Exception {
        List<StoryDTO> storyDTOS = Arrays.asList(storyDto, storyDto);
        when(storyService.getAllStories()).thenReturn(storyDTOS);

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/stories")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(storyDTOS.size())))
                .andDo(print());

    }
}
