package com.epam.rd.autocode.observer.git;

import java.util.*;

public class GitRepoObservers {
    protected static Map<String, WebHook> commits;
    protected static Map<String, WebHook> merges;
    protected static Repository r;


    public static Repository newRepository(){
        class Repo implements Repository{
            public Repo(){
                commits = new HashMap<String, WebHook>();
                merges = new HashMap<String, WebHook>();
            }

            @Override
            public void addWebHook(WebHook webHook) {
                if(webHook.type() == Event.Type.COMMIT){
                    commits.put(webHook.branch(), webHook);
                } else if(webHook.type() == Event.Type.MERGE){
                    merges.put(webHook.branch(), webHook);
                }
            }

            @Override
            public Commit commit(String branch, String author, String[] changes) {
                if(!commits.containsKey(branch)){
                    return null;
                }
                WebHook wh = commits.get(branch);
                Commit comm = new Commit(author, changes);
                List coms = new ArrayList<Commit>();
                coms.add(comm);
                Event e = new Event(wh.type(), wh.branch(), coms);
                wh.onEvent(e);
                return comm;
            }

            @Override
            public void merge(String sourceBranch, String targetBranch) {
                WebHook source = commits.get(sourceBranch);
                WebHook target = merges.get(targetBranch);
                if((source == null)||(target == null)){
                    return;
                }
                ArrayList<Commit> mer = new ArrayList<Commit>();
                for(Event ev : source.caughtEvents()){
                    for(Commit ec : ev.commits()) {
                        mer.add(ec);
                    }
                }
                ArrayList<Commit> tar = new ArrayList<Commit>();
                for(Event evt : target.caughtEvents()){
                    for(Commit ect : evt.commits()) {
                        tar.add(ect);
                    }
                }
                if(mer.equals(tar)){
                    return;
                }

                target.onEvent(new Event(Event.Type.COMMIT, "branch", mer));
            }

        }
        if(!(r instanceof Repository)) {
            r = new Repo();
        }
        return r;

    }

    public static WebHook mergeToBranchWebHook(String branchName){
        return new WebH(branchName, Event.Type.MERGE);
    }

    public static WebHook commitToBranchWebHook(String branchName){
        return new WebH(branchName, Event.Type.COMMIT);
    }

}

class WebH implements WebHook {
    private String branchName;
    private List<Event> list;
    private Event.Type ET;
    public WebH(String branchName, Event.Type type){
        this.branchName = branchName;
        this.list = new ArrayList<Event>();
        this.ET = type;
    }
    @Override
    public String branch() {
        return this.branchName;
    }

    @Override
    public Event.Type type() {
        return this.ET;
    }

    @Override
    public List<Event> caughtEvents() {
        return this.list;
    }

    @Override
    public void onEvent(Event event) {
        this.list.add(event);
    }
}
