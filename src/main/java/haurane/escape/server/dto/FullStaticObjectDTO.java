package haurane.escape.server.dto;

import haurane.escape.server.models.StaticObject;
import haurane.escape.server.models.Summarizable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FullStaticObjectDTO extends StaticObjectDTO {
    private List<Summary> heldItems;
}
