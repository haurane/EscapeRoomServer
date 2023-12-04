package haurane.escape.server.repositories;

import haurane.escape.server.models.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends PagingAndSortingRepository<Room, String>, CrudRepository<Room,String> {
    List<Room> findByName(@Param("name") String name);
}
