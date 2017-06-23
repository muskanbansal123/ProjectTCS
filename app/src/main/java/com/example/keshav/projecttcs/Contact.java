package com.example.keshav.projecttcs;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.w3c.dom.Text;

/**
 * Created by keshav on 21-06-2017.
 */

public class Contact {


    int age, contact, pincode;
    String email_add, name, password, city, bgroup;



    public void setAge (int age)
    {
        this.age = age;
    }
    public int getAge()
    {
        return this.age;
    }

    public void setContact (int contact)
    {
        this.contact = contact;
    }
    public int getContact()
    {
        return this.contact;
    }
    public void setName (String name)
    {
        this.name = name;
    }
    public String getName()
    {
        return this.name;
    }
    public void setPassword (String password)
    {
        this.password = password;
    }
    public String getPassword()
    {
        return this.password;
    }

    public void setEmail_add (String email_add)
    {
        this.email_add = email_add;
    }
    public String getEmail_add()
    {
        return this.email_add;
    }

    public void setCity (String city)
    {
        this.city = city;
    }
    public String getCity()
    {
        return this.city;
    }

    public void setBgroup (String bgroup)
    {
        this.bgroup = bgroup;
    }
    public String getBgroup()
    {
        return this.bgroup;
    }

    public void setPincode (int pincode)
    {
        this.pincode = pincode;
    }
    public int getPincode()
    {
        return this.pincode;
    }

}
