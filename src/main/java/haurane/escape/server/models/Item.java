package haurane.escape.server.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

@Node
public class Item {
    @Id
    @GeneratedValue(UUIDStringGenerator.class)
    private String uuid;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String description;

    private Item() {
        // Needed By neo4j
    }

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
