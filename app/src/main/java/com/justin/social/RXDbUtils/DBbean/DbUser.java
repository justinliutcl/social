package com.justin.social.RXDbUtils.DBbean;

/**
 * Created by Justinliu on 2018/3/21.
 */

public class DbUser {
    public int id;
    public String name;
    public String des;

    public DbUser(int id, String name, String des) {
        this.id = id;
        this.name = name;
        this.des = des;
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

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
