package haurane.escape.server.repositories;

import haurane.escape.server.models.Room;
import haurane.escape.server.models.StaticObject;
import org.junit.After;
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

import java.util.Arrays;
import java.util.HashSet;

@DataNeo4jTest
@Transactional(propagation = Propagation.NEVER)
public class RoomRepositoryTest {
    private static Neo4j embeddedDatabaseServer;
    private final Logger log = LoggerFactory.getLogger(StaticObjectRepositoryTest.class);

    @Autowired
    private RoomRepository repository;

    // Presume Tested
    @Autowired
    private StoryRepository StoryRepository;

    private Room room;
    private StaticObject[] containedObjects;

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
        containedObjects = new StaticObject[]{StaticObject.builder().build()};
        room = Room.builder()
                .name("Test Room")
                .description("Test Room Description")
                .containedObjects(new HashSet<>(Arrays.stream(containedObjects).toList()))
                .build();
    }

    @AfterEach
    void cleanDB() {
        repository.deleteAll();
    }

    @Test
    public void RoomRepository_SaveRoom_ReturnRoom() {

        Room fetch = repository.save(room);

        Assertions.assertNotNull(fetch);
        Assertions.assertEquals(room.getName(), fetch.getName());
    }

    @Test
    public void RoomRepository_FindByuuid_ReturnRoom() {
        repository.save(room);

        Room fetch = repository.findByuuid(room.getUuid());

        Assertions.assertNotNull(fetch);
        Assertions.assertEquals(room.getName(), fetch.getName());
    }

}
