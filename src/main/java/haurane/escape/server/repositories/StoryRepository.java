package haurane.escape.server.repositories;

import haurane.escape.server.models.Story;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoryRepository extends CrudRepository<Story, String> {
    //ReactiveNeo4jRepository<Story, String> {
    List<Story> findByTitle(@Param("title") String title);

    Story findByuuid(String uuid);

}
