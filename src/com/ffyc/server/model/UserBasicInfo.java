package com.ffyc.server.model;

import java.io.Serializable;

public class UserBasicInfo implements Serializable
{
    private static final long serialVersionUID = 1308823867498701378L;
    private String id;
    private String name;
    private String gender;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getGender()
    {
        return gender;
    }

    public void setGender(String gender)
    {
        this.gender = gender;
    }

}