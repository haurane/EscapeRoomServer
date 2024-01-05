package haurane.escape.server.repositories;

import haurane.escape.server.models.Item;
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

import java.util.Collections;
import java.util.List;

@DataNeo4jTest
@Transactional(propagation = Propagation.NEVER)
public class ItemRepositoryTest {
    private static Neo4j embeddedDatabaseServer;
    @Autowired
    private ItemRepository repository;
    @Autowired
    private StaticObjectRepository staticObjectRepository;

    private Item item;
    private final Logger log = LoggerFactory.getLogger(ItemRepositoryTest.class);

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
        item = Item.builder()
                .name("TestItem")
                .description("TestItem Description")
                .build();
    }

    @AfterEach
    void cleanDB() {
        repository.deleteAll();
    }

    @Test
    public void ItemRepository_SaveItem_ReturnItem() {


        Item saved = repository.save(item);

        Assertions.assertNotNull(saved);
        Assertions.assertEquals(item.getName(), saved.getName());
    }

    @Test
    public void ItemRepository_GetByUUID_ReturnItem() {

        repository.save(item);

        Item fetched = repository.findByuuid(item.getUuid());

        Assertions.assertNotNull(fetched);
        Assertions.assertEquals(item.getUuid(), fetched.getUuid());
        Assertions.assertEquals(item.getName(), fetched.getName());
    }

    @Test
    public void ItemRepository_FindByHoldingStaticObject_ReturnNonEmptyList() {
        StaticObject staticObject = StaticObject.builder()
                .heldItems(Collections.singleton(item))
                .build();

        staticObjectRepository.save(staticObject);

        List<Item> fetched = repository.findByHoldingStaticObject(staticObject.getUuid());

        Assertions.assertNotNull(fetched);
        Assertions.assertNotEquals(0, fetched.size());
    }

    @Test
    public void ItemRepository_FindByRequiringStaticObject_ReturnNonEmptyList() {
        StaticObject staticObject = StaticObject.builder()
                .requiredItems(Collections.singleton(item))
                .build();

        staticObjectRepository.save(staticObject);

        List<Item> fetched = repository.findByRequiringStaticObject(staticObject.getUuid());

        Assertions.assertNotNull(fetched);
        Assertions.assertNotEquals(0, fetched.size());
    }


}
