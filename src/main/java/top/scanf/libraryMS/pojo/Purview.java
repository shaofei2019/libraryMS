package top.scanf.libraryMS.pojo;

public class Purview {
    private int id;
    private String name;

    public Purview() {
    }

    public Purview(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Purview{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
