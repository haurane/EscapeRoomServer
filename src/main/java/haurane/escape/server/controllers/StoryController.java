package haurane.escape.server.controllers;

import haurane.escape.server.dto.StoryDTO;
import haurane.escape.server.services.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/stories")
public class StoryController {
    private final StoryService storyService;

    @Autowired
    public StoryController(StoryService storyService) {

        this.storyService = storyService;
    }

    @GetMapping(value = {"", "/"})
    List<StoryDTO> getStories() {
        return storyService.getAllStories();
    }
}
