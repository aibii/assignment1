import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        ArrayList<String> words = readWords("res/words.txt"); //step 4
        HashMap<String, Integer> wordCounter = buildHashMap(words); //step 5
        createHTMLFile(wordCounter);
        ArrayList<WordFrequency> wordFrequencyList = new ArrayList<>();
        for(String key: wordCounter.keySet()){
            WordFrequency wordFrequency = new WordFrequency(key, wordCounter.get(key));
            wordFrequencyList.add(wordFrequency);
        }
    
        Collections.sort(wordFrequencyList);
    
        for(WordFrequency wordFrequency: wordFrequencyList){
            System.out.println("List: "+ wordFrequency.getWord() + " " + wordFrequency.getWordCount() );
        }
        createSortedHTMLFile(wordFrequencyList);
    }

    //Step 4 Read input file
    /**
     * Reads words from a file and puts them into an array
     * @param fileName The name of the input file
     * @return Populated array, containing words
     */
    private static ArrayList<String> readWords(String fileName){
        File file = new File(fileName);
        ArrayList<String> wordList = new ArrayList<>();

        try {
            FileReader reader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = bufferedReader.readLine();
            while(line != null) {
                String[] words = line.split("[ .,]+"); //if you come across that character or more than that - split it
                for(String word: words)
                {
                    if(word.trim().length() > 0)
                    {
                        wordList.add(word.toLowerCase());
                    }
                }
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
            
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return wordList;
    }

    /**
     * Defines the number of occurence for each word and fetches that value to the word
     * @param words Receives an array of words
     * @return Hashmap with word key and number of its occurence as a value pair
     */
    private static HashMap<String, Integer> buildHashMap(ArrayList<String> words) {
        HashMap<String, Integer> wordCounter = new HashMap<>();
        for(String word: words) {
            Integer count = wordCounter.get(word);
            if(count == null)
            {
                wordCounter.put(word, 1);
            }
            else
            {
                wordCounter.put(word, count + 1);
            }
        }
        return wordCounter;
    }

    //Step 6: Create output HTML file
    /**
     * Creates output HTML file, that displays a list of every word and the number of its appearance
     * @param wordCounter hashmap with word key and its occurence number as a value pair
     */
    private static void createHTMLFile(HashMap<String, Integer> wordCounter){
    File file = new File("res/words.html");

    try {
        FileWriter fileWriter = new FileWriter(file);
        StringBuilder builder = new StringBuilder();
            final String css = "<style>"
                      + " td,th { border: dashed} "
                      + " table, td, th { border-collapse: collapse}"
                      + "</style>" ;
        builder.append(css).append("\n");
        builder.append("<h1>Word Count</h1>");

        builder.append("<table>");
        for(String key: wordCounter.keySet()){
            builder.append("<tr>");
            builder.append("<td>" + key + "</td>");
            builder.append("<td>" + wordCounter.get(key) + "</td>");
            builder.append("<tr>");
        }
        builder.append("</table>");
        fileWriter.append(builder.toString());
        fileWriter.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
    for(String keyWord: wordCounter.keySet()){
        System.out.println(keyWord + ": " + wordCounter.get(keyWord));
    }
    }

    /**
     * Creates output HTML file, that displays a list of every word and the number of its occurence in an ascending order
     * @param wordFrequencyList Receives an arraylist of WordFrequency objects
     */
    private static void createSortedHTMLFile(ArrayList<WordFrequency> wordFrequencyList) {
        File file = new File("res/sorted.html");

        try {
            FileWriter fileWriter = new FileWriter(file);
            StringBuilder builder = new StringBuilder();
                final String css = "<style>"
                          + " td,th { border: dashed} "
                          + " table, td, th { border-collapse: collapse}"
                          + "</style>" ;
            builder.append(css).append("\n");
            builder.append("<h1>Word Count</h1>");
    
            builder.append("<table>");

            for(WordFrequency wordFrequency: wordFrequencyList)
            {
                builder.append("<tr>");
                builder.append("<td>" + wordFrequency.getWord() + "</td>");
                builder.append("<td>" + wordFrequency.getWordCount() + "</td>");
                builder.append("<tr>");
            }
            builder.append("</table>");
            fileWriter.append(builder.toString());
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}