import CustomControlls.*;
import DAL.Repo.IRepo;
import DAL.Repo.RepoFactory;
import DAL.Repo.SqlRepo;
import Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.util.*;
import java.util.List;

public class GUI {

    private final int WIDTH = 900;
    private final int HEIGHT = 500;

    private JFrame mainFrame;
    private JPanel panContent;
    private JPanel topMenuPan;

    private JButton btnHome;
    private JButton btnIncomplRecods;
    private JButton btnDisplayDoctors;
    private JButton btnShowPatients;
    private JButton btnReports;
    private JButton quickPatientBtn;
    private JButton btnTerminal;
    private JButton btnEditPatient;
    private JButton btnSubmit;
    private JButton btnEditSubmit;

    private RegistrationForm regForm;
    private IRepo repo;

    private List<Patient> patients = new ArrayList<>();


    public GUI() {

        mainFrame = new JFrame();
        mainFrame.setLayout(new BorderLayout());

        // The panel that will display the main content of each section of menu
        panContent = new JPanel(new BorderLayout());
        panContent.add(displayHomePage(), BorderLayout.CENTER);

        // Top bar section
        topMenuPan = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        quickPatientBtn = new JButton();
        btnTerminal = new JButton();
        btnSubmit = new JButton();
        btnSubmit.setVisible(false);
        btnEditSubmit = new JButton();
        btnEditSubmit.setVisible(false);

        topMenuPan.add(btnTerminal);
        topMenuPan.add(quickPatientBtn);
        topMenuPan.add(btnSubmit);
        topMenuPan.add(btnEditSubmit);
        mainFrame.add(topMenuPan, BorderLayout.NORTH);

        // Constructing the side menu with navigation controls
        JPanel panMenu = new JPanel(new GridLayout(6, 1));
        JLabel lblMenu = new JLabel("Menu: ");

        btnHome = new JButton();
        btnIncomplRecods = new JButton();
        btnDisplayDoctors = new JButton();
        btnShowPatients = new JButton();
        btnReports = new JButton();

        setMenuFunctionality();

        btnHome.setText("Home");
        btnIncomplRecods.setText("Review Incomplete Forms");
        btnShowPatients.setText("Display Patients");
        btnDisplayDoctors.setText("Display Doctors");
        btnReports.setText("Show Reports");
        quickPatientBtn.setText("New Patient");
        btnTerminal.setText("Terminal Mode");

        panMenu.add(lblMenu);
        panMenu.add(btnHome);
        panMenu.add(btnIncomplRecods);
        panMenu.add(btnDisplayDoctors);
        panMenu.add(btnShowPatients);
        panMenu.add(btnReports);
        panMenu.setPreferredSize(new Dimension(200, HEIGHT));
        mainFrame.add(panMenu, BorderLayout.WEST);

        repo = RepoFactory.getSqlRepo();
        patients.addAll(repo.getAllPatients());

        // Configuring the main window
        mainFrame.add(panContent, BorderLayout.CENTER);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setSize(WIDTH, HEIGHT);
        mainFrame.setResizable(false);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);

