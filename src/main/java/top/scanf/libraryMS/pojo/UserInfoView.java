package top.scanf.libraryMS.pojo;

public class UserInfoView {
    private int id;
    private String username;
    private String email;
    private String pur_name;
    private int card_id;
    private String card_status;
    private String user_status;

    public UserInfoView() {
    }

    public UserInfoView(int id, String username, String email, String pur_name, int card_id, String card_status, String user_status) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.pur_name = pur_name;
        this.card_id = card_id;
        this.card_status = card_status;
        this.user_status = user_status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPur_name() {
        return pur_name;
    }

    public void setPur_name(String pur_name) {
        this.pur_name = pur_name;
    }

    public int getCard_id() {
        return card_id;
    }

    public void setCard_id(int card_id) {
        this.card_id = card_id;
    }

    public String getCard_status() {
        return card_status;
    }

    public void setCard_status(String card_status) {
        this.card_status = card_status;
    }

    public String getUser_status() {
        return user_status;
    }

    public void setUser_status(String user_status) {
        this.user_status = user_status;
    }

    @Override
    public String toString() {
        return "UserInfoView{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", pur_name='" + pur_name + '\'' +
                ", card_id=" + card_id +
                ", card_status='" + card_status + '\'' +
                ", user_status='" + user_status + '\'' +
                '}';
    }
}
