
/**
 * Homework 1 - B; Jack Lesemann; jwl4vg; Sources - Big Java Book
 */

import java.util.ArrayList;
import java.util.HashSet;

public class Library extends PhotoContainer {
    
    /**
     * Integer unique id of Library object
     */
    private final int id;

    /**
     * HashSet of all albums that user has created
     */
    private HashSet<Album> albums;

    /**
     * Library object constructor method
     * 
     * @param n | creates new object with name 'n'
     * @param i | creates new object with id 'i'
     */
    public Library(String n, int i) { 
        super(n);
        id = i;
        albums = new HashSet<Album>();

    }

    /**
     * Albums getter
     * 
     * @return | returns current Albums HashSet
     */
    public HashSet<Album> getAlbums() {
        return this.albums;
    }

    /**
     * ID getter method
     * 
     * @return | returns current Library's ID
     */
    public int getId() {
        return this.id;
    }



    /**
     * Checks to see if ArrayList photos has given Photo p, if present, Photo p is deleted
     * 
     * @param p | photo object that needs to be deleted
     * @return true or false depending on whether Photo p was deleted
     */
    public boolean removePhoto(Photo p) {
        int removed = 0;
        for (Album a : this.albums) { // check every album
            if (a.hasPhoto(p)) {
                a.removePhoto(p);
                removed = 1; // set removed to 1 if photo is removed
            } else {
                continue;
            }
        }

        if (this.photos.contains(p)) { // check photo stream
            this.photos.remove(p);
            removed = 1; // set removed to 1 if photo is removed
        }

        if (removed == 1) {
            return true;
        } else {
            return false;
        }

    }

    
    /**
     * Equals method to compare two PhotoLirary objects
     * 
     * @return returns true or false depending on equality of Library objects
     */
    public boolean equals(Object o) {
        if (o == null) // null check
            return false;

        if (o instanceof Library) { // must be Library object
            Library newP = (Library) o;
            if (newP.id == this.id) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * toString method for Library object
     * 
     * @return Library fields in String form
     */
    public String toString() {
        return "Name: " + this.name + " |  ID#: " + this.id + " |  Photos: " + this.photos + " |  Albums: " + this.albums;
    }

    /**
     * Compares two Library objects to see if they share photos
     * 
     * @param a | first Library object
     * @param b | second Library object
     * @return ArrayList of common Photos
     */
    public static ArrayList<Photo> commonPhotos(Library a, Library b) {

        ArrayList<Photo> common = new ArrayList<Photo>();

        for (Photo itemA : a.photos) {
            for (Photo itemB : b.photos) {
                if (itemA.equals(itemB)) {
                    common.add(itemB);
                }
            }

        }
        return common;

    }

    /**
     * Supplies a measure of similarity between two Library objects
     * 
     * @param a | first Library object
     * @param b | second Library object
     * @return double numerical value between 1 and 0
     */
    public static double similarity(Library a, Library b) {
        double sizeA = a.getPhotos().size();
        double sizeB = b.getPhotos().size();

        if (sizeA == 0.0 || sizeB == 0.0) { // return 0.0 if either library is empty
            return 0.0;
        } else {
            int size = commonPhotos(a, b).size();
            if (sizeA < sizeB) { // return amount of common photos divided by the smaller library
                return size / sizeA;
            } else {
                return size / sizeB;
            }
        }

    }

    

    

    

    

    /**
     * create album with name albumName if there is none already
     * 
     * @param albumName | given album name
     * @return true or false depending if album was created
     */
    public boolean createAlbum(String albumName) {
        for (Album a : this.albums) { // presence of album check
            if (a.getName() == albumName) {
                return false;
            }
        }

        Album newAlbum = new Album(albumName);
        this.albums.add(newAlbum);
        return true;
    }

    /**
     * remove album if album with albumName exists
     * 
     * @param albumName | given album name
     * @return true or false depending if album was removed
     */
    public boolean removeAlbum(String albumName) {
        for (Album a : this.albums) {
            if (a.getName() == albumName) {
                this.albums.remove(a);
                return true;
            }
        }

        return false;
    }

    /**
     * add Photo p to Album with albumName if photo exists in stream and is not in album already
     * 
     * @param p         | given photo object
     * @param albumName | given album name
     * @return true or false depending if photo was added
     */
    public boolean addPhotoToAlbum(Photo p, String albumName) {
        if (this.photos.contains(p)) { // photo presence check
            for (Album a : this.albums) {
                if (a.getName() == albumName && !a.hasPhoto(p)) {
                    a.addPhoto(p);
                    return true;
                } else {
                    continue;
                }
            }
        }
        return false;

    }

    /**
     * remove Photo p from album with albumName if photo exists in that album
     * 
     * @param p         | given photo object
     * @param albumName | given album name
     * @return true or false depending if photo was removed
     */
    public boolean removePhotoFromAlbum(Photo p, String albumName) {
        for (Album a : this.albums) {
            if (a.getName() == albumName && a.hasPhoto(p)) {
                a.removePhoto(p);
                return true;
            }
        }
        return false;
    }

    /**
     * get album of name albumName
     * 
     * @param albumName | given album name
     * @return album with albumName or null
     */
    private Album getAlbumByName(String albumName) {
        for (Album a : this.albums) {
            if (a.getName() == albumName) {
                return a;
            }
        }
        return null;
    }

    public static void main(String[] args) {
    	 /**
        Library newLibrary = new Library("My Library", 1111);
        Library emptyLibrary = new Library("Empty", 0000);
        Library sameLibrary = new Library("Another Library", 1212);

        Photo firstPhoto = new Photo("landscape.jpg", "A nice mountain range with blue sky");
        Photo secondPhoto = new Photo("sunset.png", "Pretty sunset with palm trees");
        Photo thirdPhoto = new Photo("flower.jpg", "Macro shot of lily");

        Photo samePhoto = new Photo("landscape.jpg", "A nice mountain range with blue sky");

        newLibrary.addPhoto(firstPhoto);
        newLibrary.addPhoto(secondPhoto);
       
       
        // test addPhoto method with valid Photo object; EXPECT TRUE
        System.out.println(newLibrary.addPhoto(firstPhoto));
        System.out.println(sameLibrary.addPhoto(firstPhoto));

        // test toString when Library contains one Photo object;
        // EXPECT Name: | ID#: 1111 | Photos: [FILENAME: landscape.jpg | CAPTION: A nice mountain range with blue sky | Rating:
        // 1]
        System.out.println(newLibrary.toString());

        // test addPhoto method with different but invalid Photo object; EXPECT FALSE
        System.out.println(newLibrary.addPhoto(samePhoto));

        // test addPhoto method with same and invalid Photo object; EXPECT FALSE
        System.out.println(newLibrary.addPhoto(firstPhoto));

        // test addPhoto method with different and valid Photo object; EXPECT TRUE
        System.out.println(newLibrary.addPhoto(secondPhoto));

        // test hasPhoto with identical photo; EXPECT TRUE;
        System.out.println(newLibrary.hasPhoto(firstPhoto));

        // test hasPhoto with different photo; EXPECT FALSE;
        System.out.println(newLibrary.hasPhoto(thirdPhoto));

        // test deletePhoto with deletable photo; EXPECT TRUE;
        System.out.println(newLibrary.deletePhoto(secondPhoto));

        // test deletePhoto with non-deletable photo; EXPECT FALSE
        System.out.println(newLibrary.deletePhoto(thirdPhoto));

       
        // test numPhotos; EXPECT 1
        System.out.println(newLibrary.numPhotos());

        // test numPhotos; EXPECT 0
        System.out.println(emptyLibrary.numPhotos());

       
        // test equals with equal objects; EXPECT TRUE
        System.out.println(newLibrary.equals(newLibrary));

        // test equals with different objects; EXPECT FALSE
        System.out.println(newLibrary.equals(emptyLibrary));

        
        // test toString with full; EXPECT Name: | ID#: 1111 | Photos: [FILENAME: landscape.jpg | CAPTION: A nice mountain range
        // with blue sky | Rating: 1]
        System.out.println(newLibrary.toString());

        // test toString with empty; EXPECT Name: | ID#: 0 | Photos: []
        System.out.println(emptyLibrary.toString());

        
        // test commonPhotos with two libraries with common photos; EXPECT [FILENAME: landscape.jpg | CAPTION: A nice mountain
        // range with blue sky | Rating: 1]
        System.out.println(commonPhotos(newLibrary, sameLibrary));

        // test commonPhotos with no commons; EXPECT []
        System.out.println(commonPhotos(newLibrary, emptyLibrary));

        
        // test similarity with similar libraries; EXPECT 1.0
        System.out.println(similarity(newLibrary, sameLibrary));

        // test similarity with different libraries; EXPECT 0.0
        System.out.println(similarity(newLibrary, emptyLibrary));
        */
    }

}
