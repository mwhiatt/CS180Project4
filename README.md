# CS180Project4
Our CS180 project to construct the quiz tool of a learning management system. 
To run the project, ensure the following files are included in your directory:
	-Login.java
	-Driver.java
	-Student.java
	-Teacher.java
Parts submitted by:
	-Vocareum:
	-Report on Brightspace: 
Class Descriptions:
	-Driver: The Driver class contains the main method for the project. It contains the menus the user interacts with.
	 It makes method calls to the other classes in order to execute the options in the menus. It was tested with improper
	 inputs and protected against InputMismatchExceptions in order to ensure the program will not crash or malfunction
	 when the user enters unintended inputs. It conntains no methods besides the main method. 
	-Login: The Login class contains methods that are called in Driver to sign users in to the program. It contains 
	 the isDuplicate method which returns true or false depending on if the username being entered is already stored
	 in logins.txt. It contains the writeNewUser method which writes a new classification, username, and password to the
	 logins.txt file. It contains the login method which returns true or false depending on if the username and password
	 a user enters match a pair stored in the logins.txt file. It contains the getClassification method which returns the 
	 Teacher or Student classification of a given user. It was tested with test cases to ensure the methods are at full
	 functionality and all inputs that could be received as parameters from Driver are handled. 
	-Student: The Student class contains methods that are called in Driver to implement the Student functionality. The methods
	 are as follows. The answer method takes in the scanner, a course name, and a quiz name and displays the quiz for the student
	 to take. It also accepts the answers from the student and creates their submission file. It returns an arraylist of the students
	 answers to be formed into the submission file by writeFile. The writeFile method creates and timestamps the submission for each
	 time the student takes a quiz. It also adds the submission name to the master submission file for the quiz. The answerImportFile
	 method takes in the name a user enters to submit as an answer for a question and reads it in, it then returns that value to be
	 treated as a terminal answer would be by the answer method.
	-Teacher: The Teacher class contains the methods responsible for the teacher functionality as well as some quality of life methods
	 that are used in driver to reduce code recycling. The createCourse method creates the quiz title master file for the course as well
	 as adds the course title to the CourseNames file. The deleteCourse method delets a course from the CourseNames file and deletes all 
	 files related to the course in question with help from the deleteQuiz method. The deleteQuiz method deletes a quiz, removes the title
	 from the course quizzes master file and deletes all related submission files. createQuiz adds a new quiz title to the course's quiz 
	 name master file as well as creates the quiz either through terminal entry or through the importation of a file. editQuiz allows a
	 teacher to review a quiz and pick a question to change. viewSubmission shows a teacher a students submission for the submission title
	 they select. printCourses prints the names of the courses so the user can see their choices. printQuizzes prints the names of the 
	 quizzes. printSubmissions prints the names of the submissions. checkSubmissionExistence returns true if a submission exists to be
	 viewed, it is used to prevent misinputs. checkQuizExistence returns true if a quiz exists. checkCourseExistence returns true if a 
	 given course exists. quizImport writes the imported quiz title to the master file of quizzes for a course and creates a file for 
	 the new quiz. 
