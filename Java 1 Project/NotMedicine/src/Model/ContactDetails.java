package Model;

public class ContactDetails {
    public ContactDetails(String presentAddress, String permanentAddress, String telephoneWork, String telephoneHome, String mobile, String pager, String fax, String email) {
        this.presentAddress = presentAddress;
        this.permanentAddress = permanentAddress;
        this.telephoneWork = telephoneWork;
        this.telephoneHome = telephoneHome;
        this.mobile = mobile;
        this.pager = pager;
        this.fax = fax;
        this.email = email;
    }

    private String presentAddress;
    private String permanentAddress;
    private String telephoneWork;
    private String telephoneHome;
    private String mobile;
    private String pager;
    private String fax;
    private String email;


    public String getPresentAddress() {
        return presentAddress;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public String getTelephoneWork() {
        return telephoneWork;
    }

    public String getTelephoneHome() {
        return telephoneHome;
    }

    public String getMobile() {
        return mobile;
    }

    public String getPager() {
        return pager;
    }

    public String getFax() {
        return fax;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return String.format("Contact Details: \n\t" +
                "Present Address: %s \n\t" +
                "Permanent Address: %s \n\t" +
                "Work Phone: %s \n\t" +
                "Home Phone: %s \n\t" +
                "Mobile: %s \n\t" +
                "Pager: %s \n\t" +
                "Fax: %s \n\t" +
                "Email: %s",
                presentAddress, permanentAddress, telephoneWork, telephoneHome, mobile, pager, fax, email);
    }
}
