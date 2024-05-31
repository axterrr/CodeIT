package codeit.validators.entities;

import codeit.dto.TaskDto;

import java.util.ArrayList;
import java.util.List;

public class TaskDtoValidator {
    private static final TaskDtoValidator INSTANCE = new TaskDtoValidator();

    public static TaskDtoValidator getInstance() {
        return INSTANCE;
    }

    public List<String> validate(TaskDto taskDto) {
        List<String> errors = new ArrayList<>();
        return errors;
    }
}
