package CustomControlls;

import javax.swing.*;
import java.awt.*;

public class FullNameForm extends JPanel {

    private JTextField fNameTF;
    private JTextField mNameTF;
    private JTextField lNameTF;

    public FullNameForm() {

        JLabel nameLbl = new JLabel("Name: ");
        fNameTF = new JTextField();
        mNameTF = new JTextField();
        lNameTF = new JTextField();

        fNameTF.setPreferredSize(new Dimension(150, 20));
        mNameTF.setPreferredSize(new Dimension(150, 20));
        lNameTF.setPreferredSize(new Dimension(150, 20));

        this.setLayout(new FlowLayout());
        this.add(nameLbl);
        this.add(fNameTF);
        this.add(mNameTF);
        this.add(lNameTF);
    }

    public String getfNameInput() {
        return fNameTF.getText();
    }

    public String getmNameInput() {
        return mNameTF.getText();
    }

    public String getlNameInput() {
        return lNameTF.getText();
    }

    public void fillForm(String fName, String mName, String lName) {
        if(mName == null)
            mName = "";

        fNameTF.setText(fName);
        mNameTF.setText(mName);
        lNameTF.setText(lName);
    }
}
