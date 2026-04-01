package com.casewensitiveworriors.springassignment1.service;
import com.casewensitiveworriors.springassignment1.model.Course;
import com.casewensitiveworriors.springassignment1.model.CourseCategory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class CourseService {
    private final List<Course> courses = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong(1);

    public CourseService() {
        // 2 Foundation courses
        courses.add(new Course(counter.getAndIncrement(), "CSC101", "Introduction to Computing", 1, CourseCategory.FOUNDATION));
        courses.add(new Course(counter.getAndIncrement(), "CSC102", "Academic Computing Skills", 1, CourseCategory.FOUNDATION));

        // 5 Undergraduate courses
        courses.add(new Course(counter.getAndIncrement(), "CSC201", "Programming Principles", 2, CourseCategory.UNDERGRADUATE));
        courses.add(new Course(counter.getAndIncrement(), "CSC202", "Data Structures", 2, CourseCategory.UNDERGRADUATE));
        courses.add(new Course(counter.getAndIncrement(), "CSC301", "Database Systems", 3, CourseCategory.UNDERGRADUATE));
        courses.add(new Course(counter.getAndIncrement(), "CSC302", "Operating Systems", 3, CourseCategory.UNDERGRADUATE));
        courses.add(new Course(counter.getAndIncrement(), "CSC303", "Software Engineering", 3, CourseCategory.UNDERGRADUATE));

        // 4 Honours courses
        courses.add(new Course(counter.getAndIncrement(), "CSC401", "Advanced Algorithms", 4, CourseCategory.HONOURS));
        courses.add(new Course(counter.getAndIncrement(), "CSC402", "Machine Learning", 4, CourseCategory.HONOURS));
        courses.add(new Course(counter.getAndIncrement(), "CSC403", "Distributed Systems", 4, CourseCategory.HONOURS));
        courses.add(new Course(counter.getAndIncrement(), "CSC404", "Research Methods in Computer Science", 4, CourseCategory.HONOURS));
    }

    public List<Course> findAll() {
        return courses.stream()
                .sorted(Comparator.comparing(Course::getCategory).thenComparing(Course::getCourseCode))
                .collect(Collectors.toList());
    }

    public Optional<Course> findById(Long id) {
        return courses.stream().filter(c -> c.getId().equals(id)).findFirst();
    }

    public void save(Course course) {
        course.setId(counter.getAndIncrement());
        courses.add(course);
    }

    public void update(Long id, Course updatedCourse) {
        findById(id).ifPresent(existing -> {
            existing.setCourseCode(updatedCourse.getCourseCode());
            existing.setCourseName(updatedCourse.getCourseName());
            existing.setLevel(updatedCourse.getLevel());
            existing.setCategory(updatedCourse.getCategory());
        });
    }

    public void delete(Long id) {
        courses.removeIf(c -> c.getId().equals(id));
    }

    public boolean existsByCodeIgnoreCase(String courseCode, Long excludeId) {
        return courses.stream().anyMatch(course ->
                course.getCourseCode().equalsIgnoreCase(courseCode)
                        && (excludeId == null || !course.getId().equals(excludeId))
        );
    }
}
