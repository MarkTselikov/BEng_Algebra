package CustomControlls;

import Model.BasicDetails;
import Model.Doctor;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DoctorForm extends JPanel {

    private int id = 0;

    private FullNameForm nameDoctor;
    private JRadioButton maleRB;
    private JRadioButton femaleRB;
    private JFormattedTextField birthDateTF;
    private JTextField txtField;

    public DoctorForm() {
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        nameDoctor = new FullNameForm();

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

        JLabel lblField = new JLabel("Field: ");
        txtField = new JTextField();

        this.add(nameDoctor);
        this.add(sexPan);
        this.add(bdPan);
        this.add(lblField);
        this.add(txtField);
    }

    public char getSex() {
        if(maleRB.isSelected())
            return 'M';
        else
            return 'F';
    }

    public void setBtnSubmit(JButton btnSubmit) {
        this.add(btnSubmit);
    }

    public void fillForm(Doctor d) {
        id = d.getId();
        nameDoctor.fillForm(d.getfName(), d.getmName(), d.getlName());
        if(d.getSex() == 'M')
            maleRB.setSelected(true);
        else
            femaleRB.setSelected(true);
        birthDateTF.setText(d.getBirthDate().toString());
        txtField.setText(d.getField());
    }

    public Doctor getDoctor() {
        return new Doctor(
                id,
                nameDoctor.getfNameInput(),
                nameDoctor.getmNameInput(),
                nameDoctor.getlNameInput(),
                getSex(),
                new Date(),
                txtField.getText()
        );
    }
}
