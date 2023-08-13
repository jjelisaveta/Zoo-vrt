package rs.ac.bg.etf.pki.zoopandica.data;

public class PurchaseNotification {
    private String date;
    private String status;
    private String user;
    private String type;

    public PurchaseNotification(String date, String status, String user, String type) {
        this.date = date;
        this.status = status;
        this.user = user;
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return date + ',' + status + ',' + user + ',' + type;
    }


}
