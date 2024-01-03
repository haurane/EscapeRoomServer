package haurane.escape.server.models;

import lombok.*;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

import java.util.HashSet;
import java.util.Set;

@Node
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StaticObject {

    @Id
    @GeneratedValue(generatorClass = UUIDStringGenerator.class)
    private String uuid;
    private String name;
    private String description;
    private boolean isLocked;
    private String[] combination;

    public StaticObject(String name, String description, boolean isLocked, String[] combination) {
        this.name = name;
        this.description = description;
        this.isLocked = isLocked;
        this.combination = combination;
    }

    @Relationship(type = "holds", direction = Relationship.Direction.OUTGOING)
    public Set<Item> heldItems;

    public void addHeldItem(Item item) {
        if (heldItems == null) {
            heldItems = new HashSet<>();
        }
        heldItems.add(item);
    }

    @Relationship(type = "requires", direction = Relationship.Direction.OUTGOING)
    public Set<Item> requiredItems;

    public void addRequiredItem(Item item) {
        if (requiredItems == null) {
            requiredItems = new HashSet<>();
        }
        requiredItems.add(item);
    }
}
