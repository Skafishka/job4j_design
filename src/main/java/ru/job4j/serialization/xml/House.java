package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "house")
@XmlAccessorType(XmlAccessType.FIELD)
public class House {

    @XmlAttribute
    private boolean empty;

    @XmlAttribute
    private int constructionAge;
    private Seller seller;

    private String[] possibleBuyers;

    public House() { }

    public House(boolean empty, int constructionAge, Seller seller, String[] possibleBuyers) {
        this.empty = empty;
        this.constructionAge = constructionAge;
        this.seller = seller;
        this.possibleBuyers = possibleBuyers;
    }

    @Override
    public String toString() {
        return "House{"
                + "empty=" + empty
                + ", constructionAge=" + constructionAge
                + ", seller=" + seller
                + ", possibleBuyers=" + Arrays.toString(possibleBuyers)
                + '}';
    }

    public static void main(String[] args) throws JAXBException {

        final House house = new House(true, 1965, new Seller("Oleg"), new String[] {"Ivan, Anna, Kristina"});

        JAXBContext context = JAXBContext.newInstance(House.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(house, writer);
            String result = writer.getBuffer().toString();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
