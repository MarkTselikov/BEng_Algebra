package CustomControlls;

import Model.BasicComplaints;
import Model.BasicDetails;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BasicInfoForm extends JPanel {

    private FullNameForm namePatient;
    private JRadioButton maleRB;
    private JRadioButton femaleRB;
    private JFormattedTextField birthDateTF;
    private JCheckBox cbIsComplete;

    public BasicInfoForm() {

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        namePatient = new FullNameForm();

        JPanel sexPan = new JPanel();
        JLabel sexLbl = new JLabel("Sex: ");
        ButtonGroup sexGroup = new ButtonGroup();
        maleRB = new JRadioButton("M");
        femaleRB = new JRadioButton("F");
        sexGroup.add(maleRB);
        sexGroup.add(femaleRB);
        sexPan.add(sexLbl);
        sexPan.add(maleRB);
        sexPan.add(femaleRB);

        JPanel bdPan = new JPanel();
        JLabel birthDateLbl = new JLabel("Date of Birth: ");
        birthDateTF = new JFormattedTextField(new SimpleDateFormat("MM/dd/yyyy"));
        birthDateTF.setPreferredSize(new Dimension(150, 20));
        bdPan.add(birthDateLbl);
        bdPan.add(birthDateTF);
        cbIsComplete = new JCheckBox("Is the record complete?");

        this.add(namePatient);
        this.add(sexPan);
        this.add(bdPan);
        this.add(cbIsComplete);
    }

    public FullNameForm getNamePatient() {
        return namePatient;
    }

    public char getSex() {
        if(maleRB.isSelected())
            return 'M';
        else
            return 'F';
    }

    public String getBirthDate() {
        return birthDateTF.getText();
    }

    public boolean isCompleted() {
        return cbIsComplete.isSelected();
    }

    public void fillForm(BasicDetails d) {
        namePatient.fillForm(d.getfName(), d.getmName(), d.getlName());
        if(d.getSex() == 'M')
            maleRB.setSelected(true);
        else
            femaleRB.setSelected(true);
        birthDateTF.setText(d.getBirthDate());
        cbIsComplete.setSelected(d.isComplete());
    }

    public BasicDetails getBasicDetails() {
        return new BasicDetails(
                namePatient.getfNameInput(),
                namePatient.getmNameInput(),
                namePatient.getlNameInput(),
                getSex(),
                getBirthDate(),
                isCompleted()
        );
    }
}
