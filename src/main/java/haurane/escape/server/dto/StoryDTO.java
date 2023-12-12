package haurane.escape.server.dto;

import java.util.List;

public interface StoryDTO {
    String getUuid();
    String getTitle();
    String getDescription();
    String getIntro();
    List<StartingRoomID> getStartingRoom();

    interface StartingRoomID{
        String getUuid();
        String getName();
    }
}
