package xmm.bean;


import java.io.Serializable;

public class User implements Serializable {


    private static final long serialVersionUID = 6780884191935720389L;
    
    
    private int id;
    private String userName;
    private int gender;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", gender=" + gender +
                '}';
    }
}
