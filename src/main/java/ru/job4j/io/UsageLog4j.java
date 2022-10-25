package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Sergei Kolygin";
        int age = 34;
        boolean student = true;
        double collegeNumber = 2.256;
        float memory = 2.26f;
        byte amount = 127;
        short phoneNumber = 12589;
        long homeNumber = 92235789;
        char className = 'A';
        LOG.debug("User info name : {}, age : {}, student : {}, number in college: {}, \n"
                        + "laptop memory: {}, money amount : {}, phonenumber : {}, home number : {}, number of class : {}",
                name, age, student, collegeNumber, memory, amount, phoneNumber, homeNumber, className);
    }
}