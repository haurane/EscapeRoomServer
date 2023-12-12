package haurane.escape.server.repositories;

import haurane.escape.server.dto.StaticObjectDTO;
import haurane.escape.server.models.StaticObject;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaticObjectRepository extends
        PagingAndSortingRepository<StaticObject,String>, CrudRepository<StaticObject, String> {
    List<StaticObject> findByName(@Param("name") String name);
    StaticObjectDTO findByUuid(String uuid);
    /*
    @Query("MATCH (R:Room{uuid:'roomId'}) -[:contains]-> (s:StaticObject) return s")
    List<StaticObjectDTO> findByContainingRoom(@Param("roomId") String roomId);
     */
}
