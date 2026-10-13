package Department;

public class Department {

    private int departmentCode;
    private String departmentName;
    private int totalSeats;

    public Department(int departmentCode, String departmentName, int totalSeats){
        this.departmentCode = departmentCode;
        this.departmentName = departmentName;
        this.totalSeats = totalSeats;
    }

    public int getDepartmentCode(){
        return departmentCode;
    }

    public String getDepartmentName(){
        return departmentName;
    }

    public int getTotalSeats(){
        return totalSeats;
    }

    @Override
    public String toString(){
        return departmentName + " total seats= " + totalSeats;
    }

}
