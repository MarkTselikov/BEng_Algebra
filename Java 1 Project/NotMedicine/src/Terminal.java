import DAL.Repo.IRepo;
import DAL.Repo.RepoFactory;
import DAL.Repo.SqlRepo;
import Model.Doctor;
import Model.Patient;
import Model.ReportMaker;

import java.util.*;

public class Terminal {

    private static Scanner in;
    private static IRepo repo = RepoFactory.getSqlRepo();

    public static void mainMenu() {

        int input;
        in = new Scanner(System.in);

        do {
            System.out.println("Main Menu:");
            System.out.println("\t1. Show incomplete records");
            System.out.println("\t2. Show doctors");
            System.out.println("\t3. Show patients");
            System.out.println("\t4. Show reports");
            System.out.println("\t0. Exit");

            input = in.nextInt();
            evaluateInput(input);

            System.out.println("\n\n");
        } while (input != 0);

        in.close();
    }


    public static void evaluateInput(int input) {

        switch (input) {
            case 1:
                List<Patient> records = repo.getAllPatients();
                for (Patient p:records) {
                    if(p.getBasicDetails().isComplete())
                        System.out.println(p);
                }
                System.out.println("\n\n");
                break;

            case 2:
                List<Doctor> doctors = repo.getDoctors();
                for (Doctor d:doctors) {
                    System.out.println(d);
                }
                System.out.println("\n\n");
                break;

            case 3:
                List<Patient> patients = repo.getAllPatients();
                for (Patient p:patients) {
                    System.out.println(p);
                }
                System.out.println("\n\n");
                break;

            case 4:
                System.out.println("Select the report type: \n\t1. Short \n\t2. Full ");
                int option = in.nextInt();

                if(option == 1)
                    System.out.println(ReportMaker.getShortReport());
                else if(option == 2)
                    System.out.println(ReportMaker.getShortReport());
                else
                    break;

                break;

            case 0:
                break;

            default:
                break;
        }
    }



}
