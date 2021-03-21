package com.epam.rd.autocode.decorator;

import javax.crypto.spec.PSource;
import java.util.*;

public class Decorators {
    public static List<String> list;

    public static List<String> evenIndexElementsSubList(List<String> sourceList) {
        list = new ArrayList<>();
        for ( int i = 0; i < sourceList.size(); i++){
            if( i % 2==0 ) {
                list.add(sourceList.get(i));
            }
        }
        return list;
    }

}
