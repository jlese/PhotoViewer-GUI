import java.util.ArrayList;

public abstract class PhotoContainer {
    /**
     * A String containing the PhotoContainer’s name in whatever form it was provided
     */
    protected String name;

    /**
     * A HashSet of Photo objects in the PhotoContainer
     */
    protected ArrayList<Photo> photos;

    /**
     * PhotoContainer object contstructor
     * 
     * @param n | String name of object
     */
    public PhotoContainer(String n) {
        this.name = n;
        this.photos = new ArrayList<Photo>();
    }

    /**
     * Name getter
     * 
     * @return name of current object
     */
    public String getName() {
        return this.name;
    }

    /**
     * Name setter
     * 
     * @param n | string that name is assigned to
     */
    public void setName(String n) {
        this.name = n;
    }

    /**
     * Photos getter
     * 
     * @return current photos ArrayList
     */
    public ArrayList<Photo> getPhotos() {
        return this.photos;
    }

    /**
     * get photos from photo stream that match the given rating
     * 
     * @param rating | rating to be searched for
     * @return ArrayList of Photo objects that have the desired rating
     */
    public ArrayList<Photo> getPhotos(int rating) {
        if (rating <= 5 && rating >= 1) { // valid rating check
            ArrayList<Photo> photoRated = new ArrayList<Photo>();

            for (Photo p : this.photos) {
                if (p.getRating() >= rating) {
                    photoRated.add(p);
                } else {
                    continue;
                }
            }
            return photoRated;

        } else {
            return null;
        }
    }

    /**
     * get photos from photo stream from a certain year
     * 
     * @param year | year to be searched for
     * @return ArrayList of Photo objects that are from desired year
     */
    public ArrayList<Photo> getPhotosInYear(int year) {
        if (year <= 9999 && year >= 0000) { // valid year check
            ArrayList<Photo> photosOfGivenYear = new ArrayList<Photo>();

            for (Photo p : this.photos) {
                String date = p.getDateTaken();
                int pYear = Integer.parseInt(date.substring(0, 4)); // convert year to int
                if (pYear == year) {
                    photosOfGivenYear.add(p);
                } else {
                    continue;
                }

            }
            return photosOfGivenYear;
        } else {
            return null;
        }
    }

    /**
     * get photos from photo stream from a certain month
     * 
     * @param month | month to be searched for
     * @param year  | year to be searched for
     * @return ArrayList of Photo objects that are from desired month of desired year
     */
    public ArrayList<Photo> getPhotosInMonth(int month, int year) {

        if (month <= 12 && month >= 1 && year <= 9999 && year >= 0000) { // valid month and year check
            ArrayList<Photo> photosWithYear = getPhotosInYear(year); // use getPhotosInYear method to find all photos in year
            ArrayList<Photo> photosOfGivenMonth = new ArrayList<Photo>();

            for (Photo p : photosWithYear) { // iterate through all photos of year and find photos from desired month
                String date = p.getDateTaken();
                int pMonth = Integer.parseInt(date.substring(5, 7));
                if (pMonth == month) {
                    photosOfGivenMonth.add(p);
                } else {
                    continue;
                }

            }
            return photosOfGivenMonth;
        } else {
            return null;
        }

    }

    /**
     * get photos that are from a time between two given dates
     * 
     * @param beginDate | 1st bound
     * @param endDate   | 2nd bound
     * @return ArrayList of Photo objects that are from desired time period
     */
    public ArrayList<Photo> getPhotosBetween(String beginDate, String endDate) {
        if (DateLibrary.isValidDate(beginDate) && DateLibrary.isValidDate(endDate)
                && DateLibrary.compare(beginDate, endDate) < 0) { // validity check
            ArrayList<Photo> photosBetween = new ArrayList<Photo>();

            for (Photo p : this.photos) { // use string compare to compare date order
                int resultBefore = DateLibrary.compare(beginDate, p.getDateTaken());

                int resultAfter = DateLibrary.compare(p.getDateTaken(), endDate);

                if (resultBefore < 0 && resultAfter < 0) { // if compare returned two negative values
                    photosBetween.add(p);
                } else {
                    continue;
                }
            }

            return photosBetween;
        } else {
            return null;
        }
    }

    /**
     * If a Photo p is already in ArrayList, return false, otherwise add Photo p to ArrayList
     * 
     * @param p
     * @return
     */
    public boolean addPhoto(Photo p) {
        if (p == null) { // null check
            return false;
        }

        if (this.photos.contains(p)) {
            return false;
        } else {
            this.photos.add(p);
            return true;
        }
    }

    /**
     * Checks to see if current photo ArrayList contains Photo p
     * 
     * @param p | Photo object
     * @return true or false depending on whether ArrayList has photo
     */
    public boolean hasPhoto(Photo p) {
        if (this.photos.contains(p)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks to see if current photo ArrayList contains Photo p, if so, p is removed
     * 
     * @param p | Photo object
     * @return true or false depending on whether ArrayList was removed
     */
    public boolean removePhoto(Photo p) {
        if (this.photos.contains(p)) {
            this.photos.remove(p);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Size of Photo ArrayList getter
     * 
     * @return int of size of ArrayList
     */
    public int numPhotos() {
        return this.photos.size();
    }

    /**
     * equals() method for PhotoContainer objects, compares name of album
     */
    public boolean equals(Object o) {
        if (o == null) { // null check
            return false;
        }

        if (o instanceof PhotoContainer) { // must be Album object
            PhotoContainer pc = (PhotoContainer) o;
            if (this.name.equals(pc.name)) {
                return true;
            } else {
                return false;
            }

        } else {
            return false;
        }
    }

    /**
     * toString() for PhotoContainer objects
     */
    public String toString() {
        return "NAME: " + this.name + " | PHOTOS: " + this.photos;

    }

    /**
     * hashCode()
     * 
     * @return int hashCode created from PhotoContainer name
     */
    public int hashCode() {
        int hash = 0;
        String str = this.name;

        for (int i = 0; i < str.length(); i++) {
            hash += str.charAt(i);
        }

        return hash % 3;
    }
}
