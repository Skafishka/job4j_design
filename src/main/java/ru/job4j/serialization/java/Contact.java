package ru.job4j.serialization.java;

import java.io.*;
import java.nio.file.Files;

public class Contact  implements Serializable {
    private final int zipCode;
    private final String phone;
    private final String name;

    public Contact(int zipCode, String phone, String name) {
        this.zipCode = zipCode;
        this.phone = phone;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Contact{"
                + "zipCode=" + zipCode
                + ", phone='" + phone + '\''
                + ", name='" + name + '\''
                + '}';
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        final Contact contact = new Contact(92100, "+372 (555) 666 777 99", "Alina");

        File tempFile = Files.createTempFile(null, null).toFile();
        try (FileOutputStream fos = new FileOutputStream(tempFile);
            ObjectOutputStream oos =
                    new ObjectOutputStream(fos)) {
            oos.writeObject(contact);
        }

        try (FileInputStream fis = new FileInputStream(tempFile);
            ObjectInputStream ois =
                    new ObjectInputStream(fis)) {
            final Contact contactFromFile = (Contact) ois.readObject();
            System.out.println(contactFromFile);
        }
    }
}
