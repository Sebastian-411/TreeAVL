package ui;

import java.io.*;
import java.util.ArrayList;
import model.*;
import java.util.Scanner;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    static String folder = "testcase";
    static String[] path = {"testcase/in1.txt","testcase/in2.txt","testcase/in3.txt","testcase/in4.txt","testcase/in5.txt"};


    private static TreeAVL<Integer, Integer>AVL;
    public static void main(String[] args) {
        ArrayList<Integer> operations = null;
        for (int j = 0; j < 5  ; j++) {
            AVL = new TreeAVL<>();
            try {
                operations = load(j);
                int op = operations.get(0);
                System.out.println("TestCase #" + (j+1) + " outputs \n");
                for(int i = 1; i < op*2; i = i+2){
                    int option = operations.get(i);
                    Integer key = operations.get(i+1);
                    switch (option) {
                        case 1:
                            AVL.insert(key, key);
                            break;

                        case 2:
                            AVL.delete(key);
                            break;
                    }

                    System.out.println(AVL.levels());
                    System.out.println("");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    public static ArrayList<Integer> load(int index) throws IOException {
        File file = new File(path[index]);
        ArrayList<Integer> output = new ArrayList<>();
        if (file.exists()) {
            FileInputStream fis = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String content = "";
            String line = "";

            System.out.println("TestCase #" + index);
            output.add(Integer.valueOf(reader.readLine()));
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                String[] arr = line.split(" ");
                int[] temp = new int[arr.length];
                for (int i = 0; i <arr.length; i++) {
                    temp[i] = Integer.valueOf(arr[i]);
                    output.add(temp[i]);
                }



            }
            System.out.println(" ");

        }else{
            File f = new File(folder);
            if(!f.exists()){
                f.mkdirs();
            }
            file.createNewFile();
        }
        return output;
    }
}