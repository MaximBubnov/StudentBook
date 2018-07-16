package com.max.controller;

import com.max.domain.GroupName;
import com.max.domain.Subject;
import com.max.repository.SubjectRepo;
import com.max.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/study")
public class SubjectController {

    //Это значит, что мы идем в properties -> там спринг находит переменную upload.file и подставляет ее сюда
    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private SubjectRepo subjectRepo;

    @GetMapping("/subjects")
    public String subjectsList(@RequestParam(required = false, defaultValue = "") GroupName filter, Model model) {
        Iterable<Subject> subjects = subjectService.findAll();

        if(filter != null) {
            subjects = subjectRepo.findByGroupNames(filter);
        }
        else {
            subjects = subjectRepo.findAll();
        }
        model.addAttribute("filter", filter);
        model.addAttribute("groups", GroupName.values());
        model.addAttribute("subjects", subjects);
        return "subjects";
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(headers = "content-type=multipart/*", value = "/subjects")
    public String addSubjects(
            @Valid Subject subject,
            @RequestParam Map<String, String> form,
            BindingResult bindingResult,
            Model model,
            @RequestParam("file") MultipartFile file,
            @RequestParam("description") String desc
            ) throws IOException {

        model.addAttribute("subject", subject);
        if(!subjectService.addSubjectBol(subject)) {
            model.addAttribute("subjectError", "Subject is already add!");
            return "subjects";
        }

        subject.setDescription(desc);
        //subject.setFilename(file.getOriginalFilename());

        saveFile(subject, file);

        subjectService.addSubject(subject, form);


        return "redirect:/study/subjects";
    }

    private void saveFile(@Valid Subject subject, @RequestParam("file") MultipartFile file) throws IOException {

        if(file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);
            //если дериктория  не существует - создадим ее
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            //обезопасим себя от коллизий
            String uuidFile = UUID.randomUUID().toString();

            //это будет имя файла, котое мы будем ложить в месседж
            String resultFileName = uuidFile + "." + file.getOriginalFilename();

            //теперь этот файл нужно загрузить
            file.transferTo(new File(uploadPath + "/" + resultFileName));

            subject.setFilename(resultFileName);
        }
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
    @PostMapping(headers = "content-type=multipart/*")
    public String subjectSave(
            @RequestParam String name,
            @RequestParam Map<String, String> form,
            @RequestParam("subjectId") Subject subject,
            @RequestParam String description,
            @RequestParam MultipartFile file) throws IOException {

        saveFile(subject, file);

        subjectService.saveSubject(subject, name, form, description);

        return "redirect:/study/subjects";
    }

    @GetMapping("/subject/info/{subject}")
    public String infoSubject(@PathVariable Subject subject, Model model) {

        model.addAttribute("groups", GroupName.values());
        model.addAttribute("subject", subject);

        return "subjectInfo";
    }
}
