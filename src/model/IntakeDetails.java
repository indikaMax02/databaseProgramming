package model;

public class IntakeDetails {
    private String intake_id;
    private String desc;

    public IntakeDetails() {
    }

    public IntakeDetails(String intake_id, String desc) {
        this.intake_id = intake_id;
        this.desc = desc;
    }

    public String getIntake_id() {
        return intake_id;
    }

    public void setIntake_id(String intake_id) {
        this.intake_id = intake_id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "IntakeDetails{" +
                "intake_id='" + intake_id + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
