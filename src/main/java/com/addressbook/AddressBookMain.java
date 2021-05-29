package com.addressbook;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AddressBookMain {
    public static Map<String, ContactDetailOperation> addressBookDetail = new HashMap<>();
    public static ContactDetailOperation contactDetail = new ContactDetailOperation();//object of ContactDetailOperation class is created
    public static Scanner scan = new Scanner(System.in);

    /**
     * main class to store new address book or check if the address book already there
     */
    public static void main(String[] args) {
        System.out.println("Welcome to Address Book System");
        boolean flag = true;//declaring flag
        while (flag) {
            System.out.println("1.Add new Address Book \n 2.Check Duplicate Entry \n 3.Search person by city \n " +
                               "4. Search Person by State \n 5.View person by State\n " +
                               "6.View person by city\n 7.Count people by City: " +
                               "\n8.Count people by state\n 9.Sort contact by Name: \n 10.sort contact by state:" +
                                "\n11.sort contact by city: \n 12. sort contact by zip: \n13 read data from file: \n14.Exit");
            System.out.println("Enter your choice");
            int choice = scan.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter the name of the address book");
                    String addressBookName = scan.next();//getting address book name from user
                    if (addressBookDetail.containsKey(addressBookName)) {
                        System.out.println("this address book already exists ");
                        break;
                    } else {
                        addAddressbook(addressBookName);//storing address book in map
                        for (Map.Entry<String, ContactDetailOperation> entry : addressBookDetail.entrySet()) {
                            System.out.println("Address Book Name:>" + entry.getKey()+ "==>" + "Contact Details:>" + entry.getValue());
                        }
                        break;
                    }
                case 2:
                    for (Map.Entry<String, ContactDetailOperation> entry : addressBookDetail.entrySet()) {
                        ContactDetailOperation value = entry.getValue();
                        System.out.println(value);
                        System.out.println("Addres s Book Name: " + entry.getKey());
                        value.checkDuplicate();
                        break;
                    }
                case 3:
                    System.out.println("Enter Name of City: ");
                    String cityName = scan.next();
                    contactDetail.searchPersonByCity(cityName);
                    break;
                case 4:
                    System.out.println("Enter Name of State: ");
                    String stateName = scan.next();
                    contactDetail.searchPersonByState(stateName);
                    break;
                case 5:
                    System.out.println("Enter Name of State: ");
                    String state_Name = scan.next();
                    contactDetail.viewPersonByState(state_Name);
                    break;
                case 6:
                    System.out.println("Enter Name of City: ");
                    String city_Name = scan.next();
                    contactDetail.viewPersonByCity(city_Name);
                    break;
                case 7:
                    System.out.println("Enter the name of City:");
                    String city=scan.next();
                    contactDetail.countByCity(city);
                    break;
                case 8:
                    System.out.println("Enter the name of State:");
                    String state=scan.next();
                    contactDetail.countByState(state);
                    break;
                case 9:
                    contactDetail.sortContactByName();
                    break;
                case 10:
                    contactDetail.sortContactByCity();
                    break;
                case 11:
                    contactDetail.sortContactByState();
                    break;
                case 12:
                    contactDetail.sortContactByZipCode();
                    break;
                case 13:
                    contactDetail.readData();
                    break;
                case 14:
                    System.out.println("Exit");
                    flag = false;
                    break;
            }
        }
    }
    /**
     * Adding different address book
     */
    public static void addAddressbook(String addBookName) {
        boolean isFlag = true;
        while (isFlag) {
            System.out.println("Address Book Menu!!");
            System.out.println("1.Add contact. \n 2.Edit contact. \n 3.Delete Contact \n 4. Read CSV file \n 5.Exit");//choices for operation
            System.out.print("enter choice:");
            int option = scan.nextInt();
            switch (option) {
                case 1:
                    contactDetail.addContact();
                    contactDetail.showDetails();
                    System.out.println("Contact Added Successfully!");
                    System.out.println("-----------------------------");
                    break;
                case 2:
                    contactDetail.editContact();
                    contactDetail.showDetails();
                    System.out.println("-----------------------------");
                    break;
                case 3:
                    contactDetail.deleteContact();
                    contactDetail.showDetails();
                    System.out.println("-----------------------------");
                    break;
                case 4:
                    try {
                        contactDetail.readDataFromCSV();// calling readData from CSV  method
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 5:
                    isFlag = false;
                    System.out.println("Exit");
                    break;
                default:
                    System.out.println("Please Enter Valid Choice:");
                    break;
            }
        }
        addressBookDetail.put(addBookName, contactDetail);
        System.out.println("Address book added successfully");
    }
}