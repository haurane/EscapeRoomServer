package haurane.escape.server.services;

import haurane.escape.server.dto.StoryDTO;
import haurane.escape.server.models.Room;
import haurane.escape.server.models.Story;
import haurane.escape.server.repositories.StoryRepository;
import haurane.escape.server.services.impl.StoryServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StoryServiceTest {
    @Mock
    private StoryRepository repository;

    @InjectMocks
    private StoryServiceImpl storyService;

    @Test
    public void StoryService_GetAllStories_ReturnListOfDTO() {
        Story story = Story.builder()
                .title("Story Title")
                .description("Story Description")
                .intro("Story Intro")
                .startingRoom(Room.builder().build())
                .build();

        when(repository.findAll()).thenReturn(List.of(story));
        List<StoryDTO> dtos = storyService.getAllStories();

        Assertions.assertNotEquals(0, dtos.size());
        Assertions.assertEquals(story.getTitle(), dtos.get(0).getTitle());
        Assertions.assertEquals(story.getDescription(), dtos.get(0).getDescription());
        Assertions.assertEquals(story.getIntro(), dtos.get(0).getIntro());
        Assertions.assertEquals(story.getStartingRoom().getUuid(), dtos.get(0).getStartingRoomId());
    }
}
