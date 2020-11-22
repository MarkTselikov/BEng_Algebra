package CustomControlls;

import Model.NextOfKin;

import javax.swing.*;

public class NOKForm extends JPanel {

    private ContactForm contactNextKin;
    private FullNameForm nameNextKin;
    private JTextField txtRelationNOK;

    public NOKForm() {

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        nameNextKin = new FullNameForm();

        JLabel lblRelationNOK = new JLabel("Relationship with Outpatient: ");
        txtRelationNOK = new JTextField();
        contactNextKin = new ContactForm();

        this.add(nameNextKin);
        this.add(lblRelationNOK);
        this.add(txtRelationNOK);
        this.add(contactNextKin);
    }

    public void fillForm(NextOfKin nok) {
        nameNextKin.fillForm(nok.getfName(), nok.getmName(), nok.getlName());
        txtRelationNOK.setText(nok.getRelationship());
        contactNextKin.fillForm(nok.getContactDetails());
    }

    public NextOfKin getNOK() {
        return new NextOfKin(
            txtRelationNOK.getText(), nameNextKin.getfNameInput(), nameNextKin.getmNameInput(),
                nameNextKin.getlNameInput(), contactNextKin.getContactDetails()
        );
    }
}
