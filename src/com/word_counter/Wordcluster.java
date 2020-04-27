package com.word_counter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.TreeMap;

public class Wordcluster {

    public static void main(String[] args) {

        String fileName = "C:\\Users\\NAVEEN\\Desktop\\practice\\Intern-training\\file_generator\\words.txt";
        Scanner sc = new Scanner(System.in);
        String words[];
        TreeMap<String,Integer> cls = new TreeMap<String, Integer>();
        TreeMap<String,Integer> cls1 = new TreeMap<String, Integer>();
        String temp;
        try {
            String line;
            String content = "";
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            while((line = br.readLine())!=null) {
                if(line != " ") {
                    words = line.split(" ");
                   /* for(int i = 0;i<size-3;i++){
                        temp = words[i]+" "+words[i+1]+" "+words[i+2];
                        if(cls.containsKey(temp)){
                            cls.replace(temp,cls.get(temp),cls.get(temp)+1);
                        }else{
                            cls.put(temp,1);
                        }
                    }*/
                    for(String s:words){
                        if(cls.containsKey(s)){
                            cls.replace(s,cls.get(s),cls.get(s)+1);
                        }else{
                            cls.put(s,1);
                        }
                    }
                }
            }
//            cls.forEach((k,v) -> System.out.println("key : "+k+"val : "+v));
            System.out.println("Enter output filename along with complete file path (eg:$ pwd\\*.txt) for frequency of each word :");
            String outfileName = sc.next();
            dis(outfileName,cls);
            System.out.println("Enter output filename along with complete file path (eg:$ pwd\\*.txt) for frequency of 3 words :");
            String outfileName1 = sc.next();
            dis(outfileName1,cls1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void dis(String fileName,TreeMap<String,Integer> cls){
       // String Filepath = "C:\\Users\\NAVEEN\\Desktop\\practice\\Intern-training\\file_generator\\";
        try{
            System.out.println("in Write");
            FileWriter fw=new FileWriter(fileName);
            fw.write("Word count in Descending Order\n");
            fw.write("WordCluster\tCount\n");
            cls.forEach((k,v) -> {
                try {
                    fw.write(k+"   \t   "+v+"\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            fw.close();
        }catch(Exception e){System.out.println(e);}
        System.out.println("Success...");
    }
}
