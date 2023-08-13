package rs.ac.bg.etf.pki.zoopandica.data;

public class Comment {
    private String user;
    private String text;
    private String animal;

    public Comment(String user, String text, String animal) {
        this.user = user;
        this.text = text;
        this.animal = animal;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    @Override
    public String toString() {
        return  user + ',' + text + ',' + animal;
    }
}
