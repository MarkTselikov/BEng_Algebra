package Model;

import DAL.Repo.IRepo;
import DAL.Repo.SqlRepo;

import java.util.Date;
import java.util.List;

public class ReportMaker {

    private static IRepo repo = new SqlRepo();

    public static String getLongReport() {

        StringBuilder report = new StringBuilder();
        report.append("Report for ").append(new Date().toString()).append("\n\n");

        report.append("Doctor statistics: \n");
        List<Doctor> doctors = repo.getDoctors();

        for (Doctor d:doctors) {
            report.append("\t").append(d.toString()).append(" Patient list: \n");

            List<Patient> patients = repo.getAssignedPatients(d);
            for (Patient p:patients) {
                report.append("\t\t").append(p.toString()).append("\n\n");
            }
            report.append("\tNumber of patients: ").append(patients.size());
            report.append("\n\n");
        }

        int totalBills = 0;
        report.append("\n\n\nProcedures for each Patient: \n\n");
        List<Patient> patients = repo.getAllPatients();
        for (Patient p:patients) {
            report.append("\t").append(p.toString()).append(" report: \n\n");

            List<Record> records = repo.getRecords(p);
            for (Record r:records) {
                if(!p.getBasicDetails().isComplete())
                    report.append("\t* The patients registration form is not complete, some information might be missing\n");
                report.append("\t\tAppointments: ").append(r.getAppointments()).append("\n");
                report.append("\t\tTests: ").append(r.getTests()).append("\n");
                report.append("\t\tPrescriptions: ").append(r.getPrescriptions()).append("\n");
                report.append("\t\tBill: ").append(r.getBill()).append("\n\n");
                totalBills += r.getBill();
            }
            report.append("\n");
        }
        report.append("\n\n").append("Total Bill: ").append(totalBills);

        return report.toString();
    }

    public static String getShortReport() {

        StringBuilder report = new StringBuilder();
        report.append("Report for ").append(new Date().toString()).append("\n\n");
        int incompForms = 0;


        report.append("\n\nStatistics for each Patient: \n\n");
        List<Patient> patients = repo.getAllPatients();
        for (Patient p:patients) {
            if(!p.getBasicDetails().isComplete())
                incompForms++;

            report.append("\t").append(p.toString()).append(" report:");
            report.append("\n\t").append(p.getBasicDetails().isComplete() ? "Complete form\n\n" : "Incomplete form\n\n");

            List<Record> records = repo.getRecords(p);
            for (Record r:records) {
                report.append("\t\tDiagnosis: ").append(r.getDiagnosis()).append("\n");
                report.append("\t\tDoctor: ").append(r.getDoctor()).append("\n\n");
            }
            report.append("\n");
        }
        report.append("\nNumber of incomplete forms: ").append(incompForms);

        return report.toString();
    }
}
