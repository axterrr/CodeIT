package codeit.controller;

import codeit.controller.commands.Command;
import codeit.controller.commands.HomeCommand;
import codeit.controller.commands.PageNotFoundCommand;

public enum CommandEnum {

    HOME ("GET:/", new HomeCommand()),
    PAGE_NOT_FOUND ("GET:/pageNotFound", new PageNotFoundCommand());

    private String key;
    private Command command;

    CommandEnum(String key, Command command) {
        this.key = key;
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }

    public String getKey() {
        return key;
    }

    public static Command getCommand(String key) {
        for (CommandEnum command : CommandEnum.values()) {
            if (command.getKey().equals(key)) {
                return command.getCommand();
            }
        }
        return PAGE_NOT_FOUND.getCommand();
    }
}
