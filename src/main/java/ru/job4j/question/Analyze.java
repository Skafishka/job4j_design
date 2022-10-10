package ru.job4j.question;

import java.util.*;

public class Analyze {

    public static Info diff(Set<User> previous, Set<User> current) {
        int deleted = 0;
        int changed = 0;
        Map<Integer, String> rsl = new HashMap<>();
        for (User c : current) {
            rsl.put(c.getId(), c.getName());
        }
        for (User p : previous) {
            if (rsl.get(p.getId()) == null) {
                deleted++;
            } else if (!(rsl.get(p.getId()).equals(p.getName()))) {
                changed++;
            }
        }
        int added = current.size() - previous.size() + deleted;
        return new Info(added, changed, deleted);
    }
}
