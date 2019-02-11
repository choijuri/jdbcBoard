package my.examples.jdbcboard.dto;

import java.sql.Date;

public class User {
    private Long id;
    private String name;
    private String nickname;
    private String email;
    private String passwd;
    private Date regdate;



    public User(String name, String nickname, String email, String passwd) {
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.passwd = passwd;
    }

    public User(Long id, String name, String nickname, String email, String passwd) {
        this(name, nickname, email, passwd);
        this.id = id;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public Date getRegdate() {
        return regdate;
    }

    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", passwd='" + passwd + '\'' +
                ", regdate=" + regdate +
                '}';
    }
}
