package com.study.collection;

import java.util.HashMap;

/**
 * @author XH
 * @Package com.study.collection
 * @date 2020/2/26 12:08
 */
public class Map {
    public static void main(String[] args) {
        HashMap<String, Integer> hm = new HashMap<>();
        hm.put("xiaoming", 18);
        hm.put("xiaoming", 18);
        hm.put("xiaohong", 18);
        System.out.println(hm);
    }
}
