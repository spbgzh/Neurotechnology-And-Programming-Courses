package com.epam.rd.autotasks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadFactory;

public interface ThreadUnion extends ThreadFactory {
    int totalSize();
    int activeSize();

    void shutdown();
    boolean isShutdown();
    void awaitTermination();
    boolean isFinished();

    List<FinishedThreadResult> results();

    static ThreadUnion newInstance(String name){
        return new MyThreadUnion(name);
    }
}

class MyThreadUnion implements ThreadUnion {

    String name; volatile int n=0; volatile List<Thread> allthr = new ArrayList<>(); volatile Map<Thread, Throwable> thth = new HashMap<>();
    volatile boolean isSD = false;

    public MyThreadUnion(String name) {
        this.name = name;
    }

    @Override
    public  int totalSize() {
        return n;
    }

    @Override
    public  int activeSize() {
        int acs=0;
        for(int i=0; i<allthr.size(); i++) {
            if(allthr.get(i).isAlive()) acs++;
        }
        return acs;
    }

    @Override
    public synchronized void shutdown() {
        for(int i=0; i<allthr.size(); i++) {
            allthr.get(i).interrupt();
        }
        isSD = true;
    }

    @Override
    public synchronized boolean isShutdown() {
        return isSD;
    }

    @Override
    public synchronized void awaitTermination() {
        for(int i=0; i<allthr.size(); i++) {
            try {
                allthr.get(i).join();
            } catch(InterruptedException e) {
            }
        }
    }

    @Override
    public synchronized boolean isFinished() {
        return isSD && (this.activeSize() == 0);
    }

    @Override
    public synchronized List<FinishedThreadResult> results() {
        List<FinishedThreadResult> ans = new ArrayList<>();
        for(int i=0; i<allthr.size(); i++) {
            if(!allthr.get(i).isAlive()) {
                if(thth.containsKey(allthr.get(i))) ans.add(new FinishedThreadResult(allthr.get(i).getName(), thth.get(allthr.get(i))));
                else ans.add(new FinishedThreadResult(allthr.get(i).getName()));
            }
        }
        return ans;
    }

    @Override
    public synchronized Thread newThread(Runnable r) {
        if(isSD) throw new IllegalStateException();
        Thread nth = new Thread(r, name + "-worker-" + Integer.toString(n++));
        nth.setUncaughtExceptionHandler((t, e) -> thth.put(t, e));
        allthr.add(nth);
        return nth;
    }
}
