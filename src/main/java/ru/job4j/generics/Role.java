package ru.job4j.generics;

public class Role extends Base {

    private final String executionRole;
    private final String username;

    public Role(String id, String name, String position) {
        super(id);
        this.username = name;
        this.executionRole = position;
    }

    public String getExecutionRole() {
        return executionRole;
    }

    public String getUsername() {
        return username;
    }
}
