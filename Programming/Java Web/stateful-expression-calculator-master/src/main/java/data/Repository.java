package data;

import java.util.concurrent.ConcurrentHashMap;

public class Repository {
    public static final Repository SINGLETON_REP = new Repository();

    private Repository() {
    }

    private final ConcurrentHashMap<String, ConcurrentHashMap<String, String>> repMap = new ConcurrentHashMap<>();

    public ConcurrentHashMap<String, ConcurrentHashMap<String, String>> getParametersData() {
        return repMap;
    }
}