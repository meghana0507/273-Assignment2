
package A1;

/**
 * Created by Meghana on 3/28/2015.
 */
//import org.springframework.data.repository.Repository;
        import java.util.ArrayList;
        import java.util.List;
        import org.springframework.data.mongodb.repository.MongoRepository;
        import org.springframework.data.mongodb.repository.Query;

interface PollRepository extends MongoRepository<Modpolls, String>{
    Modpolls findOne(String id);
    Modpolls save(Modpolls saved);
    void delete(Modpolls deleted);
    @Query(value="{'modId':?0}")
    ArrayList<Modpolls> findByModId(String id);

}