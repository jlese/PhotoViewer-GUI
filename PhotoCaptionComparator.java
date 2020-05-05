import java.util.Comparator;

public class PhotoCaptionComparator implements Comparator<Photo>{

    /**
     * comparator for two photo objects, compares caption first then rating
     */
    @Override
    public int compare(Photo o1, Photo o2) {
        int retVal = o1.getCaption().compareTo(o2.getCaption());
        if (retVal != 0) return retVal;
        return o2.getRating() - o1.getRating();
    }

}
