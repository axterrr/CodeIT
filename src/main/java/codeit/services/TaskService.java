package codeit.services;

import codeit.dao.TaskDao;
import codeit.dao.DaoFactory;
import codeit.models.entities.Task;

import java.util.List;

public class TaskService {

    private static final TaskService INSTANCE = new TaskService(DaoFactory.getDaoFactory());
    private final DaoFactory daoFactory;
    TaskService(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    public static TaskService getInstance() {
        return TaskService.INSTANCE;
    }

    public List<Task> getAllTasks() {
        try (TaskDao dao = daoFactory.createTaskDao()) {
            return dao.getAll();
        }
    }

    public Task getTaskById(String taskId) {
        try (TaskDao dao = daoFactory.createTaskDao()) {
            return dao.getById(taskId);
        }
    }

    public void createTask(Task task) {
        try (TaskDao dao = daoFactory.createTaskDao()) {
            dao.create(task);
        }
    }

    public void updateTask(Task task) {
        try (TaskDao dao = daoFactory.createTaskDao()) {
            dao.update(task);
        }
    }

    public void deleteTask(String taskId) {
        try (TaskDao dao = daoFactory.createTaskDao()) {
            dao.delete(taskId);
        }
    }

    public List<Task> getAllTasksByProject(String projectId) {
        try (TaskDao dao = daoFactory.createTaskDao()) {
            return dao.getAllByProject(projectId);
        }
    }

    public List<Task> getAllTasksByDeveloper(String developerId) {
        try (TaskDao dao = daoFactory.createTaskDao()) {
            return dao.getAllByDeveloper(developerId);
        }
    }

    public List<Task> getAllTasksByTester(String testerId) {
        try (TaskDao dao = daoFactory.createTaskDao()) {
            return dao.getAllByTester(testerId);
        }
    }
}
