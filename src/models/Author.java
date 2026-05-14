package models;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Author {
    private int id;
    private String name;
    private Set<Book> books;

    public Author(int id, String name) {
        this.id = id;
        this.name = name;
        this.books = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Book> getBooks() {
        return new HashSet<>(books);
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Author)) return false;
        return this.id == ((Author) o).id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Author{id=" + id + ", name='" + name + "', bookCount=" + books.size() + "}";
    }
}
