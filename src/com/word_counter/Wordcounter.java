package com.word_counter;

import java.io.*;
import java.util.*;

/*
 * Approach:
 * read the file line by line
 * to get num of words in each line split with space
 * Then -- split the string with space and get size of the array and map with the line name.
 */

public class Wordcounter {

	public static void swap(int[] keys, int left, int right){
		keys[left] = keys[left]+keys[right];
		keys[right] = keys[left]-keys[right];
		keys[left] = keys[left]-keys[right];
	}

	public static void quicksort(LinkedHashMap<Integer,Integer> list, int[] keys, int left, int right){
		if(left>=right){
			return;
		}
		int pivot = list.get(keys[(left + right)/2]);
		int index = partition(list,keys,left,right,pivot);
		quicksort(list,keys,left,index-1);
		quicksort(list, keys, index, right);
	}

	public static int partition(LinkedHashMap<Integer,Integer> list, int[] keys, int left, int right, int pivot){
		while(left<=right){
			while(list.get(keys[left]) < pivot){
				left++;
			}
			while (list.get(keys[right]) > pivot){
				right--;
			}
			if(left<=right){
				swap(keys,left,right);
				left++;
				right--;
			}
		}
		return left;
	}

	public static LinkedHashMap<Integer, Integer> sort(LinkedHashMap<Integer, Integer> list,int size){
		System.out.println("in Sort");
		int big = 0;
		int key = 1;
		LinkedHashMap<Integer,Integer> ans = new LinkedHashMap<>();
		int[] keys = new int[size];
		for(int i = 0;i<size;i++){
			keys[i] = key++;
		}
		quicksort(list,keys,0,size-1);
		for(int i = 0;i<keys.length;i++){
			ans.put(keys[i],list.get(keys[i]));
		}
//		while(!list.isEmpty()){
//			for(int i : list.keySet()){
//				if(big < list.get(i)) {
//					big = list.get(i);
//					key = i;
//				}
//			}
//			ans.put(key,big);
//			list.remove(key);
//			big = 0;
//		}

		return ans;
	}


	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		FileReader fr = null;
		BufferedReader br = null;
		//LinkedHashMap<Integer,Integer> ans = new LinkedHashMap<Integer,Integer>();
		int i = 0;
		int size = 0;
		String line = null;
		String[] words = null;
		System.out.println("Enter filename along with complete file path (eg:$ pwd\\*.txt) :");
		String fileName = sc.next();
		//String fileName = "C:\\Users\\NAVEEN\\Desktop\\practice\\Intern-training\\file_generator\\words.txt";
		Date d = new Date();
		Linkedlist ll = new Linkedlist();
		try {
			long start = System.currentTimeMillis();
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);
			while((line = br.readLine())!=null) {
				size = 0;
				if(line != " ") {
					words = line.split(" ");
					size = words.length;
				}
				ll.add(++i,size);
			}
			System.out.println("Enter output filename along with complete file path (eg:$ pwd\\*.txt) :");
			String outfileName = sc.next();
			ll.dis(outfileName);
			long end = System.currentTimeMillis();
			System.out.println("Total Time taken : "+(end-start)+"ms");
		}catch (FileNotFoundException fe) {
			System.out.println("No File exist!!!"+fe.getMessage());
		} catch (IOException e) {
			System.out.println("Exception occured during buffered read "+e.getMessage());
		}
		finally {
			try {
				fr.close();
				br.close();
				sc.close();
			} catch (Exception e) {
				System.out.println("Exception occured during file close!!! "+e.getMessage());
			}
		}
	}
}


class Node{
	int key;
	int val;
	Node next;

	public Node(int key, int val){
		this.key = key;
		this.val = val;
		this.next = null;
	}
}
class Linkedlist{
	Node head;

	public void add(int key, int val) {
		try {
			if (head == null) {
				head = new Node(key, val);
				return;
			}
			if (head.val <= val) {
				Node newHead = new Node(key, val);
					newHead.next = head;
					head = newHead;
				return;
			}
			Node current = head;
			while (current != null) {
				if (current.next == null){
					current.next = new Node(key, val);
					return;
				}
				if (current.next.val <= val) {
					Node i = new Node(key, val);
					i.next = current.next;
					current.next = i;
					return;
				}
				current = current.next;
			}
		}catch(NullPointerException e){
			System.out.println("NullPointerException in key : " + key + " val : " + val);
		}
	}

	public void dis(String fileName){
		Node current = head;
		//String Filepath = "C:\\Users\\NAVEEN\\Desktop\\practice\\Intern-training\\file_generator\\";
		try{
			System.out.println("in Write");
			FileWriter fw=new FileWriter(fileName);
			fw.write("Word count in Descending Order\n");
			fw.write("LineNo\tWordCount\n");
			while(current != null){
				fw.write(current.key+"   \t   "+current.val+"\n");
				current = current.next;
			}
			fw.close();
		}catch(Exception e){System.out.println(e);}
		System.out.println("Success...");
	}
}