package com.epam.rd.autocode.observer.git;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GitRepoObserversTest {

    @Test
    public void readmeCase() {
        final Repository repo = GitRepoObservers.newRepository();

        final WebHook commitMasterWebHook = GitRepoObservers.commitToBranchWebHook("master");
        final WebHook commitReadmeWebHook = GitRepoObservers.commitToBranchWebHook("dev-readme");
        final WebHook mergeMasterBranch = GitRepoObservers.mergeToBranchWebHook("master");

        repo.addWebHook(mergeMasterBranch);
        repo.addWebHook(commitMasterWebHook);
        repo.addWebHook(commitReadmeWebHook);

        repo.commit(
                "dev-readme",
                "Johnny Mnemonic",
                new String[]{
                        "Added README.md",
                        "Added project description",
                });
        repo.commit(
                "dev-readme",
                "Johnny Mnemonic",
                new String[]{
                        "Added functional requirements",
                });
        repo.commit(
                "dev-readme",
                "Johnny Silverhand",
                new String[]{
                        "Added cyberanarchy manifest",
                });

        repo.merge("dev-readme", "master");

        assertEquals(
                "[]",
                commitMasterWebHook.caughtEvents().toString()
        );
        assertEquals(
                "[Event[COMMIT, dev-readme, [Commit[Johnny Mnemonic, [Added README.md, Added project description]]]]," +
                        " Event[COMMIT, dev-readme, [Commit[Johnny Mnemonic, [Added functional requirements]]]]," +
                        " Event[COMMIT, dev-readme, [Commit[Johnny Silverhand, [Added cyberanarchy manifest]]]]]",
                commitReadmeWebHook.caughtEvents().toString()
        );
        assertEquals(
                "[Event[COMMIT, branch, " +
                        "[Commit[Johnny Mnemonic, [Added README.md, Added project description]]," +
                        " Commit[Johnny Mnemonic, [Added functional requirements]]," +
                        " Commit[Johnny Silverhand, [Added cyberanarchy manifest]]]]]",
                mergeMasterBranch.caughtEvents().toString()
        );

    }

    @Test
    public void readmeCaseWithPreviousCommits() {
        final Repository repo = GitRepoObservers.newRepository();
        repo.commit(
                "dev-readme",
                "Johnny Mnemonic",
                new String[]{"Added README.txt"});
        repo.merge("dev-readme", "master");
        repo.commit(
                "master",
                "Johnny Mnemonic",
                new String[]{"Removed README.txt"});
        repo.merge("master", "dev-readme");

        final WebHook commitMasterWebHook = GitRepoObservers.commitToBranchWebHook("master");
        final WebHook commitReadmeWebHook = GitRepoObservers.commitToBranchWebHook("dev-readme");
        final WebHook mergeMasterBranch = GitRepoObservers.mergeToBranchWebHook("master");

        repo.addWebHook(mergeMasterBranch);
        repo.addWebHook(commitMasterWebHook);
        repo.addWebHook(commitReadmeWebHook);

        repo.commit(
                "dev-readme",
                "Johnny Mnemonic",
                new String[]{
                        "Added README.md",
                        "Added project description",
                });
        repo.commit(
                "dev-readme",
                "Johnny Mnemonic",
                new String[]{
                        "Added functional requirements",
                });
        repo.commit(
                "dev-readme",
                "Johnny Silverhand",
                new String[]{
                        "Added cyberanarchy manifest",
                });

        repo.merge("dev-readme", "master");

        assertEquals(
                "[]",
                commitMasterWebHook.caughtEvents().toString()
        );
        assertEquals(
                "[Event[COMMIT, dev-readme, [Commit[Johnny Mnemonic, [Added README.md, Added project description]]]]," +
                        " Event[COMMIT, dev-readme, [Commit[Johnny Mnemonic, [Added functional requirements]]]]," +
                        " Event[COMMIT, dev-readme, [Commit[Johnny Silverhand, [Added cyberanarchy manifest]]]]]",
                commitReadmeWebHook.caughtEvents().toString()
        );
        assertEquals(
                "[Event[COMMIT, branch, " +
                        "[Commit[Johnny Mnemonic, [Added README.md, Added project description]]," +
                        " Commit[Johnny Mnemonic, [Added functional requirements]]," +
                        " Commit[Johnny Silverhand, [Added cyberanarchy manifest]]]]]",
                mergeMasterBranch.caughtEvents().toString()
        );

    }


    @Test
    public void readmeCaseWithPreviousCommitsAndBackwardEmptyMerge() {
        final Repository repo = GitRepoObservers.newRepository();
        repo.commit(
                "dev-readme",
                "Johnny Mnemonic",
                new String[]{"Added README.txt"});
        repo.merge("dev-readme", "master");
        repo.commit(
                "master",
                "Johnny Mnemonic",
                new String[]{"Removed README.txt"});
        repo.merge("master", "dev-readme");

        final WebHook commitMasterWebHook = GitRepoObservers.commitToBranchWebHook("master");
        final WebHook commitReadmeWebHook = GitRepoObservers.commitToBranchWebHook("dev-readme");
        final WebHook mergeMasterBranch = GitRepoObservers.mergeToBranchWebHook("master");

        repo.addWebHook(mergeMasterBranch);
        repo.addWebHook(commitMasterWebHook);
        repo.addWebHook(commitReadmeWebHook);

        repo.commit(
                "dev-readme",
                "Johnny Mnemonic",
                new String[]{
                        "Added README.md",
                        "Added project description",
                });
        repo.commit(
                "dev-readme",
                "Johnny Mnemonic",
                new String[]{
                        "Added functional requirements",
                });
        repo.commit(
                "dev-readme",
                "Johnny Silverhand",
                new String[]{
                        "Added cyberanarchy manifest",
                });

        repo.merge("dev-readme", "master");
        repo.merge("master", "dev-readme");
        repo.merge("dev-readme", "master");


        assertEquals(
                "[]",
                commitMasterWebHook.caughtEvents().toString()
        );
        assertEquals(
                "[Event[COMMIT, dev-readme, [Commit[Johnny Mnemonic, [Added README.md, Added project description]]]]," +
                        " Event[COMMIT, dev-readme, [Commit[Johnny Mnemonic, [Added functional requirements]]]]," +
                        " Event[COMMIT, dev-readme, [Commit[Johnny Silverhand, [Added cyberanarchy manifest]]]]]",
                commitReadmeWebHook.caughtEvents().toString()
        );
        assertEquals(
                "[Event[COMMIT, branch, " +
                        "[Commit[Johnny Mnemonic, [Added README.md, Added project description]]," +
                        " Commit[Johnny Mnemonic, [Added functional requirements]]," +
                        " Commit[Johnny Silverhand, [Added cyberanarchy manifest]]]]]",
                mergeMasterBranch.caughtEvents().toString()
        );

    }

    @Test
    public void readmeCaseWithPreviousCommitsAndBackwardNonEmptyMerge() {
        final Repository repo = GitRepoObservers.newRepository();
        repo.commit(
                "dev-readme",
                "Johnny Mnemonic",
                new String[]{"Added README.txt"});
        repo.merge("dev-readme", "master");
        repo.commit(
                "master",
                "Johnny Mnemonic",
                new String[]{"Removed README.txt"});
        repo.merge("master", "dev-readme");

        final WebHook commitMasterWebHook = GitRepoObservers.commitToBranchWebHook("master");
        final WebHook commitReadmeWebHook = GitRepoObservers.commitToBranchWebHook("dev-readme");
        final WebHook mergeMasterBranch = GitRepoObservers.mergeToBranchWebHook("master");
        final WebHook mergeReadmeWebHook = GitRepoObservers.mergeToBranchWebHook("dev-readme");

        repo.addWebHook(mergeMasterBranch);
        repo.addWebHook(commitMasterWebHook);
        repo.addWebHook(commitReadmeWebHook);

        repo.commit(
                "dev-readme",
                "Johnny Mnemonic",
                new String[]{
                        "Added README.md",
                        "Added project description",
                });
        repo.commit(
                "dev-readme",
                "Johnny Mnemonic",
                new String[]{
                        "Added functional requirements",
                });
        repo.commit(
                "dev-readme",
                "Johnny Silverhand",
                new String[]{
                        "Added cyberanarchy manifest",
                });

        repo.merge("dev-readme", "master");
        repo.commit("master",
                "CrashOverrider",
                new String[]{"Added full implementation"});

        repo.addWebHook(mergeReadmeWebHook);

        repo.merge("master", "dev-readme");
        repo.merge("dev-readme", "master");


        assertEquals(
                "[Event[COMMIT, master, [Commit[CrashOverrider, [Added full implementation]]]]]",
                commitMasterWebHook.caughtEvents().toString()
        );
        assertEquals(
                "[Event[COMMIT, dev-readme, [Commit[Johnny Mnemonic, [Added README.md, Added project description]]]]," +
                        " Event[COMMIT, dev-readme, [Commit[Johnny Mnemonic, [Added functional requirements]]]]," +
                        " Event[COMMIT, dev-readme, [Commit[Johnny Silverhand, [Added cyberanarchy manifest]]]]]",
                commitReadmeWebHook.caughtEvents().toString()
        );
        assertEquals(
                "[Event[COMMIT, branch, " +
                        "[Commit[Johnny Mnemonic, [Added README.md, Added project description]]," +
                        " Commit[Johnny Mnemonic, [Added functional requirements]]," +
                        " Commit[Johnny Silverhand, [Added cyberanarchy manifest]]]]]",
                mergeMasterBranch.caughtEvents().toString()
        );

        assertEquals(
                "[Event[COMMIT, branch, [Commit[CrashOverrider, [Added full implementation]]]]]",
                mergeReadmeWebHook.caughtEvents().toString()
        );

    }
}