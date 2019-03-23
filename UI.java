
import java.awt.Dimension;
import java.net.URL;

import javax.swing.*;

/**
 * 
 * @author Aadam Dirie
 *
 *         The Public Class UI inherits its main functionality from the JFrame,
 *         but to make it simpler I just made the edits to the object itself as
 *         it simplifies the Class Inheritance.
 */
public class UI extends JFrame {

	/**
	 * Main Frame that we will be using as our Main GUI
	 */
	private JFrame MainFrame;
	/**
	 * Creating a Label for the Title of the Program
	 */
	private JLabel Title;
	/**
	 * Creating an I/O Panel to add in the TextFields Created in the previous files
	 */
	private JPanel io;

	/**
	 * Here is the default constructor, it basically calls
	 */
	public UI() {

		/*
		 * Inheritance
		 */
		super("Query App"); // call super and name frame Query App
		/*
		 * JFrame
		 */
		int height = 1000, width = 1000;// int and width of JFrame

		this.setVisible(true);// set the frame to visible
		URL url = UI.class.getResource("/patent.jpg");
		ImageIcon img = new ImageIcon(url); // create the image icon with the image
		this.setIconImage(img.getImage()); // add the image to the JFrame
		/*
		 * JPanels
		 */
		MenuBar bar = new MenuBar();
		/*
		 * JMenuBar
		 */
		this.setMinimumSize(new Dimension(1000, 1000));// set the size to be 1k by 1k
		Title = new JLabel("Query Creator");// set the JLabel to this text vlaue
		io = new JPanel(); // instantiate the JPanel
		io.add(new TextFields()); // Add the textFields object that we created into this new panel
		// this.setLayout();
		this.setJMenuBar(bar); // set the JMenuBar to hold that new bar
		this.getContentPane().add(io); // add the panel into our JFrame
		this.setVisible(true); // set the visibility to true
		this.pack(); // and pack all the values together

	}

	/**
	 * 
	 * @param args
	 *            args of course holds the number of command line arguments and is
	 *            stored in this array
	 * 
	 *            Main Functionality of this main method is to basically instantiate
	 *            the UI class
	 */
	public static void main(String[] args) {

		UI ui = new UI();
	}

}
