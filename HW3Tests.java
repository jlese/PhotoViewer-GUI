import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import org.junit.Before;
import org.junit.Test;

public class HW3Tests {
    Library l1, l2, l3, l4;
    Album a1, a2, a3, a3Similar;
    Photo pValid1, pValid2, pValid3, pValid4, pValid5, pValid6, pValid7, pInValid1, pInValid2;
    
    @Before
    public void SetUp() {
        l1 = new Library("Library 1", 1000);
        l2 = new Library("Library 2", 1001);
        l3 = new Library("Library 3", 1002);
        l4 = new Library("Library 4", 1003);


        
        a1 = new Album("Jack's Birthday");
        a2 = new Album("Landscapes");
        a3 = new Album("Random");
        a3Similar = new Album ("Misc");
        
        pValid1 = new Photo("cake.png", "blowing out candles", "2003-01-25", 3);
        pValid2 = new Photo("mountains.png", "blue ridge", "2009-10-24", 4);
        pValid3 = new Photo("dog.png", "cute dog", "2015-07-29", 2);
        pValid4 = new Photo("presents.jpg", "opening presents", "2003-01-24", 4);
        pValid5 = new Photo("desert.jpg", "lonely cactus", "2020", 2);
        pValid6 = new Photo("something.png", "____", "2015-01-02", 4);
        pValid7 = new Photo("?.png", "____", "2005-10-14", 3);
        
        pInValid1 = new Photo("****", "****", "123-4-56", 3);
        pInValid2 = new Photo("****", "****", "1234-12-12", 7);
        
        
    }
    @Test
    public void testGetPhotos() {
        l1.addPhoto(pValid1);
        l1.addPhoto(pValid2);

        //create received arraylist
        ArrayList<Photo> photosReceived = new ArrayList<Photo>();
        photosReceived = l1.getPhotos(4)
        		
        
        //expected arraylist
        ArrayList<Photo> photosExpected = new ArrayList<Photo>();
        photosExpected.add(pValid2);
        
        //test for proper valid output
        assertEquals("Expected ArrayList not returned", photosExpected, photosReceived);
        
        
        ArrayList<Photo> expectNull = new ArrayList<Photo>();
        expectNull = l1.getPhotos(7);
        
        //test for proper invalid output
        assertNull("Did not return null when given invalid ratiing", expectNull);
    }
    
    @Test
    public void testGetPhotosInMonth() {
        l1.addPhoto(pValid1);
        l1.addPhoto(pValid4);
        
        //create received arraylist
        ArrayList<Photo> photosJanuaryRec = new ArrayList<Photo>();
        photosJanuaryRec = l1.getPhotosInMonth(1, 2003);
        
        //create expected arraylist
        ArrayList<Photo> photosJanuaryExp = new ArrayList<Photo>();
        photosJanuaryExp.add(pValid1);
        photosJanuaryExp.add(pValid4);
        
        //test for output with valid inputs
        assertEquals("Did not return photos in same month", photosJanuaryExp, photosJanuaryRec);
        
        
        ArrayList<Photo> photosExpectNull = new ArrayList<Photo>();
        photosExpectNull = l1.getPhotosInMonth(0000, 10);
        
        //test for output with invalid inputs
        assertNull("Did not return null when dates were invalid", photosExpectNull);    
    }
    
    @Test
    public void testGetPhotosBetween() {
        l1.addPhoto(pValid1);
        l1.addPhoto(pValid2);
        l1.addPhoto(pValid3);
        
        //create received arraylist
        ArrayList<Photo> photosBetweenAct = new ArrayList<Photo>();
        photosBetweenAct = l1.getPhotosBetween("2009-01-01", "2021-01-01");
        
        //create expected arraylist
        ArrayList<Photo> photosBetweenExp = new ArrayList<Photo>();
        photosBetweenExp.add(pValid2);
        photosBetweenExp.add(pValid3);
        
        //test for output with valid inputs
        assertEquals("Did not return correct photos between given date", photosBetweenExp, photosBetweenAct);
        
        
        ArrayList<Photo> photosNull = new ArrayList<Photo>();
        photosNull = l1.getPhotosBetween("2009-****", "2021-01-01");
        
        //test for output with invalid inputs
        assertNull("Did not return null when given invalid dates", photosNull);

    }
    
    /*
    @Test
    public void testDeletePhoto() {
        
        l1.addPhoto(pValid1);
        l1.addPhoto(pValid2);
        l1.addPhoto(pValid3);
        
        l1.createAlbum("Album1");
        l1.addPhotoToAlbum(pValid2, "Album1");
        
        //test for output with photo that can be deleted
        //assertTrue("Did not return true", l1.deletePhoto(pValid2));
        
        //test for output with photo that cannot be deleted
        //assertFalse("Did not return false", l1.deletePhoto(pInValid1));
        
    }
    */
    
    @Test
    public void testSimilarity() {
        l1.addPhoto(pValid1);
        l1.addPhoto(pValid2);
        l1.addPhoto(pValid3);
        
        l2.addPhoto(pValid1);
        l2.addPhoto(pValid2);
        
        assertEquals("Did not return proper ratio of shared photos", 1.0, Library.similarity(l1, l2), .0001);
        
        assertEquals("Did not return 0.0 when libraries were empty", 0.0, Library.similarity(l3, l4), .0001);
        
    }

    @Test
    public void testRemovePhoto() {
        l1.addPhoto(pValid1);
        l1.addPhoto(pValid2);
        l1.addPhoto(pValid3);
        
        l1.createAlbum("Album1");
        l1.addPhotoToAlbum(pValid2, "Album1");
        
        //test for output with photo that can be deleted
        assertTrue("Did not return true", l1.removePhoto(pValid2));
        
        //test for output with photo that cannot be deleted
        assertFalse("Did not return false", l1.removePhoto(pInValid1));
    }
    
    @Test
    public void testCompareTo() {
        int num = pValid1.compareTo(pValid2);
        System.out.println(num);
        boolean result = num < 0;
        
        assertTrue("Did not return correct sign when photos were taken at different dates", result);
    }
    
    @Test
    public void testCompareCaption() {
        ArrayList<Photo> list1 = new ArrayList<>();
        list1.add(pValid1);
        list1.add(pValid2);
        list1.add(pValid3);
        
        Collections.sort(list1, new PhotoCaptionComparator());
        
        assertEquals("Did not return correct sign when photo captions were different", list1.get(0), pValid1);
        assertEquals("Did not return correct sign when photo captions were different", list1.get(1), pValid2);
        
        
    }
    
    @Test
    public void testCompareRating() {
        ArrayList<Photo> list1 = new ArrayList<>();
        list1.add(pValid1);
        list1.add(pValid2);
        list1.add(pValid3);
        
        Collections.sort(list1, new PhotoRatingComparator());
        System.out.println(list1);
        
        assertEquals("Did not return correct sign when photo captions were different", list1.get(0), pValid2);
        assertEquals("Did not return correct sign when photo captions were different", list1.get(1), pValid1);
    }
}
