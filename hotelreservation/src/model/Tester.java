package model;

import service.CustomerService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Tester {
    public static void main(String[] args) {
        Customer customer = new Customer("first","second","t@gmail.com");
        System.out.println(customer);
    }
}
