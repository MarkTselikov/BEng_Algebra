package Model;

import java.util.ArrayList;
import java.util.Date;

public class Person {
    protected String fName;
    protected String mName = "";
    protected String lName;
    protected char sex;
    protected Date birthDate;

    protected ArrayList<Appointment> appointments = new ArrayList<Appointment>();


    protected ArrayList<Appointment> getAppointments() {
        return appointments;
    }

    public void addAppointment(Appointment newAppmnt) {
        appointments.add(newAppmnt);
    }

    @Override
    public String toString(){
        return super.toString();    // --- change this to a required format
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public void setLName(String lName) {
        this.lName = lName;
    }
}
