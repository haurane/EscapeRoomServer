package haurane.escape.server.dto;

import haurane.escape.server.models.Item;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StaticObjectDTO {
    private String uuid;
    private String name;
    private String description;
    private boolean isLocked;
    private String[] combination;
    private List<ItemDTO> heldItems;
    private List<ItemDTO> requiredItems;

    interface SOItems {
        String getUuid();

        String getName();
    }

}

