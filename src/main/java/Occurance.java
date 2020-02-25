package main.java;



import java.util.*;
import java.util.stream.Collectors;


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
        List<String> save = new ArrayList<String>();

        list = Arrays.asList(input.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+"));
        Collections.sort(list);

        int count;
        String word;

        List<String> deDupStringList = list.stream().distinct().collect(Collectors.toList());

        String[][] wordNCountNPercentage = new String[deDupStringList.size()][3];
        int index = 0;

        int totalCount = 0;
        for (int i = 0; i < list.size(); i++) {

            word = list.get(i).toLowerCase();

            if (!save.contains(word)) {
                count = Collections.frequency(list, word);
                save.add(word);
                wordNCountNPercentage[index][0] = word;
                wordNCountNPercentage[index][1] = Integer.toString(count);
                wordNCountNPercentage[index++][2] = Integer.toString(count);

                totalCount += count;
            }
        }


        for (int i = 0; i < wordNCountNPercentage.length; i++) {


            wordNCountNPercentage[i][2] =  Double.toString(Double.parseDouble(wordNCountNPercentage[i][1]) * 100 / totalCount );


            System.out.print(wordNCountNPercentage[i][0] + " ");
            System.out.print(wordNCountNPercentage[i][1] + " ");
            System.out.println(wordNCountNPercentage[i][2]);

        }


    }



>>>>>>> cd476c7dfde6a08a38f286fba0dcd634b5e2fb04
}
