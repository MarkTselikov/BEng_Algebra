package Model;

import java.util.ArrayList;
import java.util.Date;

public class Record {

    private int id;
    private Doctor doctor;
    private Patient patient;

    private String diagnosis;
    private String diagnDescription;

    private String appointments;
    private String tests;
    private String prescriptions;
    private int bill;


    public Record(Patient newPatient, Doctor newDoc, String diagnosis, String diagnDescription, String appointments,
                    String tests, String prescriptions, int bill){
        patient = newPatient;
        doctor = newDoc;

        this.diagnosis = diagnosis;
        this.diagnDescription = diagnDescription;
        this.appointments = appointments;
        this.tests = tests;
        this.prescriptions = prescriptions;
        this.bill = bill;
        // here comes the filling in of the basic form
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getDiagnDescription() {
        return diagnDescription;
    }

    public String getAppointments() {
        return appointments;
    }

    public String getTests() {
        return tests;
    }

    public String getPrescriptions() {
        return prescriptions;
    }

    public int getBill() {
        return bill;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id + ", " + diagnosis;
    }

    public String getDetails() {
        return String.format("Patient: %s \tDoctor: %s\n\n" +
                "Diagnosis: \n%s\n\n" +
                "Description:\n%s\n\n" +
                "Appointments: \n%s \n\n" +
                "Tests: \n%s \n\n" +
                "Prescription: \n%s \n\n\n" +
                "Bill: %d",
                patient.toString(), doctor.toString(), diagnosis, diagnDescription, appointments, tests, prescriptions,
                bill);
    }
}
