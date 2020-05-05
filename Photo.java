import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Homework 1 - A Jack Lesemann, jwl4vg Sources: Big Java Book
 */
public class Photo implements Comparable<Photo>{
    /**
     * constant caption field as a string
     */
    private final String CAPTION;

    /**
     * constant filename field as a string
     */
    private final String FILENAME;

    /**
     * rating integer
     */
    private int rating;

    /**
     * dateTaken string
     */
    private final String dateTaken;
    
    /**
     * image data
     */
    protected BufferedImage imageData;
    

    /**
     * Photo object constructor method
     * 
     * @param file    | create new object with this filename
     * @param caption | create new object with this caption
     */
    public Photo(String file, String caption) {
        this.rating = 1;
        this.dateTaken = "1901-01-01";
        this.FILENAME = file;
        this.CAPTION = caption;
    }

    /**
     * overload Photo object constructor
     * 
     * @param file      | create new object with this filename
     * @param caption   | create new object with this caption
     * @param dateTaken | create new object with this date if date is valid, otherwise use default
     * @param r         | create new object with this rating if rating is valid, otherwise use default
     */
    public Photo(String file, String caption, String dateTaken, int r) {
        if ((r <= 5) && (r >= 1)) { // valid rating check
            this.rating = r;
        } else {
            this.rating = 1;
        }

        if ((DateLibrary.isValidDate(dateTaken))) { // valid date check
            this.dateTaken = dateTaken;
        } else {
            this.dateTaken = "1901-01-01";
        }
        this.FILENAME = file;
        this.CAPTION = caption;
    }

    /**
     * Rating mutator method for setting a new rating as long as new rating is between 1 & 5 and is different than current
     * rating
     * 
     * @param newRating | newRating given for photo
     * @return returns true if newRating was applied
     */
    public boolean setRating(int newRating) {
        if (this.rating != newRating) {
            if ((newRating <= 5) && (newRating >= 1)) {
                this.rating = newRating;
                return true;
            } else {
                return false;
            }

        } else {
            return false;
        }
    }
    
    /**
     * imageData mutator
     * 
     * @param newData | newData given for photo
     * 
     */
    public void setImageData(BufferedImage img) {
    	this.imageData = img;
    }
    /**
     * equals method for comparing two photo objects' captions and filenames
     * 
     * @param o | passed object
     * @return returns true if objects are equal
     */
    public boolean equals(Object o) {
        if (o == null) // null check
            return false;

        if (o instanceof Photo) { // must be Photo object
            Photo p2 = (Photo) o;
            if (this.CAPTION.equals(p2.CAPTION) && this.FILENAME.equals(p2.FILENAME)) {
                return true;
            } else {
                return false;
            }

        } else {
            return false;
        }

    }

    /**
     * toString method that converts current photo object into string
     * 
     * @return returns a string that shows photo's filename, caption, and rating
     */
    public String toString() {
        return "FILENAME: " + this.FILENAME + " | CAPTION: " + this.CAPTION + " | Rating: " + this.rating;
    }

    /**
     * caption getter
     * 
     * @return returns current photo's caption
     */
    public String getCaption() {
        return this.CAPTION;
    }

    /**
     * filename getter
     * 
     * @return returns current photo's filename
     */
    public String getFilename() {
        return this.FILENAME;
    }

    /**
     * rating getter
     * 
     * @return returns current photo's rating
     */
    public int getRating() {
        return this.rating;
    }

    /**
     * dateTaken getter
     * 
     * @return
     */
    public String getDateTaken() {
        return this.dateTaken;
    }

    /**
     * imageData getter
     */
    
    public BufferedImage getImageData() {
    	return this.imageData;
    }
    /**
     * hashCode producer
     * 
     * @param str | takes filename
     * @return
     */
    public int hashCode() {
        return this.FILENAME.hashCode();
    }
    
    public boolean loadImageData(String filename) {
    	try
    	{
    		BufferedImage img = ImageIO.read(new File(filename));
    		this.imageData = img;
    		return true;
    	}
    	catch (IOException exception)
    	{
    		return false;
    	}
    	
    }

    @Override
    public int compareTo(Photo p) {
        int retVal = this.dateTaken.compareTo(p.dateTaken);
        if (retVal != 0) return this.dateTaken.compareTo(p.dateTaken);
        return this.CAPTION.compareTo(p.CAPTION);
    }

    public static void main(String[] args) {

        Photo newPhoto = new Photo("landscape.jpg", "A nice mountain range with blue sky");
        //Photo newerPhoto = new Photo("Blah blah", "dfmgmwigwt", "2005-10-12", 3);
        // System.out.println(newerPhoto.dateTaken);

        System.out.println(newPhoto.hashCode());
        /**
         * // test setRating method with a valid rating, EXPECT TRUE //System.out.println(newPhoto.setRating(4));
         * System.out.println(newPhoto.getCaption()); System.out.println(newPhoto.getFilename()); // test setRating method with
         * an invalid rating, EXPECT FALSE System.out.println(newPhoto.setRating(10)); // test toString, EXPECT STRING OUTPUT
         * System.out.println(newPhoto.toString()); // test equals with identical objects, EXPECT TRUE
         * System.out.println(newPhoto.equals(newPhoto)); // test equals with different objects, EXPECT FALSE Photo secondPhoto
         * = new Photo("sunset.png", "Beautiful beach sunset"); System.out.println(secondPhoto.equals(newPhoto));
         * System.out.println(secondPhoto.getCaption()); System.out.println(secondPhoto.getFilename());
         */
    }

    
    
}
