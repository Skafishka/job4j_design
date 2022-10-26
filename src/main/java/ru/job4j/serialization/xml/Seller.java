package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "seller")
public class Seller {

    @XmlAttribute
    private String name;

    public Seller() { }

    public Seller(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Seller{"
                + "name='"
                + name
                + '\''
                + '}';
    }
}