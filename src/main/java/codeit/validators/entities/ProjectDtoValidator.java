package codeit.validators.entities;

import codeit.dto.ProjectDto;

import java.util.ArrayList;
import java.util.List;

public class ProjectDtoValidator {
    private static final ProjectDtoValidator INSTANCE = new ProjectDtoValidator();

    public static ProjectDtoValidator getInstance() {
        return INSTANCE;
    }

    public List<String> validate(ProjectDto projectDto) {
        List<String> errors = new ArrayList<>();
        return errors;
    }
}
