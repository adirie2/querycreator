import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

/**
 * 
 * @author Aadam Dirie
 *
 */
public class TextFields extends JPanel {
	/**
	 * Input is for our area where the text will be input into
	 */
	private JTextArea Input;
	/**
	 * Output is for our area where the text will be output into
	 */
	private JTextArea Output;
	/**
	 * In is for our ScrollPane for the input text area
	 */
	private JScrollPane in;
	/**
	 * Out is for our ScrollPane for the output text area
	 */
	private JScrollPane out;
	/**
	 * This is the button to read the file
	 */
	private JButton button;
	/**
	 * This is the button to Copy The Output directly to the keyboard
	 */
	private JButton button2;
	/**
	 * This is the String output which will hold the values in the each line in the
	 * file
	 */
	private String output = "";
	/**
	 * This is the String that will take the output strings stored in the ArrayList
	 * and add them all up
	 */
	private String realout = "";
	/**
	 * Create an ArrayList of String that will hold every single line of output This
	 * is where we will traverse and write into the text area
	 */
	private ArrayList<String> array;
	/**
	 * Now we create a File pointer to save the file at a certain point
	 */
	private File file;
/**
 * TextFields Constructor that is Default basically defines all of the behavior that we need.
 */
	public TextFields() {
		// Basically create Readfile Button
		button = new JButton("Read File");
		button.setOpaque(false);
		button.setContentAreaFilled(false);
		// Basically Create the CopyOutput Button
		button2 = new JButton("Copy Output");
		button2.setOpaque(false);
		button2.setContentAreaFilled(false);
		// Basically Create the Necessary JTextArea
		Input = new JTextArea(10, 7);
		Input.setEditable(false);
		Output = new JTextArea(30, 50);
		Output.setEditable(false);
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		// All This code is basically for positioning.
		// I created a Inset which acts like a positioning tool and using a grid of
		// values going up or down, we add certain things in a organized manner
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(100, 100, 100, 40);
		gbc.anchor = GridBagConstraints.WEST;
		add(button, gbc);
		gbc.gridx++;

		in = new JScrollPane(Input);
		in.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		add(in, gbc);
		gbc.gridx = 0;
		gbc.gridy++;
		gbc.anchor = GridBagConstraints.WEST;

		add(button2, gbc);
		gbc.gridx++;
		out = new JScrollPane(Output);
		out.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		add(out, gbc);
		gbc.gridwidth = 5;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		/**
		 * Here I instantiate all the logic for each of the buttons action listeners
		 * inside the constructor.
		 */
		button2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				file = MenuBar.getFile();
				// If the file doesnt exist, make sure to throw and error message
				if (file == null) {
					JOptionPane.showMessageDialog(button2,
							"First upload a .txt file and read it in using the \"Read File\" Button", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					// If not we select the string and we copy it inside of our clip board
					StringSelection stringSelection = new StringSelection(realout);
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					clipboard.setContents(stringSelection, null);
					JOptionPane.showMessageDialog(button2, "Contents successfully copied to clipboard", "Success",
							JOptionPane.PLAIN_MESSAGE);
				}

			}

		});
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// Here we also get the file and we check if it is a text file
				int count = 0;
				file = MenuBar.getFile();
				array = new ArrayList<String>();
				output = "";
				realout = "";
				// if it is null than its wrong
				if (file == null) {
					JOptionPane.showMessageDialog(button, "First upload a .txt file", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					try {
						// We create a scanner to read through all the values as long as its not empty
						// and not null
						Scanner scan = new Scanner(file);
						output = "";
						while (scan.hasNextLine()) {

							String nextline = scan.nextLine();
							if ((!nextline.equals("")) && nextline != null) {
								array.add(nextline);
								output += nextline + '\n';

							}
						}
						// We then close the Scanner and traverse through the array list of all the
						// values and append to our real out value
						scan.close();
						int size = array.size();
						for (String str : array) {
							// First line in the query as a /PN and an OR
							if (count == 0) {
								realout += "/PN " + str + " OR ";
								count++;
							}
							// Last line in the query ends with a new line and we reset the counter for the
							// line
							else if (count % 54 == 0) {
								realout += str + "\n";
								count = 0;
							}
							// of we are at the end of the array, then we do not need a new line
							else if (str.equals(array.get(size - 1))) {
								realout += str;
								count++;
								// if we are in the middle somewhere not in the beginning or end
							} else {
								realout += str + " OR ";
								count++;
							}

						}
						// we then set the input text to the output except for the last Line
						Input.setText(output.substring(0, output.length() - 1));
						// we then set the output to the JText Area
						Output.setText(realout);
						System.out.println(size);
					} catch (Exception e) {

					}
				}
			}

		});
	}

}
