import java.util.Comparator;

public class PhotoRatingComparator implements Comparator<Photo>{
    
    /**
     * comparator for two photo objects, compares rating first then caption
     */
    @Override
    public int compare(Photo o1, Photo o2) {
        int retVal = o2.getRating() - o1.getRating();
        if (retVal != 0) return retVal;
        return o1.getCaption().compareTo(o2.getCaption());
    }

}
