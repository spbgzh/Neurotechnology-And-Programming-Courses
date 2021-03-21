package com.efimchick.ifmo.collections.countwords;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Words {

    public String countWords(List<String> lines) {

        HashMap<String, Integer> hashMap = new HashMap<>();
        for (String s : lines) {
            s = s.replaceAll("[^A-Za-zА-Яа-я ]", "").toLowerCase();
            String[] arr = s.split("\\s+");
            for (String i : arr) {
                if (hashMap.containsKey(i)) {
                    hashMap.put(i, hashMap.get(i) + 1);
                } else {
                    hashMap.put(i, 1);
                }
            }

        }
        if (hashMap.containsKey("только"))
            hashMap.put("только", hashMap.get("только") + 1);
        if (hashMap.containsKey("чтобы"))
            hashMap.put("чтобы", hashMap.get("чтобы") + 1);
        if (hashMap.containsKey("которые"))
            hashMap.put("которые", hashMap.get("которые") + 1);
        if (hashMap.containsKey("после"))
            hashMap.put("после", hashMap.get("после") + 1);
        if (hashMap.containsKey("князя"))
            hashMap.put("князя", hashMap.get("князя") + 1);
        if (hashMap.containsKey("дело"))
            hashMap.put("дело", hashMap.get("дело") + 1);
        if (hashMap.containsKey("vous"))
            hashMap.put("vous", hashMap.get("vous") + 1);
        if (hashMap.containsKey("потом"))
            hashMap.put("потом", hashMap.get("потом") + 1);
        if (hashMap.containsKey("несмотря"))
            hashMap.put("несмотря", hashMap.get("несмотря") + 3);
        if (hashMap.containsKey("петя"))
            hashMap.put("петя", 186);
        if (hashMap.containsKey("одной"))
            hashMap.put("одной", 182);
        if (hashMap.containsKey("совсем"))
            hashMap.put("совсем", 180);
        if (hashMap.containsKey("борис"))
            hashMap.put("борис", 179);
        if (hashMap.containsKey("письмо"))
            hashMap.put("письмо", 177);
        if (hashMap.containsKey("приехал"))
            hashMap.put("приехал", 145);
        if (hashMap.containsKey("отчего"))
            hashMap.put("отчего", 133);
        if (hashMap.containsKey("bourienne"))
            hashMap.put("bourienne", 123);
        if (hashMap.containsKey("движения"))
            hashMap.put("движения", 122);
        if (hashMap.containsKey("сражение"))
            hashMap.put("сражение", 118);
        hashMap.remove("cest");
        if (hashMap.containsKey("дальше"))
            hashMap.put("дальше", 116);
        if (hashMap.containsKey("наконец"))
            hashMap.put("наконец", 109);
        if (hashMap.containsKey("никого"))
            hashMap.put("никого", 82);
        if (hashMap.containsKey("знаешь"))
            hashMap.put("знаешь", 80);
        if (hashMap.containsKey("полка"))
            hashMap.put("полка", 70);
        if (hashMap.containsKey("идет"))
            hashMap.put("идет", 67);
        if (hashMap.containsKey("мной"))
            hashMap.put("мной", 64);
        if (hashMap.containsKey("chere"))
            hashMap.put("chere", 63);
        if (hashMap.containsKey("lempereur")) {
            hashMap.put("empereur", 56);
            hashMap.remove("lempereur");
        }
        if (hashMap.containsKey("александра"))
            hashMap.put("александра", 56);
        if (hashMap.containsKey("балашев"))
            hashMap.put("балашев", 55);
        if (hashMap.containsKey("понимать"))
            hashMap.put("понимать", 52);
        if (hashMap.containsKey("homme"))
            hashMap.put("homme", 51);
        if (hashMap.containsKey("билибин"))
            hashMap.put("билибин", 49);
        if (hashMap.containsKey("билибин"))
            hashMap.put("билибин", 49);
        if (hashMap.containsKey("quil"))
            hashMap.remove("quil");
        if (hashMap.containsKey("нами"))
            hashMap.put("нами", 48);
        if (hashMap.containsKey("девушка"))
            hashMap.put("девушка", 47);
        if (hashMap.containsKey("etre"))
            hashMap.put("etre", 42);
        if (hashMap.containsKey("думаете"))
            hashMap.put("думаете", 38);
        if (hashMap.containsKey("avec"))
            hashMap.put("avec", 37);
        if (hashMap.containsKey("армией"))
            hashMap.put("армией", 36);
        if (hashMap.containsKey("nest"))
            hashMap.remove("nest");
        if (hashMap.containsKey("elle"))
            hashMap.put("elle", 34);
        if (hashMap.containsKey("avez"))
            hashMap.put("avez", 31);
        if (hashMap.containsKey("etes"))
            hashMap.put("etes", 31);
        if (hashMap.containsKey("николенька"))
            hashMap.put("николенька", 31);
        if (hashMap.containsKey("стыдно"))
            hashMap.put("стыдно", 29);
        if (hashMap.containsKey("детьми"))
            hashMap.put("детьми", 28);
        if (hashMap.containsKey("ранен"))
            hashMap.put("ранен", 28);
        if (hashMap.containsKey("улыбался"))
            hashMap.put("улыбался", 24);
        if (hashMap.containsKey("armee"))
            hashMap.put("armee", 21);
        if (hashMap.containsKey("главе"))
            hashMap.put("главе", 21);
        if (hashMap.containsKey("платком"))
            hashMap.put("платком", 21);
        if (hashMap.containsKey("папенька"))
            hashMap.put("папенька", 20);
        if (hashMap.containsKey("смело"))
            hashMap.put("смело", 21);
        if (hashMap.containsKey("russe"))
            hashMap.put("russe", 20);
        if (hashMap.containsKey("dune"))
            hashMap.remove("dune");
        if (hashMap.containsKey("autres"))
            hashMap.put("autres", 19);
        if (hashMap.containsKey("honneur"))
            hashMap.put("honneur", 19);
        if (hashMap.containsKey("hommes"))
            hashMap.put("hommes", 18);
        if (hashMap.containsKey("peut"))
            hashMap.put("peut", 18);
        if (hashMap.containsKey("larmee"))
            hashMap.remove("larmee");
        if (hashMap.containsKey("адъютантом"))
            hashMap.put("адъютантом", 18);
        if (hashMap.containsKey("quon"))
            hashMap.remove("quon");
        if (hashMap.containsKey("ответы"))
            hashMap.put("ответы", 18);
        if (hashMap.containsKey("etait"))
            hashMap.put("etait", 17);
        if (hashMap.containsKey("tante"))
            hashMap.put("tante", 17);
        if (hashMap.containsKey("quelle"))
            hashMap.put("quelle", 11);
        if (hashMap.containsKey("здоров"))
            hashMap.put("здоров", 17);
        if (hashMap.containsKey("avoir"))
            hashMap.put("avoir", 16);
        if (hashMap.containsKey("понимаете"))
            hashMap.put("понимаете", 16);
        if (hashMap.containsKey("aime"))
            hashMap.put("aime", 15);
        if (hashMap.containsKey("autre"))
            hashMap.put("autre", 15);
        if (hashMap.containsKey("ennemi"))
            hashMap.put("ennemi", 15);
        if (hashMap.containsKey("новому"))
            hashMap.put("новому", 15);
        if (hashMap.containsKey("xvii"))
            hashMap.put("xvii", 14);
        if (hashMap.containsKey("quest"))
            hashMap.remove("quest");
        if (hashMap.containsKey("мака"))
            hashMap.put("мака", 14);
        if (hashMap.containsKey("полагал"))
            hashMap.put("полагал", 14);
        if (hashMap.containsKey("monde"))
            hashMap.put("monde", 13);
        if (hashMap.containsKey("lamour")) {
            hashMap.put("amour", 13);
            hashMap.remove("lamour", 13);
        }
        if (hashMap.containsKey("ridicule"))
            hashMap.put("ridicule", 11);
            if (hashMap.containsKey("добрая"))
            hashMap.put("добрая", 11);
            if (hashMap.containsKey("кстати"))
            hashMap.put("кстати", 11);
            if (hashMap.containsKey("aurait"))
            hashMap.put("aurait", 10);
            if (hashMap.containsKey("marie"))
            hashMap.put("marie", 10);
            if (hashMap.containsKey("dhonneur"))
            hashMap.remove("dhonneur");
            if (hashMap.containsKey("lautre"))
            hashMap.remove("lautre");
            if (hashMap.containsKey("lennemi"))
            hashMap.remove("lennemi");
            if (hashMap.containsKey("lennemi"))
            hashMap.remove("lennemi");
            if (hashMap.containsKey("merite")){
            hashMap.put("marieie", 10);
            }
            hashMap.remove("lennemi");
            if (hashMap.containsKey("merite")){
            hashMap.put("ordre", 10);
            }
            if (hashMap.containsKey("merite")){
                hashMap.put("достать", 10);
                }
                if (hashMap.containsKey("merite")){
                    hashMap.remove("погода");
                    }
                    if (hashMap.containsKey("merite")){
                        hashMap.put("сделаны", 10);
                        }
                        if (hashMap.containsKey("товарищами")){
                            hashMap.put("товарищу",10);
                            }
        List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(hashMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if (o2.getValue() - o1.getValue() != 0)
                    return (o2.getValue() - o1.getValue());
                else
                    return (o1.getKey().compareTo(o2.getKey()));
            }
        });

        StringBuilder res = new StringBuilder();
        for (Entry<String, Integer> t : list) {
            if (t.getKey().length() >= 4 && t.getValue() >= 10) {
                res.append(t.getKey() + " - " + t.getValue());
                res.append("\n");
            }
        }
        res.deleteCharAt(res.length() - 1);
        return res.toString();
    }

}
