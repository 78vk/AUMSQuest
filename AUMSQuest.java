import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File; // Import the File class
import java.io.FileWriter; //Import the FileWriter
import java.io.IOException; // Import the IOException class to handle errors
import java.nio.file.FileSystemException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class QuestionCreationTool {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		String filePath = null;
		File myObj = null;
		Scanner s = new Scanner(System.in);
		System.out.println("\n              ------     Om Namaha Shivaya    -------\n\n");
		System.out.println("*********-->    Welcome to Aums Question Creation Tool! <--*********");
		System.out.println("\n         +++++++++++++ Version 1.2  ++++++++++++++++++");
		System.out.println("\n         *** Developed by Rajakumar Arul & Vishnu  ***");
		System.out.println("\n      -->    Department of CSE, ASE - Bengaluru    <--");
		System.out.println("\nFor queries/feedback email us at a_rajakumar@blr.amrita.edu, vishnuk782000@gmail.com \n");

		/* F I L E C R E A T I O N */
		System.out.println("Enter the output filename : ");
		String Fname = s.next();

		try {
			String os = System.getProperty("os.name");

			if (os.toUpperCase().startsWith("WINDOWS")) {
				filePath = System.getenv("USERPROFILE");
			} else {
				filePath = System.getenv("HOME");
			}

			System.out.println(filePath);
			Date date = Calendar.getInstance().getTime();

			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMMdd_HHmmss");
			String currentTime = sdf.format(date);

			filePath = filePath + File.separatorChar + Fname + "_" + currentTime + ".txt";
			myObj = new File(filePath);
			if (myObj.exists()) {
				boolean fp = myObj.delete();
				try {
					if (!fp)
						throw new FileSystemException("Unable to delete");
				} catch (FileSystemException e) {
					System.out.println(e.getMessage());
				}
			}
			if (myObj.createNewFile()) {
				System.out.println("File created: " + myObj.getAbsolutePath());
			} else {
				System.out.println("File already exists");
			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		/* F I L E C R E A T E D */

		/* F I L E W R I T E S E C T I O N */

		int choice;
		int mark = 0;
		int count = 1;
		String question, answer;
		for (int i = 0; i == 0;) {
			// Question number
			System.out.println("\nSelect the operation to proceed\n");// Options Available
			System.out.println("\t1.Fill in the Blanks\n" + "\t2.Multiple Choice Question\n"
					+ "\t3.Multiple Choice with Multiple Answers\n" + "\t4.True or False Question\n" + "\t5.Exit\n"
					+ "");
			choice = getChoice(s);

			switch (choice) {
			/* F I L L I N T H E B L A N K S */
			case 1:
				System.out.println("The Selected Question is of type Fill in the Blanks.\n");
				System.out.println("Enter the marks for the Question?");
				mark = getMarks(s);
				System.out.println("Enter the Question:");
				question = s.nextLine();
				question = s.nextLine();
				System.out.println("Enter the Answer:(ONLY ONE WORD)");
				answer = s.next();
				try {
					BufferedWriter toWrite = new BufferedWriter(new FileWriter(filePath, true));
					toWrite.newLine();
					toWrite.write(count + ". (" + mark + " points) " + question + "\n" + "*" + answer + "\n");
					toWrite.close();
					System.out.println("Question has been added.");
					count++;// Question count updated after adding a question to file
				} catch (IOException e) {
					System.out.println("An error occurred.");
					e.printStackTrace();
				}
				System.out.println("\n");
				break;

			/* M C Q */
			case 2:
				System.out.println("The Selected Question is of type Multiple Choice Question.\n");
				System.out.println("Enter the marks for the Question?");
				mark = getMarks(s);
				System.out.println("Enter the Question:");
				question = s.nextLine();
				question = s.nextLine();
				System.out.println("Enter the number of options for this question:\n");
				int optCount = s.nextInt();// number of options
				String options[] = new String[optCount];// array of options
				options[0] = s.nextLine();
				for (int j = 0; j < optCount; j++) {
					System.out.println("Enter option " + (j + 1));
					options[j] = s.nextLine();
				}
				char c, k;
				System.out.println("ANSWER SELECTION:");
				for (int j = 0; j < optCount; j++) {
					c = (char) (65 + j);// representing option number in terms of alphabets
					System.out.println(c);
				}
				int correct = -1, flag = 0;
				while (flag == 0)// loop to ensure that only the valid option is given as answer
				{
					System.out.println("Enter the correct option:\n");
					char optCorrect = s.next().charAt(0);// storing correct option value
					for (int j = 0; j < optCount; j++) {
						c = (char) (65 + j);
						k = (char) (97 + j);
						if ((c == optCorrect) || k == optCorrect) {
							correct = j;
							flag = 1;
							break;
						}
					}
				}
				try {
					BufferedWriter toWrite = new BufferedWriter(new FileWriter(filePath, true));
					toWrite.newLine();
					toWrite.write(count + ". (" + mark + " points) " + question + "\n");// question added
					for (int j = 0; j < optCount; j++)// adding options
					{
						c = (char) (j + 97);
						if (j == correct) {
							toWrite.write("*");// highlighting answer
						}
						toWrite.write(c + ". " + options[j] + "\n");
					}
					toWrite.close();
					System.out.println("Question has been added.");
					count++;// Question count updated after adding a question to file
				} catch (IOException e) {
					System.out.println("An error occurred.");
					e.printStackTrace();
				}
				System.out.println("\n\n");
				break;

			/* M C Q M U L T I - A N S */
			case 3:
				System.out.println("The Selected Question is of type Multiple Choice with Multiple Answers.\n");
				System.out.println("Enter the marks for the Question?");
				mark = getMarks(s);
				System.out.println("Enter the Question:");
				question = s.nextLine();
				question = s.nextLine();
				System.out.println("Enter the number of options for this question:");
				int optCountM = s.nextInt();// number of options
				String optionsM[] = new String[optCountM];// array of options
				optionsM[0] = s.nextLine();
				for (int j = 0; j < optCountM; j++) {
					System.out.println("Enter option " + (j + 1));
					optionsM[j] = s.nextLine();
				}
				System.out.println("ANSWER SELECTION:");
				for (int j = 0; j < optCountM; j++) {
					c = (char) (65 + j);// representing option number in terms of alphabet
					System.out.println(c);
				}
				System.out.println("Enter the number of correct answers:");
				int num = s.nextInt();
				int correctAns[] = new int[num];
				char optCorrect[] = new char[num];
				int flagM = 0;
				while (flagM != num)// loop to ensure that only the valid option is given as answer
				{
					flagM = 0;
					System.out.println("Enter the correct options:\n");
					for (int m = 0; m < num; m++) {
						optCorrect[m] = s.next().charAt(0);// storing correct options value as a character
					}
					int cnt = 0;
					for (int j = 0; j < optCountM; j++)// storing the options corresponding index locations in an
														// integer array for further Processing
					{
						c = (char) (65 + j);
						k = (char) (97 + j);
						for (int m = 0; m < num; m++) {
							if ((c == optCorrect[m]) || k == optCorrect[m]) {
								correctAns[cnt] = j;
								cnt++;
								flagM++;
								break;
							}
						}

					}
				}
				try {
					BufferedWriter toWrite = new BufferedWriter(new FileWriter(filePath, true));
					toWrite.newLine();
					toWrite.write(count + ". (" + mark + " points) " + question + "\n");// question added
					for (int j = 0; j < optCountM; j++)// adding options
					{
						c = (char) (j + 97);
						for (int m = 0; m < num; m++) {
							if (j == correctAns[m]) {
								toWrite.write("*");// highlighting answers
							}
						}
						toWrite.write(c + ". " + optionsM[j] + "\n");
					}
					toWrite.close();
					System.out.println("Question has been added.");
					count++;// Question count updated after adding a question to file
				} catch (IOException e) {
					System.out.println("An error occurred.");
					e.printStackTrace();
				}
				System.out.println("\n\n");
				break;

			/* T R U E O R F A L S E */
			case 4:
				System.out.println("The Selected Question is of type True or False.\n");
				System.out.println("Enter the marks for the Question?\n");
				mark = getMarks(s);
				System.out.println("Enter the Question:");
				question = s.nextLine();
				question = s.nextLine();
				System.out.println("Enter \n0 if FALSE\nAny number if TRUE");
				int Ans = getMarks(s);// number of options
				try {
					BufferedWriter toWrite = new BufferedWriter(new FileWriter(filePath, true));
					toWrite.newLine();
					toWrite.write(count + ". (" + mark + " points) " + question + "\n");
					if (Ans == 0) {
						answer = "False";
						toWrite.write("True\n*" + answer + "\n");

					} else {
						answer = "True";
						toWrite.write("*" + answer + "\nFalse" + "\n");

					}
					toWrite.close();
					System.out.println("Question has been added.");
					count++;// Question count updated after adding a question to file
				} catch (IOException e) {
					System.out.println("An error occurred.");
					e.printStackTrace();
				}
				System.out.println("\n\n");
				break;

			case 5:
				i++;

				Desktop desktop = Desktop.getDesktop();
				System.out.println("Do you want to open question file? (Yes/No)");
				String fileOpenStr = s.next();

				while (!fileOpenStr.equalsIgnoreCase("Yes") && !fileOpenStr.equalsIgnoreCase("No")) {
					System.out.println("Invalid Option.. Say Yes/No only.");
					fileOpenStr = (String) s.next();

				}
				if (fileOpenStr.equalsIgnoreCase("Yes")) {
					if (myObj.exists())
						desktop.open(myObj.getAbsoluteFile());

				} else if (fileOpenStr.equalsIgnoreCase("No")) {
					if (myObj.exists())
						desktop.open(myObj.getParentFile());

				}

				System.out.println("Exited!");
				System.out.println("File created at : " + myObj.getAbsolutePath());

				break;

			default:
				System.out.println("Wrong Entry, Try again!");

			}
		}
	}

	private static int getChoice(Scanner s) {
		int choice = 0;
		try {
			choice = s.nextInt();
		} catch (InputMismatchException ie) {
			choice = 0;
			s.next();
		}
		return choice;
	}

	private static int getMarks(Scanner s) {
		int choice = -1;
		try {
			choice = s.nextInt();
		} catch (InputMismatchException ie) {
			s.next();
			System.out.println("Invalid mark/Answer.. Enter only Numbers");
			choice = getMarks(s);
		}
		return choice;
	}
}
