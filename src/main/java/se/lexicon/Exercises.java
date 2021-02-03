package se.lexicon;

import se.lexicon.data.DataStorage;
import se.lexicon.model.Gender;
import se.lexicon.model.Person;

import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Exercises {

    private final static DataStorage storage = DataStorage.INSTANCE;

    /*
       1.	Find everyone that has firstName: “Erik” using findMany().
    */
    public static void exercise1(String message) {
        System.out.println(message);
        //Write your code here
        Predicate<Person> filter = person -> person.getFirstName().equalsIgnoreCase("Erik");
        List<Person> result = storage.findMany(filter);

        result.forEach(System.out::println);

        System.out.println("----------------------");
    }

    /*
        2.	Find all females in the collection using findMany().
     */
    public static void exercise2(String message) {
        System.out.println(message);
        //Write your code here
        Predicate<Person> filter = person -> person.getGender().equals(Gender.FEMALE);
        List<Person> result = storage.findMany(filter);

        result.forEach(System.out::println);

        System.out.println("----------------------");
    }

    /*
        3.	Find all who are born after (and including) 2000-01-01 using findMany().
     */
    public static void exercise3(String message) {
        System.out.println(message);
        //Write your code here
        Predicate<Person> filter = person -> person.getBirthDate().isAfter(LocalDate.of(1999, 12, 31));
        List<Person> result = storage.findMany(filter);

        result.forEach(System.out::println);

        System.out.println("----------------------");
    }

    /*
        4.	Find the Person that has an id of 123 using findOne().
     */
    public static void exercise4(String message) {
        System.out.println(message);
        //Write your code here
        Predicate<Person> filter = person -> person.getId() == 123;
        List<Person> result = storage.findMany(filter);

        result.forEach(System.out::println);


        System.out.println("----------------------");
    }

    /*
        5.	Find the Person that has an id of 456 and convert to String with following content:
            “Name: Nisse Nilsson born 1999-09-09”. Use findOneAndMapToString().
     */
    public static void exercise5(String message) {
        System.out.println(message);
        //Write your code here
        Predicate<Person> filter = person -> person.getId() == 456;
        Function<Person, String> mapper =
                person -> person.getFirstName() + " " + person.getLastName() + " born " + person.getBirthDate();

        String result = storage.findOneAndMapToString(filter, mapper);

        System.out.println(result);

        System.out.println("----------------------");
    }

    /*
        6.	Find all male people whose names start with “E” and convert each to a String using findManyAndMapEachToString().
     */
    public static void exercise6(String message) {
        System.out.println(message);
        //Write your code here
        Predicate<Person> filter = person -> person.getFirstName().startsWith("E");
        Function<Person, String> mapper = person ->
                person.getFirstName() + " " + person.getLastName() + " born " + person.getBirthDate();

        List<String> result = storage.findManyAndMapEachToString(filter, mapper);

        result.forEach(System.out::println);

        System.out.println("----------------------");
    }

    /*
        7.	Find all people who are below age of 10 and convert them to a String like this:
            “Olle Svensson 9 years”. Use findManyAndMapEachToString() method.
     */
    public static void exercise7(String message) {
        System.out.println(message);
        //Write your code here
        Predicate<Person> filter = person ->
                Period.between(person.getBirthDate(), LocalDate.now()).getYears() < 10;
        Function<Person, String> mapper = person ->
                person.getFirstName() + " " + person.getLastName() + " " + Period.between(person.getBirthDate(),
                        LocalDate.now()).getYears() + " years";

        List<String> result = storage.findManyAndMapEachToString(filter, mapper);

        result.forEach(System.out::println);

        System.out.println("----------------------");
    }

    /*
        8.	Using findAndDo() print out all people with firstName “Ulf”.
     */
    public static void exercise8(String message) {
        System.out.println(message);
        //Write your code here
        Predicate<Person> filter = person -> person.getFirstName().equalsIgnoreCase("Ulf");
        Consumer<Person> printPerson = System.out::println;
        storage.findAndDo(filter, printPerson);

        System.out.println("----------------------");
    }

    /*
        9.	Using findAndDo() print out everyone who have their lastName contain their firstName.
     */
    public static void exercise9(String message) {
        System.out.println(message);
        //Write your code here
        Predicate<Person> filter = person -> person.getLastName().contains(person.getFirstName());
        Consumer<Person> printPerson = System.out::println;

        storage.findAndDo(filter, printPerson);

        System.out.println("----------------------");
    }

    /*
        10.	Using findAndDo() print out the firstName and lastName of everyone whose firstName is a palindrome.
     */
    public static void exercise10(String message) {
        System.out.println(message);
        //Write your code here
        Predicate<Person> firstNamePalindrome =
                person -> new StringBuilder(person.getFirstName()).reverse()
                        .toString().equalsIgnoreCase(person.getFirstName());
        Consumer<Person> printPerson = System.out::println;

        storage.findAndDo(firstNamePalindrome, printPerson);

        System.out.println("----------------------");
    }

    /*
        11.	Using findAndSort() find everyone whose firstName starts with A sorted by birthdate.
     */
    public static void exercise11(String message) {
        System.out.println(message);
        //Write your code here
        Predicate<Person> filter = person -> person.getFirstName().startsWith("A");
        Comparator<Person> compareByBirthdate = Comparator.comparing(Person::getBirthDate);

        List<Person> sortedPersons = storage.findAndSort(filter, compareByBirthdate);

        sortedPersons.forEach(System.out::println);

        System.out.println("----------------------");
    }

    /*
        12.	Using findAndSort() find everyone born before 1950 sorted reversed by latest to earliest.
     */
    public static void exercise12(String message) {
        System.out.println(message);
        //Write your code here

        System.out.println("----------------------");
    }

    /*
        13.	Using findAndSort() find everyone sorted in following order: lastName > firstName > birthDate.
     */
    public static void exercise13(String message) {
        System.out.println(message);
        //Write your code here

        System.out.println("----------------------");
    }
}
