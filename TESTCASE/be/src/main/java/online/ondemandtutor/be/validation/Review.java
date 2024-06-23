package online.ondemandtutor.be.validation;

public class Review {
    private int id;
    private String content;
    private String author;

    public Review(int id, String content, String author) {
        this.id = id;
        this.content = content;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }
}
