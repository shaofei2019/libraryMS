package top.scanf.libraryMS.pojo;

public class Card {
    private Integer id;
    private Integer status_id;

    public Card() {
    }

    public Card(Integer id, Integer status_id) {
        this.id = id;
        this.status_id = status_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStatus_id() {
        return status_id;
    }

    public void setStatus_id(Integer status_id) {
        this.status_id = status_id;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", status_id=" + status_id +
                '}';
    }
}
