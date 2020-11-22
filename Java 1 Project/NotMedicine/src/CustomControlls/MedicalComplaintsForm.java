package CustomControlls;

import Model.MedicalComplaints;

import javax.swing.*;

public class MedicalComplaintsForm extends JPanel {

    private JCheckBox cbDiabetic;
    private JCheckBox cbHypertensive;
    private JCheckBox cbCardiac;
    private JCheckBox cbRespiratory;
    private JCheckBox cbDigestive;
    private JCheckBox cbOrthopedic;
    private JCheckBox cbMuscular;
    private JCheckBox cbNeurological;
    private JTextField txtAllergies;
    private JTextField txtAdvReactions;
    private JTextField txtSurgeries;

    public MedicalComplaintsForm() {

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        cbDiabetic = new JCheckBox("Diabetic ");
        cbHypertensive = new JCheckBox("Hypertensive ");
        cbCardiac = new JCheckBox("Cardiac Condition ");
        cbRespiratory = new JCheckBox("Respiratory Condition ");
        cbDigestive = new JCheckBox("Digestive Condition ");
        cbOrthopedic = new JCheckBox("Orthopedic Condition ");
        cbMuscular = new JCheckBox("Muscular Condition ");
        cbNeurological = new JCheckBox("Neurological Condition ");

        JLabel lblAllergies = new JLabel("Known Allergies: ");
        txtAllergies = new JTextField();
        JLabel lblAdvReactions = new JLabel("Known Adverse Reactions: ");
        txtAdvReactions = new JTextField();
        JLabel lblSurgeries = new JLabel("Major Surgeries: ");
        txtSurgeries = new JTextField();

        this.add(cbDiabetic);
        this.add(cbHypertensive);
        this.add(cbCardiac);
        this.add(cbRespiratory);
        this.add(cbDigestive);
        this.add(cbOrthopedic);
        this.add(cbMuscular);
        this.add(cbNeurological);
        this.add(lblAllergies);
        this.add(txtAllergies);
        this.add(lblAdvReactions);
        this.add(txtAdvReactions);
        this.add(lblSurgeries);
        this.add(txtSurgeries);
        //this.add(btnSubmit);
    }

    public boolean getDiabeticInput() {
        return cbDiabetic.isSelected();
    }

    public boolean getHypertensiveInput() {
        return cbHypertensive.isSelected();
    }

    public boolean getCardiacInput() {
        return cbCardiac.isSelected();
    }

    public boolean getRespiratoryInput() {
        return cbRespiratory.isSelected();
    }

    public boolean getDigestiveInput() {
        return cbDigestive.isSelected();
    }

    public boolean getOrthopedicInput() {
        return cbOrthopedic.isSelected();
    }

    public boolean getMuscularInput() {
        return cbMuscular.isSelected();
    }

    public boolean getNeurologicalInput() {
        return cbNeurological.isSelected();
    }

    public String getAllergiesInput() {
        return txtAllergies.getText();
    }

    public String getAdvReactionsInput() {
        return txtAdvReactions.getText();
    }

    public String getSurgeriesInput() {
        return txtSurgeries.getText();
    }

    public void fillForm(MedicalComplaints m)
    {
        cbDiabetic.setSelected(m.isDiabetic());
        cbHypertensive.setSelected(m.isHypertensive());
        cbCardiac.setSelected(m.isCardCondition());
        cbRespiratory.setSelected(m.isRespirCondition());
        cbDigestive.setSelected(m.isDigestCondition());
        cbOrthopedic.setSelected(m.isOrthopedicCondition());
        cbMuscular.setSelected(m.isMuscularCondition());
        cbNeurological.setSelected(m.isNeuroCondition());
        txtAllergies.setText(m.getAllergies());
        txtAdvReactions.setText(m.getAdverseDrugsReaction());
        txtSurgeries.setText(m.getMajorSurgeries());
    }

    public MedicalComplaints getMedComplaints() {
        return new MedicalComplaints(
                getDiabeticInput(),
                getHypertensiveInput(),
                getCardiacInput(),
                getRespiratoryInput(),
                getDigestiveInput(),
                getOrthopedicInput(),
                getMuscularInput(),
                getNeurologicalInput(),
                getAllergiesInput(),
                getAdvReactionsInput(),
                getSurgeriesInput()
        );
    }
}
