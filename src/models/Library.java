package models;

import enums.BookStatus;
import enums.Category;

import java.util.*;

public class Library {
    private String name;
    private Map<Integer, Book> books;
    private Map<Integer, Member> members;
    private Set<Author> authors;
    private Map<Integer, BorrowRecord> activeBorrows;
    private List<Invoice> allInvoices;

    public Library(String name) {
        this.name = name;
        this.books = new HashMap<>();
        this.members = new HashMap<>();
        this.authors = new HashSet<>();
        this.activeBorrows = new HashMap<>();
        this.allInvoices = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addBook(Book book) {
        books.put(book.getBookId(), book);
        authors.add(book.getAuthor());
        book.getAuthor().addBook(book);
    }

    public boolean removeBook(int bookId) {
        Book book = books.get(bookId);
        if (book == null) return false;
        if (book.getStatus() == BookStatus.BORROWED) return false;
        books.remove(bookId);
        book.getAuthor().removeBook(book);
        return true;
    }

    public boolean updateBook(int bookId, String newTitle, double newPrice, Category newCategory) {
        Book book = books.get(bookId);
        if (book == null) return false;
        book.setTitle(newTitle);
        book.setPrice(newPrice);
        book.setCategory(newCategory);
        return true;
    }

    public Book findById(int bookId) {
        return books.get(bookId);
    }

    public List<Book> findByTitle(String title) {
        List<Book> result = new ArrayList<>();
        for (Book b : books.values()) {
            if (b.getTitle().toLowerCase().contains(title.toLowerCase())) {
                result.add(b);
            }
        }
        return result;
    }

    public List<Book> findByAuthor(String authorName) {
        List<Book> result = new ArrayList<>();
        for (Book b : books.values()) {
            if (b.getAuthor().getName().toLowerCase().contains(authorName.toLowerCase())) {
                result.add(b);
            }
        }
        return result;
    }

    public List<Book> findByCategory(Category category) {
        List<Book> result = new ArrayList<>();
        for (Book b : books.values()) {
            if (b.getCategory() == category) {
                result.add(b);
            }
        }
        return result;
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(books.values());
    }

    public void registerMember(Member member) {
        members.put(member.getId(), member);
    }

    public Member findMemberById(int memberId) {
        return members.get(memberId);
    }

    public List<Member> getAllMembers() {
        return new ArrayList<>(members.values());
    }

    public boolean lendBook(int bookId, int memberId) {
        Book book = books.get(bookId);
        Member member = members.get(memberId);

        if (book == null || member == null) return false;
        if (book.getStatus() == BookStatus.BORROWED) return false;
        if (!member.canBorrow()) return false;

        Invoice invoice = new Invoice(bookId, memberId, book.getPrice());
        member.addInvoice(invoice);
        allInvoices.add(invoice);

        BorrowRecord record = new BorrowRecord(bookId, memberId, invoice.getInvoiceId());
        activeBorrows.put(bookId, record);

        book.setStatus(BookStatus.BORROWED);
        member.incBooksIssued();
        return true;
    }

    public boolean returnBook(int bookId) {
        Book book = books.get(bookId);
        if (book == null) return false;

        BorrowRecord record = activeBorrows.get(bookId);
        if (record == null) return false;

        Member member = members.get(record.getMemberId());
        if (member == null) return false;

        for (Invoice inv : member.getInvoices()) {
            if (inv.getInvoiceId() == record.getInvoiceId() && !inv.isRefunded()) {
                inv.refund();
                break;
            }
        }

        book.setStatus(BookStatus.AVAILABLE);
        member.decBooksIssued();
        activeBorrows.remove(bookId);
        return true;
    }

    public List<BorrowRecord> getActiveBorrows() {
        return new ArrayList<>(activeBorrows.values());
    }

    public List<Invoice> getAllInvoices() {
        return new ArrayList<>(allInvoices);
    }

    public Set<Author> getAllAuthors() {
        return new HashSet<>(authors);
    }
}
