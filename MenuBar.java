import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * 
 * @author Aadam Dirie
 *
 */
public class MenuBar extends JMenuBar {

	/**
	 * Settings Menu that will contain the MenuItem that allows for Opening of the
	 * file
	 */
	private JMenu settings = new JMenu("Settings");
	/**
	 * Here is the MenuItem that will be included into our Settings Menu
	 */
	private JMenuItem FileChange;
	/**
	 * Here we create a static File object that is by default set to NULL in the
	 * case that when the file is not found it will stay as null and we can throw an
	 * error messsage.
	 */
	private static File file = null;
	/**
	 * here we store the flag value which will determine if we need to go into the
	 * try and catch once a file is properly selected
	 */
	private int val;

	/**
	 * Here is our Constructor that defines the behavior of the MenuBar
	 */
	public MenuBar() {

		FileChange = new JMenuItem("Open a File", KeyEvent.VK_T);
		FileChange.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
		FileChange.getAccessibleContext().setAccessibleDescription("Change the location of FileOutput");
		// Create the action listener for our MenuItem, that has error checking and a
		// text file filter that only excepts text files for input
		FileChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				final JFileChooser filechooser = new JFileChooser();
				final FileNameExtensionFilter docFilter = new FileNameExtensionFilter("Text Files", "txt");
				filechooser.setFileFilter(docFilter);
				val = filechooser.showOpenDialog(settings);
				if (val == JFileChooser.APPROVE_OPTION) {
					file = filechooser.getSelectedFile();
					try {
						if (file.getName().toLowerCase().endsWith(".txt") == false) {
							throw new Exception();
						}
						JOptionPane.showMessageDialog(settings, "File Upload is successful", "Success",
								JOptionPane.PLAIN_MESSAGE);

					} catch (Exception e) {
						JOptionPane.showMessageDialog(settings, "File is not a .txt file", "Error",
								JOptionPane.ERROR_MESSAGE);
						file = null;
					}

				}
			}
		});
		settings.add(FileChange);
		this.add(settings);
	}

	/**
	 * @return The File Object that the user selects
	 */
	public static File getFile() {
		return file;
	}
}
