package DAL.Repo;

import Model.*;

import java.util.List;

public interface IRepo {

    Patient getPatient(int id);

    int createPatient(Patient p);

    void createRecord(Record r);

    void createDoctor(Doctor d);

    void updatePatient(Patient p);

    void updateRecord(Record r);

    void updateDoctor(Doctor d);

    List<Doctor> getDoctors();

    List<Patient> getAllPatients();

    List<Patient> getAssignedPatients(Doctor d);

    List<Record> getRecords(Patient p);

    void deletePatient(int id);

    void deleteRecord(int id);

    void deleteDoctor(int id);
}
