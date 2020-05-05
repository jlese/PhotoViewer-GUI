import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

//Homework 4, Jack Lesemann, jwl4vg

/**
 * Assumptions: - for this project, I assumed that any folder being used would
 * have 5 images
 * 
 * Citations: - Big JAVA Textbook, Oracle API, MIT Swing tutorials, any other
 * citations below
 * 
 * @author jackw
 *
 */
public class PhotoViewer extends JFrame {
	// instance variables
	JButton previous;
	JButton next;
	JRadioButton one;
	JRadioButton two;
	JRadioButton three;
	JRadioButton four;
	JRadioButton five;
	JLabel buttonLabel;
	JLabel imageLabel;

	// keeps track of photo index
	int currentIndex = 0;

	private PhotoContainer imageLibrary;

	/**
	 * imageLibrary setter
	 * 
	 * @param p | given PhotoContainer obj
	 */
	public void setImageLibrary(PhotoContainer p) {
		this.imageLibrary = p;
	}

	/**
	 * imageLibrary getter
	 * 
	 * @return current imageLibrary object
	 */
	public PhotoContainer getImageLibrary() {
		return this.imageLibrary;
	}

	/**
	 * 
	 * @param img
	 * @return
	 */
	public int[] scaleImage(BufferedImage img) {
		/**
		 * https://stackoverflow.com/questions/10245220/java-image-resize-maintain-aspect-ratio/45902544
		 * Credit: Ozzy
		 * 
		 * I used this stackoverflow thread to help me with the math of scaling down
		 * images while maintaining aspect ratios. I did not copy and paste any code
		 */
		// original dimensions
		int imgHeight = img.getHeight();
		int imgWidth = img.getWidth();
		double aspectRatio = imgHeight / imgWidth;

		int scaledHeight = 400;
		int scaledWidth = (int) Math.round(400 / aspectRatio);

		int[] Dimensions = { scaledWidth, scaledHeight };

		return Dimensions;
	}

	/**
	 * create image icon of current image
	 * 
	 * @param index | passed currentIndex
	 * @return ImageIcon image
	 */
	public ImageIcon createImageIcon(int index) {

		BufferedImage img = getImageLibrary().getPhotos().get(currentIndex).getImageData();

		int[] imgDimensions = scaleImage(img);
		ImageIcon image = new ImageIcon(img.getScaledInstance(imgDimensions[0], imgDimensions[1], Image.SCALE_DEFAULT));
		return image;
	}

	/**
	 * method to set button that matches current rating to visible
	 */
	public void displayRating() {

		/**
		 * https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/uiswing/examples/components/RadioButtonDemoProject/src/components/RadioButtonDemo.java
		 * 
		 * I used this code example to find the setSelected() method for RadioButtons
		 */
		int rating = getImageLibrary().getPhotos().get(currentIndex).getRating();

		// very bad code, I am sorry, it is 10:34 the night it is due
		if (rating == 1) {
			one.setSelected(true);
			two.setSelected(false);
			three.setSelected(false);
			four.setSelected(false);
			five.setSelected(false);
		} else if (rating == 2) {
			one.setSelected(false);
			two.setSelected(true);
			three.setSelected(false);
			four.setSelected(false);
			five.setSelected(false);
		} else if (rating == 3) {
			one.setSelected(false);
			two.setSelected(false);
			three.setSelected(true);
			four.setSelected(false);
			five.setSelected(false);
		} else if (rating == 4) {
			one.setSelected(false);
			two.setSelected(false);
			three.setSelected(false);
			four.setSelected(true);
			five.setSelected(false);
		} else if (rating == 5) {
			one.setSelected(false);
			two.setSelected(false);
			three.setSelected(false);
			four.setSelected(false);
			five.setSelected(true);
		}
	}

	public static void main(String[] args) {

		PhotoViewer myViewer = new PhotoViewer();

		String imageDirectory = "images\\";

		// create all photo objects
		Photo p1 = new Photo(imageDirectory + "jpg1.jpg", "a jpeg", "2020-01-01", 5);
		Photo p2 = new Photo(imageDirectory + "jpg2.jpg", "another jpeg", "2020-01-01", 4);
		Photo p3 = new Photo(imageDirectory + "jpg3.jpg", "also another jpeg", "2020-01-01", 3);
		Photo p4 = new Photo(imageDirectory + "jpg4.jpg", "oh look, another jpeg", "2020-01-01", 2);
		Photo p5 = new Photo(imageDirectory + "PNG1.PNG", "another png", "2020-01-01", 1);

		// load BufferedImage objects using loadImageData method from the Photo class
		p1.loadImageData(imageDirectory + "jpg1.jpg");
		p2.loadImageData(imageDirectory + "jpg2.jpg");
		p3.loadImageData(imageDirectory + "jpg3.jpg");
		p4.loadImageData(imageDirectory + "jpg4.jpg");
		p5.loadImageData(imageDirectory + "PNG1.PNG");

		myViewer.setImageLibrary(new Library("Library1", 1));

		// add photos to library
		myViewer.getImageLibrary().addPhoto(p1);
		myViewer.getImageLibrary().addPhoto(p2);
		myViewer.getImageLibrary().addPhoto(p3);
		myViewer.getImageLibrary().addPhoto(p4);
		myViewer.getImageLibrary().addPhoto(p5);

		javax.swing.SwingUtilities.invokeLater(() -> myViewer.createAndShowGUI());
	}

