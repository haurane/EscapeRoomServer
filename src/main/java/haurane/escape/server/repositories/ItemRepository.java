package haurane.escape.server.repositories;

import haurane.escape.server.models.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends PagingAndSortingRepository<Item, String>, CrudRepository<Item, String> {
    List<Item> findByName(@Param("name") String name);
}
