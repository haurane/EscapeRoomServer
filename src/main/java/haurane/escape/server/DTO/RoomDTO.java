package haurane.escape.server.DTO;

import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.Optional;

public interface RoomDTO {
    String getDescription();
    String getuuid();
    String getName();
    List<containsSummary> getContainedObjects();

    interface containsSummary{
        String getuuid();
    }
}
