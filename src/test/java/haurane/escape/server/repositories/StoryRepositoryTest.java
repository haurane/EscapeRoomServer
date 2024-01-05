package haurane.escape.server.repositories;

import haurane.escape.server.models.Room;
import haurane.escape.server.models.Story;
import org.junit.jupiter.api.*;
import org.neo4j.harness.Neo4j;
import org.neo4j.harness.Neo4jBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@DataNeo4jTest
@Transactional(propagation = Propagation.NEVER)
public class StoryRepositoryTest {
    private static Neo4j embeddedDatabaseServer;
    private final Logger log = LoggerFactory.getLogger(StaticObjectRepositoryTest.class);

    @Autowired
    private StoryRepository repository;

    private Story story;

    @BeforeAll
    static void initializeNeo4j() {
        embeddedDatabaseServer = Neo4jBuilders.newInProcessBuilder().withDisabledServer().build();
    }

    @DynamicPropertySource
    static void neo4jProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.neo4j.uri", embeddedDatabaseServer::boltURI);
        registry.add("spring.neo4j.authentication.username", () -> "neo4j");
        registry.add("spring.neo4j.authentication.password", () -> null);
    }

    @AfterAll
    static void stopNeo4j() {
        embeddedDatabaseServer.close();
    }

    @BeforeEach
    void init() {
        story = Story.builder()
                .title("Test Story Title")
                .description("Test Story Description")
                .intro("Test Story Intro")
                .startingRoom(Room.builder().build())
                .build();

    }

    @AfterEach
    void cleanDB() {
        repository.deleteAll();
    }

    @Test
    public void StoryRepository_SaveStory_ReturnStory() {
        Story fetch = repository.save(story);

        Assertions.assertNotNull(fetch);
        Assertions.assertEquals(story.getTitle(), fetch.getTitle());
    }

    @Test
    public void StoryRepository_FindAll_ReturnNonEmptyList() {
        repository.save(story);

        List<Story> list = new ArrayList<Story>();
        repository.findAll().forEach(list::add);

        Assertions.assertNotEquals(list.size(), 0);

    }

    @Test
    public void StoryRepository_FindByuuid_ReturnStory() {
        repository.save(story);

        Story fetch = repository.findByuuid(story.getUuid());

        Assertions.assertNotNull(fetch);
        Assertions.assertEquals(story.getTitle(), fetch.getTitle());
    }
}
