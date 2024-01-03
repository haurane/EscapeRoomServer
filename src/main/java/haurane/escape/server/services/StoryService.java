package haurane.escape.server.services;

import haurane.escape.server.dto.StoryDTO;

import java.util.List;

public interface StoryService {
    public List<StoryDTO> getAllStories();
}
