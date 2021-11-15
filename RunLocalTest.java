import org.junit.Test;
import org.junit.After;
import java.lang.reflect.Field;
import org.junit.Assert;
import org.junit.Before;
import org.junit.rules.Timeout;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import javax.swing.*;
import java.io.*;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * A framework to run public test cases.
 *
 * <p>Purdue University -- CS18000 -- Fall 2021</p>
 *
 * @author Purdue CS
 * @version August 23, 2021
 */
public class RunLocalTest {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Result result = JUnitCore.runClasses(TestCase.class);
        if (result.wasSuccessful()) {
            System.out.println("Excellent - Tests ran successfully");
        } else {
            for (Failure failure : result.getFailures()) {
                System.out.println(failure.toString());
            }
        }
    }

    /**
     * A set of public test cases.
     *
     * <p>Purdue University -- CS18000 -- Fall 2021</p>
     *
     * @author Purdue CS
     * @version August 23, 2021
     */
    public static class TestCase {
        private final PrintStream originalOutput = System.out;
        private final InputStream originalSysin = System.in;

        @SuppressWarnings("FieldCanBeLocal")
        private ByteArrayInputStream testIn;

        @SuppressWarnings("FieldCanBeLocal")
        private ByteArrayOutputStream testOut;

        @Before
        public void outputStart() {
            testOut = new ByteArrayOutputStream();
            System.setOut(new PrintStream(testOut));
        }

        @After
        public void restoreInputAndOutput() {
            System.setIn(originalSysin);
            System.setOut(originalOutput);
        }

        private String getOutput() {
            return testOut.toString();
        }

        @SuppressWarnings("SameParameterValue")
        private void receiveInput(String str) {
            testIn = new ByteArrayInputStream(str.getBytes());
            System.setIn(testIn);
        }


//pathway test 1. Aryan needs to upload according .txt files for this to work
        @Test
        public void testExpectedOne() {
            try {
                String input = "2" + System.lineSeparator() +
                        "T" + System.lineSeparator() +
                        "T" + System.lineSeparator() +
                        "1" + System.lineSeparator() +
                        "CS259" + System.lineSeparator() +
                        "2" + System.lineSeparator() +
                        "CS259" + System.lineSeparator() +
                        "2" + System.lineSeparator() +
                        "1" + System.lineSeparator() +
                        "No" + System.lineSeparator() +
                        "Quiz1" + System.lineSeparator() +
                        "Test 1 Question" + System.lineSeparator() +
                        "Test A" + System.lineSeparator() +
                        "Test B" + System.lineSeparator() +
                        "Test C" + System.lineSeparator() +
                        "Test D" + System.lineSeparator() +
                        "A" + System.lineSeparator() +
                        "Yes" + System.lineSeparator() +
                        "1" + System.lineSeparator() +
                        "No" + System.lineSeparator() +
                        "3" + System.lineSeparator();
                String expected = "Welcome to the Quiz Learning Program\n" +
                        "1. Create Account\n" +
                        "2. Log in\n" +
                        "3. Exit" + System.lineSeparator() +
                        "Enter a username: " + System.lineSeparator() +
                        "Enter a password: \n" + System.lineSeparator() +
                        "Welcome Teacher T\n" +
                        "1. Create Course\n" +
                        "2. View Course\n" +
                        "3. Exit" + System.lineSeparator() +
                        "Enter a Course Name: " + System.lineSeparator() +
                        "Creating a new file!\n" +
                        "1. Create Course\n" +
                        "2. View Course\n" +
                        "3. Exit\n" + System.lineSeparator() +
                        "Available Courses:\n" +
                        "CS259\n\n" +
                        "Enter a Course Name: " + System.lineSeparator() +
                        "1. Delete Course\n2. View Quizzes\n" + System.lineSeparator() +
                        "View Menu\n1. Create Quiz\n2. Edit Quiz\n3. Delete Quiz\n4. View Submissions" + System.lineSeparator() +
                        "Would you like to import a quiz? Yes or No" +
                        " (If no, you will be directed to create a file inside terminal immediately)" + System.lineSeparator() +
                        "What would you like to name the quiz?" + System.lineSeparator() +
                        "Creating a new file!\nPlease write the question" + System.lineSeparator() +
                        "Please write answer A):" + System.lineSeparator() +
                        "Please write answer B):" + System.lineSeparator() +
                        "Please write answer C):" + System.lineSeparator() +
                        "Please write answer D):" + System.lineSeparator() +
                        "Please write which letter is the answer:" + System.lineSeparator() +
                        "What you like to put a point value in? Yes or No" + System.lineSeparator() +
                        "Please input a value (digit):" + System.lineSeparator() +
                        "Would you like to write another question? Yes or No" + System.lineSeparator() +
                        "Creating a new file!\nDone!\n" +
                        "1. Create Course\n" +
                        "2. View Course\n" +
                        "3. Exit" + System.lineSeparator() +
                        "Logged Out\nHave a Good Day";
                receiveInput(input);
                Driver.main(new String[0]);

                //Retrieves the output from the program
                String actual = getOutput();

                // Trims the output and verifies it is correct.
                assertEquals("Verify you follow the handout and use the given strings for the output!",
                        expected.trim(), actual.trim());

            } catch (Exception e) {
                e.printStackTrace();
                fail();
            }
        }
//pathway test 2
        @Test(timeout = 1000)
        public void testExpectedTwo() {
            try {
                String input = "2" + System.lineSeparator() +
                        "T" + System.lineSeparator() +
                        "T" + System.lineSeparator() +
                        "3" + System.lineSeparator();
                String expected = "Welcome to the Quiz Learning Program\n" +
                        "1. Create Account\n" +
                        "2. Log in\n" +
                        "3. Exit" + System.lineSeparator() +
                        "Enter a username: " + System.lineSeparator() +
                        "Enter a password: \n" + System.lineSeparator() +
                        "Welcome Teacher T\n" +
                        "1. Create Course\n" +
                        "2. View Course\n" +
                        "3. Exit" + System.lineSeparator() +
                        "Logged Out\nHave a Good Day";
                receiveInput(input);
                Driver.main(new String[0]);

                //Retrieves the output from the program
                String actual = getOutput();

                // Trims the output and verifies it is correct.
                assertEquals("Verify you follow the handout and use the given strings for the output!",
                        expected.trim(), actual.trim());

            } catch (Exception e) {
                e.printStackTrace();
                fail();
            }
        }
        //pathway test 3. Doesn't work yet because of input.nextLine() stuff
//        @Test(timeout = 1000)
//        public void testExpectedThree() {
//            try {
//                String input = Integer.parseInt("1") + System.lineSeparator() +
//                        Integer.parseInt("2") + System.lineSeparator() +
//                        "Aniket" + System.lineSeparator() +
//                        "Aniket1" + System.lineSeparator() +
//                        "1" + System.lineSeparator() +
//                        Integer.parseInt("1") + System.lineSeparator() + " " + System.lineSeparator() +
//                        "CS180" + System.lineSeparator() +
//                        "Geography" + System.lineSeparator() +
//                        Integer.parseInt("2") + System.lineSeparator() +
//                        "A" + System.lineSeparator() +
//                        Integer.parseInt("2") + System.lineSeparator() +
//                        "B" + System.lineSeparator() +
//                        Integer.parseInt("2");
//                String expected = "Welcome to the Quiz Learning Program\n" +
//                        "1. Create Account\n" +
//                        "2. Log in\n" +
//                        "3. Exit" + System.lineSeparator() +
//                        "1. Teacher\n" +
//                        "2. Student" + System.lineSeparator() +
//                        "Enter a username:" + System.lineSeparator() +
//                        "Sorry that username is taken, please try a new one." + System.lineSeparator() +
//                        "Enter a username:" + System.lineSeparator() +
//                        "Enter a password:" + System.lineSeparator() +
//                        "Welcome Student Aniket1\n" +
//                        "1. Take Quiz\n" +
//                        "2. Exit" + System.lineSeparator() +
//                        "Available Courses:\n" +
//                        "CS180" + System.lineSeparator() +
//                        "Which course would you like to access:" + System.lineSeparator() +
//                        "Available Quizzes:\n" +
//                        "Geography" + System.lineSeparator()+
//                        "Which quiz would you like to take:" + System.lineSeparator() +
//                        "What is the capital of Maine?\n" +
//                        "A. Augusta\n" +
//                        "B. San Jose\n" +
//                        "C. Austin\n" +
//                        "D. Chicago" + System.lineSeparator() +
//                        "How would you like to answer this question\n" +
//                        "1. Import files\n" +
//                        "2. Answer through terminal" + System.lineSeparator() +
//                        "Please enter your answer option." + System.lineSeparator()+
//                        "What is the capital of Illinois?\n" +
//                        "A. Boise\n" +
//                        "B. Springfield\n" +
//                        "C. Oklahoma City\n" +
//                        "D. Austin" + System.lineSeparator() +
//                        "How would you like to answer this question\n" +
//                        "1. Import files\n" +
//                        "2. Answer through terminal" + System.lineSeparator() +
//                        "Please enter your answer option." + System.lineSeparator() +
//                        "1. Take Quiz\n" +
//                        "2. Exit" + System.lineSeparator() +
//                        "Logged Out\n" +
//                        "Have a Good Day\n";
//                receiveInput(input);
//                Driver.main(new String[0]);
//
//                //Retrieves the output from the program
//                String actual = getOutput();
//
//                // Trims the output and verifies it is correct.
//                assertEquals("Path 3",
//                        expected.trim(), actual.trim());
//
//            } catch (Exception e) {
//                e.printStackTrace();
//                fail();
//            }
//        }
    }
}

