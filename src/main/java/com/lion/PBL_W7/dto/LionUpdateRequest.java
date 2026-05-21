package com.lion.PBL_W7.dto;

public class LionUpdateRequest {
    private String major;
    private int generation;
    private String part;
    private String studentId;

    // Getter & Setter 메서드들
    public String getMajor() { return major; }
    public void setMajor(String major) { this.major = major; }

    public int getGeneration() { return generation; }
    public void setGeneration(int generation) { this.generation = generation; }

    public String getPart() { return part; }
    public void setPart(String part) { this.part = part; }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
}