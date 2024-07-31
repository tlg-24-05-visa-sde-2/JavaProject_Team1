//package com.seafooddelakec.test;
//
//import com.apps.util.Prompter;
//import com.seafooddelakec.app.Host;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.util.Scanner;
//
//import static org.junit.Assert.assertEquals;
//
//
//public class SeafoodDelakecTest {
//
//    private Host host;
//    private Prompter prompter;
//
//    @Before
//    public void setUp() {
//        host = new Host();
//        Scanner scanner = new Scanner(System.in);
//        prompter = new Prompter(scanner);
//    }
//
//    @Test
//    public void testHost_getCustomerNamePrompter_withSpaceBetweenInput() {
//    }
//
////    @Test
////    public void testHost_getCustomerNamePrompter_withoutSpacesInput() {
////        String name = prompter.prompt("Enter your customer name: ");
////        assertEquals("Cort", name);
////    }
//
//    @Test
//    public void testHost_getCustomerNamePrompter_nullInput() {
//        prompter.prompt("");
//
//        // Act
//        host.greeting();
//
//        // Assert
//        assertEquals("", Host.getCustomerName());
//    }
//
//    @Test
//    public void testHost_getCustomerNamePrompter_numericInput() {
//    }
//}
//
///*
//package com.javatunes.personnel;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import java.sql.Date;
//
//import static org.junit.Assert.*;
//
//public class HourlyEmployeeTest {
//
//    private HourlyEmployee hemp;
//
//    @Before
//    public void setUp() {
//        hemp = new HourlyEmployee("Malachi", Date.valueOf("2018-11-29"), 32.0, 80.0);
//    }
//
//    @Test
//    public void testPay() {
//        assertEquals(2560, hemp.pay(), .001); // rate * hours
//    }
//
//    @Test
//    public void testPayTaxes() {
//        assertEquals(640.0, hemp.payTaxes(), .001); // should be 640
//    }
//
//    @Test
//    public void name() {
//    }
//}
// */