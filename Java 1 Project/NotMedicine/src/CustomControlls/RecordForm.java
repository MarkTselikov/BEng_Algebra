package CustomControlls;

import DAL.Repo.IRepo;
import DAL.Repo.SqlRepo;
import Model.BasicComplaints;
import Model.Doctor;
import Model.Patient;
import Model.Record;

import javax.print.Doc;
import javax.swing.*;
import java.util.List;

public class RecordForm extends JPanel {

    private int recordID;
    private Patient patient;
    private IRepo repo = new SqlRepo();

    private JComboBox<Doctor> cbDoctorList;
    private JTextField txtDiagnosis;
    private JTextArea txtDiagnosisDescription;
    private JTextArea txtAppointments;
    private JTextArea txtTests;
    private JTextArea txtPrescriptions;
    private JSpinner spBill;

    public RecordForm(Patient p) {

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.patient = p;

        List<Doctor> docL = repo.getDoctors();
        Doctor[] docA = new Doctor[docL.size()];
        docA = docL.toArray(docA);

        JLabel lblDoc = new JLabel("Doctor: ");
        cbDoctorList = new JComboBox<>(docA);

        JLabel lblDiagn = new JLabel("Diagnosis: ");
        txtDiagnosis = new JTextField();
        JLabel lblDiagnDescr = new JLabel("Diagnosis Description: ");
        txtDiagnosisDescription = new JTextArea();
        JLabel lblApp = new JLabel("Appointments: ");
        txtAppointments = new JTextArea();
        JLabel lblTests = new JLabel("Tests: ");
        txtTests = new JTextArea();
        JLabel lblPrescr = new JLabel("Prescription: ");
        txtPrescriptions = new JTextArea();
        JLabel lblBill = new JLabel("Bill amount: ");
        spBill = new JSpinner(new SpinnerNumberModel(0, 0, 1000000, 100));

        this.add(lblDoc);
        this.add(cbDoctorList);
        this.add(lblDiagn);
        this.add(txtDiagnosis);
        this.add(lblDiagnDescr);
        this.add(txtDiagnosisDescription);
        this.add(lblApp);
        this.add(txtAppointments);
        this.add(lblTests);
        this.add(txtTests);
        this.add(lblPrescr);
        this.add(txtPrescriptions);
        this.add(lblBill);
        this.add(spBill);
    }

    public String getDiagnosis() {
        return txtDiagnosis.getText();
    }

    public String getDiagnosisDescription() {
        return txtDiagnosisDescription.getText();
    }

    public String getAppointments() {
        return txtAppointments.getText();
    }

    public String getTests() {
        return txtTests.getText();
    }

    public String getPrescription() {
        return txtPrescriptions.getText();
    }

    public Doctor getDoctor() { return (Doctor) cbDoctorList.getSelectedItem(); }

    public int getBill() {
        return (int)spBill.getValue();
    }

    public void setBtnSubmit(JButton btnSubmit) {
        this.add(btnSubmit);
    }

    public void fillForm(Record r) {
        recordID = r.getId();
        txtDiagnosis.setText(r.getDiagnosis());
        txtDiagnosisDescription.setText(r.getDiagnDescription());
        txtAppointments.setText(r.getAppointments());
        txtTests.setText(r.getTests());
        txtPrescriptions.setText(r.getPrescriptions());
        spBill.setValue(r.getBill());
    }

    public Record getRecord() {
        Record r = new Record(
                patient,
                getDoctor(),
                getDiagnosis(),
                getDiagnosisDescription(),
                getAppointments(),
                getTests(),
                getPrescription(),
                getBill()
        );
        r.setId(recordID);
        return r;
    }
}
