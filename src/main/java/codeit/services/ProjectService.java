package codeit.services;

import codeit.dao.OrderDao;
import codeit.dao.ProjectDao;
import codeit.dao.DaoFactory;
import codeit.models.entities.Project;
import codeit.models.enums.OrderStatus;
import codeit.models.enums.ProjectStatus;

import java.util.List;

public class ProjectService {

    private static final ProjectService INSTANCE = new ProjectService(DaoFactory.getDaoFactory());
    private final DaoFactory daoFactory;
    ProjectService(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    public static ProjectService getInstance() {
        return ProjectService.INSTANCE;
    }

    public List<Project> getAllProjects() {
        try (ProjectDao dao = daoFactory.createProjectDao()) {
            return dao.getAll();
        }
    }

    public Project getProjectById(String projectId) {
        try (ProjectDao dao = daoFactory.createProjectDao()) {
            return dao.getById(projectId);
        }
    }

    public void createProject(Project project) {
        try (ProjectDao dao = daoFactory.createProjectDao()) {
            dao.create(project);
        }
    }

    public void updateProject(Project project) {
        try (ProjectDao dao = daoFactory.createProjectDao()) {
            dao.update(project);
        }
    }

    public void deleteProject(String projectId) {
        try (ProjectDao dao = daoFactory.createProjectDao()) {
            dao.delete(projectId);
        }
    }

    public Project getProjectByOrder(String orderId) {
        try (ProjectDao dao = daoFactory.createProjectDao()) {
            return dao.getByOrder(orderId);
        }
    }

    public List<Project> getAllProjectsByManager(String managerId) {
        try (ProjectDao dao = daoFactory.createProjectDao()) {
            return dao.getAllByManager(managerId);
        }
    }

    public Long getEmployeesNumberOnProject(String projectId) {
        try (ProjectDao dao = daoFactory.createProjectDao()) {
            return dao.getEmployeesNumber(projectId);
        }
    }

    public void submitProject(String projectId) {
        try (ProjectDao dao = daoFactory.createProjectDao()) {
            dao.changeStatus(ProjectStatus.AWAITING_CONFIRMATION.getValue(), projectId);
        }
    }

    public void rejectProject(String projectId) {
        try (ProjectDao dao = daoFactory.createProjectDao()) {
            dao.changeStatus(ProjectStatus.DEVELOPING.getValue(), projectId);
        }
    }

    public void confirmProject(String projectId) {
        try (ProjectDao dao = daoFactory.createProjectDao()) {
            dao.finish(projectId);
        }
    }

    public void startDevelopingProject(String projectId) {
        try (ProjectDao dao = daoFactory.createProjectDao()) {
            dao.changeStatus(ProjectStatus.DEVELOPING.getValue(), projectId);
        }
    }

    public void cancelProjectByOrder(String orderId) {
        try (ProjectDao dao = daoFactory.createProjectDao()) {
            dao.cancelByOrder(orderId);
        }
    }

    public List<Project> getAllCreatedDevelopingProjects() {
        try (ProjectDao dao = daoFactory.createProjectDao()) {
            return dao.getAllCreatedDeveloping();
        }
    }

    public List<Project> getAllProjectsByDeveloper(String developerId) {
        try (ProjectDao dao = daoFactory.createProjectDao()) {
            return dao.getAllByDeveloper(developerId);
        }
    }

    public List<Project> getAllProjectsByTester(String testerId) {
        try (ProjectDao dao = daoFactory.createProjectDao()) {
            return dao.getAllByTester(testerId);
        }
    }
}
