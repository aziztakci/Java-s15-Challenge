package models;

import java.time.LocalDate;

public class Invoice {
    public static int counter = 0;
    private int invoiceId;
    private int bookId;
    private int memberId;
    private double amount;
    private LocalDate date;
    private boolean refunded;

    public Invoice (int bookId, int memberId, double amount) {
        this.bookId = bookId;
        this.memberId = memberId;
        this.amount = amount;
        this.date = LocalDate.now();
        this.refunded = false;
        this.invoiceId = counter++;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public int getBookId() {
        return bookId;
    }

    public int getMemberId() {
        return memberId;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public boolean isRefunded() {
        return refunded;
    }

    public void refund () {
        this.refunded = true;
    }

    @Override
    public String toString() {
        return "Fatura{" +
                "Fatura id=" + invoiceId +
                ", Kitap id=" + bookId +
                ", Üye id=" + memberId +
                ", Tutar=" + amount + "TL" +
                ", Tarih=" + date +
                ", Ödendi/İade edildi=" + refunded +
                '}';
    }
}
