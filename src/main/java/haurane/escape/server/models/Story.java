package haurane.escape.server.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

@Node
public class Story {
    @Id
    @GeneratedValue()
    private String id;

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private String intro;

    private Story() {
    }

    public Story(String title, String description, String intro) {
        this.title = title;
        this.description = description;
        this.intro = intro;
    }

    @Relationship(type = "starts", direction = Relationship.Direction.OUTGOING)
    Room startingRoom;
}
