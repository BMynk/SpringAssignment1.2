package com.casewensitiveworriors.springassignment1.model;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
public class Course {
    private Long id;

    @NotBlank(message = "Course code is required")
    @Size(min = 4, max = 15, message = "Course code must be between 4 and 15 characters")
    @Pattern(
            regexp = "^[A-Z]{2,4}\\d{3,4}$",
            message = "Course code must look like CSC101 or COMP4001"
    )
    private String courseCode;

    @NotBlank(message = "Course name is required")
    @Size(min = 3, max = 100, message = "Course name must be between 3 and 100 characters")
    private String courseName;

    @NotNull(message = "Level is required")
    @Min(value = 1, message = "Level must be at least 1")
    @Max(value = 6, message = "Level must not be greater than 6")
    private Integer level;

    @NotNull(message = "Category is required")
    private CourseCategory category;

    public Course() {
    }

    public Course(Long id, String courseCode, String courseName, Integer level, CourseCategory category) {
        this.id = id;
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.level = level;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public CourseCategory getCategory() {
        return category;
    }

    public void setCategory(CourseCategory category) {
        this.category = category;
    }
}
