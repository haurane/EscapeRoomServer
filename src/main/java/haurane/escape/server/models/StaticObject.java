package haurane.escape.server.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashSet;
import java.util.Set;

@Node
public class StaticObject {

    @Id
    @GeneratedValue
    private String id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String description;

    private StaticObject(){
        // Empty Constructor required by Neo4j API
    }

    public StaticObject(String name, String description){
        this.name = name;
        this.description = description;
    }

    @Relationship(type="holds", direction = Relationship.Direction.OUTGOING)
    public Set<Item> heldItems;

    public void addHeldItem(Item item){
        if (heldItems == null){
            heldItems = new HashSet<>();
        }
        heldItems.add(item);
    }

    @Relationship(type="requires", direction = Relationship.Direction.OUTGOING)
    public Set<Item> requiredItems;

    public void addRequiredItem(Item item){
        if(requiredItems == null){
            requiredItems = new HashSet<>();
        }
        requiredItems.add(item);
    }

}
