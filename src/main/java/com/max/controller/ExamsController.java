package com.max.controller;

import com.max.domain.Exams;
import com.max.domain.Subject;
import com.max.domain.User;
import com.max.service.ExamsService;
import com.max.service.SubjectService;
import com.max.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.Set;

@Controller
@RequestMapping("/exams")
public class ExamsController {

    @Autowired
    private ExamsService examsService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private UserService userService;

    @GetMapping("/exam/{user}")
    public String examsList(Model model,
                            @PathVariable User user,
                            @AuthenticationPrincipal User currentUser,
                            @RequestParam(required = false) Exams exams) {


        Set<Exams> examsSet = user.getExams();

        model.addAttribute("username", currentUser.getUsername());
        model.addAttribute("examsList", examsSet);
        model.addAttribute("subjects", subjectService.findAll());
        model.addAttribute("isCurrentUser", currentUser.equals(user));
        return "examsList";
    }

    @PostMapping("/examAdd")
    public String addExams(
            @Valid Exams exams,
            BindingResult bindingResult,
            @AuthenticationPrincipal User user,
            @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateTime,
            Model model) {

        model.addAttribute("username", user.getId());
        model.addAttribute("date", dateTime);

        examsService.addExams(exams, user);

        return "redirect:/main";
    }

    @PostMapping("delete/{id}")
    public String deleteExamsById(@PathVariable Long id) {
        examsService.deleteExam(id);
        return "redirect:/main";
    }

    @GetMapping("/edit/{exam}")
    public String editExams(@PathVariable Exams exam, Model model) {

        model.addAttribute("exam", exam);
        model.addAttribute("subjects", subjectService.findAll());

        return "examsEdit";
    }

    @PostMapping("/save")
    public String saveExam(@RequestParam Subject subject,
                           @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
                           @RequestParam("examsId") Exams exams) {

        examsService.saveExam(exams, subject, date);

        return "redirect:/main";

    }

}
