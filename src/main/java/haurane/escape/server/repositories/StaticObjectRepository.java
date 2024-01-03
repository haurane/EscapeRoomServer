package haurane.escape.server.repositories;

import haurane.escape.server.models.StaticObject;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaticObjectRepository extends
        PagingAndSortingRepository<StaticObject, String>, CrudRepository<StaticObject, String> {
    List<StaticObject> findByName(@Param("name") String name);

    StaticObject findByuuid(String uuid);

    @Query("""
            MATCH (R:Room{uuid:$roomId}) -[contained:contains]-> (s:StaticObject)\s
            Optional Match (s) -[attached *0..]-> (i:Item)\s
            return s, collect(attached), collect(i)""")
    List<StaticObject> findByContainingRoom(@Param("roomId") String roomId);
}
