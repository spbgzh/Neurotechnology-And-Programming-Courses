package com.epam.rd.autocode.observer.git;

public interface Repository {
    void addWebHook(WebHook webHook);

    Commit commit(String branch, String author, String[] changes);

    void merge(String sourceBranch, String targetBranch);

}
