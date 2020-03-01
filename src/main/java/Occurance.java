package main.java;
/**
 * Occurancy of words Assignment
 *
 * @author  George Zummar & Fadi Ishaq
 * @since   1/3/2020
 * @teacher Anas Samara
 *
 * Git-Hub work.
 * https://github.com/idafqahsi/occurance-assignment
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

public class Occurance {

    /////////////////// MAIN CLASS /////////////////////////////
    public static void main(String[] args) {
        Occurance occurance = new Occurance();

        String filename = "test.txt";
        String stopWords = "stop_words.txt";

        System.out.println("____________ ArrayList_________________");
        execTimeArraylist(filename, stopWords);
        System.out.println("_____________HashSet___________________");
        execTimeHashSet(filename, stopWords);
        System.out.println("_____________HashMap___________________");
        execTimeHashMap(filename, stopWords);
    }
    /////////////////// Calculate Time for ArrayList Method /////////////////////////////
    public static void execTimeArraylist(String filename, String stopWords) {
        Instant startAList = Instant.now();
        try {
            occuranceUsingArrayList(filename, stopWords);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Instant finishAList = Instant.now();
        long timeElapsedAList = Duration.between(startAList, finishAList).toMillis();
        System.out.println("Execution time using ArrayList : " + timeElapsedAList + " Milliseconds");
    }
    /////////////////// Calculate Time for HashMap Method /////////////////////////////
    public static void execTimeHashMap(String filename, String stopWords) {
        Instant startAList = Instant.now();
        try {
            occuranceUsingHashMap(filename, stopWords);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Instant finishAList = Instant.now();
        long timeElapsedAList = Duration.between(startAList, finishAList).toMillis();
        System.out.println("Execution time using Hash Map : " + timeElapsedAList + " Milliseconds");
    }
    /////////////////// Calculate Time for HashSet Method /////////////////////////////
    public static void execTimeHashSet(String filename, String stopWords) {
        Instant startAList = Instant.now();
        try {
            occuranceUsingHashSet(filename, stopWords);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Instant finishAList = Instant.now();
        long timeElapsedAList = Duration.between(startAList, finishAList).toMillis();
        System.out.println("Execution time using Hash Set : " + timeElapsedAList + " Milliseconds");
    }
    /////////////////// Implementation of ArrayList Method /////////////////////////////
    public static void occuranceUsingArrayList(String fileName, String stopWords) throws IOException {

        List<String> list = new ArrayList<String>();
        ArrayList<String> stopwords = (ArrayList<String>) Files.readAllLines(Paths.get(stopWords));




        Scanner file = new Scanner(new File(fileName));
        while (file.hasNext()) {

            String word = file.next();
            word = word.toLowerCase();
            word = word.replaceAll("[^a-zA-Z0-9]", "");

            boolean found = false;

            for (int i = 0; i < stopwords.size(); i++) {
                if (word.equalsIgnoreCase(stopwords.get(i))) {
                    found = true;
                }
            }

            if (!found) {
                list.add(word);
            }
        }

        file.close();
//////////

        // List<String> list = Arrays.asList(input.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+"));
        List<String> save = new ArrayList<String>();
        Collections.sort(list);

        int count;
        String word;

        List<String> deDupStringList = list.stream().distinct().collect(Collectors.toList());
        String[][] wordNCountNPercentage = new String[deDupStringList.size()][3];

        int index = 0;
        int totalCount = 0;

        for (int i = 0; i < list.size(); i++) {

            word = list.get(i);
            word = word.replaceAll("[^a-zA-Z0-9]", "");
            word = word.toLowerCase();
            word = word.trim();

            if (!save.contains(word)) {
                count = Collections.frequency(list, word);
                save.add(word);
                wordNCountNPercentage[index][0] = word;
                wordNCountNPercentage[index][1] = Integer.toString(count);
                wordNCountNPercentage[index++][2] = Integer.toString(count);

                totalCount += count;
            }
        }
        System.out.println("Number of words without stop words : "+(int)totalCount);

        for (int i = 0; i < wordNCountNPercentage.length; i++) {
            double percentage = Math.round(Double.parseDouble(wordNCountNPercentage[i][1]) * 100 / totalCount);
            ;
            wordNCountNPercentage[i][2] = Double.toString(percentage);

            if (percentage > 2) {
                System.out.print(wordNCountNPercentage[i][0] + " ");
                System.out.print(wordNCountNPercentage[i][1] + " ");
                System.out.println(wordNCountNPercentage[i][2] + "%");
            }
        }


    }
    /////////////////// Implementation of HashMap Method /////////////////////////////
    public static void occuranceUsingHashMap(String filename, String stopwords) throws IOException {
        Map<String, Integer> map = new HashMap<String, Integer>();
        countEachWords(filename, map, stopwords);
        Map<String, Integer> words = new TreeMap<String, Integer>(map);

        double count = 0;
        double percentage = 0;
        for (Map.Entry<String, Integer> entry : words.entrySet()) { // i calculate total count here

            Integer value = entry.getValue();
            String key = entry.getKey();
            if (!key.equalsIgnoreCase("")) {
                count += value;
            }

        }
        System.out.println("Number of words without stop words : "+(int)count);
        for (Map.Entry<String, Integer> entry : words.entrySet()) { // here i display occurancy with precentage
            String key = entry.getKey();
            double value = entry.getValue();
            percentage = value * 100 / count;
            double percentage2 = Math.round(percentage);


            if (percentage2 > 2 && !key.equalsIgnoreCase("")) {
                System.out.println(key + " : " + (int) value + " " + percentage2 + "%");
            }

        }
    }
    /////////////////// Count Method for HashMap /////////////////////////////
    public static void countEachWords(String fileName, Map<String, Integer> words, String stopFilename) throws FileNotFoundException, IOException {
        Scanner file = new Scanner(new File(fileName));
        ArrayList<String> stopwords = (ArrayList<String>) Files.readAllLines(Paths.get(stopFilename));
        while (file.hasNext()) {
            String word = file.next();
            word = word.toLowerCase();
            word = word.replaceAll("[^a-zA-Z0-9]", "");
            for (int i = 0; i < stopwords.size(); i++) {
                if (word.equalsIgnoreCase(stopwords.get(i))) {
                    word = word.replaceAll(stopwords.get(i), "");
                }
            }

            Integer count = words.get(word);
            if (count != null)
                count++;
            else
                count = 1;
            words.put(word, count);


        }
        file.close();
    }
    /////////////////// Implementation of HashSet Method /////////////////////////////
    public static void occuranceUsingHashSet(String fileName, String stopWords) throws IOException {
        ArrayList<String> list = new ArrayList<String>();
        Scanner file = new Scanner(new File(fileName));
        ArrayList<String> stopwords = (ArrayList<String>) Files.readAllLines(Paths.get(stopWords));
        double percentage = 0;

        while (file.hasNext()) {
            String word = file.next();
            word = word.toLowerCase();
            word = word.replaceAll("[^a-zA-Z0-9]", "");
            for (int i = 0; i < stopwords.size(); i++) {
                if (word.equalsIgnoreCase(stopwords.get(i))) {
                    word = word.replaceAll(stopwords.get(i), "");
                }
            }

            list.add(word);


        }
        Set<String> sb = new HashSet<String>(list);
        Set<String> st = new TreeSet<String>(sb);
        //Map<String, Integer> words = new TreeMap<String, Integer>(map);

        double count = 0;
        for (String s : st) {
            if (!s.equalsIgnoreCase("")) {
                count += Collections.frequency(list, s);
            }
        }
        System.out.println("Number of words without stop words : "+(int)count);
        for (String ss : st) {
            double value = Collections.frequency(list, ss);
            percentage = value * 100 / count;
            double percentage2 = Math.round(percentage);
            if (percentage2 >= 3 && !ss.equalsIgnoreCase("")) {
                System.out.println(ss + ": " + (int) value + " " + (int) percentage2 + "%");
            }
        }
    }


}
