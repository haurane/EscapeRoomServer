package haurane.escape.server.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

import java.util.HashSet;
import java.util.Set;

@Node
public class Room {
    @Id
    @GeneratedValue(generatorClass = UUIDStringGenerator.class)
    private String uuid;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String description;

    private Room(){
        // Empty Constructor required by Neo4j API
    }

    public Room(String name, String description){
        this.name = name;
        this.description = description;
    }

    @Relationship(type="contains", direction = Relationship.Direction.OUTGOING)
    public Set<StaticObject> containedObjects;

    public void roomContains(StaticObject object){
        if (containedObjects == null){
            containedObjects = new HashSet<>();
        }
        containedObjects.add(object);
    }


}
