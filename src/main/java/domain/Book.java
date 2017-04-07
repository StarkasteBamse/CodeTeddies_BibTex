package domain;

public class Book extends Reference{

    public String author;
    public String title;
    public String publisher;
    public String year;

    public Book() {

    }
//CHECKSTYLE:OFF

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public boolean hasRequiredFields() {
        if (this.author == null) {
            return false;
        } else if (this.title == null) {
            return false;
        } else if (this.publisher == null) {
            return false;
        } else if (this.year == null) {
            return false;
        } else {
            return true;
        }
    }
//CHECKSTYLE:ON

}
