package com.company;
import java.util.*;
import java.io.*;

//Class to read a .txt file and print the most common word
//Ignores (some) articles
//To do: add different data structures
public class Main {

    private static final String[] articles = {"a", "an", "the"};

    public static void main(String[] args) throws FileNotFoundException {

        //Finds most common word and longest word
        Scanner s = new Scanner(System.in);
        System.out.print("What is your name? ");
        String name = capitalizeName(s.nextLine());
        File book = new File("C:/Users/arjun/Documents/UW/CompSci/test.txt");
        Scanner textFile = new Scanner(book);
        Map<String, Integer> wordOccurrenceMap = wordOccurrences(textFile);
        System.out.print("Hello " + name + ". " + "The most common word in your .txt file is " + "'" + mostCommonWord(wordOccurrenceMap) + "'.");
        Scanner textFile2 = new Scanner(book);
        Map<String, Integer> wordLengthMap = wordLength(textFile2);
        System.out.println();
        System.out.print("The longest word in your .txt file is " + "'" + longestWord(wordLengthMap) + "'.");
        Scanner textFile3 = new Scanner(book);
        System.out.println();

        //Prints every word and its number of occurrences
        System.out.print("Would you like to see the number of occurrences of all words in the .txt file? (y/n) ");
        String a = s.nextLine();
        if (a.equalsIgnoreCase("y")) {
            printWords(wordOccurrenceMap);
        }

        //User searches for word
        System.out.print("Would you like to search for words in the .txt file ? (y/n) ");
        String a1 = s.nextLine();
        if (a1.equalsIgnoreCase("y")) {
            System.out.print("Please enter the word you would like to search for: ");
            String search = s.nextLine();
            System.out.print("The word you searched for," + "'" + search + "', appears " + findWordOccurrences(textFile3, search) + " time(s).");
            System.out.println();
            System.out.print("Would you like to search for another word? (y/n) ");
            String answer = s.nextLine();
            while (answer.equalsIgnoreCase("y")) {
                Scanner tempScanner = new Scanner(book);
                System.out.print("Please enter the word you would like to search for: ");
                String tempSearch = s.nextLine();
                System.out.print("The word you searched for," + "'" + tempSearch + "', appears " + findWordOccurrences(tempScanner, tempSearch) + " time(s).");
                System.out.println();
                System.out.print("Would you like to search for another word? (y/n) ");
                answer = s.nextLine();
            }
        }
        System.exit(0);
    }

    //Accepts a Scanner as a parameter
    //Method returns a map of each unique String in the input with its number of occurrences as a value
    //Ignores articles
    public static Map<String, Integer> wordOccurrences(Scanner input)
    {
        Map<String, Integer> newMap = new HashMap<>();
        while (input.hasNext())
        {
            String word = input.next().trim().toLowerCase().replaceAll("[^a-zA-Z]","");
            boolean contains = Arrays.stream(articles).anyMatch(word::equals);
            if (!contains)
            {
                if (!newMap.containsKey(word)) {
                    newMap.put(word, 1);
                } else {
                    newMap.put(word, newMap.get(word) + 1);
                }
            }
        }
        return newMap;
    }

    //Method accepts map as a parameter and returns the most frequently occurring word
    //In the case of a tie: returns most recent
    public static String mostCommonWord(Map<String, Integer> map)
    {
        Set<String> words = map.keySet();
        int max = 0;
        String word = "";
        for (String i : words)
        {
            int num = map.get(i);
            if (num >= max)
            {
                max = num;
                word = i;
            }
        }
        return checkI(word);
    }

    //Method accepts Scanner as a parameter and returns a map of each unique String in the scanner with its length as a value
    public static Map<String, Integer> wordLength(Scanner input)
    {
        Map<String, Integer> wordMap = new HashMap<>();
        while (input.hasNext())
        {
            String word = input.next().trim().toLowerCase().replaceAll("[^a-zA-Z]","");
            if (!wordMap.containsKey(word)) {
                wordMap.put(word, word.length());
            }
        }
        return wordMap;
    }

    //Method accepts map as a parameter and returns the longest word
    //In the case of a tie: returns most recent
    public static String longestWord(Map<String, Integer> map)
    {
        Set<String> keys = map.keySet();
        int max = 0;
        String longest = "";

        for (String i : keys)
        {
            int num = map.get(i);
            if (num >= max)
            {
                max = num;
                longest = i;
            }
        }
        return checkI(longest);
    }

    //Method that takes a Scanner and a String and returns the number of times that String appears in the Scanner
    public static int findWordOccurrences(Scanner input, String search)
    {
        search = search.trim().toLowerCase();
        Map<String, Integer> newMap = wordOccurrences(input);

        if (newMap.containsKey(search))
        {
            return newMap.get(search);
        }
        else
        {
            return 0;
        }
    }

    //Converts string from "i" to "I"
    public static String checkI(String i)
    {
        if (i.equals("i"))
        {
            return "I";
        }
        else
        {
            return i;
        }
    }

    //Capitalizes the first letter of the user's name
    public static String capitalizeName(String name)
    {
        int length = name.length();
        String firstLetter = name.substring(0,1);
        firstLetter = firstLetter.toUpperCase();
        name = firstLetter + name.substring(1);
        return name;
    }

    public static void printWords(Map<String, Integer> wordMap)
    {
        Set<String> keys = wordMap.keySet();
        for (String key : keys)
        {
            System.out.print(key + ": " + wordMap.get(key) + " time(s)");
            System.out.println();
        }
    }


}
