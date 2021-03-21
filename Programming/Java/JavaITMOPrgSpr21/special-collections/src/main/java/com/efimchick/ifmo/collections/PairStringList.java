package com.efimchick.ifmo.collections;
import java.util.*;
public class PairStringList extends ArrayList<String> {

    @Override
    public String get(int index) {
        return super.get(index);
    }

    @Override
    public int size() {
        return super.size();
    }

    @Override
    public boolean add(String s) {
        return super.add(s) && super.add(s);
    }

    @Override
    public void add(int index, String element) {
        if (index % 2 == 0) {
            super.add(index, element);
            super.add(index + 1, element);
        }else {
            super.add(index+1, element);
            super.add(index+2, element);
        }
    }

    @Override
    public boolean remove(Object o) {
        return super.remove(o) && super.remove(o);
    }

    @Override
    public String remove(int index) {
        if (index % 2 == 0) {
            super.remove(index + 1);
            return super.remove(index);
        } else {
            super.remove(index);
            return super.remove(index - 1);
        }
    }

    @Override
    public String set(int index, String element) {
        if (index % 2 == 0) {
            super.set(index+1, element);
            return super.set(index, element);
        } else {
            super.set(index, element);
            return super.set(index-1, element);
        }
    }

    @Override
    public boolean addAll(Collection<? extends String> c) {
        for (String str: c){
            add(str);
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        int counter = 0;
        for (String str: c){
            add(index+counter, str);
            counter+=2;
        }
        return true;
    }

    @Override
    public Iterator<String> iterator() {
        return super.iterator();
    }
}
