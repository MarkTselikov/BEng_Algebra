package CustomControlls;

import Model.PersonalDetails;

import javax.swing.*;

public class PersonalDetailsForm extends JPanel {

    private JTextField txtMartStatus;
    private JSpinner numDepend;
    private JSpinner spHeight;
    private JSpinner spWeight;
    private JTextField txtBloodType;
    private JTextField txtOccupation;
    private JSpinner spIncome;

    public PersonalDetailsForm() {

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JLabel lblMartStatus = new JLabel("Martial Status: ");
        txtMartStatus = new JTextField();
        JLabel lblDepend = new JLabel("Number of Dependents: ");
        numDepend = new JSpinner(new SpinnerNumberModel(0, 0, 20, 1));
        JLabel lblHeight = new JLabel("Height: ");
        spHeight = new JSpinner(new SpinnerNumberModel());
        JLabel lblWeight = new JLabel("Weight: ");
        spWeight = new JSpinner(new SpinnerNumberModel());
        JLabel lblBloodType = new JLabel("Blood Type - RH: ");
        txtBloodType = new JTextField();
        JLabel lblOccupation = new JLabel("Occupation: ");
        txtOccupation = new JTextField();
        JLabel lblIncome  = new JLabel("Gross Annual Income: ");
        spIncome = new JSpinner(new SpinnerNumberModel());
        spIncome.setValue(0);

        this.add(lblMartStatus);
        this.add(txtMartStatus);
        this.add(lblDepend);
        this.add(numDepend);
        this.add(lblHeight);
        this.add(spHeight);
        this.add(lblWeight);
        this.add(spWeight);
        this.add(lblBloodType);
        this.add(txtBloodType);
        this.add(lblOccupation);
        this.add(txtOccupation);
        this.add(lblIncome);
        this.add(spIncome);
    }

    public String getMartStatusInput() {
        return txtMartStatus.getText();
    }

    public int getNumDependInput() {
        return (int)numDepend.getValue();
    }

    public int getHeightInput() {
        return (int)spHeight.getValue();
    }

    public int getWeightInput() {
        return (int)spWeight.getValue();
    }

    public String getBloodTypeInput() {
        return txtBloodType.getText();
    }

    public String getOccupationInput() {
        return txtOccupation.getText();
    }

    public int getIncomeInput() {
        return (int)spIncome.getValue();
    }

    public void fillForm(PersonalDetails p) {
        txtMartStatus.setText(p.getMartialStatus());
        numDepend.setValue(p.getNumDependends());
        spHeight.setValue(p.getHeight());
        spWeight.setValue(p.getWeight());
        txtBloodType.setText(p.getBloodType());
        txtOccupation.setText(p.getOccupation());
        spIncome.setValue(p.getIncome());
    }

    public PersonalDetails getPesonalDetails() {

        return new PersonalDetails(
                getMartStatusInput(),
                getNumDependInput(),
                getHeightInput(),
                getWeightInput(),
                getBloodTypeInput(),
                getOccupationInput(),
                getIncomeInput()
        );
    }

}
