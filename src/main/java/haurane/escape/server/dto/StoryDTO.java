package haurane.escape.server.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoryDTO {
    private String uuid;
    private String title;
    private String description;
    private String intro;
    private String startingRoomId;

}
