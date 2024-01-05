package haurane.escape.server.repositories;

import haurane.escape.server.models.Item;
import haurane.escape.server.models.Room;
import haurane.escape.server.models.StaticObject;
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
import java.util.List;
import java.util.Optional;

@DataNeo4jTest
@Transactional(propagation = Propagation.NEVER)
public class StaticObjectRepositoryTest {
    private static Neo4j embeddedDatabaseServer;
    private final Logger log = LoggerFactory.getLogger(StaticObjectRepositoryTest.class);

    @Autowired
    private StaticObjectRepository repository;
    // Presume Tested
    @Autowired
    private RoomRepository roomRepository;

    private StaticObject object;
    private Item[] requiredItem;
    private Item[] heldItem;
    private String[] combination = {"123", "456"};


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

        requiredItem = new Item[]{Item.builder().build()};
        heldItem = new Item[]{Item.builder().build()};
        object = StaticObject.builder()
                .name("Test Static Object")
                .description("Test Static Object Description")
                .isLocked(false)
                .combination(combination)
                .heldItems(new HashSet<>(Arrays.stream(heldItem).toList()))
                .requiredItems(new HashSet<>(Arrays.stream(requiredItem).toList()))
                .build();
    }

    @AfterEach
    void cleanDB() {
        repository.deleteAll();
    }

    @Test
    public void StaticObjectRepository_SaveObject_ReturnObject() {

        StaticObject fetch = repository.save(object);

        Assertions.assertNotNull(fetch);
        Assertions.assertEquals(object.getName(), fetch.getName());
    }

    @Test
    public void StaticObjectRepository_FindByContainingRoom_ReturnNonEmptyList() {

        HashSet<StaticObject> s = new HashSet<StaticObject>();
        s.add(object);
        Room r = Room.builder()
                .containedObjects(s)
                .build();
        roomRepository.save(r);

        List<StaticObject> fetch = repository.findByContainingRoom(r.getUuid());

        Assertions.assertNotNull(fetch);
        Assertions.assertNotEquals(0, fetch.size());
    }

    @Test
    public void StaticObjectRepository_FindByuuid_ReturnStaticObject() {

        repository.save(object);

        StaticObject fetch = repository.findByuuid(object.getUuid());

        Assertions.assertNotNull(fetch);
        Assertions.assertEquals(object.getName(), fetch.getName());
    }
}
