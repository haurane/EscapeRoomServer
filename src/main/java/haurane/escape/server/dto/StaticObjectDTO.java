package haurane.escape.server.dto;

import java.util.List;

public interface StaticObjectDTO {
    String getUuid();
    String getName();
    String getDescription();
    String getIsLocked();
    String[] getCombination();
    List<SOItems> getHeldItems();
    List<SOItems> getRequiredItems();
    interface SOItems {
        String getUuid();
        String getName();
    }
}
