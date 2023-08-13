package rs.ac.bg.etf.pki.zoopandica.data;

public class Promo {
    private String code;
    private double discount;

    public Promo(String code, double discount) {
        this.code = code;
        this.discount = discount;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
