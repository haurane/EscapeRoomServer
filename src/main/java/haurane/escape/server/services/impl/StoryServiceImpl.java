package haurane.escape.server.services.impl;

import haurane.escape.server.dto.StoryDTO;
import haurane.escape.server.models.Story;
import haurane.escape.server.repositories.StoryRepository;
import haurane.escape.server.services.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StoryServiceImpl implements StoryService {
    private final StoryRepository storyRepository;

    @Autowired
    public StoryServiceImpl(StoryRepository storyRepository) {
        this.storyRepository = storyRepository;
    }

    @Override
    public List<StoryDTO> getAllStories() {
        List<StoryDTO> storyDTOS = new ArrayList<>();
        for (Story s : storyRepository.findAll()) {
            StoryDTO storyDto = DTOMapper.StoryToDTO(s);
            storyDTOS.add(storyDto);
        }
        return storyDTOS;

    }


}
