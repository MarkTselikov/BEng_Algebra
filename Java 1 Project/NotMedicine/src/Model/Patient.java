package Model;

import java.util.Date;
import java.util.List;

public class Patient extends Person {
    private int id;
    private String incomingDate;
    private List<Record> records;

    private BasicDetails basicDetails;
    private ContactDetails contactDetails;
    private NextOfKin nextOfKin;
    private PersonalDetails personalDetails;
    private Lifestyle lifestyle;
    private BasicComplaints basicComplaints;
    private MedicalComplaints medicalComplaints;

    public void setId(int id) {
        this.id = id;
    }

    public Patient(int id, String incomingDate, BasicDetails basicDetails, ContactDetails contactDetails, NextOfKin nextOfKin, PersonalDetails personalDetails, Lifestyle lifestyle, BasicComplaints basicComplaints, MedicalComplaints medicalComplaints) {
        this.id = id;
        this.incomingDate = incomingDate;
        this.basicDetails = basicDetails;
        this.contactDetails = contactDetails;
        this.nextOfKin = nextOfKin;
        this.personalDetails = personalDetails;
        this.lifestyle = lifestyle;
        this.basicComplaints = basicComplaints;
        this.medicalComplaints = medicalComplaints;
    }

    public Patient(BasicDetails basicDetails, ContactDetails contactDetails, NextOfKin nextOfKin, PersonalDetails personalDetails, Lifestyle lifestyle, BasicComplaints basicComplaints, MedicalComplaints medicalComplaints) {
        incomingDate = new Date().toString();
        this.basicDetails = basicDetails;
        this.contactDetails = contactDetails;
        this.nextOfKin = nextOfKin;
        this.personalDetails = personalDetails;
        this.lifestyle = lifestyle;
        this.basicComplaints = basicComplaints;
        this.medicalComplaints = medicalComplaints;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        if(basicDetails.getmName().equals(""))
            return id + ", " + basicDetails.getfName() + " " + basicDetails.getlName();
        else
            return id + ", " + basicDetails.getfName() + " " + basicDetails.getmName() + " " + basicDetails.getlName();
    }

    public ContactDetails getContactDetails() {
        return contactDetails;
    }

    public NextOfKin getNextOfKin() {
        return nextOfKin;
    }

    public void setContactDetails(ContactDetails contactDetails) {
        this.contactDetails = contactDetails;
    }

    public void setNextOfKin(NextOfKin nextOfKin) {
        this.nextOfKin = nextOfKin;
    }

    public PersonalDetails getPersonalDetails() {
        return personalDetails;
    }

    public void setPersonalDetails(PersonalDetails personalDetails) {
        this.personalDetails = personalDetails;
    }

    public Lifestyle getLifestyle() {
        return lifestyle;
    }

    public void setLifestyle(Lifestyle lifestyle) {
        this.lifestyle = lifestyle;
    }

    public BasicComplaints getBasicComplaints() {
        return basicComplaints;
    }

    public void setBasicComplaints(BasicComplaints basicComplaints) {
        this.basicComplaints = basicComplaints;
    }

    public MedicalComplaints getMedicalComplaints() {
        return medicalComplaints;
    }

    public void setMedicalComplaints(MedicalComplaints medicalComplaints) {
        this.medicalComplaints = medicalComplaints;
    }

    public BasicDetails getBasicDetails() {
        return basicDetails;
    }

    public void setBasicDetails(BasicDetails basicDetails) {
        this.basicDetails = basicDetails;
    }

    public String getDetails() {
        return String.format("%s \n\n%s \n\n%s \n\n%s \n\n%s \n\n%s",
                basicDetails.toString(), contactDetails.toString(), personalDetails.toString(),
                lifestyle.toString(), basicComplaints.toString(), medicalComplaints.toString());
    }

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }

    public String getIncomingDate() {
        return incomingDate;
    }

    public void setIncomingDate(String incomingDate) {
        this.incomingDate = incomingDate;
    }
}
