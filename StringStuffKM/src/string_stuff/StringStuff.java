package string_stuff;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;

public class StringStuff {

	private JFrame frmStringStuff;
	private static JTextField txtUserString;
	private static JLabel lblMaxRun;
	private static JButton btnBlowUp;
	private static JButton btnExport;
	private static JMenuItem mniImport;
	private JTextPane txtFinalString;
	private File textFile; 		
	// Creates a file chooser class and new file chooser named fc
	private static JFileChooser fc = new JFileChooser();
	
	private String OutputString;
	private int OutputRun = 0;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StringStuff window = new StringStuff();
					window.frmStringStuff.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		// Sets the file chooser to the directory of the jar of this program
		fc.setCurrentDirectory(new java.io.File("."));
		// Sets and displays the title of the file chooser 
		fc.setDialogTitle("Please select your text file");
		// Creates a file name filter for only text files
		FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt","txt",".pdf","pdf",".doc","doc",".rtf","rtf");
		fc.addChoosableFileFilter(filter);
		fc.setFileFilter(filter);
	}

	/**
	 * Create the application.
	 */
	public StringStuff() {
		initialize();
		// Hides the max run label
		lblMaxRun.setVisible(false);
		// Hides the export button
		btnExport.setVisible(false);
	}
	
	/*
	 * FUNCTION NAME: StringBlowup
	 * PARAMETERS: String variable
	 * Takes a string and blows it up
	 * Returns a string
	 */
	public String StringBlowup (String userString) {
		// A character variable that is used to hold character variable at the index
		char c;
		// A character variable that is used to hold the character variable after c
		char next;
		// A class that allows me to create a string and use code like append
		StringBuilder string = new StringBuilder();
		// A string variable that is used to hold the final string
		String FinalString = "";
		
				// Loops until it goes through the whole string and checks every character
				for (int count = 0; count < userString.length(); count++) {	
					// Assigns the character at the count to c
					c = userString.charAt(count);
					// Checks to see if the variable c is a number and if the count is less than length of the string minus 1
					if (Character.isDigit(c) && count < userString.length() - 1) {	
						next = userString.charAt(count + 1);	
						// Checks to see if the next character after c is not a digit
							if (!Character.isDigit(next)) {
								// Converts the variable c to a string then to a integer and then assigns it to the repeat variable
								int repeat = Integer.parseInt(Character.toString(c));
								// Adds the character next which is a character at the index of count to the end of the string builder
								string.append(next);
								// Loops until is no longer less than the repeat variable
								for (int i = 0; i < repeat; i++) {
									// Adds the character next which is a character at the index of count to the end of the string builder
									string.append(next);
								}
							}
							// Checks to see if the next character after c is a digit
							else if (Character.isDigit(next)) {
								int repeat = Integer.parseInt(Character.toString(c));
								for (int i = 0; i < repeat; i++) {
									string.append(next);
								}
								
							}
								
					// Increments the counter of the for loop				
					count++;		
					}
					// Checks to see if the character variable c is not a number
					else if (!Character.isDigit(c)) {	
						// Adds the variable c which is a character at the index of count to the end of the string builder
						string.append(c);
						
					}
					// Converts the string builder to a string variable and assigns it to the FinalString variable
					FinalString = string.toString();
								
					}
			
			// Shows the text pane
			txtFinalString.setVisible(true);
			// Displays the Final string in the text pane
			txtFinalString.setText(FinalString);
			// Returns the variable FinalString
			return FinalString;
	}
	
	/*
	 * FUNCTION NAME: MaxRun
	 * PARAMETERS: String variable
	 * Loops through a string and records the max run of a sequence of the same repeated characters
	 * Returns integer MaxRun
	 */
	public static int MaxRun (String str) {
		// A integer variable that is used to hold the current amount of times a character has repeated in a sequence
		int numTimesRepeated = 1;
		// A integer variable that holds the result of the max run method and is initialized to 1
		int maxRun = 1;
		// A boolean variable used to loop the code until the max run has been found
		boolean running = true;
		
		// loops the code until the running boolean variable is no longer true
		while (running){
			// loops through the string using the variable of i that increments itself to check every character in the string
			for (int i = 0; i < str.length() - 1; i++) {
				//
				int e = i + 1;
				// Checks if the characters at the index of i and e which is the index ahead of i by 1
					if (str.charAt(i) == str.charAt(e)) {
						// increments the number of times a character has been repeated keeping track of how many times it has repeated in one sequence
						numTimesRepeated++;
						
						/* Checks if the number of times a character has occurred in a row is greater than the max run 
						 * Will keep replace the longest line of the same character repeated in a row until the largest one is set
						 */
						if (numTimesRepeated > maxRun) {
							
							// Assigns the number of times a character has repeated to the max run
				            maxRun = numTimesRepeated;
				            // Sets the boolean variable running to false breaking the loop signifying that the max run has been found
				            running = false;;
				        }
					}
					else{
						
						// Resets the number of times a character has repeated to 1 if the characters don't match each other
						numTimesRepeated = 1;
					}
				}
			
		}
		
		// Shows the export button and the max run label
		btnExport.setVisible(true);
		lblMaxRun.setVisible(true);
		
		// Sets the text to display what the max run is in the label
		lblMaxRun.setText("The max run for " + str + " is " + maxRun);
		// Returns the value of maxRun
		return maxRun;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		// Creates the frame/form in C# that holds all my GUI elements
		frmStringStuff = new JFrame();
		frmStringStuff.setTitle("String Stuff(KM)");
		frmStringStuff.setBounds(100, 100, 535, 385);
		frmStringStuff.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmStringStuff.getContentPane().setLayout(null);
		
		// Creates the label that asks the user to input a string
		JLabel lblAskString = new JLabel("Please enter a string. (i.e 2rt4g5 or 54677)");
		lblAskString.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAskString.setBounds(20, 11, 404, 22);
		frmStringStuff.getContentPane().add(lblAskString);
		
		// Creates the text field the user uses to input a string
		txtUserString = new JTextField();
		txtUserString.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtUserString.setBounds(20, 44, 489, 35);
		frmStringStuff.getContentPane().add(txtUserString);
		txtUserString.setColumns(10);
		
		// Creates the label that displays the max run of the user's string
		lblMaxRun = new JLabel("The max run for your string is:");
		lblMaxRun.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMaxRun.setBounds(10, 223, 414, 56);
		frmStringStuff.getContentPane().add(lblMaxRun);
		
		// Creates my Blow up string button
		btnBlowUp = new JButton("Blow up string");
		
		// Adds an object called an action listener that detects if the button has been pressed
		btnBlowUp.addActionListener(new ActionListener() {
			
			// Method that runs when the button is pressed
			public void actionPerformed(ActionEvent e) {
				// A string variable used to hold the end result of the string blowup function
				String EndString;
				/*
				 * A string variable that is used to hold the user's inputed string
				 * Gets the user's string from the text field and assigns it to userString
				 */
				String userString = txtUserString.getText();
				
				// Runs the code if the string the user inputs has more than 1 characters 
				if (userString.length() > 1) {
					/*
					 *  Calls StringBlowup function passing userString to the function by reference
					 *  Assigns the returned variable from the function StringBlowup to EndString
					 *  Calls MaxRun method passing EndString to the method by reference
					 *  Assigns EndString to the global string variable OutputString
					 *  Assigns MaxRun integer to the global OutputRun variable
					 */
					EndString = StringBlowup(userString);
					OutputString = EndString;
					OutputRun = MaxRun(EndString);
					
				}
				else {
					// Displays an error message pop up when the user clicks the button without inputting string for error checking
					JOptionPane.showMessageDialog(frmStringStuff, "No string found, please enter a string!");
				}
				
				
			}
		});
		
		
		btnBlowUp.setFont(new Font("Dialog", Font.PLAIN, 14));
		btnBlowUp.setBounds(20, 90, 139, 69);
		frmStringStuff.getContentPane().add(btnBlowUp);
		
		// Creates my export file button
		btnExport = new JButton("Export text file");
		btnExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					// Creates a new buffered reader and wraps a new file reader with it that creates a text file called output in the directory it's in
					BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(".\\output.txt"));
					// Writes out the output string either from import text or input text
					bufferedWriter.write(OutputString);
					// Creates a new line in the text file
					bufferedWriter.newLine();
					// Writes out the max run of the string
					bufferedWriter.write("The max run of this string is " + OutputRun);
					// Closes the buffered writer to prevent memory leaks
					bufferedWriter.close();
				}catch (IOException ex) {
					JOptionPane.showMessageDialog(frmStringStuff, "Error writing to file!");
				}
				
				
			}
		});
		btnExport.setFont(new Font("Dialog", Font.PLAIN, 14));
		btnExport.setBounds(378, 90, 131, 69);
		frmStringStuff.getContentPane().add(btnExport);
		
		// Creates the text pane that the final string will be displayed in
		txtFinalString = new JTextPane();
		txtFinalString.setEditable(false);
		txtFinalString.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtFinalString.setBounds(10, 180, 499, 46);
		
		// Creates a border around the text pane that is black and 1 pixel thick
		Border border = BorderFactory.createLineBorder(Color.black, 1);
		txtFinalString.setBorder(border);
		
		// Hides the text pane
		txtFinalString.setVisible(false);
		frmStringStuff.getContentPane().add(txtFinalString);
		
		// Creates my Options menu bar
		JMenuBar mnuOptions = new JMenuBar();
		frmStringStuff.setJMenuBar(mnuOptions);
		
		// Creates my file menu 
		JMenu mnuFile = new JMenu("File");
		mnuFile.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnuOptions.add(mnuFile);
		
		// Creates my import text file menu item
		mniImport = new JMenuItem("Import text file");
		mniImport.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mniImport.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
			
				// Displays the file explorer
				fc.showOpenDialog(null);
				// The file that gets selected by the file chooser
				textFile = fc.getSelectedFile();
				
				// String variable created to only reference one line of a string at a time
				String line = null;
				
				// Try catch for error checking purposes like if the text file isn't found or is not a text file
				try {
					// Creates a file reader that reads the text of a text file
					FileReader filereader = new FileReader(textFile);
					// Wraps the file reader in a buffered reader
					BufferedReader bufferedReader = new BufferedReader(filereader);
					
					while ((line = bufferedReader.readLine()) != null) {	
						OutputString = StringBlowup(line);
						OutputRun = MaxRun(OutputString);
					}
					// Closes the buffered reader to prevent memory from leaking
					bufferedReader.close();
					// Catches an error if the file is not found
				}catch (FileNotFoundException ex) {
					// Displays a message telling the user that the program can't open the text file
					JOptionPane.showMessageDialog(frmStringStuff, "Unable to open the text file");
					// Catches an error if the program is unable to read the program for reasons such as no permission
				}catch  (IOException ex) {
					// Displays a message telling the user the program is can't read the text file
					JOptionPane.showMessageDialog(frmStringStuff, "Unable to read the text file!");
					// Catches an error if the user backs out of the file chooser without selecting a text file
				}catch (NullPointerException ex) {
					
				}
			}
		});
		mnuFile.add(mniImport);
	}
}
