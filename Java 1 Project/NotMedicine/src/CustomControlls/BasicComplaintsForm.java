package CustomControlls;

import Model.BasicComplaints;

import javax.swing.*;

public class BasicComplaintsForm extends JPanel {

    private JTextArea txtStatement;
    private JTextArea txtPrevTreatment;
    private JTextField txtPhysician;

    public BasicComplaintsForm() {

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JLabel lblStatement = new JLabel("Statement of Complaint: ");
        txtStatement = new JTextArea();
        JLabel lblTrHistory = new JLabel("Treatment History: ");
        txtPrevTreatment = new JTextArea();
        JLabel lblPhysician = new JLabel("Physician / Hospital Treated: ");
        txtPhysician = new JTextField();

        this.add(lblStatement);
        this.add(txtStatement);
        this.add(lblTrHistory);
        this.add(txtPrevTreatment);
        this.add(lblPhysician);
        this.add(txtPhysician);
    }

    public String getStatement() {
        return txtStatement.getText();
    }

    public String getPhysician() {
        return txtPhysician.getText();
    }

    public String getPrevTreatment() {
        return txtPrevTreatment.getText();
    }

    public void fillForm(BasicComplaints b) {
        txtStatement.setText(b.getStatementOfComplaint());
        txtPrevTreatment.setText(b.getPrevTreatment());
        txtPhysician.setText(b.getPhysicianHospital());
    }

    public BasicComplaints getBasicComplaints() {
        return new BasicComplaints(
            getStatement(),
            getPrevTreatment(),
            getPhysician()
        );
    }
}
