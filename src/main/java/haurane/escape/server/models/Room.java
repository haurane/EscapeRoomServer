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
public class Room {

    @Id
    @GeneratedValue(generatorClass = UUIDStringGenerator.class)
    private String uuid;
    private String name;
    private String description;

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Relationship(type = "contains", direction = Relationship.Direction.OUTGOING)
    public Set<StaticObject> containedObjects;

    public void roomContains(StaticObject object) {
        if (containedObjects == null) {
            containedObjects = new HashSet<>();
        }
        containedObjects.add(object);
    }


}
