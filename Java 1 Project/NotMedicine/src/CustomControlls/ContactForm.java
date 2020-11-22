package CustomControlls;

import Model.ContactDetails;

import javax.swing.*;

public class ContactForm extends JPanel {

    private JTextField txtPresAddress;
    private JTextField txtPermAddress;
    private JTextField txtTelWork;
    private JTextField txtTelHome;
    private JTextField txtMobile;
    private JTextField txtPager;
    private JTextField txtFax;
    private JTextField txtEmail;

    public ContactForm() {

        JLabel lblPresAddress = new JLabel("Present Address: ");
        txtPresAddress = new JTextField();

        JLabel lblPermAddress = new JLabel("Permanent Address: ");
        txtPermAddress = new JTextField();

        JLabel lblTelWork = new JLabel("Telephone (Work): ");
        txtTelWork = new JTextField();

        JLabel lblTelHome = new JLabel("Telephone (Home): ");
        txtTelHome = new JTextField();

        JLabel lblMobile = new JLabel("Mobile: ");
        txtMobile = new JTextField();

        JLabel lblPager = new JLabel("Pager: ");
        txtPager = new JTextField();

        JLabel lblFax = new JLabel("Fax: ");
        txtFax = new JTextField();

        JLabel lblEmail = new JLabel("E-Mail: ");
        txtEmail = new JTextField();

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        this.add(lblPresAddress);
        this.add(txtPresAddress);
        this.add(lblPermAddress);
        this.add(txtPermAddress);
        this.add(lblTelWork);
        this.add(txtTelWork);
        this.add(lblTelHome);
        this.add(txtTelHome);
        this.add(lblMobile);
        this.add(txtMobile);
        this.add(lblPager);
        this.add(txtPager);
        this.add(lblFax);
        this.add(txtFax);
        this.add(lblEmail);
        this.add(txtEmail);
    }

    public String getPresAddressInput() {
        return txtPresAddress.getText();
    }

    public String getPermAddressInput() {
        return txtPermAddress.getText();
    }

    public String getTelWorkInput() {
        return txtTelWork.getText();
    }

    public String getTelHomeInput() {
        return txtTelHome.getText();
    }

    public String getMobileInput() {
        return txtMobile.getText();
    }

    public String getPagerInput() {
        return txtPager.getText();
    }

    public String getFaxInput() {
        return txtFax.getText();
    }

    public String getEmailInput() {
        return txtEmail.getText();
    }

    public void fillForm(ContactDetails c) {
        txtPresAddress.setText(c.getPresentAddress());
        txtPermAddress.setText(c.getPermanentAddress());
        txtTelWork.setText(c.getTelephoneWork());
        txtTelHome.setText(c.getTelephoneHome());
        txtMobile.setText(c.getMobile());
        txtPager.setText(c.getPager());
        txtFax.setText(c.getFax());
        txtEmail.setText(c.getEmail());
    }

    public ContactDetails getContactDetails() {
        return new ContactDetails(
                getPresAddressInput(),
                getPermAddressInput(),
                getTelWorkInput(),
                getTelHomeInput(),
                getMobileInput(),
                getPagerInput(),
                getFaxInput(),
                getEmailInput()
        );
    }
}


