package haurane.escape.server.models;

import lombok.*;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

import java.util.Optional;

@Node
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Story {
    @Id
    @GeneratedValue(generatorClass = UUIDStringGenerator.class)
    private String uuid;
    private String title;
    private String description;
    private String intro;


    public Story(String title, String description, String intro) {
        this.title = title;
        this.description = description;
        this.intro = intro;
    }

    @Relationship(type = "starts", direction = Relationship.Direction.OUTGOING)
    private Room startingRoom;
}
