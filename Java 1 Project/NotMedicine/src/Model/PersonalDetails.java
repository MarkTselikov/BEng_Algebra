package Model;

public class PersonalDetails {

    private String martialStatus;
    private int numDependends;
    private int height;
    private int weight;
    private String bloodType;
    private String occupation;
    private int income;

    public String getMartialStatus() {
        return martialStatus;
    }

    public PersonalDetails(String martialStatus, int numDependends, int height, int weight, String bloodType, String occupation, int income) {
        this.martialStatus = martialStatus;
        this.numDependends = numDependends;
        this.height = height;
        this.weight = weight;
        this.bloodType = bloodType;
        this.occupation = occupation;
        this.income = income;
    }

    public int getNumDependends() {
        return numDependends;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    public String getBloodType() {
        return bloodType;
    }

    public String getOccupation() {
        return occupation;
    }

    public int getIncome() {
        return income;
    }

    @Override
    public String toString() {
        return String.format("Personal Details: \n\t" +
                "Martial Status: %s \n\t" +
                "Number of Dependents: %d \n\t" +
                "Height: %d  /  Weight: %d \n\t" +
                "Blood Type: %s \n\t" +
                "Occupation: %s \n\t" +
                "Income: %d",
                martialStatus, numDependends, height, weight, bloodType, occupation, income);
    }
}
