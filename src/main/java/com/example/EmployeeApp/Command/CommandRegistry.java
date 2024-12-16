package com.example.EmployeeApp.Command;

import java.util.HashMap;
import java.util.Map;

public class CommandRegistry {

    private static final Map<String, Command> commands = new HashMap<>();

    public void register(String action, Command command) {
        commands.put(action, command);
    }

    public Command getCommand(String action) {
        return commands.get(action);
    }
}
