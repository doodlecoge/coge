package com.szwx.yht.test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: szwx
 * Date: 13-6-12
 * Time: 上午10:29
 * To change this template use File | Settings | File Templates.
 */
public class JaxbHelloWorld {
    public static void main(String[] args) {
        Customer customer = new Customer();
        customer.setId(100);
        customer.setName("hello world");
        customer.setAge(29);

        try {

            File file = new File("e:\\Customer.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Customer.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(customer, file);
            jaxbMarshaller.marshal(customer, System.out);

        } catch (JAXBException e) {
            e.printStackTrace();
        }



        try {

            File file = new File("e:\\Customer.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Customer.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Customer customer2 = (Customer) jaxbUnmarshaller.unmarshal(file);
            System.out.println(customer2.age);
            System.out.println(customer2.id);
            System.out.println(customer2.name);

        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }
}
