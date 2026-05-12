package models;

import java.util.ArrayList;
import java.util.List;

public class Member extends Person {

    public static final int maxBooks = 5;
    private String phone;
    private int booksIssued;
    private List<Invoice> invoices;


    public Member(int id, String name, String phone) {
        super(id, name);
        this.phone = phone;
        this.booksIssued = 0;
        this.invoices = new ArrayList<>();
    }

    public String getPhone() {
        return phone;
    }

    public int getBooksIssued() {
        return booksIssued;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public boolean canBorrow () {
        // return booksIssued < maxBooks; kısa şekilde yazılabilir...
        if (booksIssued<maxBooks) {
            return true;
        } else {
            return false;
        }
    }

    public void incBooksIssued () {
        booksIssued++;
    }

    public void decBooksIssued () {
        if(booksIssued>0) {
            booksIssued--;
        }
    }

    public void addInvoice(Invoice invoice) {
        invoices.add(invoice);
    }

    @Override
    public String whoYouAre() {
        return "Member: " + getName() + "ID: " + getId();
    }
}
