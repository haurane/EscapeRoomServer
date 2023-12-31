package haurane.escape.server.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {
    String uuid;
    String name;
    String description;
}
