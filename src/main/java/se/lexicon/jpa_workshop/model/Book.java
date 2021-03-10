package se.lexicon.jpa_workshop.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int bookId;
    String isbn;
    String title;
    int maxLoanDays;
    @ManyToMany(cascade = {CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.LAZY)
    @JoinTable(name = "author_id_book_id", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "author_id"))
    Set<Author> authors;

    public Book(int bookId, String isbn, String title, int maxLoanDays) {
        this.bookId = bookId;
        this.isbn = isbn;
        this.title = title;
        this.maxLoanDays = maxLoanDays;
    }

    public Book() {
    }

    public int getBookId() {
        return bookId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getMaxLoanDays() {
        return maxLoanDays;
    }

    public void setMaxLoanDays(int maxLoanDays) {
        this.maxLoanDays = maxLoanDays;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return maxLoanDays == book.maxLoanDays &&
                Objects.equals(isbn, book.isbn) &&
                Objects.equals(title, book.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn, title, maxLoanDays);
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", maxLoanDays=" + maxLoanDays +
                '}';
    }

}
