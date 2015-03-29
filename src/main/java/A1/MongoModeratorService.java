package A1;

/**
 * Created by Meghana on 3/27/2015.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;
import java.text.SimpleDateFormat;


@Service
final class MongoModeratorService implements ModeratorService{
    private final ModeratorRepository repository;

    @Autowired
    MongoModeratorService(ModeratorRepository repository){
        this.repository = repository;
    }

    @Override
    public Moderator create(Moderator mod){
          repository.save(mod);
        return mod;
    }

    @Override
    public Moderator findById(String id){
       Moderator mod = repository.findOne(id);
        return mod;
    }

    @Override
    public Moderator update(Moderator mod)
    {
        Moderator modUpdated = findById(mod.getId());
        if (mod.getName() != null) {
            modUpdated.setName(mod.getName());
        }

        if (mod.getEmail() != null) {
            modUpdated.setEmail(mod.getEmail());
        }

        if (mod.getPassword() != null) {
            modUpdated.setPassword(mod.getPassword());
        }

        repository.save(modUpdated);

        return modUpdated;
    }
/*
    private Moderator findModById(long id)
    {
        Optional<Moderator> res = repository.findOne(id);
        String str = Long.toString(id);
        return res.orElseThrow(() -> new NotFoundException(str));
    }
  */

}
