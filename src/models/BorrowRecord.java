package models;

import java.time.LocalDate;

public class BorrowRecord {
    private int bookId;
    private int memberId;
    private LocalDate borrowDate;
    private int invoiceId;

    public BorrowRecord(int bookId, int memberId, int invoiceId) {
        this.bookId = bookId;
        this.memberId = memberId;
        this.invoiceId = invoiceId;
        this.borrowDate = LocalDate.now();
    }

    public int getBookId() {
        return bookId;
    }

    public int getMemberId() {
        return memberId;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    @Override
    public String toString() {
        return "Kitap ID: " + bookId
                + " | Üye ID: " + memberId
                + " | Ödünç Tarihi: " + borrowDate
                + " | Fatura ID: " + invoiceId;
    }
}
