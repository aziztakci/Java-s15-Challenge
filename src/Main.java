import enums.Category;
import models.Author;
import models.Book;
import models.BorrowRecord;
import models.Invoice;
import models.Librarian;
import models.Member;
import models.Library;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);
        Library library = new Library("WiT Library");
        Librarian librarian = new Librarian(1, "Aziz Takcı", "aziz123", library);

        Author yazar1 = new Author(1, "Orhan Pamuk");
        Author yazar2 = new Author(2, "Jack London");
        Author yazar3 = new Author(3, "David McCullough");
        librarian.addBook(new Book(1, "Kar", yazar1, 90.0, Category.FICTION));
        librarian.addBook(new Book(2, "Beyaz Diş", yazar2, 114.5, Category.FICTION));
        librarian.addBook(new Book(3, "1776", yazar3, 170.0, Category.HISTORY));
        librarian.addBook(new Book(4, "Martin Eden", yazar2, 130.0, Category.FICTION));
        librarian.registerMember(new Member(1, "Aziz Takcı", "0555-111-22-31"));
        librarian.registerMember(new Member(2, "Şebnem Y.", "0555-111-22-32"));
        librarian.registerMember(new Member(3, "Ali Bey", "0555-111-22-33"));

        System.out.println("--- Kütüphaneci girişi (" + librarian.whoYouAre() + ") ---");
        while (true) {
            System.out.print("Şifre: ");
            String girilen = scanner.nextLine();
            if (librarian.verifyPassword(girilen)) {
                break;
            }
            System.out.println("Hatalı şifre. Tekrar deneyin.");
        }
        System.out.println("Giriş başarılı.");

        boolean calis = true;

        while (calis) {

            System.out.println("=============================");
            System.out.println("    WiT-KÜTÜPHANESİ    ");
            System.out.println(librarian.whoYouAre());
            System.out.println("=============================");
            System.out.println("1)  Yeni kitap ekle");
            System.out.println("2)  Kitap ara (ID / isim / yazar)");
            System.out.println("3)  Kitap güncelle");
            System.out.println("4)  Kitap sil");
            System.out.println("5)  Kategoriye göre kitap listele");
            System.out.println("6)  Yazara göre kitap listele");
            System.out.println("7)  Yeni üye kaydet");
            System.out.println("8)  Kitap ödünç ver");
            System.out.println("9)  Kitap iade al");
            System.out.println("10) Tüm kitapları listele");
            System.out.println("11) Tüm üyeleri listele");
            System.out.println("12) Aktif ödünç kayıtları");
            System.out.println("13) Tüm faturalar");
            System.out.println("0)  Çıkış");
            System.out.print("Seçiminiz: ");

            int secim = scanner.nextInt();
            scanner.nextLine();

            switch (secim) {

                case 1:
                    System.out.print("Kitap ID: ");
                    int yeniId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Kitap adı: ");
                    String yeniAd = scanner.nextLine();

                    System.out.print("Yazar ID: ");
                    int yazarId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Yazar adı: ");
                    String yazarAd = scanner.nextLine();

                    System.out.print("Fiyat: ");
                    double yeniFiyat = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.println("Kategoriler: SCIENCE, HISTORY, FICTION, TECHNOLOGY, ART");
                    System.out.print("Kategori: ");
                    Category yeniKat = Category.valueOf(scanner.nextLine().toUpperCase());

                    Author yeniYazar = new Author(yazarId, yazarAd);
                    librarian.addBook(new Book(yeniId, yeniAd, yeniYazar, yeniFiyat, yeniKat));
                    System.out.println("Kitap eklendi.");
                    break;

                case 2:
                    System.out.println("1) ID ile  2) İsim ile  3) Yazar ile");
                    System.out.print("Seçim: ");
                    int aramaTipi = scanner.nextInt();
                    scanner.nextLine();

                    if (aramaTipi == 1) {
                        System.out.print("Kitap ID: ");
                        int araId = scanner.nextInt();
                        scanner.nextLine();
                        Book bulunan = librarian.findById(araId);
                        if (bulunan == null) {
                            System.out.println("Kitap bulunamadı.");
                        } else {
                            bulunan.display();
                        }
                    } else if (aramaTipi == 2) {
                        System.out.print("Kitap adı: ");
                        String araAd = scanner.nextLine();
                        List<Book> sonuclar = librarian.findByTitle(araAd);
                        if (sonuclar.isEmpty()) {
                            System.out.println("Kitap bulunamadı.");
                        } else {
                            for (Book b : sonuclar) b.display();
                        }
                    } else if (aramaTipi == 3) {
                        System.out.print("Yazar adı: ");
                        String araYazar = scanner.nextLine();
                        List<Book> sonuclar = librarian.findByAuthor(araYazar);
                        if (sonuclar.isEmpty()) {
                            System.out.println("Kitap bulunamadı.");
                        } else {
                            for (Book b : sonuclar) b.display();
                        }
                    } else {
                        System.out.println("Geçersiz seçim.");
                    }
                    break;

                case 3:
                    System.out.print("Güncellenecek kitap ID: ");
                    int gunId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Yeni kitap adı: ");
                    String gunAd = scanner.nextLine();

                    System.out.print("Yeni fiyat: ");
                    double gunFiyat = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.println("Kategoriler: SCIENCE, HISTORY, FICTION, TECHNOLOGY, ART");
                    System.out.print("Yeni kategori: ");
                    Category gunKat = Category.valueOf(scanner.nextLine().toUpperCase());

                    if (librarian.updateBook(gunId, gunAd, gunFiyat, gunKat)) {
                        System.out.println("Kitap güncellendi.");
                    } else {
                        System.out.println("Kitap bulunamadı.");
                    }
                    break;

                case 4:
                    System.out.print("Silinecek kitap ID: ");
                    int silId = scanner.nextInt();
                    scanner.nextLine();

                    if (librarian.removeBook(silId)) {
                        System.out.println("Kitap silindi.");
                    } else {
                        System.out.println("Kitap silinemedi (mevcut değil veya ödünçte).");
                    }
                    break;

                case 5:
                    System.out.println("Kategoriler: SCIENCE, HISTORY, FICTION, TECHNOLOGY, ART");
                    System.out.print("Kategori: ");
                    Category listKat = Category.valueOf(scanner.nextLine().toUpperCase());
                    List<Book> katSonuc = librarian.findByCategory(listKat);
                    if (katSonuc.isEmpty()) {
                        System.out.println("Bu kategoride kitap yok.");
                    } else {
                        for (Book b : katSonuc) b.display();
                    }
                    break;

                case 6:
                    System.out.print("Yazar adı: ");
                    String yazAd = scanner.nextLine();
                    List<Book> yazSonuc = librarian.findByAuthor(yazAd);
                    if (yazSonuc.isEmpty()) {
                        System.out.println("Bu yazarın kitabı yok.");
                    } else {
                        for (Book b : yazSonuc) b.display();
                    }
                    break;

                case 7:
                    System.out.print("Üye ID: ");
                    int uyeId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Üye adı: ");
                    String uyeAd = scanner.nextLine();

                    System.out.print("Telefon: ");
                    String tel = scanner.nextLine();

                    librarian.registerMember(new Member(uyeId, uyeAd, tel));
                    System.out.println("Üye eklendi.");
                    break;

                case 8:
                    System.out.print("Kitap ID: ");
                    int odKitap = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Üye ID: ");
                    int odUye = scanner.nextInt();
                    scanner.nextLine();

                    if (librarian.lendBook(odKitap, odUye)) {
                        System.out.println("Kitap ödünç verildi, fatura kesildi.");
                    } else {
                        System.out.println("Ödünç verilemedi (kitap yok, ödünçte ya da üye limitte).");
                    }
                    break;

                case 9:
                    System.out.print("İade edilecek kitap ID: ");
                    int iadeId = scanner.nextInt();
                    scanner.nextLine();

                    if (librarian.returnBook(iadeId)) {
                        System.out.println("Kitap iade alındı, ücret iade edildi.");
                    } else {
                        System.out.println("İade alınamadı.");
                    }
                    break;

                case 10:
                    List<Book> tumKitaplar = librarian.getAllBooks();
                    if (tumKitaplar.isEmpty()) {
                        System.out.println("Hiç kitap yok.");
                    } else {
                        for (Book b : tumKitaplar) b.display();
                    }
                    break;

                case 11:
                    List<Member> tumUyeler = librarian.getAllMembers();
                    if (tumUyeler.isEmpty()) {
                        System.out.println("Hiç üye yok.");
                    } else {
                        for (Member m : tumUyeler) {
                            System.out.println(m.whoYouAre()
                                    + " | Ödünçteki kitap: " + m.getBooksIssued() + "/" + Member.maxBooks);
                        }
                    }
                    break;

                case 12:
                    List<BorrowRecord> aktifler = librarian.getActiveBorrows();
                    if (aktifler.isEmpty()) {
                        System.out.println("Aktif ödünç yok.");
                    } else {
                        for (BorrowRecord r : aktifler) System.out.println(r);
                    }
                    break;

                case 13:
                    List<Invoice> faturalar = librarian.getAllInvoices();
                    if (faturalar.isEmpty()) {
                        System.out.println("Hiç fatura yok.");
                    } else {
                        for (Invoice i : faturalar) System.out.println(i);
                    }
                    break;

                case 0:
                    calis = false;
                    System.out.println("Çıkılıyor...");
                    break;

                default:
                    System.out.println("Geçersiz seçim.");
            }
        }

        scanner.close();
    }
}
