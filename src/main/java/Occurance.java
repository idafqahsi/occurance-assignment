package main.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import java.text.ParseException;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Occurance {


    public static void countEachWords(String fileName, Map<String, Integer> words) throws FileNotFoundException, IOException {
        Scanner file = new Scanner(new File(fileName));
        ArrayList<String> stopwords = (ArrayList<String>) Files.readAllLines(Paths.get("stop_words.txt"));
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

    public static void occuranceUsingHash() throws IOException {
        Map<String, Integer> map = new HashMap<String, Integer>();
        countEachWords("test.txt", map);
        Map<String, Integer> words = new TreeMap<String, Integer>(map);

        double count = 0;
        double percentage = 0;
        for (Map.Entry<String, Integer> entry : words.entrySet()) { // i calculate total count here

            Integer value = entry.getValue();
            count += value;


        }
        System.out.println(count);
        for (Map.Entry<String, Integer> entry : words.entrySet()) { // here i display occurancy with precentage
            String key = entry.getKey();
            double value = entry.getValue();
            percentage = value * 100 / count;
            double percentage2 = Math.round(percentage);


            if (percentage2 >= 2) {
                System.out.println(key + " : " + (int) value + " " + percentage2 + "%");
            }

        }
    }

    public static void main(String[] args) throws IOException {
        Instant start = Instant.now();
        occuranceUsingHash();
        Instant finish = Instant.now();

        long timeElapsed = Duration.between(start, finish).toMillis();
        System.out.println("Execution time using hashmap : " + timeElapsed + " Milliseconds");

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


            wordNCountNPercentage[i][2] = Double.toString(Double.parseDouble(wordNCountNPercentage[i][1]) * 100 / totalCount);


            System.out.print(wordNCountNPercentage[i][0] + " ");
            System.out.print(wordNCountNPercentage[i][1] + " ");
            System.out.println(wordNCountNPercentage[i][2]);

        }


    }

}
