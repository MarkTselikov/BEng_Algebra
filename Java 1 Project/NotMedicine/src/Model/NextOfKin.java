package Model;

public class NextOfKin extends Person {

    private String relationship;
    private String fName;
    private String mName = "";
    private String lName;
    private ContactDetails contactDetails;

    public NextOfKin(String relationship, String fName, String mName, String lName, ContactDetails contactDetails) {
        this.relationship = relationship;
        this.fName = fName;
        this.mName = mName;
        this.lName = lName;
        this.contactDetails = contactDetails;
    }

    public String getRelationship() {
        return relationship;
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

    public ContactDetails getContactDetails() {
        return contactDetails;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public void setContactDetails(ContactDetails contactDetails) {
        this.contactDetails = contactDetails;
    }

    @Override
    public String toString() {
        if(mName.equals(""))
            return fName + " " + lName;
        else
            return fName + " " + mName + " " + lName;
    }
}
