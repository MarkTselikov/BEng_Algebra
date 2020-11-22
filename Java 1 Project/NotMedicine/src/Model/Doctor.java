package Model;

import java.util.ArrayList;
import java.util.Date;

public class Doctor extends Person {
    private int id;
    private String fName;
    private String mName = "";
    private String lName;
    private char sex;
    private Date birthDate;
    private String field;

    public Doctor(int id, String fName, String mName, String lName, char sex, Date birthDate, String field) {
        this.id = id;
        this.fName = fName;
        this.mName = mName;
        this.lName = lName;
        this.sex = sex;
        this.birthDate = birthDate;
        this.field = field;
    }

    public String getField() {
        return field;
    }

    public int getId() {
        return id;
    }

    public String getfName() {
        return fName;
    }

    public String getmName() {
        return mName;
    }

    public String getlName() {
        return lName;
    }

    public char getSex() {
        return sex;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    @Override
    public String toString() {
        if(mName.equals(""))
            return id + ", " + fName + " " + lName + " / " + field;
        else
            return id + ", " + fName + " " + mName + " " + lName + " / " + field;
    }
}
