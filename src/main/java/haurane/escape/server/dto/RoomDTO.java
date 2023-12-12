package haurane.escape.server.dto;

import java.util.List;

public interface RoomDTO {
    String getDescription();
    String getuuid();
    String getName();
    List<containsSummary> getContainedObjects();

    interface containsSummary{
        String getuuid();
        String getName();
    }
}