	public void addComponentsToPane(Container pane) {
		// ******Panel 1 -- holds image and JButtons******\\
		JPanel imagePanel = new JPanel();
		BorderLayout layout1 = new BorderLayout();

		imagePanel.setLayout(layout1);

		// add image to Panel 1
		ImageIcon image = createImageIcon(currentIndex);
		imageLabel = new JLabel(image);
		pane.add(imageLabel, BorderLayout.NORTH);

		// previous button action
		class PreviousButtonListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("click")) {
					if (currentIndex > 0) {
						currentIndex -= 1;
						ImageIcon image = createImageIcon(currentIndex);
						imageLabel.setIcon(image);
						displayRating();
					} else if (currentIndex == 0) {
						currentIndex = 4;
						ImageIcon image = createImageIcon(currentIndex);
						imageLabel.setIcon(image);
						displayRating();
					}

				}

			}
		}

		// add previous to Panel 1
		previous = new JButton("Previous");
		previous.setActionCommand("click");
		previous.addActionListener(new PreviousButtonListener());
		pane.add(previous, BorderLayout.WEST);

		// next button action
		class NextButtonListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("click")) {
					if (currentIndex < 4) {
						currentIndex += 1;
						ImageIcon image = createImageIcon(currentIndex);
						imageLabel.setIcon(image);
						displayRating();
					} else if (currentIndex == 4) {
						currentIndex = 0;
						ImageIcon image = createImageIcon(currentIndex);
						imageLabel.setIcon(image);
						displayRating();
					}
				}

			}
		}

		// add next to Panel 1
		next = new JButton("Next");
		next.setActionCommand("click");
		next.addActionListener(new NextButtonListener());
		pane.add(next, BorderLayout.EAST);

		// Panel 2 -- holds JRadioButtons
		JPanel buttonPanel = new JPanel();

		FlowLayout layout2 = new FlowLayout();
		buttonPanel.setLayout(layout2);

		// one button action
		class OneButtonListener implements ActionListener {

			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("click")) {
					getImageLibrary().getPhotos().get(currentIndex).setRating(1);
				}
			}

		}

		// create one button
		one = new JRadioButton("1");
		one.setActionCommand("click");
		one.addActionListener(new OneButtonListener());

		// two button action
		class TwoButtonListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("click")) {
					getImageLibrary().getPhotos().get(currentIndex).setRating(2);
				}
			}

		}

		// create two button
		two = new JRadioButton("2");
		two.setActionCommand("click");
		two.addActionListener(new TwoButtonListener());

		// three button action
		class ThreeButtonListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("click")) {
					getImageLibrary().getPhotos().get(currentIndex).setRating(3);
				}
			}
		}

		// create three button
		three = new JRadioButton("3");
		three.setActionCommand("click");
		three.addActionListener(new ThreeButtonListener());

		// four button action
		class FourButtonListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("click")) {
					getImageLibrary().getPhotos().get(currentIndex).setRating(4);
				}
			}

		}

		// create four button
		four = new JRadioButton("4");
		four.setActionCommand("click");
		four.addActionListener(new FourButtonListener());

		// four button action
		class FiveButtonListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("click")) {
					getImageLibrary().getPhotos().get(currentIndex).setRating(5);
				}
			}

		}

		// create five button
		five = new JRadioButton("5");
		five.setActionCommand("click");
		five.addActionListener(new FiveButtonListener());

		// create button group
		ButtonGroup group = new ButtonGroup();
		group.add(one);
		group.add(two);
		group.add(three);
		group.add(four);
		group.add(five);

		// add to button panel
		buttonPanel.add(one);
		buttonPanel.add(two);
		buttonPanel.add(three);
		buttonPanel.add(four);
		buttonPanel.add(five);

		// add to main panel
		imagePanel.add(buttonPanel, BorderLayout.SOUTH);

		displayRating();
		pane.add(imagePanel);
		pane.add(buttonPanel);

	}

	// set details of GUI
	public void createAndShowGUI() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(720, 576);
		this.setLocationRelativeTo(null);

		addComponentsToPane(getContentPane());
		this.pack();
		this.setVisible(true);

	}
}