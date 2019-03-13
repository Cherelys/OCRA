package com.ocra.dwarbi.ocra.Entities;

public class User {

    //private String  name;
    private String email;
    private String username;
    private String password;
    private int id;

    public User( int id,String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //public String getName(){return  name;}
    //public void setName(String name){this.name = name;}

    public String getEmail() { return email;}
    public void setEmail(String email)  {this.email = email;}

    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}

    public String getPass() {return password;}
    public void setPassword(String password) {this.password = password;}

}

