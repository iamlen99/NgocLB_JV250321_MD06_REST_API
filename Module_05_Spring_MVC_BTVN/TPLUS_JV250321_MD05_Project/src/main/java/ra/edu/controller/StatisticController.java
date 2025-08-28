package ra.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ra.edu.model.dto.StudentCountPerCourse;
import ra.edu.service.StatisticService;

@Controller
@RequestMapping("/statistics")
public class StatisticController {
    @Autowired
    private StatisticService statisticService;


    @GetMapping
    public String showStatistics(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "5") Integer size,
            Model model
    ) {
        Long allCoursesCount = statisticService.countAllCourses();
        Long allStudentsCount = statisticService.countAllStudents();
        Long enrolledStudentsCount = statisticService.countEnrolledStudents();
        Page<StudentCountPerCourse>  studentsPerCourse= statisticService.getStudentCountPerCourse(page, size);
        Page<StudentCountPerCourse> top5Courses = statisticService.getTop5CoursesMostEnrolled();

        model.addAttribute("allCoursesCount", allCoursesCount);
        model.addAttribute("allStudentsCount", allStudentsCount);
        model.addAttribute("enrolledStudentsCount", enrolledStudentsCount);
        model.addAttribute("studentsPerCourse", studentsPerCourse);
        model.addAttribute("top5Courses", top5Courses);
        return "admin/statistic/statistics";
    }
}
