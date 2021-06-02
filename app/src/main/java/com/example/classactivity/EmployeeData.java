package com.example.classactivity;

public class EmployeeData {    private int ID;
    private String Name;
    private int Salary;

    public EmployeeData() {
    }
    public EmployeeData(int ID, String name, int salary) {
        this.ID = ID;
        Name = name;
        Salary = salary;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getSalary() {
        return Salary;
    }

    public void setSalary(int salary) {
        Salary = salary;
    }
}

