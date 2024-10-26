package com.example.spaceward2.controller;

import java.util.Optional;

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

import com.example.spaceward2.model.Schedule;
import com.example.spaceward2.service.ScheduleService;

@Controller
@RequestMapping("/schedules")
public class ScheduleController {
    static final int DEFAULT_PAGE_SIZE = 5;

    private final ScheduleService scheduleService;

    @Autowired
    public ScheduleController(final ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping(value = { "/", "" })
    public String index() {
        return "redirect:/schedules/list";
    }

    @GetMapping(value = { "/list", "/list/" })
    public String list(final Model model, 
                       @RequestParam(value = "page", defaultValue = "0") final int pageNumber, 
                       @RequestParam(value = "size", defaultValue = DEFAULT_PAGE_SIZE + "") final int pageSize) {
        final Page<Schedule> page = scheduleService.getSchedules(pageNumber, pageSize);
        
        final int currentPageNumber = page.getNumber();
        final int previousPageNumber = page.hasPrevious() ? currentPageNumber - 1 : -1;
        final int nextPageNumber = page.hasNext() ? currentPageNumber + 1 : -1;

        model.addAttribute("schedules", page.getContent());
        model.addAttribute("previousPageNumber", previousPageNumber);
        model.addAttribute("nextPageNumber", nextPageNumber);
        model.addAttribute("currentPageNumber", currentPageNumber);
        model.addAttribute("pageSize", pageSize);

        return "list";
    }

    @GetMapping(value = { "/view", "/view/" })
    public String view(final Model model, @RequestParam final int id) {
        final Optional<Schedule> record = scheduleService.getSchedule(id);

        model.addAttribute("schedule", record.isPresent() ? record.get() : new Schedule());
        model.addAttribute("id", id);

        return "view";
    }

    @GetMapping(value = { "/add", "/add/" })
    public String add(final Model model) {
        model.addAttribute("schedule", new Schedule());
        return "add";
    }

    @GetMapping(value = { "/edit", "/edit/" })
    public String edit(final Model model, @RequestParam final int id) {
        final Optional<Schedule> record = scheduleService.getSchedule(id);

        model.addAttribute("schedule", record.isPresent() ? record.get() : new Schedule());
        model.addAttribute("id", id);

        return "edit";
    }

    @PostMapping(value = { "/save", "/save/" })
    public String save(final Model model, @ModelAttribute final Schedule schedule, final BindingResult errors) {
        // Save the schedule entity to the database
        scheduleService.saveSchedule(schedule);
        return "redirect:list";
    }

    @GetMapping(value = { "/delete", "/delete/" })
    public String delete(final Model model, @RequestParam final int id) {
        final Optional<Schedule> record = scheduleService.getSchedule(id);

        model.addAttribute("schedule", record.isPresent() ? record.get() : new Schedule());
        model.addAttribute("id", id);

        return "delete";
    }

    @PostMapping(value = { "/delete", "/delete/" })
    public String deletion(final Model model, @RequestParam final int id) {
        scheduleService.deleteSchedule(id);
        return "redirect:list";
    }
}
