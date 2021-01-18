package top.scanf.libraryMS.pojo;

public class User {

    private int id;
    private String username;
    private String password;
    private String email;
    private int purview_id;
    private int card_id;
    private int user_status_id;

    public User() {
    }

    /**
     * 构造方法
     * @param username 用户名
     * @param password 用户密码
     * @param purview_id 权限id
     * @param card_id 借阅卡id
     * @param user_status_id 用户状态id
     */
    public User(String username, String password, String email, int purview_id, int card_id, int user_status_id) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.purview_id = purview_id;
        this.card_id = card_id;
        this.user_status_id = user_status_id;
    }


    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPurview_id() {
        return purview_id;
    }

    public void setPurview_id(int purview_id) {
        this.purview_id = purview_id;
    }

    public int getCard_id() {
        return card_id;
    }

    public void setCard_id(int card_id) {
        this.card_id = card_id;
    }

    public int getUser_status_id() {
        return user_status_id;
    }

    public void setUser_status_id(int user_status_id) {
        this.user_status_id = user_status_id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", purview_id=" + purview_id +
                ", card_id=" + card_id +
                ", user_status_id=" + user_status_id +
                '}';
    }
}
