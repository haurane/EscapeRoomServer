package haurane.escape.server.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node
public class Item {
    @Id
    @GeneratedValue
    private String id;

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
