package A1;

/**
 * Created by Meghana on 2/27/2015.
 */

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class ModController
{
    private ModeratorService service;
    private ModPollsService pservice;
    private final AtomicLong counter = new AtomicLong();
    private final AtomicLong pollCounter = new AtomicLong(27072);
    SimpleDateFormat ISO8601DATEFORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);


    @Autowired
    ModController(ModeratorService service,ModPollsService pservice){this.service = service; this.pservice = pservice;}

    /*--------------------------
         Moderator Creation
     --------------------------*/
    @RequestMapping(value="/moderators",method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Moderator create(@RequestBody @Valid Moderator mod)
    {
                    mod.setId(String.format("%d",counter.incrementAndGet()));
                    Date temp = new Date();
                    mod.setCreated_at(ISO8601DATEFORMAT.format(temp));
        return service.create(mod);
    }

    /*--------------------------
            View Moderator
     --------------------------*/
    @RequestMapping(value="/moderators/{moderator_id}",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Moderator findById(@PathVariable("moderator_id") String modId) {
        return service.findById(modId);
    }

    /*--------------------------
        Update Moderator
     --------------------------*/
    @RequestMapping(value="/moderators/{moderator_id}",method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Moderator update(@PathVariable("moderator_id") String modId,
                                                   @RequestBody Moderator modUpdated)
    {
        modUpdated.setId(modId);
            return service.update(modUpdated);
    }

    @RequestMapping(value="/moderators/{moderator_id}/polls",method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Poll_noRes createPoll(@PathVariable("moderator_id") String modId, @RequestBody @Valid Poll_noRes pol) {
           pol.setId(Long.toString(pollCounter.incrementAndGet(), 36).toUpperCase());
            Modpolls pol2 = new Modpolls(pol, modId);
       return pservice.create(pol2).convert_noRes();
    }

    @RequestMapping(value="/polls/{poll_id}",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Poll_noRes viewPollNoRes(@PathVariable("poll_id") String polId){

        return pservice.findById(polId).convert_noRes();
    }

    @RequestMapping(value="/moderators/{moderator_id}/polls/{poll_id}",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Poll_res viewPollRes(@PathVariable("moderator_id") String modId, @PathVariable("poll_id") String polId)
    {
        return pservice.findById(polId).convert_res();
    }

    @RequestMapping(value="/moderators/{moderator_id}/polls",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody ArrayList<Poll_res> ListPolls(@PathVariable("moderator_id") String modId) {
                ArrayList<Modpolls> obj = pservice.findPolls(modId);
                ArrayList<Poll_res> pollArr = new ArrayList<Poll_res>();
                for(int i=0; i<obj.size();i++){
                    pollArr.add(obj.get(i).convert_res());
                }
        return pollArr;
    }

   @RequestMapping(value="/moderators/{moderator_id}/polls/{poll_id}",method = RequestMethod.DELETE)
   @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePolls(@PathVariable("moderator_id") String modId, @PathVariable("poll_id") String polId)
   {
        pservice.deletePoll(polId);
    }

    @RequestMapping(value="/polls/{poll_id}",method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void votePoll(@PathVariable("poll_id") String polId, @RequestParam(value="choice") int choice)
    {
        pservice.votepoll(polId, choice);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<String> handleException(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<ObjectError> globalErrors = ex.getBindingResult().getGlobalErrors();
        List<String> errors = new ArrayList<String>(fieldErrors.size() + globalErrors.size());
        String error;
        for (FieldError fieldError : fieldErrors) {
            error = fieldError.getField() + ", " + fieldError.getDefaultMessage();
            errors.add(error);
        }
        for (ObjectError objectError : globalErrors) {
            error = objectError.getObjectName() + ", " + objectError.getDefaultMessage();
            errors.add(error);
        }
        return errors;
    }

} // End class
