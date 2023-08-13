package rs.ac.bg.etf.pki.zoopandica.data;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;

import rs.ac.bg.etf.pki.zoopandica.data.Comment;

public class Animal {
    private String name;
    private Drawable image;
    private String description;
    private List<Comment> comments;

    public Animal(String name, Drawable image, String description) {
        this.name = name;
        this.image = image;
        this.description = description;
        this.comments = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }
}
