package models;

import enums.Category;

import java.util.List;

public class Librarian extends Person {

    private String password;
    private final Library library;

    public Librarian(int id, String name, String password, Library library) {
        super(id, name);
        this.password = password;
        this.library = library;
    }

    public boolean verifyPassword(String text) {
        return this.password.equals(text);
    }

    public void addBook(Book book) {
        library.addBook(book);
    }

    public Book findById(int bookId) {
        return library.findById(bookId);
    }

    public List<Book> findByTitle(String title) {
        return library.findByTitle(title);
    }

    public List<Book> findByAuthor(String authorName) {
        return library.findByAuthor(authorName);
    }

    public List<Book> findByCategory(Category category) {
        return library.findByCategory(category);
    }

    public boolean updateBook(int bookId, String newTitle, double newPrice, Category newCategory) {
        return library.updateBook(bookId, newTitle, newPrice, newCategory);
    }

    public boolean removeBook(int bookId) {
        return library.removeBook(bookId);
    }

    public void registerMember(Member member) {
        library.registerMember(member);
    }

    public boolean lendBook(int bookId, int memberId) {
        return library.lendBook(bookId, memberId);
    }

    public boolean returnBook(int bookId) {
        return library.returnBook(bookId);
    }

    public List<Book> getAllBooks() {
        return library.getAllBooks();
    }

    public List<Member> getAllMembers() {
        return library.getAllMembers();
    }

    public List<BorrowRecord> getActiveBorrows() {
        return library.getActiveBorrows();
    }

    public List<Invoice> getAllInvoices() {
        return library.getAllInvoices();
    }

    @Override
    public String whoYouAre() {
        return "Librarian: " + getName();
    }
}
