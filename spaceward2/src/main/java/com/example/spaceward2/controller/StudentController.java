package com.example.spaceward2.controller;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.spaceward2.model.Student;
import com.example.spaceward2.service.StudentService;

@Controller
@RequestMapping("/students")
public class StudentController {
    static final int DEFAULT_PAGE_SIZE = 5;

    private final StudentService studentService;

    @Autowired
    public StudentController(final StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(value={"/", ""})
    public String index() {
        return "students/list";
    }

    @GetMapping(value={"/list", "/list/"})
    public String list(final Model model, @RequestParam(value = "page", defaultValue = "0") final int pageNumber, 
            @RequestParam(value = "size", defaultValue = DEFAULT_PAGE_SIZE + "") final int pageSize) {
        final Page<Student> page = studentService.getStudents(pageNumber, pageSize);
        
        final int currentPageNumber = page.getNumber();
        final int previousPageNumber = page.hasPrevious() ? currentPageNumber - 1 : -1;
        final int nextPageNumber = page.hasNext() ? currentPageNumber + 1 : -1;

        model.addAttribute("students", page.getContent());
        model.addAttribute("previousPageNumber", previousPageNumber);
        model.addAttribute("nextPageNumber", nextPageNumber);
        model.addAttribute("currentPageNumber", currentPageNumber);
        model.addAttribute("pageSize", pageSize);

        return "list";
    }

    @GetMapping(value={"/view", "/view/"})
    public String view(final Model model, @RequestParam final UUID studentNo) {
        final Optional<Student> record = studentService.getStudent(studentNo);

        model.addAttribute("student", record.isPresent() ? record.get() : new Student());
        model.addAttribute("studentNo", studentNo);

        return "view";

    }

    @GetMapping(value={"/add", "/add/"})
    public String add(final Model model) {
        model.addAttribute("student", new Student());
        return "add";
    }

    @GetMapping(value={"/edit", "/edit/"})
    public String edit(final Model model, @RequestParam final UUID studentNo) {
        final Optional<Student> record = studentService.getStudent(studentNo);

        model.addAttribute("student", record.isPresent() ? record.get() : new Student());
        model.addAttribute("studentNo", studentNo);

        return "edit";
    }

    @PostMapping(value={"/save", "/save/"})
    public String save(final Model model, @ModelAttribute final Student student, final BindingResult errors) {
        // Save the trading card entity to the database
        studentService.saveStudent(student);
        return "redirect:list";
    }

    @GetMapping(value={"/delete", "/delete/"})
    public String delete(final Model model, @RequestParam final UUID studentNo) {
        final Optional<Student> record = studentService.getStudent(studentNo);

        model.addAttribute("student", record.isPresent() ? record.get() : new Student());
        model.addAttribute("studentNo", studentNo);

        return "delete";
    }

    @PostMapping(value={"/delete", "/delete/"})
    public String deletion(final Model model, @RequestParam final UUID studentNo) {
        studentService.deleteStudent(studentNo);
        return "redirect:list";
    }

}