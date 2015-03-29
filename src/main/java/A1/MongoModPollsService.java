package A1;

/**
 * Created by Meghana on 3/28/2015.
 */

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.repository.Query;


@Service
final class MongoModPollsService implements ModPollsService {
    private final PollRepository repo;

    @Autowired
    MongoModPollsService(PollRepository repo){
        this.repo = repo;
    }

    @Override
    public Modpolls create(Modpolls pol){
        repo.save(pol);
        return pol;
    }

    @Override
    public Modpolls findById(String id){
        Modpolls mod = repo.findOne(id);
        return mod;
    }

    @Override
    public ArrayList<Modpolls> findPolls(String id){
       ArrayList<Modpolls> mod = repo.findByModId(id);
        return mod;
    }

    @Override
    public void deletePoll(String id){
        Modpolls deleted = repo.findOne(id);
        repo.delete(deleted);
    }

    @Override
    public void votepoll(String id, int choice){
       Modpolls obj = repo.findOne(id);
        obj.incrementResults(choice);
        repo.save(obj);
    }
}
