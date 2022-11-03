package ma.project.carnet.classes;

public class Categorie {
    private int id;
    private String name;
    public Categorie(){}

    public Categorie( String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }


}
