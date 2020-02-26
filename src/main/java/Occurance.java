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

public class Occurance {
	
	
	static void countEachWords(String fileName,Map<String,Integer> words) throws FileNotFoundException , IOException {
		Scanner file = new Scanner(new File(fileName));
		ArrayList<String> stopwords = (ArrayList<String>) Files.readAllLines(Paths.get("C:\\Users\\George Zummar\\Desktop\\stop_words.txt"));
		while(file.hasNext()) {
			String word = file.next();
			 word = word.toLowerCase(); 
			 word = word.replaceAll("[^a-zA-Z0-9]", "");
			 for (int i = 0 ; i < stopwords.size(); i++){
			 	if (word.equalsIgnoreCase(stopwords.get(i))){
					word = word.replaceAll(stopwords.get(i), "");
				}
			 }

			Integer count = words.get(word);
			if (count != null) 
				count++;
			else 
				count = 1; 
				words.put(word,count);
			
			
		}
		file.close();
	}
	
	public static void main (String[] args) throws IOException {
		Instant start = Instant.now();
		Map<String,Integer> map = new HashMap<String,Integer>();
		countEachWords("C:\\Users\\George Zummar\\Desktop\\test.txt",map);
		Map<String, Integer> words = new TreeMap<String , Integer>(map);




		double count = 0;
		double percentage = 0;
		for ( Map.Entry<String, Integer> entry : words.entrySet()) { // i calculate total count here

		    Integer value = entry.getValue();
		    count += value;
		    

		}
		System.out.println(count);
		for ( Map.Entry<String, Integer> entry : words.entrySet()) { // here i display occurancy with precentage
		    String key = entry.getKey();
		    double value = entry.getValue();
		    percentage = value * 100 / count;
			double percentage2 = Math.round(percentage);


		    if (percentage2 >= 2) {
		    	System.out.println(key + " : " + (int) value + " "+ percentage2 + "%" );
			}
			
		}
		Instant finish = Instant.now();
		
		long timeElapsed = Duration.between(start, finish).toMillis();
		System.out.println("Execution time using hashmap : "+timeElapsed + " Milliseconds");
		
	}
	
	
	
	/*
	 * public static void main(String[] args) { Occurance occurance = new
	 * Occurance();
	 * 
	 * String input =
	 * "In this assignment you need to use three different collections implementations to store and show sorted\n"
	 * +
	 * "version of the output for the given problem, including processing time each implementation has taken.\n"
	 * +
	 * "Your code should take an input file from the user and counts the number of different words in the file along\n"
	 * +
	 * "with the number of occurrences of each word. Subsequently, the output would be given as follow: Different\n"
	 * +
	 * "words in the document in alphabetical order. Occurrence number of each word. The percentage of word\n"
	 * +
	 * "occurrence in the document. You should implement this functionality using three different objects from\n"
	 * +
	 * "Collection API. Moreover, you should report the execution time for each collection object you have used. "
	 * ;
	 * 
	 * // occurance.countWords(input);
	 * 
	 * occurance.countUsingArrayList(input); }
	 */

	/*
	 * public void countWords(String input) { Map<String, String> map = new
	 * HashMap<String, String>();
	 * 
	 * if (input != null) { String[] separatedWords = input.split(" "); for (String
	 * str : separatedWords) { if (map.containsKey(str)) { int count =
	 * Integer.parseInt(map.get(str)); map.put(str, String.valueOf(count + 1)); }
	 * else { map.put(str, "1"); } } } TreeMap<String, String> sorted = new
	 * TreeMap<>();
	 * 
	 * // Copy all data from hashMap into TreeMap sorted.putAll(map);
	 * System.out.println("Count :- " + sorted); }
	 */

	public void countUsingArrayList(String input) {
		List<String> list = new ArrayList<String>();
		list = Arrays.asList(input.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+"));
		// String[] words = instring.replaceAll("[^a-zA-Z ]",
		// "").toLowerCase().split("\\s+");

		List<String> save = new ArrayList<String>();

		Collections.sort(list);
		int count;
		String word;

		for (int i = 0; i < list.size(); i++) {

			word = list.get(i).toLowerCase();
			//String[][] wordNCountNPercentage = new String[][];
			if (!save.contains(word)) {

				count = Collections.frequency(list, word);
				System.out.println("-" + word + " >  " + count);
				save.add(word);
			}

		}

	}
}
