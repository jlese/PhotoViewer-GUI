import java.util.ArrayList;


/**
 * Homework 2 - B; Jack Lesemann, jwl4vg
 * 
 * @author jackw
 */
public class Album extends PhotoContainer{

    /**
     * Album constructor
     * 
     * @param n
     */
    public Album(String n) {
        super(n);
        this.name = n;
        this.photos = new ArrayList<Photo>();

    }

    

}
