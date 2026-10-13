package Patient;

public class Patient {

    private long personalID;
    private String patientName;
    private int age;
    private int departmentCode;

    public Patient(long personalID, String patientName, int age, int departmentCode) {
        this.personalID = personalID;
        this.patientName = patientName;
        this.age = age;
        this.departmentCode = departmentCode;
    }

    public long getPersonalID() {
        return personalID;
    }

    public String getPatientName() {
        return patientName;
    }

    public int getAge() {
        return age;
    }

    public int getDepartmentCode() {
        return departmentCode;
    }

    @Override
    public String toString(){
        return patientName + " age= " + age + " department code= " + departmentCode;
    }

}
