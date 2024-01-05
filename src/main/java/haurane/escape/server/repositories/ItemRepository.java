package haurane.escape.server.repositories;

import haurane.escape.server.models.Item;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends PagingAndSortingRepository<Item, String>, CrudRepository<Item, String> {
    List<Item> findByName(@Param("name") String name);

    Item findByuuid(String uuid);

    @Query("Match (s:StaticObject{uuid:$staticObjectId}) -[:holds]-> (i:Item) return collect(i)")
    List<Item> findByHoldingStaticObject(@Param("staticObjectId") String staticObjectId);

    @Query("Match (s:StaticObject{uuid:$staticObjectId}) -[:requires]-> (i:Item) return collect(i)")
    List<Item> findByRequiringStaticObject(@Param("staticObjectId") String staticObjectId);

}
