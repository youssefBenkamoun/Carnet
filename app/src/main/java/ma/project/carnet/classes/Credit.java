package ma.project.carnet.classes;


import java.util.Date;

public class Credit {
    private int id;
    private float prix;
    private int client;
    private int product;
    private Date date;
    private String etat;

    public Credit(float prix, int client, int product, Date date, String etat) {
        this.prix = prix;
        this.client = client;
        this.product = product;
        this.date = date;
        this.etat = etat;
    }

    public Credit() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClient() {
        return client;
    }

    public void setClient(int client) {
        this.client = client;
    }

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
}

