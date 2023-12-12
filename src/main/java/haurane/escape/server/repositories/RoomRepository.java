package haurane.escape.server.repositories;

import haurane.escape.server.dto.RoomDTO;
import haurane.escape.server.dto.StaticObjectDTO;
import haurane.escape.server.models.Room;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends Neo4jRepository<Room, String>, CrudRepository<Room, String> {
    //List<Room> findByName(@Param("name") String name);
    RoomDTO findByUuid(@Param("uuid") String uuid);
    List<RoomDTO> findByName(@Param("name") String name);

    /*
    @Query("MATCH (R:Room{uuid:$id}) -[:contains]-> (s:StaticObject) return s")
    List<StaticObjectDTO> getContainedObjects(@Param("id") String id);
    */
}


