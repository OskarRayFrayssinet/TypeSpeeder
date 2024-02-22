package se.ju23.typespeeder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Patch {
    static long size = 10000;


    static List<Integer> alist = new ArrayList<>();
    static List<Integer> llist = new LinkedList<>();

    public static void main(String[] args) {


        for (int i = 0; i < size; i++) {
            alist.add(i);
            llist.add(i);
        }


        linkGet();
        linkRemove();
        arrGet();
        arrRemove();



    }

    private static void linkRemove() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            llist.remove(0);
        }
        System.out.println("LinkedList remove: " + (System.currentTimeMillis() - start));
    }

    private static void arrRemove() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            alist.remove(0);
        }
        System.out.println("ArrayList remove: " + (System.currentTimeMillis() - start));
    }

    private static void linkGet() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            int number = llist.get((int) (Math.random() * size));
        }
        System.out.println("LinkedList get: " + (System.currentTimeMillis() - start));
    }


    private static void arrGet() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            int number = alist.get((int) (Math.random() * size));
        }
        System.out.println("ArrayList get: " + (System.currentTimeMillis() - start));
    }



}
