package rs.ac.bg.etf.pki.zoopandica.data;

import android.graphics.drawable.Drawable;

public class Event {
    private String title;
    private String description;
    private Drawable image;
    private int likes;

    public Event(String title, String description, Drawable image, int likes) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.likes = likes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
