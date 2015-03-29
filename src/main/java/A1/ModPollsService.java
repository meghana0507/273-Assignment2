package A1;

/**
 * Created by Meghana on 3/28/2015.
 */
import java.util.ArrayList;
import java.util.List;

interface ModPollsService {
    Modpolls create(Modpolls modPolls);
    Modpolls findById(String id);
    ArrayList<Modpolls> findPolls(String id);
    void deletePoll(String id);
    void votepoll(String id, int choice);
}
