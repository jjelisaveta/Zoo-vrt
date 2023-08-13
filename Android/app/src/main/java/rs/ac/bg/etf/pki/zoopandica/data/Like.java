package rs.ac.bg.etf.pki.zoopandica.data;

public class Like {
    private String username;
    private String event;

    public Like(String username, String event) {
        this.username = username;
        this.event = event;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }
}
