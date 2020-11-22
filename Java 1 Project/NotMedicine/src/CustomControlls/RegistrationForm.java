package CustomControlls;

import Model.*;

import javax.swing.*;

public class RegistrationForm extends JTabbedPane {

    private int id;
    private String incomingDate;
    private BasicInfoForm basicDetails;
    private ContactForm contact;
    private NOKForm nextOfKin;
    private PersonalDetailsForm personalDetails;
    private LifestyleForm lifestyle;
    private BasicComplaintsForm basicComplaints;
    private MedicalComplaintsForm medComplaints;

    public RegistrationForm() {

        basicDetails = new BasicInfoForm();
        contact = new ContactForm();
        nextOfKin = new NOKForm();
        personalDetails = new PersonalDetailsForm();
        lifestyle = new LifestyleForm();
        basicComplaints = new BasicComplaintsForm();
        medComplaints = new MedicalComplaintsForm();


        //
        // That tabbed pane really ties the form together, maaan
        this.add(basicDetails, "Basic Information");
        this.add(contact, "Contact Details");
        this.add(nextOfKin, "Next-of-Kin");
        this.add(personalDetails, "Personal Details");
        this.add(lifestyle, "Lifestyle");
        this.add(basicComplaints, "Basic Complaints");
        this.add(medComplaints, "Medical Complaints");
    }

    public RegistrationForm(Patient p) {

        id = p.getId();
        incomingDate = p.getIncomingDate();

        basicDetails = new BasicInfoForm();
        contact = new ContactForm();
        nextOfKin = new NOKForm();
        personalDetails = new PersonalDetailsForm();
        lifestyle = new LifestyleForm();
        basicComplaints = new BasicComplaintsForm();
        medComplaints = new MedicalComplaintsForm();


        //
        // That tabbed pane really ties the form together, maaan
        this.add(basicDetails, "Basic Information");
        this.add(contact, "Contact Details");
        this.add(nextOfKin, "Next-of-Kin");
        this.add(personalDetails, "Personal Details");
        this.add(lifestyle, "Lifestyle");
        this.add(basicComplaints, "Basic Complaints");
        this.add(medComplaints, "Medical Complaints");

        basicDetails.fillForm(p.getBasicDetails());
        contact.fillForm(p.getContactDetails());
        nextOfKin.fillForm(p.getNextOfKin());
        personalDetails.fillForm(p.getPersonalDetails());
        lifestyle.fillForm(p.getLifestyle());
        basicComplaints.fillForm(p.getBasicComplaints());
        medComplaints.fillForm(p.getMedicalComplaints());
    }

    public BasicInfoForm getBasicDetails() {
        return basicDetails;
    }

    public ContactForm getContact() {
        return contact;
    }

    public PersonalDetailsForm getPersonalDetails() {
        return personalDetails;
    }

    public LifestyleForm getLifestyle() {
        return lifestyle;
    }

    public BasicComplaintsForm getBasicComplaints() {
        return basicComplaints;
    }

    public MedicalComplaintsForm getMedComplaints() {
        return medComplaints;
    }

    public NOKForm getNextOfKin() {
        return nextOfKin;
    }

    public int getId() {
        return id;
    }

    public String getIncomingDate() {
        return incomingDate;
    }

    public void setBasicDetails(BasicInfoForm basicDetails) {
        this.basicDetails = basicDetails;
    }

    public void setContact(ContactForm contact) {
        this.contact = contact;
    }

    public void setPersonalDetails(PersonalDetailsForm personalDetails) {
        this.personalDetails = personalDetails;
    }

    public void setLifestyle(LifestyleForm lifestyle) {
        this.lifestyle = lifestyle;
    }

    public void setBasicComplaints(BasicComplaintsForm basicComplaints) {
        this.basicComplaints = basicComplaints;
    }

    public void setMedComplaints(MedicalComplaintsForm medComplaints) {
        this.medComplaints = medComplaints;
    }
}
