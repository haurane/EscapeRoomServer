package haurane.escape.server.controllers;

import haurane.escape.server.dto.StoryDTO;
import haurane.escape.server.repositories.StoryRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/stories")
public class StoryController {
    private final StoryRepository storyRepository;
    public StoryController(StoryRepository storyRepository){
        this.storyRepository = storyRepository;
    }

    @GetMapping(value = {"", "/"})
    List<StoryDTO> getStories(){
        return storyRepository.findAllStoryDTOBy();
    }
}
