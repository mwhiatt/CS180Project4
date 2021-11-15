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


//to test creating quiz with teacher
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
        //Test for Student to take Quiz in CS259
        @Test
        public void testExpectedSix() {
            try {
                String input = "2" + System.lineSeparator() +
                        "S" + System.lineSeparator() +
                        "S" + System.lineSeparator() +
                        "1" + System.lineSeparator() +
                        "CS259" + System.lineSeparator() +
                        "Quiz1" + System.lineSeparator() +
                        "2" + System.lineSeparator() +
                        "A" + System.lineSeparator() +
                        "3" + System.lineSeparator();
                String expected = "Welcome to the Quiz Learning Program\n" +
                        "1. Create Account\n" +
                        "2. Log in\n" +
                        "3. Exit" + System.lineSeparator() +
                        "Enter a username: " + System.lineSeparator() +
                        "Enter a password: " + System.lineSeparator() +
                        "Welcome Student S\n" +
                        "1. Take Quiz\n" +
                        "2. View Submissions\n" +
                        "3. Exit\n" + System.lineSeparator() +
                        "Available Courses:\n" +
                        "CS259\n" +
                        "Which course would you like to access: \n" + System.lineSeparator() +
                        "Available Quizzes:\n" +
                        "Quiz1\n" +
                        "Which quiz would you like to take: " + System.lineSeparator() +
                        "Test 1 Question\n" +
                        "A. Test A\nB. Test B\nC. Test C\nD. Test D\nHow would you like to answer this question" +
                        "\n1. Import files\n2. Answer through terminal\n" +System.lineSeparator() +
                        "Please enter your answer option.\n" +
                        "1. Take Quiz\n" +
                        "2. View Submissions\n" +
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
//Test for logging in and logging out for teacher
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

        @Test
        public void testExpectedThree() {
            try {
                String input = "2" + System.lineSeparator() +
                        "T" + System.lineSeparator() +
                        "T" + System.lineSeparator() +
                        "2" + System.lineSeparator() +
                        "CS259" + System.lineSeparator() +
                        "2" + System.lineSeparator() +
                        "2" + System.lineSeparator() +
                        "Quiz1" + System.lineSeparator() +
                        "1" + System.lineSeparator() +
                        "Test 1 Question Test Three" + System.lineSeparator() +
                        "Test A" + System.lineSeparator() +
                        "Test B" + System.lineSeparator() +
                        "Test C" + System.lineSeparator() +
                        "Test D" + System.lineSeparator() +
                        "A" + System.lineSeparator() +
                        "Yes" + System.lineSeparator() +
                        "1" + System.lineSeparator() +
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
                        "3. Exit\n" + System.lineSeparator() +
                        "Available Courses:\n" +
                        "CS259\n\n" +
                        "Enter a Course Name: " + System.lineSeparator() +
                        "1. Delete Course\n2. View Quizzes\n" + System.lineSeparator() +
                        "View Menu\n1. Create Quiz\n2. Edit Quiz\n3. Delete Quiz\n4. View Submissions\n" + System.lineSeparator() +
                        "Available Quizzes:\nQuiz1\n" + System.lineSeparator() +
                        "Enter Quiz Name you wish to edit: \n" + System.lineSeparator() +
                        "Which question would you like to edit?" + System.lineSeparator() +
                        "Please write the question" + System.lineSeparator() +
                        "Please write answer A):" + System.lineSeparator() +
                        "Please write answer B):" + System.lineSeparator() +
                        "Please write answer C):" + System.lineSeparator() +
                        "Please write answer D):" + System.lineSeparator() +
                        "Please write which letter is the answer:" + System.lineSeparator() +
                        "What you like to put a point value in? Yes or No" + System.lineSeparator() +
                        "Please input a value (digit):" + System.lineSeparator() +
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

        @Test
        public void testExpectedFour() {
            try {
                String input = "2" + System.lineSeparator() +
                        "T" + System.lineSeparator() +
                        "T" + System.lineSeparator() +
                        "2" + System.lineSeparator() +
                        "CS259" + System.lineSeparator() +
                        "2" + System.lineSeparator() +
                        "4" + System.lineSeparator() +
                        "Quiz1" + System.lineSeparator() +
                        "S" + System.lineSeparator() +
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
                        "3. Exit\n" + System.lineSeparator() +
                        "Available Courses:\n" +
                        "CS259\n\n" +
                        "Enter a Course Name: " + System.lineSeparator() +
                        "1. Delete Course\n2. View Quizzes\n" + System.lineSeparator() +
                        "View Menu\n1. Create Quiz\n2. Edit Quiz\n3. Delete Quiz\n4. View Submissions\n" + System.lineSeparator() +
                        "Available Quizzes:\nQuiz1\n" + System.lineSeparator() +
                        "Enter Quiz Name you wish to see submissions for: \n" + System.lineSeparator() +
                        "Available Submissions\nS\n\nEnter Submission you wish to view\n" + System.lineSeparator() +
                        "Name: S\n1. Student Answer: A, Correct: 1\nPoints Earned : 1\nTimestamp: 2021.11.15.15.07.15" +
                        // Time stamp will change for each test
                        "\n\n" +
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

        @Test(timeout = 1000)
        public void testExpectedFive() {
            try {
                String input = "3" + System.lineSeparator();
                String expected = "Welcome to the Quiz Learning Program\n" +
                        "1. Create Account\n" +
                        "2. Log in\n" +
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


    }
}

