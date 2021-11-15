import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Project 4 - Learning Management Quiz Tool - Student
 * Contains methods for student functionality
 * <p>
 * 
 * @author Matt Hiatt, Aryan Mathur, Aniket Mohanty, and Nathan Lo
 * @version 11/15/2021
 */
public class Student {

    public static ArrayList<String> answer(Scanner input, String course, String quiz) {
        String courseQuizFileName = course + quiz + ".txt";
        ArrayList<String> List = new ArrayList<>();
        try (BufferedReader bfr = new BufferedReader(new FileReader(courseQuizFileName))) {
            String s = " ";
            do {
                for (int c = 0; c < 5; c++) {
                        s = bfr.readLine();
                        if (s == null) {
                            continue;
                        }
                        System.out.println(s);
                }

                if (s == null) {
                    continue;
                }

                for (int c = 0; c < 2; c++) {
                    if (c == 0) {
                        s = bfr.readLine();
                        List.add(s);//ans
                    } else if (c == 1) {
                        s = bfr.readLine();
                        List.add(s);//points
                    }
                }
                int inputType;
                try {
	                System.out.println("How would you like to answer this question\n1. Import files\n" +
	                        "2. Answer through terminal");
	                inputType = input.nextInt();
	                input.nextLine();
                } catch (InputMismatchException e) {
                	inputType = 0;
                	input.nextLine();
                }
                while (inputType != 1 && inputType != 2) {
                	try {
                		System.out.println("You must chose a number between 1 and 2!");
    	                System.out.println("How would you like to answer this question\n1. Import files\n" +
    	                        "2. Answer through terminal");
    	                inputType = input.nextInt();
    	                input.nextLine();
                    } catch (InputMismatchException e) {
                    	inputType = 0;
                    	input.nextLine();
                    }
                }
                if (inputType == 2) {
                    System.out.println("\nPlease enter your answer option.");
                    String answer = input.nextLine();
                    List.add(answer);

                } else if (inputType == 1) {
                    System.out.println("\nRemember files should just contain the answer choice.\nPlease enter"
                    		+ " your file name");
                    String fileName = input.nextLine();
                    String ans1 = answerImportFile(fileName);
                    List.add(ans1);
                }
            } while (s != null);
            int total = 0;
            for (int i = 0; i < List.size(); i += 3) {

                if (!List.get(i).toLowerCase().equals(List.get(i+2).toLowerCase())) {
                    List.set(i + 1, "0");
                }
                total += Integer.parseInt(List.get(i+1));
            }
            List.add(String.valueOf(total));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return List;

    }

    public static void writeFile(String course, String quiz, String user, 
    		ArrayList<String> ListToWrite, String total) {
        int i = 1;
        int n = 1;
        String fileName = course + quiz + user + ".txt";
        File f = new File(fileName);
        while (f.exists()) {
            i++;
            fileName = course + quiz + user + i + ".txt";
            f = new File(fileName);
        }
        try (PrintWriter pw = new PrintWriter(new FileOutputStream(fileName, false))) {
            pw.println("Name: " + user);
            for (int c = 2; c < ListToWrite.size(); c += 3) {
                String ans = "Student Answer: " + ListToWrite.get(c);
                String points;
                if (ListToWrite.get(c).toLowerCase().equals(ListToWrite.get(c - 2).toLowerCase()))
                {
                	points = "Correct: " + ListToWrite.get(c  - 1);
                } else {
                	points = "Incorrect: 0"; 
                }
                String fileInput = n + ". " + ans + ", " + points;
                n++;
                pw.println(fileInput);
            }
            pw.println("Points Earned : " + total);
            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
            pw.println("Timestamp: " + timeStamp);
        } catch ( FileNotFoundException e) {
            e.printStackTrace();
        }

        String masterFileName = course + quiz + "Submissions.txt";
        try (PrintWriter pw = new PrintWriter(new FileOutputStream(masterFileName, true))) {
            pw.println(fileName);
        } catch ( FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static String answerImportFile(String fileName) throws IOException {
        File f = null;
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        String ans;
        try {
            f = new File(fileName + ".txt");
            fileReader = new FileReader(f);
            bufferedReader = new BufferedReader(fileReader);
            ans = bufferedReader.readLine();

        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        } catch (IOException e) {
            throw new IOException();
        }
        bufferedReader.close();
        return ans;
    }
        //Asks user whether he/she/they want to view previous submissions after they enter course and quiz name
    public static void viewSubmissions(Scanner input, String course, String quiz, String user) {
        String fileName = course + quiz + "Submissions.txt";
        ArrayList<String> userSubmissions = new ArrayList<>(); //ArrayList that holds a particular user's submissions
        String prompt = "";
        try (BufferedReader bfr = new BufferedReader(new FileReader(fileName))) {
            String s = bfr.readLine();
            while (s != null) {
                if (s.contains(user)) { //checks whether a submission contains username
                    userSubmissions.add(s); // adds it to ArrayList
                }
                s = bfr.readLine();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        do {
            System.out.println("Which submission would you like to view?");
            for (int i = 0; i < userSubmissions.size(); i++) {
                System.out.println(i + 1 + ". " + userSubmissions.get(i));//prints list of user submissions
            }
            int submissionToView = input.nextInt() - 1;// index of submission user wants to view
            input.nextLine();
            while (submissionToView < userSubmissions.size() || submissionToView < 0) {
                System.out.println("Invalid Input: Please try again");
                submissionToView = input.nextInt() - 1;
                input.nextLine();
            }
            try (BufferedReader bfr = new BufferedReader(new FileReader(userSubmissions.get(submissionToView)))) {
                String s = bfr.readLine();
                while (s != null) {
                    System.out.println(s);//prints the user submission line by line
                    s = bfr.readLine();
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Would you like to view more submissions? [Y/N]");//checks if user wants to
            // view more submissions
            prompt = input.nextLine();
        } while ( prompt.equals("Y")); // continues while the user says yes
    }
	
}
