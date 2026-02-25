package ru.rutmiit.dto;


import java.io.Serializable;

public class ShowCompanyInfoDto implements Serializable {
    private String name;

    private String town;

    private int imployeeCount;

    private String description;

    private int employeeCount;

    // Геттер и сеттер (или @Data если есть Lombok)
    public int getEmployeeCount() { return employeeCount; }
    public void setEmployeeCount(int employeeCount) { this.employeeCount = employeeCount; }

    public int getImployeeCount() {
        return imployeeCount;
    }

    public void setImployeeCount(int imployeeCount) {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
