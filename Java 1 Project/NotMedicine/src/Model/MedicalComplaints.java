package Model;

public class MedicalComplaints {
    public MedicalComplaints(boolean diabetic, boolean hypertensive, boolean cardCondition, boolean respirCondition,
                             boolean digestCondition, boolean orthopedicCondition, boolean muscularCondition,
                             boolean neuroCondition, String allergies, String adverseDrugsReaction, String majorSurgeries) {
        this.diabetic = diabetic;
        this.hypertensive = hypertensive;
        this.cardCondition = cardCondition;
        this.respirCondition = respirCondition;
        this.digestCondition = digestCondition;
        this.orthopedicCondition = orthopedicCondition;
        this.muscularCondition = muscularCondition;
        this.neuroCondition = neuroCondition;
        this.allergies = allergies;
        this.adverseDrugsReaction = adverseDrugsReaction;
        this.majorSurgeries = majorSurgeries;
    }

    private boolean diabetic;
    private boolean hypertensive;
    private boolean cardCondition;
    private boolean respirCondition;
    private boolean digestCondition;
    private boolean orthopedicCondition;
    private boolean muscularCondition;
    private boolean neuroCondition;
    private String allergies;
    private String adverseDrugsReaction;
    private String majorSurgeries;

    public boolean isDiabetic() {
        return diabetic;
    }

    public boolean isHypertensive() {
        return hypertensive;
    }

    public boolean isCardCondition() {
        return cardCondition;
    }

    public boolean isRespirCondition() {
        return respirCondition;
    }

    public boolean isDigestCondition() {
        return digestCondition;
    }

    public boolean isOrthopedicCondition() {
        return orthopedicCondition;
    }

    public boolean isMuscularCondition() {
        return muscularCondition;
    }

    public boolean isNeuroCondition() {
        return neuroCondition;
    }

    public String getAllergies() {
        return allergies;
    }

    public String getAdverseDrugsReaction() {
        return adverseDrugsReaction;
    }

    public String getMajorSurgeries() {
        return majorSurgeries;
    }

    @Override
    public String toString() {
        return String.format("Medical Complaints: \n\t" +
                "Diabetic: %s \n\t" +
                "Hypertensive: %s \n\t" +
                "Respiratory Condition: %s \n\t" +
                "Digestive Condition: %s \n\t" +
                "Orthopedic Condition: %s \n\t" +
                "Muscular Condition: %s \n\t" +
                "Neurological Condition: %s \n\t" +
                "Known Allergies: %s \n\t" +
                "Adverse Reactions on Drugs: %s \n\t" +
                "Major Surgeries: %s",
                diabetic ? "yes" : "no", hypertensive ? "yes" : "no", respirCondition ? "yes" : "no",
                digestCondition ? "yes" : "no", orthopedicCondition ? "yes" : "no", muscularCondition ? "yes" : "no",
                neuroCondition ? "yes" : "no", allergies, adverseDrugsReaction, majorSurgeries);
    }
}
