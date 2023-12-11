package haurane.escape.server.DTO;

import java.util.List;

public interface StoryDTO {
    String getUuid();
    String getTitle();
    String getDescription();
    String getIntro();
    List<StartingRoomID> getStartingRoom();

    interface StartingRoomID{
        String getUuid();
    }
}
