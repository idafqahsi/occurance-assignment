package main.java;

import java.util.*;

public class Occurance {

    public static void main(String[] args) {
        Occurance occurance = new Occurance();

        String input = "In this assignment you need to use three different collections implementations to store and show sorted\n" +
                "version of the output for the given problem, including processing time each implementation has taken.\n" +
                "Your code should take an input file from the user and counts the number of different words in the file along\n" +
                "with the number of occurrences of each word. Subsequently, the output would be given as follow: Different\n" +
                "words in the document in alphabetical order. Occurrence number of each word. The percentage of word\n" +
                "occurrence in the document. You should implement this functionality using three different objects from\n" +
                "Collection API. Moreover, you should report the execution time for each collection object you have used. ";

        //   occurance.countWords(input);

        occurance.countUsingArrayList(input);
    }

    public void countWords(String input) {
        Map<String, String> map = new HashMap<String, String>();

        if (input != null) {
            String[] separatedWords = input.split(" ");
            for (String str : separatedWords) {
                if (map.containsKey(str)) {
                    int count = Integer.parseInt(map.get(str));
                    map.put(str, String.valueOf(count + 1));
                } else {
                    map.put(str, "1");
                }
            }
        }
        TreeMap<String, String> sorted = new TreeMap<>();

        // Copy all data from hashMap into TreeMap
        sorted.putAll(map);
        System.out.println("Count :- " + sorted);
    }


    public void countUsingArrayList(String input) {
        List<String> list = new ArrayList<String>();
        list = Arrays.asList(input.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+"));
        //String[] words = instring.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");

        List<String> save = new ArrayList<String>();

        Collections.sort(list);
        int count;
        String word;



        for (int i = 0; i < list.size(); i++) {

            word = list.get(i).toLowerCase();
            String[][] wordNCountNPercentage = new String[][];
            if (!save.contains(word)) {

                count = Collections.frequency(list, word);
                System.out.println("-" + word + " >  " + count);
                save.add(word);
            }

        }
    }















}
