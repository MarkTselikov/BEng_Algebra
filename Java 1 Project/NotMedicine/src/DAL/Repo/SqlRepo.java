package DAL.Repo;

import DAL.SQL.DataSourceSingleton;
import Model.*;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SqlRepo implements IRepo {

    private DataSource ds = DataSourceSingleton.getInstance();

    @Override
    public Patient getPatient(int id) {
        ds = DataSourceSingleton.getInstance();
        final String GET_PATIENT = " { call GetPatient (?) } ";
        try (Connection con = ds.getConnection();
                CallableStatement statement = con.prepareCall(GET_PATIENT))
        {
             statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                // and here we create stuff
                Patient p = new Patient(null, null, null, null, null, null, null);
                p.setFName(rs.getString(2));
                p.setLName(rs.getString(4));

                return p;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Patient> getAllPatients() {

        List<Patient> patients = new ArrayList<>();

        final String GET_ALL_PATIENTS_IND = "{ CALL GetPatientsIndexes }";
        final String GET_BASIC_INFO = "{ CALL GetPatientBasicInformation (?) }";
        final String GET_CONTACT = "{ CALL GetPatientContact (?) }";
        final String GET_NOK = "{ CALL GetPatientNOK (?) }";
        final String GET_LIFESTYLE = "{ CALL GetPatientLifestyle (?) }";
        final String GET_PERSONAL_DATA = "{ CALL GetPatientPersonalData (?) }";
        final String GET_BASIC_COMP = "{ CALL GetPatientBasicComplaints (?) }";
        final String GET_MED_COMP = "{ CALL GetPatientMedicalComplaints (?) }";
        final String GET_PATIENT = "{ CALL GetPatient (?) }";

        List<Integer> indexes = new ArrayList<>();

        BasicDetails basicDetails = null;
        ContactDetails contact = null;
        NextOfKin nextOfKin = null;
        Lifestyle lifestyle = null;
        PersonalDetails personalData = null;
        BasicComplaints basicComplaints = null;
        MedicalComplaints medicalComplaints = null;

        try (Connection con = ds.getConnection();
             CallableStatement stmt = con.prepareCall(GET_ALL_PATIENTS_IND);
             ResultSet resultSet = stmt.executeQuery()){

            while (resultSet.next()) {
                indexes.add(resultSet.getInt(1));
            }


        } catch (Exception e) { e.printStackTrace(); }

        for (int id:indexes) {
            try (Connection con = ds.getConnection();
                 CallableStatement stmt = con.prepareCall(GET_BASIC_INFO)){
                 stmt.setInt(1, id);
                 ResultSet resultSet = stmt.executeQuery();
                while (resultSet.next()) {
                    basicDetails = new BasicDetails(
                            resultSet.getString("FirstName"),
                            resultSet.getString("MiddleName"),
                            resultSet.getString("LastName"),
                            resultSet.getString("Sex").charAt(0),
                            resultSet.getString("BirthDate"),
                            resultSet.getBoolean("IsComplete")
                    );
                }

                CallableStatement stmtC = con.prepareCall(GET_CONTACT);
                stmtC.setInt(1, id);
                ResultSet resultSetC = stmtC.executeQuery();
                while (resultSetC.next()) {
                    contact = new ContactDetails(
                            resultSetC.getString(2),
                            resultSetC.getString(3),
                            resultSetC.getString(4),
                            resultSetC.getString(5),
                            resultSetC.getString(6),
                            resultSetC.getString(7),
                            resultSetC.getString(8),
                            resultSetC.getString(9)
                    );
                }

                CallableStatement stmtNOK = con.prepareCall(GET_NOK);
                stmtNOK.setInt(1, id);
                ResultSet resultSetNOK = stmtNOK.executeQuery();
                while (resultSetNOK.next()) {
                    nextOfKin = new NextOfKin(
                            resultSetNOK.getString(2),
                            resultSetNOK.getString(3),
                            resultSetNOK.getString(4),
                            resultSetNOK.getString(5),
                            new ContactDetails(
                                    resultSetNOK.getString(6),
                                    resultSetNOK.getString(7),
                                    resultSetNOK.getString(8),
                                    resultSetNOK.getString(9),
                                    resultSetNOK.getString(10),
                                    resultSetNOK.getString(11),
                                    resultSetNOK.getString(12),
                                    resultSetNOK.getString(13)
                            )
                    );
                }

                CallableStatement stmtL = con.prepareCall(GET_LIFESTYLE);
                stmtL.setInt(1, id);
                ResultSet resultSetL = stmtL.executeQuery();
                while (resultSetL.next()) {
                    lifestyle = new Lifestyle(
                            resultSetL.getBoolean(2),
                            resultSetL.getBoolean(3),
                            resultSetL.getBoolean(4),
                            resultSetL.getBoolean(5),
                            resultSetL.getInt(6),
                            resultSetL.getInt(7),
                            resultSetL.getBoolean(8),
                            resultSetL.getBoolean(9)
                    );
                }

                CallableStatement stmtPD = con.prepareCall(GET_PERSONAL_DATA);
                stmtPD.setInt(1, id);
                ResultSet resultSetPD = stmtPD.executeQuery();
                while (resultSetPD.next()) {

                    personalData = new PersonalDetails(
                            resultSetPD.getString(2),
                            resultSetPD.getInt(3),
                            resultSetPD.getInt(4),
                            resultSetPD.getInt(5),
                            resultSetPD.getString(6),
                            resultSetPD.getString(7),
                            resultSetPD.getInt(8)
                    );
                }

                CallableStatement stmtBC = con.prepareCall(GET_BASIC_COMP);
                stmtBC.setInt(1, id);
                ResultSet resultSetBC = stmtBC.executeQuery();
                while (resultSetBC.next()) {
                    basicComplaints = new BasicComplaints(
                            resultSetBC.getString(2),
                            resultSetBC.getString(3),
                            resultSetBC.getString(4)
                    );
                }

                CallableStatement stmtMC = con.prepareCall(GET_MED_COMP);
                stmtMC.setInt(1, id);
                ResultSet resultSetMC = stmtMC.executeQuery();
                while (resultSetMC.next()) {
                    medicalComplaints = new MedicalComplaints(
                            resultSetMC.getBoolean(2),
                            resultSetMC.getBoolean(3),
                            resultSetMC.getBoolean(4),
                            resultSetMC.getBoolean(5),
                            resultSetMC.getBoolean(6),
                            resultSetMC.getBoolean(7),
                            resultSetMC.getBoolean(8),
                            resultSetMC.getBoolean(9),
                            resultSetMC.getString(10),
                            resultSetMC.getString(11),
                            resultSetMC.getString(12)
                    );
                }

                CallableStatement stmtPt = con.prepareCall(GET_PATIENT);
                stmtPt.setInt(1, id);
                ResultSet resultSetPt = stmtPt.executeQuery();
                while (resultSetPt.next()) {
                    patients.add(
                            new Patient(id, resultSetPt.getString("IncomingDate"),
                                    basicDetails, contact, nextOfKin, personalData, lifestyle, basicComplaints, medicalComplaints));
                }
            } catch (Exception e) { e.printStackTrace(); break; }
        }

        return patients;
    }

    @Override
    public List<Patient> getAssignedPatients(Doctor d) {

        List<Patient> patients = new ArrayList<>();

        final String GET_ALL_PATIENTS_IND = "{ CALL GetPatientsByDoctor (?) }";
        final String GET_BASIC_INFO = "{ CALL GetPatientBasicInformation (?) }";
        final String GET_CONTACT = "{ CALL GetPatientContact (?) }";
        final String GET_NOK = "{ CALL GetPatientNOK (?) }";
        final String GET_LIFESTYLE = "{ CALL GetPatientLifestyle (?) }";
        final String GET_PERSONAL_DATA = "{ CALL GetPatientPersonalData (?) }";
        final String GET_BASIC_COMP = "{ CALL GetPatientBasicComplaints (?) }";
        final String GET_MED_COMP = "{ CALL GetPatientMedicalComplaints (?) }";
        final String GET_PATIENT = "{ CALL GetPatient (?) }";

        List<Integer> indexes = new ArrayList<>();

        BasicDetails basicDetails = null;
        ContactDetails contact = null;
        NextOfKin nextOfKin = null;
        Lifestyle lifestyle = null;
        PersonalDetails personalData = null;
        BasicComplaints basicComplaints = null;
        MedicalComplaints medicalComplaints = null;

        try (Connection con = ds.getConnection();
             CallableStatement stmt = con.prepareCall(GET_ALL_PATIENTS_IND)){
             stmt.setInt(1, d.getId());
             ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                indexes.add(resultSet.getInt(1));
            }


        } catch (Exception e) { e.printStackTrace(); }

        for (int id:indexes) {
            try (Connection con = ds.getConnection();
                 CallableStatement stmt = con.prepareCall(GET_BASIC_INFO)){
                stmt.setInt(1, id);
                ResultSet resultSet = stmt.executeQuery();
                while (resultSet.next()) {
                    basicDetails = new BasicDetails(
                            resultSet.getString("FirstName"),
                            resultSet.getString("MiddleName"),
                            resultSet.getString("LastName"),
                            resultSet.getString("Sex").charAt(0),
                            resultSet.getString("BirthDate"),
                            resultSet.getBoolean("IsComplete")
                    );
                }

                CallableStatement stmtC = con.prepareCall(GET_CONTACT);
                stmtC.setInt(1, id);
                ResultSet resultSetC = stmtC.executeQuery();
                while (resultSetC.next()) {
                    contact = new ContactDetails(
                            resultSetC.getString(2),
                            resultSetC.getString(3),
                            resultSetC.getString(4),
                            resultSetC.getString(5),
                            resultSetC.getString(6),
                            resultSetC.getString(7),
                            resultSetC.getString(8),
                            resultSetC.getString(9)
                    );
                }

                CallableStatement stmtNOK = con.prepareCall(GET_NOK);
                stmtNOK.setInt(1, id);
                ResultSet resultSetNOK = stmtNOK.executeQuery();
                while (resultSetNOK.next()) {
                    nextOfKin = new NextOfKin(
                            resultSetNOK.getString(2),
                            resultSetNOK.getString(3),
                            resultSetNOK.getString(4),
                            resultSetNOK.getString(5),
                            new ContactDetails(
                                    resultSetNOK.getString(6),
                                    resultSetNOK.getString(7),
                                    resultSetNOK.getString(8),
                                    resultSetNOK.getString(9),
                                    resultSetNOK.getString(10),
                                    resultSetNOK.getString(11),
                                    resultSetNOK.getString(12),
                                    resultSetNOK.getString(13)
                            )
                    );
                }

                CallableStatement stmtL = con.prepareCall(GET_LIFESTYLE);
                stmtL.setInt(1, id);
                ResultSet resultSetL = stmtL.executeQuery();
                while (resultSetL.next()) {
                    lifestyle = new Lifestyle(
                            resultSetL.getBoolean(2),
                            resultSetL.getBoolean(3),
                            resultSetL.getBoolean(4),
                            resultSetL.getBoolean(5),
                            resultSetL.getInt(6),
                            resultSetL.getInt(7),
                            resultSetL.getBoolean(8),
                            resultSetL.getBoolean(9)
                    );
                }

                CallableStatement stmtPD = con.prepareCall(GET_PERSONAL_DATA);
                stmtPD.setInt(1, id);
                ResultSet resultSetPD = stmtPD.executeQuery();
                while (resultSetPD.next()) {

                    personalData = new PersonalDetails(
                            resultSetPD.getString(2),
                            resultSetPD.getInt(3),
                            resultSetPD.getInt(4),
                            resultSetPD.getInt(5),
                            resultSetPD.getString(6),
                            resultSetPD.getString(7),
                            resultSetPD.getInt(8)
                    );
                }

                CallableStatement stmtBC = con.prepareCall(GET_BASIC_COMP);
                stmtBC.setInt(1, id);
                ResultSet resultSetBC = stmtBC.executeQuery();
                while (resultSetBC.next()) {
                    basicComplaints = new BasicComplaints(
                            resultSetBC.getString(2),
                            resultSetBC.getString(3),
                            resultSetBC.getString(4)
                    );
                }

                CallableStatement stmtMC = con.prepareCall(GET_MED_COMP);
                stmtMC.setInt(1, id);
                ResultSet resultSetMC = stmtMC.executeQuery();
                while (resultSetMC.next()) {
                    medicalComplaints = new MedicalComplaints(
                            resultSetMC.getBoolean(2),
                            resultSetMC.getBoolean(3),
                            resultSetMC.getBoolean(4),
                            resultSetMC.getBoolean(5),
                            resultSetMC.getBoolean(6),
                            resultSetMC.getBoolean(7),
                            resultSetMC.getBoolean(8),
                            resultSetMC.getBoolean(9),
                            resultSetMC.getString(10),
                            resultSetMC.getString(11),
                            resultSetMC.getString(12)
                    );
                }

                CallableStatement stmtPt = con.prepareCall(GET_PATIENT);
                stmtPt.setInt(1, id);
                ResultSet resultSetPt = stmtPt.executeQuery();
                while (resultSetPt.next()) {
                    patients.add(
                            new Patient(id, resultSetPt.getString("IncomingDate"),
                                    basicDetails, contact, nextOfKin, personalData, lifestyle, basicComplaints, medicalComplaints));
                }
            } catch (Exception e) { e.printStackTrace(); break; }
        }

        return patients;
    }

    @Override
    public List<Record> getRecords(Patient p) {

        int id = p.getId();
        List<Record> records = new ArrayList<>();
        Doctor doctor = null;

        final String GET_RECORDS = "{ CALL GetRecords (?) }";
        final String GET_DOCTOR = "{ CALL GetDoctor (?) }";

        try (Connection con = ds.getConnection();
             CallableStatement stmt = con.prepareCall(GET_RECORDS)){
             stmt.setInt(1, id);
             ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {

                CallableStatement stmtDoc = con.prepareCall(GET_DOCTOR);
                stmtDoc.setInt(1, resultSet.getInt("DoctorID"));
                ResultSet resultSetDoc = stmtDoc.executeQuery();

                if(resultSetDoc.next())
                    doctor = new Doctor(
                            resultSet.getInt("DoctorID"),
                            resultSetDoc.getString("FirstName"),
                            resultSetDoc.getString("MiddleName"),
                            resultSetDoc.getString("LastName"),
                            resultSetDoc.getString("Sex").charAt(0),
                            resultSetDoc.getDate("BirthDate"),
                            resultSetDoc.getString("Field")
                    );

                Record r = new Record(
                        p, doctor,
                        resultSet.getString("Diagnosis"),
                        resultSet.getString("DiagnosisDescription"),
                        resultSet.getString("Appointments"),
                        resultSet.getString("Tests"),
                        resultSet.getString("Prescription"),
                        resultSet.getInt("BillAmount")
                );
                r.setId(resultSet.getInt("IDRecord"));
                records.add(r);
            }
            return records;

        } catch (Exception e) { e.printStackTrace(); }
        return records;
    }

    @Override
    public List<Doctor> getDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        final String GET_DOCTORS = "{ CALL getAllDoctors }";

        try (Connection con = ds.getConnection();
             CallableStatement stmt = con.prepareCall(GET_DOCTORS);
             ResultSet resultSet = stmt.executeQuery()){
            while (resultSet.next()) {
                doctors.add(
                        new Doctor(
                                resultSet.getInt("IDDoctor"),
                                resultSet.getString("FirstName"),
                                resultSet.getString("MiddleName"),
                                resultSet.getString("LastName"),
                                resultSet.getString("Sex").charAt(0),
                                new Date(),
                                resultSet.getString("Field")));
            }
            return doctors;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return doctors;
    }

    @Override
    public int createPatient(Patient p) {

        final String CREATE_PATIENT = "{ CALL CreatePatient (?,?) }";
        final String CREATE_BASIC_INFO = "{ CALL CreateBasicInformation (?,?,?,?,?,?,?) }";
        final String CREATE_CONTACT = "{ CALL CreateContact (?,?,?,?,?,?,?,?,?) }";
        final String CREATE_PERSONAL_DATA = "{ CALL InsertPersonalData (?,?,?,?,?,?,?,?) }";
        final String CREATE_LIFESTYLE = "{ CALL InsertLifestyle (?,?,?,?,?,?,?,?,?) }";
        final String CREATE_BASIC_COMP = "{ CALL InsertBasicComplaints (?,?,?,?) }";
        final String CREATE_MED_COMP = "{ CALL InsertMedComplaints (?,?,?,?,?,?,?,?,?,?,?,?) }";

        int id = 0;

        try(Connection con = ds.getConnection();
            CallableStatement stmt = con.prepareCall(CREATE_PATIENT)) {
            stmt.setString(1, p.getIncomingDate());
            stmt.registerOutParameter(2, Types.INTEGER);
            stmt.executeUpdate();
            id = stmt.getInt(2);

            CallableStatement stmtBI = con.prepareCall(CREATE_BASIC_INFO);
            BasicDetails bd = p.getBasicDetails();
            stmtBI.setInt(1, id);
            stmtBI.setString(2, bd.getfName());
            stmtBI.setString(3, bd.getmName());
            stmtBI.setString(4, bd.getlName());
            stmtBI.setString(5, "" + bd.getSex());
            stmtBI.setString(6, bd.getBirthDate());
            stmtBI.setBoolean(7, bd.isComplete());
            stmtBI.executeUpdate();

            CallableStatement stmtCD = con.prepareCall(CREATE_CONTACT);
            ContactDetails c = p.getContactDetails();
            stmtCD.setInt(1, id);
            stmtCD.setString(2, c.getPresentAddress());
            stmtCD.setString(3, c.getPermanentAddress());
            stmtCD.setString(4, c.getTelephoneWork());
            stmtCD.setString(5, c.getTelephoneHome());
            stmtCD.setString(6, c.getMobile());
            stmtCD.setString(7, c.getPager());
            stmtCD.setString(8, c.getFax());
            stmtCD.setString(9, c.getEmail());
            stmtCD.executeUpdate();

            CallableStatement stmtPD = con.prepareCall(CREATE_PERSONAL_DATA);
            PersonalDetails pd = p.getPersonalDetails();
            stmtPD.setInt(1, id);
            stmtPD.setString(2, pd.getMartialStatus());
            stmtPD.setInt(3, pd.getNumDependends());
            stmtPD.setInt(4, pd.getHeight());
            stmtPD.setInt(5, pd.getWeight());
            stmtPD.setString(6, pd.getBloodType());
            stmtPD.setString(7, pd.getOccupation());
            stmtPD.setInt(8, pd.getIncome());
            stmtPD.executeUpdate();

            CallableStatement stmtL = con.prepareCall(CREATE_LIFESTYLE);
            Lifestyle l = p.getLifestyle();
            stmtL.setInt(1, id);
            stmtL.setBoolean(2, l.isVegetarian());
            stmtL.setBoolean(3, l.isSmoker());
            stmtL.setBoolean(4, l.isAlcohol());
            stmtL.setBoolean(5, l.isStimulants());
            stmtL.setInt(6, l.getTeaCoffeeDay());
            stmtL.setInt(7, l.getSoftDrinksDay());
            stmtL.setBoolean(8, l.isRegularMeals());
            stmtL.setBoolean(9, l.isHomeFood());
            stmtL.executeUpdate();

            CallableStatement stmtBC = con.prepareCall(CREATE_BASIC_COMP);
            BasicComplaints b = p.getBasicComplaints();
            stmtBC.setInt(1, id);
            stmtBC.setString(2, b.getStatementOfComplaint());
            stmtBC.setString(3, b.getPrevTreatment());
            stmtBC.setString(4, b.getPhysicianHospital());
            stmtBC.executeUpdate();

            CallableStatement stmtMC = con.prepareCall(CREATE_MED_COMP);
            MedicalComplaints m = p.getMedicalComplaints();
            stmtMC.setInt(1, id);
            stmtMC.setBoolean(2, m.isDiabetic());
            stmtMC.setBoolean(3, m.isHypertensive());
            stmtMC.setBoolean(4, m.isCardCondition());
            stmtMC.setBoolean(5, m.isRespirCondition());
            stmtMC.setBoolean(6, m.isDigestCondition());
            stmtMC.setBoolean(7, m.isOrthopedicCondition());
            stmtMC.setBoolean(8, m.isMuscularCondition());
            stmtMC.setBoolean(9, m.isNeuroCondition());
            stmtMC.setString(10, m.getAllergies());
            stmtMC.setString(11, m.getAdverseDrugsReaction());
            stmtMC.setString(12, m.getMajorSurgeries());
            stmtMC.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
        return id;
    }

    @Override
    public void createRecord(Record r) {

        final String CREATE_RECORD = "{ CALL CreateRecord (?,?,?,?,?,?,?,?) }";

        try(Connection con = ds.getConnection();
            CallableStatement stmt = con.prepareCall(CREATE_RECORD)) {
            stmt.setString(1, r.getDiagnosis());
            stmt.setString(2, r.getDiagnDescription());
            stmt.setInt(3, r.getBill());
            stmt.setString(4, r.getAppointments());
            stmt.setString(5, r.getTests());
            stmt.setString(6, r.getPrescriptions());
            stmt.setInt(7, r.getDoctor().getId());
            stmt.setInt(8, r.getPatient().getId());

            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createDoctor(Doctor d) {

        final String CREATE_DOCTOR = "{ CALL CreateDoctor (?,?,?,?,?,?) }";

        try(Connection con = ds.getConnection();
            CallableStatement stmt = con.prepareCall(CREATE_DOCTOR)) {
            stmt.setString(1, d.getfName());
            stmt.setString(2, d.getmName());
            stmt.setString(3, d.getlName());
            stmt.setString(4, d.getField());
            stmt.setString(5, "" + d.getSex());
            stmt.setString(6, d.getBirthDate().toString());

            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateRecord(Record r) {

        final String UPDATE_RECORD = "{ CALL UpdateRecord (?,?,?,?,?,?,?,?,?) }";

        try(Connection con = ds.getConnection();
            CallableStatement stmt = con.prepareCall(UPDATE_RECORD)) {
            stmt.setInt(1, r.getId());
            stmt.setString(2, r.getDiagnosis());
            stmt.setString(3, r.getDiagnDescription());
            stmt.setInt(4, r.getBill());
            stmt.setString(5, r.getAppointments());
            stmt.setString(6, r.getTests());
            stmt.setString(7, r.getPrescriptions());
            stmt.setInt(8, r.getDoctor().getId());
            stmt.setInt(9, r.getPatient().getId());

            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateDoctor(Doctor d) {

        final String UPDATE_DOCTOR = "{ CALL UpdateDoctor (?,?,?,?,?,?,?) }";

        try(Connection con = ds.getConnection();
            CallableStatement stmt = con.prepareCall(UPDATE_DOCTOR)) {
            stmt.setInt(1, d.getId());
            stmt.setString(2, d.getfName());
            stmt.setString(3, d.getmName());
            stmt.setString(4, d.getlName());
            stmt.setString(5, d.getField());
            stmt.setString(6, "" + d.getSex());
            stmt.setString(7, d.getBirthDate().toString());

            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updatePatient(Patient p) {

        final String UPDATE_PATIENT = "{ CALL UpdatePatient (?,?) }";
        final String UPDATE_BASIC_INFO = "{ CALL UpdateBasicInformation (?,?,?,?,?,?,?) }";
        final String UPDATE_CONTACT = "{ CALL UpdateContact (?,?,?,?,?,?,?,?,?) }";
        final String UPDATE_PERSONAL_DATA = "{ CALL UpdatePersonalData (?,?,?,?,?,?,?,?) }";
        final String UPDATE_LIFESTYLE = "{ CALL UpdateLifestyle (?,?,?,?,?,?,?,?,?) }";
        final String UPDATE_BASIC_COMP = "{ CALL UpdateBasicComplaints (?,?,?,?) }";
        final String UPDATE_MED_COMP = "{ CALL UpdateMedComplaints (?,?,?,?,?,?,?,?,?,?,?,?) }";

        int id = p.getId();

        try(Connection con = ds.getConnection();
            CallableStatement stmt = con.prepareCall(UPDATE_PATIENT)) {
            stmt.setInt(1, id);
            stmt.setString(2, p.getIncomingDate());
            stmt.executeUpdate();

            CallableStatement stmtBI = con.prepareCall(UPDATE_BASIC_INFO);
            BasicDetails bd = p.getBasicDetails();
            stmtBI.setInt(1, id);
            stmtBI.setString(2, bd.getfName());
            stmtBI.setString(3, bd.getmName());
            stmtBI.setString(4, bd.getlName());
            stmtBI.setString(5, "" + bd.getSex());
            stmtBI.setString(6, bd.getBirthDate());
            stmtBI.setBoolean(7, bd.isComplete());
            stmtBI.executeUpdate();

            CallableStatement stmtCD = con.prepareCall(UPDATE_CONTACT);
            ContactDetails c = p.getContactDetails();
            stmtCD.setInt(1, id);
            stmtCD.setString(2, c.getPresentAddress());
            stmtCD.setString(3, c.getPermanentAddress());
            stmtCD.setString(4, c.getTelephoneWork());
            stmtCD.setString(5, c.getTelephoneHome());
            stmtCD.setString(6, c.getMobile());
            stmtCD.setString(7, c.getPager());
            stmtCD.setString(8, c.getFax());
            stmtCD.setString(9, c.getEmail());
            stmtCD.executeUpdate();

            CallableStatement stmtPD = con.prepareCall(UPDATE_PERSONAL_DATA);
            PersonalDetails pd = p.getPersonalDetails();
            stmtPD.setInt(1, id);
            stmtPD.setString(2, pd.getMartialStatus());
            stmtPD.setInt(3, pd.getNumDependends());
            stmtPD.setInt(4, pd.getHeight());
            stmtPD.setInt(5, pd.getWeight());
            stmtPD.setString(6, pd.getBloodType());
            stmtPD.setString(7, pd.getOccupation());
            stmtPD.setInt(8, pd.getIncome());
            stmtPD.executeUpdate();

            CallableStatement stmtL = con.prepareCall(UPDATE_LIFESTYLE);
            Lifestyle l = p.getLifestyle();
            stmtL.setInt(1, id);
            stmtL.setBoolean(2, l.isVegetarian());
            stmtL.setBoolean(3, l.isSmoker());
            stmtL.setBoolean(4, l.isAlcohol());
            stmtL.setBoolean(5, l.isStimulants());
            stmtL.setInt(6, l.getTeaCoffeeDay());
            stmtL.setInt(7, l.getSoftDrinksDay());
            stmtL.setBoolean(8, l.isRegularMeals());
            stmtL.setBoolean(9, l.isHomeFood());
            stmtL.executeUpdate();

            CallableStatement stmtBC = con.prepareCall(UPDATE_BASIC_COMP);
            BasicComplaints b = p.getBasicComplaints();
            stmtBC.setInt(1, id);
            stmtBC.setString(2, b.getStatementOfComplaint());
            stmtBC.setString(3, b.getPrevTreatment());
            stmtBC.setString(4, b.getPhysicianHospital());
            stmtBC.executeUpdate();

            CallableStatement stmtMC = con.prepareCall(UPDATE_MED_COMP);
            MedicalComplaints m = p.getMedicalComplaints();
            stmtMC.setInt(1, id);
            stmtMC.setBoolean(2, m.isDiabetic());
            stmtMC.setBoolean(3, m.isHypertensive());
            stmtMC.setBoolean(4, m.isCardCondition());
            stmtMC.setBoolean(5, m.isRespirCondition());
            stmtMC.setBoolean(6, m.isDigestCondition());
            stmtMC.setBoolean(7, m.isOrthopedicCondition());
            stmtMC.setBoolean(8, m.isMuscularCondition());
            stmtMC.setBoolean(9, m.isNeuroCondition());
            stmtMC.setString(10, m.getAllergies());
            stmtMC.setString(11, m.getAdverseDrugsReaction());
            stmtMC.setString(12, m.getMajorSurgeries());
            stmtMC.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    @Override
    public void deletePatient(int id) {
        final String DELETE_PATIENT = "{ CALL DeletePatient (?) }";

        try(Connection con = ds.getConnection();
            CallableStatement stmt = con.prepareCall(DELETE_PATIENT)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteRecord(int id) {
        final String DELETE_RECORD = "{ CALL DeleteRecord (?) }";

        try(Connection con = ds.getConnection();
            CallableStatement stmt = con.prepareCall(DELETE_RECORD)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteDoctor(int id) {
        final String DELETE_DOCOTER = "{ CALL DeleteDoctor (?) }";

        try(Connection con = ds.getConnection();
            CallableStatement stmt = con.prepareCall(DELETE_DOCOTER)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


