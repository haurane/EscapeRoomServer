package haurane.escape.server.dto;

import haurane.escape.server.models.Summarizable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoomDTO {
    String description;
    String uuid;
    String name;
    List<Summary> containedObjects;
}
