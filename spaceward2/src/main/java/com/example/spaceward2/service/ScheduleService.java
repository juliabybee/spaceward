package com.example.spaceward2.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.spaceward2.model.Schedule;
import com.example.spaceward2.repository.ScheduleRepository;

@Service
public class ScheduleService {

    private final ScheduleRepository repository;

    @Autowired
    public ScheduleService(final ScheduleRepository repository) {
        this.repository = repository;
    }

    public Page<Schedule> getSchedules(final int pageNumber, final int size) {
        return repository.findAll(PageRequest.of(pageNumber, size));
    }

    public Optional<Schedule> getSchedule(final int id) {
        return repository.findById(id);
    }

    public Schedule saveSchedule(final Schedule schedule) {
        return repository.save(schedule);
    }

    public void deleteSchedule(final int id) {
        repository.deleteById(id);
    }
}



