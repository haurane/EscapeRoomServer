package haurane.escape.server.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UnlockDTO {
    String uuid;
    String[] combination;
    ItemDTO[] items;

}
