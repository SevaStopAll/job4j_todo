package ru.job4j.todo.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.service.SimpleTaskService;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Controller
@RequestMapping("/tasks")
@ThreadSafe
public class TaskController {

    private final SimpleTaskService simpleTaskService;

    public TaskController(SimpleTaskService simpleTaskService) {
        this.simpleTaskService = simpleTaskService;
    }

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("tasks", simpleTaskService.findAll());
        return "/tasks";
    }

    @GetMapping("/add")
    public String getCreationPage() {
        return "tasks/add";
    }

    @PostMapping("/add")
    public String create(@ModelAttribute Task task, Model model) {
        try {
            simpleTaskService.add(task);
            return "redirect:/tasks";
        } catch (Exception exception) {
            model.addAttribute("message", exception.getMessage());
            return "errors/404";
        }
    }

    @GetMapping("/{id}")
    public String getById(Model model, @PathVariable int id) {
        var taskOptional = simpleTaskService.findById(id);
        if (taskOptional.isEmpty()) {
            model.addAttribute("message", "Task not found");
            return "errors/404";
        }
        model.addAttribute("task", taskOptional.get());
        return "tasks/one";
    }

    @GetMapping("/edit/{taskId}")
    public String formUpdateCandidate(Model model, @PathVariable("taskId") Integer id) {
        model.addAttribute("task", simpleTaskService.findById(id));
        return "tasks/edit";
    }

    @GetMapping("/finish/{id}")
    public String finishTask(@PathVariable int id) {
        Task task = simpleTaskService.findById(id).get();
        task.setDone(true);
        simpleTaskService.update(task);
        return "redirect:/tasks";
    }

    @PostMapping("/edit")
    public String update(@ModelAttribute Task task, Model model) {
        try {
            var isUpdated = simpleTaskService.update(task);
            if (!isUpdated) {
                model.addAttribute("message", "Task is not found");
                return "errors/404";
            }
            return "redirect:/tasks";
        } catch (Exception exception) {
            model.addAttribute("message", exception.getMessage());
            return "errors/404";
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable int id) {
        var isDeleted = simpleTaskService.deleteById(id);
        if (!isDeleted) {
            model.addAttribute("message", "Вакансия с указанным идентификатором не найдена");
            return "errors/404";
        }
        return "redirect:/tasks";
    }

    @GetMapping("/finished")
    public String findAllFinishedTasks(Model model) {
        List<Task> finishedTasks = simpleTaskService.findAll().stream()
                .filter(Task::isDone)
                .collect(toList());
        model.addAttribute("finishedTasks", finishedTasks);
        return "tasks/finished";
    }

    @GetMapping("/new")
    public String findAllNewTasks(Model model) {
        List<Task> newTasks = simpleTaskService.findAll().stream()
                .filter(task -> !task.isDone())
                .collect(toList());
        model.addAttribute("newTasks", newTasks);
        return "tasks/new";
    }
}
