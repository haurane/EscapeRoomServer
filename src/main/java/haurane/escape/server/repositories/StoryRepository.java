package haurane.escape.server.repositories;

import haurane.escape.server.models.Story;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoryRepository extends PagingAndSortingRepository<Story, String>, CrudRepository<Story,String> {
    List<Story> findByTitle(@Param("title") String title);

}