        // other functional buttons
        btnEditPatient = new JButton("Edit");
        JButton btnPatientDetails = new JButton("Details");
        JButton btnPatientRecord = new JButton("View Record");
        JButton btnDeletePatient = new JButton("Delete");
    }

    private void setMenuFunctionality() {

        btnHome.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panContent.removeAll();
                panContent.add(displayHomePage(), BorderLayout.CENTER);
                panContent.revalidate();
                panContent.repaint();
            }
        });

        btnIncomplRecods.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panContent.removeAll();
                panContent.add(displayIncompleteForms(), BorderLayout.CENTER);
                panContent.revalidate();
                panContent.repaint();
            }
        });

        btnDisplayDoctors.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panContent.removeAll();
                panContent.add(displayDoctors(), BorderLayout.CENTER);
                panContent.revalidate();
                panContent.repaint();
            }
        });

        btnShowPatients.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panContent.removeAll();
                panContent.add(displayPatientsList(), BorderLayout.CENTER);
                panContent.revalidate();
                panContent.repaint();
            }
        });

        btnReports.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panContent.removeAll();
                panContent.add(displayReport(), BorderLayout.CENTER);
                panContent.revalidate();
                panContent.repaint();
            }
        });

        quickPatientBtn.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quickPatientBtn.setVisible(false);
                btnSubmit.setVisible(true);
                topMenuPan.revalidate();
                topMenuPan.repaint();

                panContent.removeAll();
                registrationForm();
                panContent.add(regForm, BorderLayout.CENTER);
                panContent.revalidate();
                panContent.repaint();
            }
        });

        btnTerminal.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setVisible(false);
                Terminal.mainMenu();
                mainFrame.dispatchEvent(new WindowEvent(mainFrame, WindowEvent.WINDOW_CLOSING));
            }
        });

        btnSubmit.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                quickPatientBtn.setVisible(true);
                btnSubmit.setVisible(false);
                topMenuPan.revalidate();
                topMenuPan.repaint();

                Patient p = new Patient(
                        regForm.getBasicDetails().getBasicDetails(),
                        regForm.getContact().getContactDetails(),
                        regForm.getNextOfKin().getNOK(),
                        regForm.getPersonalDetails().getPesonalDetails(),
                        regForm.getLifestyle().getLifestyle(),
                        regForm.getBasicComplaints().getBasicComplaints(),
                        regForm.getMedComplaints().getMedComplaints()
                );
                p.setId(repo.createPatient(p));
                patients.add(p);

                btnShowPatients.doClick();
            }
        });

        btnEditSubmit.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                quickPatientBtn.setVisible(true);
                btnEditSubmit.setVisible(false);
                topMenuPan.revalidate();
                topMenuPan.repaint();

                Patient p = new Patient(
                        regForm.getBasicDetails().getBasicDetails(),
                        regForm.getContact().getContactDetails(),
                        regForm.getNextOfKin().getNOK(),
                        regForm.getPersonalDetails().getPesonalDetails(),
                        regForm.getLifestyle().getLifestyle(),
                        regForm.getBasicComplaints().getBasicComplaints(),
                        regForm.getMedComplaints().getMedComplaints()
                );
                p.setId(regForm.getId());
                p.setIncomingDate(regForm.getIncomingDate());

                repo.updatePatient(p);
                patients.add(p);

                btnShowPatients.doClick();
            }
        });
    }

    private JPanel displayHomePage() {

        JPanel homePan = new JPanel();
        homePan.setLayout(new BorderLayout());

        JTextArea txtAbout = new JTextArea();
        txtAbout.setLineWrap(true);

        StringBuilder message = new StringBuilder();
        message.append("Welcome to the Outpatient management module!");
        message.append("In this program you can manage incoming patients, their doctors and their treatment.\n\n");
        message.append("On the side bar you can find menu with the main functionality. When you click a button, ")
            .append("the screen with a specific functionality will be displayed. On each screen you will find ")
            .append("additional functions, that are controlled by the buttons at the bottom of the screen.\n\n");
        message.append("On the top bar you can find buttons for quick access, like quick insert or Terminal mode.\n\n");
        message.append("There is also a Terminal mode available, but the functions in this mode are limited.")
                .append(" In this mode, you can navigate using number input. Available options will be displayed on the screen");

        txtAbout.setText(message.toString());
        txtAbout.setEditable(false);

        homePan.add(txtAbout, BorderLayout.CENTER);

        return homePan;
    }

    private JPanel displayPatientsList() {

        JPanel patientsPan = new JPanel();
        patientsPan.setLayout(new BorderLayout());

        Patient[] patA = new Patient[patients.size()];
        patA = patients.toArray(patA);

        DefaultListModel<Patient> model = new DefaultListModel<>();
        for (Patient p:patA) {
            model.addElement(p);
        }
        JList<Patient> patientList = new JList<>(model);

        JButton btnAdd = new JButton("Add New");;
        JButton btnDetails = new JButton("Details");
        JButton btnRecord = new JButton("View Records");
        JButton btnDelete = new JButton("Delete");

        btnAdd.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                quickPatientBtn.setVisible(false);
                btnSubmit.setVisible(true);
                topMenuPan.revalidate();
                topMenuPan.repaint();

                panContent.removeAll();
                registrationForm();
                panContent.add(regForm, BorderLayout.CENTER);
                panContent.revalidate();
                panContent.repaint();
            }
        });

        btnEditPatient.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(patientList.getSelectedValue() == null)
                    return;

                quickPatientBtn.setVisible(false);
                btnEditSubmit.setVisible(true);
                topMenuPan.revalidate();
                topMenuPan.repaint();

                patients.remove(patientList.getSelectedValue());

                panContent.removeAll();
                registrationForm(patientList.getSelectedValue());
                panContent.add(regForm, BorderLayout.CENTER);

                ListModel model = (DefaultListModel)patientList.getModel();
                ((DefaultListModel) model).removeElement(patientList.getSelectedValue());

                panContent.revalidate();
                panContent.repaint();
            }
        });

        btnDetails.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(patientList.getSelectedValue() == null)
                    return;

                panContent.removeAll();
                panContent.add(showPatientInformation(patientList.getSelectedValue()), BorderLayout.CENTER);
                panContent.revalidate();
                panContent.repaint();
            }
        });

        btnRecord.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(patientList.getSelectedValue() == null)
                    return;

                panContent.removeAll();
                panContent.add(displayRecordsList(patientList.getSelectedValue()), BorderLayout.CENTER);
                panContent.revalidate();
                panContent.repaint();
            }
        });

        btnDelete.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(patientList.getSelectedValue() == null)
                    return;

                repo.deletePatient(patientList.getSelectedValue().getId());
                patients.remove(patientList.getSelectedValue());
                ListModel model = (DefaultListModel)patientList.getModel();
                ((DefaultListModel) model).removeElement(patientList.getSelectedValue());

                panContent.revalidate();
                panContent.repaint();
            }
        });

        btnAdd.setText("Add New");
        btnEditPatient.setText("Edit");
        btnDetails.setText("Details");
        btnRecord.setText("View Records");
        btnDelete.setText("Delete");

        JPanel controlsPan = new JPanel(new FlowLayout());
        controlsPan.add(btnAdd);
        controlsPan.add(btnEditPatient);
        controlsPan.add(btnDetails);
        controlsPan.add(btnRecord);
        controlsPan.add(btnDelete);

        JScrollPane listScroller = new JScrollPane(patientList);
        patientsPan.add(listScroller, BorderLayout.CENTER);
        patientsPan.add(controlsPan, BorderLayout.SOUTH);

        return patientsPan;
    }

    private JPanel displayPatientsList(List<Patient> p) {

        JPanel patientsPan = new JPanel();
        patientsPan.setLayout(new BorderLayout());

        Patient[] patA = new Patient[p.size()];
        patA = p.toArray(patA);

        DefaultListModel<Patient> model = new DefaultListModel<>();
        for (Patient pt:patA) {
            model.addElement(pt);
        }
        JList<Patient> patientList = new JList<>(model);

        JButton btnAdd = new JButton("Add New");;
        JButton btnDetails = new JButton("Details");
        JButton btnRecord = new JButton("View Records");
        JButton btnDelete = new JButton("Delete");

        btnAdd.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                quickPatientBtn.setVisible(false);
                btnSubmit.setVisible(true);
                topMenuPan.revalidate();
                topMenuPan.repaint();

                panContent.removeAll();
                registrationForm();
                panContent.add(regForm, BorderLayout.CENTER);
                panContent.revalidate();
                panContent.repaint();
            }
        });

        btnEditPatient.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(patientList.getSelectedValue() == null)
                    return;

                quickPatientBtn.setVisible(false);
                btnEditSubmit.setVisible(true);
                topMenuPan.revalidate();
                topMenuPan.repaint();

                patients.remove(patientList.getSelectedValue());

                panContent.removeAll();
                registrationForm(patientList.getSelectedValue());
                panContent.add(regForm, BorderLayout.CENTER);

                ListModel model = (DefaultListModel)patientList.getModel();
                ((DefaultListModel) model).removeElement(patientList.getSelectedValue());

                panContent.revalidate();
                panContent.repaint();
            }
        });

        btnDetails.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(patientList.getSelectedValue() == null)
                    return;

                panContent.removeAll();
                panContent.add(showPatientInformation(patientList.getSelectedValue()), BorderLayout.CENTER);
                panContent.revalidate();
                panContent.repaint();
            }
        });

        btnRecord.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(patientList.getSelectedValue() == null)
                    return;

                panContent.removeAll();
                panContent.add(displayRecordsList(patientList.getSelectedValue()), BorderLayout.CENTER);
                panContent.revalidate();
                panContent.repaint();
            }
        });

        btnDelete.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(patientList.getSelectedValue() == null)
                    return;

                repo.deletePatient(patientList.getSelectedValue().getId());
                patients.remove(patientList.getSelectedValue());
                ListModel model = (DefaultListModel)patientList.getModel();
                ((DefaultListModel) model).removeElement(patientList.getSelectedValue());

                panContent.revalidate();
                panContent.repaint();
            }
        });

        btnAdd.setText("Add New");
        btnEditPatient.setText("Edit");
        btnDetails.setText("Details");
        btnRecord.setText("View Records");
        btnDelete.setText("Delete");

        JPanel controlsPan = new JPanel(new FlowLayout());
        controlsPan.add(btnAdd);
        controlsPan.add(btnEditPatient);
        controlsPan.add(btnDetails);
        controlsPan.add(btnRecord);
        controlsPan.add(btnDelete);

        JScrollPane listScroller = new JScrollPane(patientList);
        patientsPan.add(listScroller, BorderLayout.CENTER);
        patientsPan.add(controlsPan, BorderLayout.SOUTH);

        return patientsPan;
    }

    private JPanel displayIncompleteForms() {

        JPanel patientsPan = new JPanel();
        patientsPan.setLayout(new BorderLayout());

        List<Patient> incomPatients = new ArrayList<>();
        for (Patient p:patients) {
            if(!p.getBasicDetails().isComplete())
                incomPatients.add(p);
        }

        Patient[] patA = new Patient[incomPatients.size()];
        patA = incomPatients.toArray(patA);

        DefaultListModel<Patient> model = new DefaultListModel<>();
        for (Patient pt:patA) {
            model.addElement(pt);
        }
        JList<Patient> incompList = new JList<>(model);

        JButton btnEdit = new JButton("Edit");
        JButton btnDelete = new JButton("Delete");

        btnEdit.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(incompList.getSelectedValue() == null)
                    return;

                quickPatientBtn.setVisible(false);
                btnEditSubmit.setVisible(true);
                topMenuPan.revalidate();
                topMenuPan.repaint();

                patients.remove(incompList.getSelectedValue());

                panContent.removeAll();
                registrationForm(incompList.getSelectedValue());
                panContent.add(regForm, BorderLayout.CENTER);

                ListModel model = (DefaultListModel)incompList.getModel();
                ((DefaultListModel) model).removeElement(incompList.getSelectedValue());

                panContent.revalidate();
                panContent.repaint();
            }
        });

        btnDelete.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(incompList.getSelectedValue() == null)
                    return;

                repo.deletePatient(incompList.getSelectedValue().getId());
                patients.remove(incompList.getSelectedValue());
                ListModel model = (DefaultListModel)incompList.getModel();
                ((DefaultListModel) model).removeElement(incompList.getSelectedValue());

                panContent.revalidate();
                panContent.repaint();
            }
        });


        btnEdit.setText("Edit");
        btnDelete.setText("Delete");

        JPanel controlsPan = new JPanel(new FlowLayout());
        controlsPan.add(btnEdit);
        controlsPan.add(btnDelete);

        patientsPan.add(incompList, BorderLayout.CENTER);
        patientsPan.add(controlsPan, BorderLayout.SOUTH);

        return patientsPan;
    }

    private JPanel displayRecordsList(Patient p) {

        JPanel recordPan = new JPanel();
        recordPan.setLayout(new BorderLayout());

        JLabel lblTop = new JLabel("Records of patient: " + p.getBasicDetails().toString());
        recordPan.add(lblTop, BorderLayout.NORTH);

        List<Record> records = repo.getRecords(p);
        Record[] patA = new Record[records.size()];
        patA = records.toArray(patA);

        DefaultListModel<Record> model = new DefaultListModel<>();
        for (Record r:patA) {
            model.addElement(r);
        }
        JList<Record> recordList = new JList<>(model);

        JButton btnAdd = new JButton("Add New");;
        JButton btnDetails = new JButton("Details");
        JButton btnDelete = new JButton("Delete");

        btnAdd.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                RecordForm rForm = new RecordForm(p);
                JButton btnSub = new JButton();
                btnSub.setAction(new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        repo.createRecord(rForm.getRecord());
                        ListModel model = (DefaultListModel)recordList.getModel();
                        ((DefaultListModel)model).addElement(rForm.getRecord());
                        displayRecordsList(p);

                        JOptionPane.showMessageDialog(null,"New Record successfully created");

                        panContent.revalidate();
                        panContent.repaint();
                    }
                });

                rForm.setBtnSubmit(btnSub);
                rForm.revalidate();
                rForm.repaint();

                btnSub.setText("Submit");
                panContent.removeAll();
                panContent.add(rForm, BorderLayout.CENTER);
                panContent.revalidate();
                panContent.repaint();
            }
        });

        btnEditPatient.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(recordList.getSelectedValue() == null)
                    return;

                RecordForm rForm = new RecordForm(p);
                rForm.fillForm(recordList.getSelectedValue());
                JButton btnSub = new JButton();
                btnSub.setAction(new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        repo.updateRecord(rForm.getRecord());
                        ListModel model = (DefaultListModel)recordList.getModel();
                        ((DefaultListModel)model).addElement(rForm.getRecord());
                        displayRecordsList(p);

                        JOptionPane.showMessageDialog(null,"New Record successfully created");

                        panContent.revalidate();
                        panContent.repaint();
                    }
                });

                rForm.setBtnSubmit(btnSub);
                rForm.revalidate();
                rForm.repaint();

                btnSub.setText("Submit");
                panContent.removeAll();
                panContent.add(rForm, BorderLayout.CENTER);
                panContent.revalidate();
                panContent.repaint();
            }
        });

        btnDetails.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(recordList.getSelectedValue() == null)
                    return;

                panContent.removeAll();
                panContent.add(showRecordDetails(recordList.getSelectedValue()), BorderLayout.CENTER);
                panContent.revalidate();
                panContent.repaint();
            }
        });


        btnDelete.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(recordList.getSelectedValue() == null)
                    return;

                repo.deleteRecord(recordList.getSelectedValue().getId());
                ListModel model = (DefaultListModel)recordList.getModel();
                ((DefaultListModel) model).removeElement(recordList.getSelectedValue());

                panContent.revalidate();
                panContent.repaint();
            }
        });

        btnAdd.setText("Add New");
        btnEditPatient.setText("Edit");
        btnDetails.setText("Details");
        btnDelete.setText("Delete");

        JPanel controlsPan = new JPanel(new FlowLayout());
        controlsPan.add(btnAdd);
        controlsPan.add(btnEditPatient);
        controlsPan.add(btnDetails);
        controlsPan.add(btnDelete);

        JScrollPane listScroller = new JScrollPane(recordList);
        recordPan.add(listScroller, BorderLayout.CENTER);
        recordPan.add(controlsPan, BorderLayout.SOUTH);

        return recordPan;
    }

    private JPanel displayDoctors() {

        JPanel doctorPan = new JPanel();
        doctorPan.setLayout(new BorderLayout());

        List<Doctor> docL = repo.getDoctors();
        Doctor[] docA = new Doctor[docL.size()];
        docA = docL.toArray(docA);

        DefaultListModel<Doctor> model = new DefaultListModel<>();
        for (Doctor d:docA) {
            model.addElement(d);
        }
        JList<Doctor> doctorList = new JList<>(model);

        JButton btnAdd = new JButton("Create New");
        JButton btnPatients = new JButton("Display Patients");
        JButton btnEdit = new JButton("Edit");
        JButton btnDelete = new JButton("Delete");

        btnAdd.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                panContent.removeAll();
                DoctorForm form = new DoctorForm();
                JButton btnSub = new JButton();
                btnSub.setAction(new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        repo.createDoctor(form.getDoctor());
                        ListModel model = (DefaultListModel)doctorList.getModel();
                        ((DefaultListModel)model).addElement(form.getDoctor());

                        panContent.revalidate();
                        panContent.repaint();

                        btnDisplayDoctors.doClick();
                    }
                });
                btnSub.setText("Submit");
                form.setBtnSubmit(btnSub);

                panContent.add(form, BorderLayout.CENTER);
                panContent.revalidate();
                panContent.repaint();
            }
        });

        btnEdit.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(doctorList.getSelectedValue() == null)
                    return;

                DoctorForm form = new DoctorForm();
                form.fillForm(doctorList.getSelectedValue());
                JButton btnSub = new JButton();
                btnSub.setAction(new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        repo.updateDoctor(form.getDoctor());
                        ListModel model = (DefaultListModel)doctorList.getModel();
                        ((DefaultListModel)model).addElement(form.getDoctor());

                        panContent.revalidate();
                        panContent.repaint();

                        btnDisplayDoctors.doClick();
                    }
                });

                btnSub.setText("Submit");

                form.setBtnSubmit(btnSub);
                form.revalidate();
                form.repaint();

                panContent.removeAll();
                panContent.add(form, BorderLayout.CENTER);
                panContent.revalidate();
                panContent.repaint();


            }
        });

        btnPatients.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(doctorList.getSelectedValue() == null)
                    return;

                panContent.removeAll();
                panContent.add(displayPatientsList(repo.getAssignedPatients(doctorList.getSelectedValue())), BorderLayout.CENTER);
                panContent.revalidate();
                panContent.repaint();
            }
        });

        btnDelete.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(doctorList.getSelectedValue() == null)
                    return;

                repo.deleteDoctor(doctorList.getSelectedValue().getId());
                ListModel model = (DefaultListModel)doctorList.getModel();
                ((DefaultListModel) model).removeElement(doctorList.getSelectedValue());

                panContent.revalidate();
                panContent.repaint();
            }
        });

        btnAdd.setText("Create New");
        btnPatients.setText("Display Patients");
        btnEdit.setText("Edit");
        btnDelete.setText("Delete");

        JPanel controlsPan = new JPanel(new FlowLayout());
        controlsPan.add(btnAdd);
        controlsPan.add(btnEdit);
        controlsPan.add(btnPatients);
        controlsPan.add(btnDelete);

        doctorPan.add(doctorList, BorderLayout.CENTER);
        doctorPan.add(controlsPan, BorderLayout.SOUTH);

        return doctorPan;
    }

    private void registrationForm() {

        btnSubmit.setText("Submit");
        btnEditSubmit.setText("Submit Change");

        regForm = new RegistrationForm();
    }

    private void registrationForm(Patient p) {

        btnSubmit.setText("Submit");
        btnEditSubmit.setText("Submit Change");

        regForm = new RegistrationForm(p);
    }

    private JScrollPane showPatientInformation(Patient p) {

        JTextArea record = new JTextArea();
        record.setEditable(false);
        record.setText(p.getDetails());

        return new JScrollPane(record);
    }

    private JScrollPane showRecordDetails(Record r) {

        JTextArea record = new JTextArea();
        record.setEditable(false);
        record.setText(r.getDetails());

        return new JScrollPane(record);
    }

    private JScrollPane showRecord(Record r) {

        JTextArea record = new JTextArea();
        record.setEditable(false);
        record.setText(r.toString());

        return new JScrollPane(record);
    }

    private JPanel showRecordList(Patient p) {

        JPanel patientsPan = new JPanel();
        patientsPan.setLayout(new BorderLayout());

        Record[] r = (Record[])p.getRecords().toArray();    ////////////////////////////////////// TEST
        JList<Record> recordList = new JList<>(r);
        JButton btnPatients = new JButton("See Details");

        btnPatients.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(recordList.getSelectedValue() == null)
                    return;

                panContent.removeAll();
                panContent.add(showRecord(recordList.getSelectedValue()), BorderLayout.CENTER);
                panContent.revalidate();
                panContent.repaint();
            }
        });

        btnPatients.setText("See Details");

        JPanel controlsPan = new JPanel(new FlowLayout());
        controlsPan.add(btnPatients);

        patientsPan.add(recordList, BorderLayout.CENTER);
        patientsPan.add(controlsPan, BorderLayout.SOUTH);

        return patientsPan;
    }

    private JPanel displayReport(){

        JPanel content = new JPanel(new BorderLayout());
        JPanel panReportType = new JPanel();

        JTextArea report = new JTextArea();
        report.setEditable(false);

        JButton btnShort = new JButton();
        JButton btnBig = new JButton();

        btnShort.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                report.setText(ReportMaker.getShortReport());
            }
        });

        btnBig.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                report.setText(ReportMaker.getLongReport());
            }
        });

        btnShort.setText("Short");
        btnBig.setText("Full");

        panReportType.add(btnShort);
        panReportType.add(btnBig);

        JScrollPane panReport = new JScrollPane(report);
        content.add(panReportType, BorderLayout.NORTH);
        content.add(panReport, BorderLayout.CENTER);

        return content;
    }
}
