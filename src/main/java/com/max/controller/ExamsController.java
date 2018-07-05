package com.max.controller;

import com.max.domain.GroupName;
import com.max.domain.Subject;
import com.max.domain.User;
import com.max.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/study")
public class ExamsController {

    @Autowired
    private SubjectService subjectService;

    @GetMapping("/subjects")
    public String subjectsList(Model model) {
        model.addAttribute("groups", GroupName.values());
        model.addAttribute("subjects", subjectService.findAll());
        return "subjects";
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/subjects")
    public String addSubjects(
            @Valid Subject subject,
            @RequestParam Map<String, String> form,
            BindingResult bindingResult,
            Model model
            ) {

        model.addAttribute("subject", subject);
        if(!subjectService.addSubjectBol(subject)) {
            model.addAttribute("subjectError", "Subject is already add!");
            return "subjects";
        }

        subjectService.addSubject(subject, form);

        return "redirect:/study/subjects";
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("delete/{id}")
    public String deleteSubjectById(@PathVariable Long id) {
        subjectService.deleteSubject(id);
        return "redirect:/study/subjects";
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/subject/{subject}")
    public String editSubjectForm(@PathVariable Subject subject, Model model) {
        model.addAttribute("subject", subject);
        model.addAttribute("groups", GroupName.values());

        return "subjectEdit";
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public String subjectSave(
            @RequestParam String name,
            @RequestParam Map<String, String> form,
            @RequestParam("subjectId") Subject subject) {

        subjectService.saveSubject(subject, name, form);

        return "redirect:/study/subjects";
    }
}
