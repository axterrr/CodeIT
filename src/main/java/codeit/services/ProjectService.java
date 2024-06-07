package codeit.services;

import codeit.dao.ProjectDao;
import codeit.dao.DaoFactory;
import codeit.models.entities.Project;

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
}
