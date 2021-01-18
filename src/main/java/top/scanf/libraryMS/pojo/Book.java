package top.scanf.libraryMS.pojo;

public class Book {
    private int id;
    private String title;
    private String author;
    private String ISBN;
    private Integer category_id;
    private String present;
    private Integer pub_year;
    private String language;
    private Integer leftAmount;

    public Book() {
    }

    public Book(String title, String author, String ISBN, Integer category_id, String present, Integer pub_year, String language, Integer leftAmount) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.category_id = category_id;
        this.present = present;
        this.pub_year = pub_year;
        this.language = language;
        this.leftAmount = leftAmount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return ISBN;
    }

    public void setIsbn(String isbn) {
        this.ISBN = isbn;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    public String getPresent() {
        return present;
    }

    public void setPresent(String present) {
        this.present = present;
    }

    public Integer getPub_year() {
        return pub_year;
    }

    public void setPub_year(Integer pub_year) {
        this.pub_year = pub_year;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getLeftAmount() {
        return leftAmount;
    }

    public void setLeftAmount(Integer leftAmount) {
        this.leftAmount = leftAmount;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + ISBN + '\'' +
                ", category_id=" + category_id +
                ", present='" + present + '\'' +
                ", pub_year=" + pub_year +
                ", language='" + language + '\'' +
                ", leftAmount=" + leftAmount +
                '}';
    }
}
