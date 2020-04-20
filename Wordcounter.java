package com.word_counter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/*
 * Approach:
 * read the file line by line
 * to get num of words in each line need to consider the types of word separators, 
 * lets say [' ',',','.'] -- but for now lets consider only space.
 * Then -- split the string with space and get size of the array and map with the line name.
 */

public class Wordcounter {

	public static void main(String[] args){
		FileReader fr = null;
		BufferedReader br = null;
		HashMap<Integer,Integer> ans = new HashMap<Integer,Integer>();
		int i = 0;
		int size = 0;
		String line = null;
		String[] words = null;
		try {
			fr = new FileReader("./src/Sample.txt");
			br = new BufferedReader(fr);
			while((line = br.readLine())!=null) {
				if(line != " ") {
					words = line.split(" ");
					size = words.length;
				}else {
					size = 0;
				}
				ans.put(i++, size);
				System.out.println(i+":"+size);
				size = 0;
			}
		}catch (FileNotFoundException fe) {
			System.out.println("No File exist!!!"+fe.getMessage());
		} catch (IOException e) {
			System.out.println("Exception occured during buffered read "+e.getMessage());
		}
		finally {
			try {
				fr.close();
				br.close();
			} catch (IOException e) {
				System.out.println("Exception occured during file close!!! "+e.getMessage());
			}
		}
	}
}
