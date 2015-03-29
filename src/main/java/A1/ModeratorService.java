package A1;

/**
 * Created by Meghana on 3/27/2015.
 */

import java.util.List;

interface ModeratorService {
    Moderator create(Moderator mod);
    Moderator findById(String id);
    Moderator update(Moderator mod);
}
