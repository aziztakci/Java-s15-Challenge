package models;

public class Librarian extends Person{
    private String password;

    public Librarian(int id, String name,String password) {
        super(id,name);
        this.password = password;
    }

    public boolean verifyPassword( String text) {

        return this.password.equals(text);
    }


    @Override
    public String whoYouAre() {
        return "";
    }
}
