package peaksoft.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Course;
import peaksoft.service.CourseService;

@Controller
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @GetMapping("/newCourse/{id}")
    public String newCompany(Model model,@PathVariable Long id) {
        model.addAttribute("course", new Course());
        model.addAttribute("companyId",id);
        return "/course/newCourse";
    }

    @PostMapping("/{comId}/saveCourse")
    public String createCompany(@PathVariable("comId")Long id,
                                @ModelAttribute("course") Course course){
        courseService.saveCourse(id,course);
        return "redirect:/courses/getAllCourses/"+id;
    }

    @GetMapping("/getAllCourses/{comId}")
    public String getCourses(@PathVariable("comId")Long id, Model model) {
        model.addAttribute("courseList", courseService.getAllCourse(id));
        model.addAttribute("compId",id);
        return "/course/getCourse";
    }

    @GetMapping("/{courseId}/editCourse")
    public String edit(Model model, @PathVariable("courseId") Long id) {
        model.addAttribute("course", courseService.getCourseById(id));
        return "/course/editCourse";
    }

    @PatchMapping("/{courseId}/{comId}/updateCourse")
    public String updateCourse(@PathVariable("courseId")Long courseId,
            @ModelAttribute("company") Course course, @PathVariable Long id) {
        courseService.updateCourse(courseId,course);
        return "redirect:/courses/getAllCourses/ " + id;
    }

    @PostMapping("/{comId}/{courseId}/delete")
    public String deleteCourse(@PathVariable("comId")Long comId,@PathVariable("courseId") long id) {
        courseService.deleteCourseById(id);
        return "redirect:/courses/getAllCourses/ "+comId;
    }
}



