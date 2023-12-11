package haurane.escape.server.DTO;

import java.util.List;

public interface StaticObjectDTO {
    String getUuid();
    String getName();
    String getDescription();
    String getIsLocked();
    String[] getCombination();
    List<HeldItemUuid> getHeldItems();
    List<HeldItemUuid> getRequiredItems();
    interface HeldItemUuid{
        String getUuid();
    }
}
