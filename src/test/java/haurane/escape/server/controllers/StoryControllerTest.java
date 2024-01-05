package haurane.escape.server.controllers;

import haurane.escape.server.models.Room;
import haurane.escape.server.models.Story;
import haurane.escape.server.services.StoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = StoryController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class StoryControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private StoryService storyService;

    private Story story;
    private Room room;

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
    }
}
