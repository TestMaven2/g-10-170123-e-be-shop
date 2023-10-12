package de.telran.g10170123ebeshop.service.jpa;

import de.telran.g10170123ebeshop.domain.entity.jpa.Task;
import de.telran.g10170123ebeshop.repository.jpa.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    @Autowired
    private TaskRepository repository;

    public void save(Task task) {
        repository.save(task);
    }
}