package A1;

/**
 * Created by Meghana on 3/28/2015.
 */

//import org.springframework.data.repository.Repository;
        import java.util.List;
        import org.springframework.data.mongodb.repository.MongoRepository;

interface ModeratorRepository extends MongoRepository<Moderator, String>{
    Moderator findOne(String id);
    Moderator save(Moderator saved);
}
