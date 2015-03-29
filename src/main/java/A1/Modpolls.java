package A1;

import javax.validation.constraints.NotNull;

/**
 * Created by Meghana on 3/28/2015.
 */

public class Modpolls {
    private String modId;
    private  String id;
    @NotNull
    private  String question;
    @NotNull
    private  String started_at;
    @NotNull
    private  String expired_at;
    @NotNull
    private  String[] choice;

    private  int[] results;

    public Modpolls(String modId, String id, String question, String started_at, String expired_at, String[] choice, int[] results) {
        this.modId = modId;
        this.id = id;
        this.question = question;
        this.started_at = started_at;
        this.expired_at = expired_at;
        this.choice = choice;
        this.results = results;
    }

    public Modpolls() {
        this.modId = null;
        this.id = null;
        this.question = null;
        this.started_at = null;
        this.expired_at = null;
        this.choice = null;
        this.results = null;
    }

    public Poll_noRes convert_noRes()
    {
        Poll_noRes pol = new Poll_noRes(this.id,this.question,this.started_at,this.expired_at,this.choice);
        return pol;
    }
    public Poll_res convert_res()
    {
        Poll_res pol2 = new Poll_res(this.id,this.question,this.started_at,this.expired_at,this.choice,this.results);
        return pol2;
    }
    public void setModId(String Mid)
    {
        this.modId = Mid;
    }

    public void setId(String mid)
    {
        this.id = mid;
    }

    public String getId() {
        return id;
    }
    public String getModId() {
        return modId;
    }

    public void setQuestion(String qn)
    {
        this.question = qn;
    }

    public String getQuestion() {
        return question;
    }
    public String getStarted_at() {
        return started_at;
    }
    public String getExpired_at() {
        return expired_at;
    }
    public String[] getChoice() {
        return choice;
    }

    public int[] getResults() {
        return results;
    }
    public void incrementResults(int i)
    {
        this.results[i]++;
    }
    public Modpolls(Poll_noRes obj, String Mid){
        this.id = obj.getId();
        this.question = obj.getQuestion();
        this.choice = obj.getChoice();
        this.expired_at = obj.getExpired_at();
        this.started_at = obj.getStarted_at();
        results = new int[this.choice.length];
        for(int i=0; i<results.length; i++){
            results[i]=0;
        }
        this.modId = Mid;
    }

}
