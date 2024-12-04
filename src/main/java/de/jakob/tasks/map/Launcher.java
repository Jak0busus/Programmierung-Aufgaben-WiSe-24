package de.jakob.tasks.map;

import java.util.HashSet;
import java.util.Set;

public class Launcher {

    public static void main(String[] args){

        AbstractMap<String, Integer> map = new ArrayMap<>();
        putEntries(map);
        printMap(map);

        Set<String> set = new HashSet<>();
        set.add("unknown");
        try {
            map.getValuesAsSetOrThrow(set);
        } catch (UnknownKeyException e) {
            System.out.println("unknown key");
        }

    }

    private static void putEntries(AbstractMap<String, Integer> abstractMap){
        abstractMap.put("sizeInMB", 42);
        abstractMap.put("version", 4);
        abstractMap.put("yearOfRelease", 2015);
    }

    private static <K,V> void printMap(AbstractMap<K,V> abstractMap){
        for (K key : abstractMap.keysAsSet()){
            try {
                System.out.println(key + ":" + abstractMap.getOrThrow(key));
            } catch (UnknownKeyException e) {
                System.out.println("Fehler: Der Schlüssel \"" + key + "\" existiert nicht in der Map.");
            }
        }

    }

}
