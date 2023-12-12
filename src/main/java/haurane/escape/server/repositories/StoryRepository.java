package haurane.escape.server.repositories;

import haurane.escape.server.dto.StoryDTO;
import haurane.escape.server.models.Story;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.List;

@Repository
public interface StoryRepository extends CrudRepository<Story, String>{
        //ReactiveNeo4jRepository<Story, String> {
    List<Story> findByTitle(@Param("title") String title);
    StoryDTO findByUuid(String uuid);
    List<StoryDTO> findAllStoryDTOBy();

}
