package Model;

import java.util.Date;

public class BasicDetails {
    protected String fName;
    protected String mName = "";
    protected String lName;
    protected char sex;
    protected String birthDate;
    private boolean isComplete = false;

    public BasicDetails(String fName, String mName, String lName, char sex, String birthDate, boolean isComplete) {
        this.fName = fName;
        this.mName = mName;
        this.lName = lName;
        this.sex = sex;
        this.birthDate = birthDate;
        this.isComplete = isComplete;
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

    public String getBirthDate() {
        return birthDate;
    }

    public boolean isComplete() {
        return isComplete;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s (%s) - %s", fName, mName, lName, sex, birthDate);
    }
}
