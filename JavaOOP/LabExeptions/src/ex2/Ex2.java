package ex2;

import java.util.Scanner;

public class Ex2 {
    public static void main(String[] args) {
        System.out.println("Enter sentence: ");
        Scanner sc=new Scanner(System.in);
        String s=sc.nextLine();
        sc.close();

        String sentence=s+" ";
        int n=sentence.length();

        int counter=0;
        String longest="";
        boolean flag=false;

        StringBuilder current=new StringBuilder();
        StringBuilder reversed=new StringBuilder();

        for(int i=0;i<n;i++){
            char currentChar=sentence.charAt(i);
            if(Character.isLetterOrDigit(currentChar)){
                if(!flag){
                    flag=true;
                    counter++;
                }
                current.append(currentChar);
            }
            else if(flag){
                flag = false;
                String word=current.toString();
                if(word.length()>longest.length()){
                    longest=word;
                }
                StringBuilder reversedWord=new StringBuilder();
                for(int j=word.length()-1;j>=0;j--){
                    reversedWord.append(word.charAt(j));
                }
                reversed.append(reversedWord);
                reversed.append(currentChar);

                current.setLength(0);
            }
            else{
                reversed.append(currentChar);
            }
        }
        System.out.println("Брой на думите: " + counter);

        if (longest.isEmpty()) {
            System.out.println("Въведеното изречение не съдържа думи.");
        } else {
            System.out.println("Най-дългата дума: " + longest);
        }

        System.out.println("Нов низ с обърнати думи: " + reversed.toString().trim());
    }
}
