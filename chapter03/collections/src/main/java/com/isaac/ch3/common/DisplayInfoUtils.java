package com.isaac.ch3.common;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class DisplayInfoUtils {
    public static void displayInfo(Map<String, Object> map, Properties props, Set<Object> set, List<Object> list) {
        System.out.println("Map contents:\n");
        map.forEach((key, value) -> System.out.println("Key: " + key + " - Value: " + value));

        System.out.println("\nProperties contents:\n");
        props.forEach((key, value) -> System.out.println("Key: " + key + " - Value: " + value));

        System.out.println("\nSet contents:\n");
        set.forEach(obj -> System.out.println("Value: " + obj));

        System.out.println("\nList contents:\n");
        list.forEach(obj -> System.out.println("Value: " + obj));
    }
}
