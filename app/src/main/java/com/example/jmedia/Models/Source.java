package com.example.jmedia.Models;

import java.io.Serializable;

public class Source implements Serializable {


    String id="";
    String name="";
    //Note : The name of the variables must be same as that of them in the respone

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
