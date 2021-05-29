package com.addressbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;


public class ContactDetailOperation {
    //add contact in arraylist
    public static ArrayList<ContactPerson> contact = new ArrayList<ContactPerson>();//initializing array list
    public static Scanner scan = new Scanner(System.in);
    public HashMap<String, ArrayList<ContactPerson>> personByState;
    public HashMap<String, ArrayList<ContactPerson>> personByCity;
    public static String ADDRESSBOOK_FILE_NAME = "addressBook_file.txt";

    public ContactDetailOperation() {
        personByCity = new HashMap<String, ArrayList<ContactPerson>>();
        personByState = new HashMap<String, ArrayList<ContactPerson>>();
    }

    /**
     * contact delete method to delete the contact
     */
    public void deleteContact() {
        System.out.println("Enter name wants to delete:");
        String firstName = scan.next();
        boolean flag = false;
        for (ContactPerson personDetail : contact) {
            if (personDetail.first_name.equals(firstName)) {
                contact.remove(personDetail);
                flag = true;
                break;
            }
        }
        if (flag == false) {
            System.out.println(firstName + "Name not found");
        } else {
            System.out.println("Contact Deleted successfully");
        }
    }

    /**
     * edit contact method called from main method
     */
    public void editContact() {
        System.out.println("Enter the name you want to edit:");
        String enteredName = scan.next();
        boolean flag = false;
        for (ContactPerson person : contact) {
            if (person.getFirst_name().equals(enteredName)) {
                flag = true;
                System.out.println("1. First Name\n" + "2.Last Name\n" + "3.Address\n" + "4.city\n" + "5.State\n" + "6.zip\n" + "7.phoneNumber\n" + "8.email");
                System.out.println("Enter your choice:");
                int choice = scan.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println("Enter First Name : ");
                        String firstName = scan.next();
                        person.setFirst_name(firstName);
                        break;
                    case 2:
                        System.out.println("Enter First Name : ");
                        String lastName = scan.next();
                        person.setLast_name(lastName);
                        break;
                    case 3:
                        System.out.println("Enter Address : ");
                        String newAddress = scan.next();
                        person.setAddress(newAddress);
                        break;
                    case 4:
                        System.out.println("Enter city : ");
                        String newCity = scan.next();
                        person.setCity(newCity);
                        break;
                    case 5:
                        System.out.println("Enter State : ");
                        String newState = scan.next();
                        person.setState(newState);
                        break;
                    case 6:
                        System.out.println("Enter zip : ");
                        String newZip = scan.next();
                        person.setZip(newZip);
                        break;
                    case 7:
                        System.out.println("Enter phone Number : ");
                        String new_phoneNumber = scan.next();
                        person.setPhone_number(new_phoneNumber);
                        break;
                    case 8:
                        System.out.println("Enter email : ");
                        String new_email = scan.next();
                        person.setEmail(new_email);
                        break;
                    default:
                        System.out.println("invalid choice");
                }
                break;
            }
        }
        if (flag == false) {
            System.out.println(enteredName + " Not Found!");
        } else {
            System.out.println("Contact edited successfully");
        }
    }

    /**
     * Add contact to the arraylist
     * @return
     */
    public ArrayList<ContactPerson> addContact() {
        //taking Contact details from user
        System.out.println("First name");
        String First_name = scan.next();
        System.out.println("Last Name");
        String last_name = scan.next();
        System.out.println("Address");
        String address = scan.next();
        System.out.println("State");
        String state = scan.next();
        System.out.println("city");
        String city = scan.next();
        System.out.println("Zip");
        String zip = scan.next();
        System.out.println("PhoneNo");
        String phone_number = scan.next();
        System.out.println("Email");
        String email = scan.next();

        //parameterized constructor calling of Contact person class
        ContactPerson person = new ContactPerson(First_name, last_name, address, city, state, zip, phone_number, email);
        //Adding object element in arrayList
        contact.add(person);

        if (!personByState.containsKey(state)) {
            personByState.put(state, new ArrayList<ContactPerson>());
        }
        personByState.get(state).add(person);

        if (!personByCity.containsKey(city)) {
            personByCity.put(city, new ArrayList<ContactPerson>());
        }
        personByCity.get(city).add(person);

        //Exception if exist then will be handled by catch block and method call added
        try {
            writeData();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return contact;
    }

    /**
     * Write data to text file which created statically
     * Writes bytes to a file. The options parameter specifies how the file is created or opened.
     * @throws IOException
     */
    public void writeData() throws IOException {
        StringBuffer contactBuffer = new StringBuffer();
        contact.forEach(person -> {
            String contactDataString = person.toString().concat("\n");
            System.out.println(contactDataString);
            contactBuffer.append(contactDataString);
        });
        try {
            Files.write(Paths.get(ADDRESSBOOK_FILE_NAME), contactBuffer.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *read data from file and display on console
     */
    public void readData() {
        try {
            Files.lines(new File(ADDRESSBOOK_FILE_NAME).toPath())
                    .map(String::trim).forEach(System.out::println);
        } catch (IOException ignored) {
            System.out.println("ignored");
        }
    }

    /**
     * check duplicate contact in Address book if it is find will show the result
     */
    public void checkDuplicate() {
        Set<String> ContactSet = new HashSet<>();
        Set<ContactPerson> newSet = contact.stream().filter(n -> !ContactSet.add(n.getFirst_name())).collect(Collectors.toSet());
        for (ContactPerson contact : newSet) {
            System.out.println("The Duplicate Contact is: " + contact.getFirst_name() + " " + contact.getLast_name());
        }
    }


    /**
     * Getting first name and lastname according to city name
     *
     * @param cityName
     */
    public void getPersonNameByCity(String cityName) {
        List<ContactPerson> list = contact.stream().filter(person -> person.getCity().equals(cityName)).collect(Collectors.toList());
        for (ContactPerson contact : list) {
            System.out.println("First Name: " + contact.getFirst_name());
            System.out.println("Last Name: " + contact.getLast_name());
        }
    }

    /**
     * Getting first name and lastname according to State name
     *
     * @param stateName
     */
    public void getPersonNameByState(String stateName) {
        List<ContactPerson> list = contact.stream().filter(person -> person.getState().equals(stateName)).collect(Collectors.toList());
        for (ContactPerson contact : list) {
            System.out.println("First Name: " + contact.getFirst_name());
            System.out.println("Last Name: " + contact.getLast_name());
        }
    }

    /**
     * show contact details
     */
    public void showDetails() {
        for (int i = 0; i < contact.size(); i++) {
            System.out.println(contact.get(i));
        }
    }

    /**
     * Getting first name and lastname according to city name
     * @param cityName
     */
    public void searchPersonByCity(String cityName) {
        for (Map.Entry<String, ContactDetailOperation> entry : AddressBookMain.addressBookDetail.entrySet()) {
            ContactDetailOperation value = entry.getValue();
            System.out.println("The Address Book: " + entry.getKey());
            value.getPersonNameByState(cityName);
        }
    }

    /**
     * Search person by State and gives the firstname and lastname
     * @param stateName
     */
    public void searchPersonByState(String stateName) {
        for (Map.Entry<String, ContactDetailOperation> entry : AddressBookMain.addressBookDetail.entrySet()) {
            ContactDetailOperation value = entry.getValue();
            System.out.println("The Address Book: " + entry.getKey());
            value.getPersonNameByCity(stateName);
        }
    }

    /**
     * view person by city
     * @param cityName
     */
    public void viewPersonByCity(String cityName) {
        for (Map.Entry<String, ContactDetailOperation> entry : AddressBookMain.addressBookDetail.entrySet()) {
            ContactDetailOperation value = entry.getValue();
            ArrayList<ContactPerson> contacts = value.personByCity.entrySet().stream()
                    .filter(findCity -> findCity.getKey().equals(cityName))
                    .map(Map.Entry::getValue).findFirst().orElse(null);
            for (ContactPerson contact : contacts) {
                System.out.println("First Name: " + contact.getFirst_name() + " Last Name: " + contact.getLast_name());
            }
        }
    }

    /**
     * view person by state
     * @param stateName
     */
    public void viewPersonByState(String stateName) {
        for (Map.Entry<String, ContactDetailOperation> entry : AddressBookMain.addressBookDetail.entrySet()) {
            ContactDetailOperation value = entry.getValue();
            ArrayList<ContactPerson> contacts = value.personByState.entrySet().stream()
                    .filter(findState -> findState.getKey().equals(stateName))
                    .map(Map.Entry::getValue).findFirst().orElse(null);
            for (ContactPerson contact : contacts) {
                System.out.println("First Name: " + contact.getFirst_name() + " Last Name: " + contact.getLast_name());
            }
        }
    }

    /**
     * Search Result will show count by city
     * @param city
     */
    public void countByCity(String city) {
        int personCountInCity=0;
        for (Map.Entry<String,ContactDetailOperation> entry:AddressBookMain.addressBookDetail.entrySet()){
            for (int i=0;i<(entry.getValue()).contact.size();i++){
                ContactPerson personCount= entry.getValue().contact.get(i);
                if (city.equals(personCount.getCity())){
                    personCountInCity++;
                }
            }
            System.out.println("Total Count of People in "+city+"-->"+personCountInCity);
        }
    }

    /**
     * Search Result will show count by state
     * @param state
     */
    public void countByState(String state) {
        int personCountInState=0;
        for (Map.Entry<String,ContactDetailOperation> entry:AddressBookMain.addressBookDetail.entrySet()){
            for (int i=0;i<(entry.getValue()).contact.size();i++){
                ContactPerson personCount= entry.getValue().contact.get(i);
                if (state.equals(personCount.getState())){
                    personCountInState++;
                }
            }
            System.out.println("Total Count of People in State "+state+"-->"+personCountInState);
        }
    }

    /**
     * Sorts the entries in the address book Alphabetically
     * by person name
     */
    public void sortContactByName() {
        for (Map.Entry<String,ContactDetailOperation>entry:AddressBookMain.addressBookDetail.entrySet()){
            ContactDetailOperation sort=entry.getValue();
            List<ContactPerson> sortedList = sort.contact.stream().sorted(Comparator
                                            .comparing(ContactPerson::getFirst_name))
                                            .collect(Collectors.toList());

            for (ContactPerson contacts:sortedList){
                System.out.println("First Name: "+contacts.getFirst_name());
                System.out.println("Last Name: "+contacts.getLast_name());
            }
        }
    }

    /**
     * sort contact by city
     */
    public void sortContactByCity() {
        for (Map.Entry<String,ContactDetailOperation>entry:AddressBookMain.addressBookDetail.entrySet()){
            ContactDetailOperation sort=entry.getValue();
            List<ContactPerson> sortedList = sort.contact.stream().sorted(Comparator
                    .comparing(ContactPerson::getCity))
                    .collect(Collectors.toList());
            for (ContactPerson contacts:sortedList){
                System.out.println("First Name: "+contacts.getFirst_name());
                System.out.println("Last Name: "+contacts.getLast_name());
            }
        }
    }

    /**
     * sort contact by state
     */
    public void sortContactByState() {
        for (Map.Entry<String,ContactDetailOperation>entry:AddressBookMain.addressBookDetail.entrySet()){
            ContactDetailOperation sort=entry.getValue();
            List<ContactPerson> sortedList = sort.contact.stream().sorted(Comparator
                    .comparing(ContactPerson::getState))
                    .collect(Collectors.toList());
            for (ContactPerson contacts:sortedList){
                System.out.println("First Name: "+contacts.getFirst_name());
                System.out.println("Last Name: "+contacts.getLast_name());
            }
        }
    }

    /**
     *Sort contact by zipcode
     */
    public void sortContactByZipCode() {
        for (Map.Entry<String,ContactDetailOperation>entry:AddressBookMain.addressBookDetail.entrySet()){
            ContactDetailOperation sort=entry.getValue();
            List<ContactPerson> sortedList = sort.contact.stream().sorted(Comparator
                    .comparing(ContactPerson::getZip))
                    .collect(Collectors.toList());
            for (ContactPerson contacts:sortedList){
                System.out.println("First Name: "+contacts.getFirst_name());
                System.out.println("Last Name: "+contacts.getLast_name());
            }
        }
    }

    @Override
    public String toString() {
        return "city wise detail=>"+personByCity ;
    }
}


