package Model;

public class BasicComplaints {
    private String statementOfComplaint;
    private String prevTreatment;
    private String physicianHospital;

    public BasicComplaints(String statementOfComplaint, String prevTreatment, String physicianHospital) {
        this.statementOfComplaint = statementOfComplaint;
        this.prevTreatment = prevTreatment;
        this.physicianHospital = physicianHospital;
    }

    public String getStatementOfComplaint() {
        return statementOfComplaint;
    }

    public String getPrevTreatment() {
        return prevTreatment;
    }

    public String getPhysicianHospital() {
        return physicianHospital;
    }

    @Override
    public String toString() {
        return String.format("Statement of complaint: %s \n" +
                "Previous treatment: %s \n" +
                "Physician: %s\n",
                statementOfComplaint, prevTreatment, physicianHospital);
    }
}
