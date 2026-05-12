package models;

import enums.BookStatus;
import enums.Category;

public class Book {
    private int bookId;
    private String title;
    private Author author;
    private double price;
    private Category category;
    private BookStatus status;

    public Book(int bookId, String title, Author author, double price, Category category) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.price = price;
        this.category = category;
        this.status = BookStatus.AVAILABLE;
    }

    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    public void display() {
        System.out.println("ID: " + bookId
                + " | " + title
                + " | Yazar: " + author.getName()
                + " | Fiyat: " + price + " TL"
                + " | Kategori: " + category
                + " | Durum: " + status);
    }
}
