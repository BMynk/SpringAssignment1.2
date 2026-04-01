package com.casewensitiveworriors.springassignment1.controller;

import com.casewensitiveworriors.springassignment1.model.Course;
import com.casewensitiveworriors.springassignment1.model.CourseCategory;
import com.casewensitiveworriors.springassignment1.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public String listCourses(Model model) {
        model.addAttribute("courses", courseService.findAll());
        return "courses/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("course", new Course());
        model.addAttribute("categories", CourseCategory.values());
        model.addAttribute("pageTitle", "Add Course");
        return "courses/form";
    }

    @PostMapping
    public String createCourse(@Valid @ModelAttribute("course") Course course,
                               BindingResult result,
                               Model model) {

        if (courseService.existsByCodeIgnoreCase(course.getCourseCode(), null)) {
            result.rejectValue("courseCode", "duplicate", "Course code already exists");
        }

        validateCategoryLevel(course, result);

        if (result.hasErrors()) {
            model.addAttribute("categories", CourseCategory.values());
            model.addAttribute("pageTitle", "Add Course");
            return "courses/form";
        }

        courseService.save(course);
        return "redirect:/courses";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Course course = courseService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid course id: " + id));

        model.addAttribute("course", course);
        model.addAttribute("categories", CourseCategory.values());
        model.addAttribute("pageTitle", "Edit Course");
        return "courses/form";
    }

    @PostMapping("/update/{id}")
    public String updateCourse(@PathVariable Long id,
                               @Valid @ModelAttribute("course") Course course,
                               BindingResult result,
                               Model model) {

        if (courseService.existsByCodeIgnoreCase(course.getCourseCode(), id)) {
            result.rejectValue("courseCode", "duplicate", "Course code already exists");
        }

        validateCategoryLevel(course, result);

        if (result.hasErrors()) {
            model.addAttribute("categories", CourseCategory.values());
            model.addAttribute("pageTitle", "Edit Course");
            return "courses/form";
        }

        courseService.update(id, course);
        return "redirect:/courses";
    }

    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable Long id) {
        courseService.delete(id);
        return "redirect:/courses";
    }

    private void validateCategoryLevel(Course course, BindingResult result) {
        if (course.getCategory() == null || course.getLevel() == null) {
            return;
        }

        switch (course.getCategory()) {
            case FOUNDATION:
                if (course.getLevel() != 1) {
                    result.rejectValue("level", "invalid.level",
                            "Foundation courses must be level 1");
                }
                break;

            case UNDERGRADUATE:
                if (course.getLevel() < 1 || course.getLevel() > 3) {
                    result.rejectValue("level", "invalid.level",
                            "Undergraduate courses must be between level 1 and 3");
                }
                break;

            case HONOURS:
                if (course.getLevel() != 4) {
                    result.rejectValue("level", "invalid.level",
                            "Honours courses must be level 4");
                }
                break;

            case MASTERS:
                if (course.getLevel() != 5) {
                    result.rejectValue("level", "invalid.level",
                            "Masters courses must be level 5");
                }
                break;

            case PHD:
                if (course.getLevel() != 6) {
                    result.rejectValue("level", "invalid.level",
                            "PhD courses must be level 6");
                }
                break;
        }
    }
}
