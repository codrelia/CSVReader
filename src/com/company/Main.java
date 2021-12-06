package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("This program reads data from a file containing foreigners, namely:\n" +
                "id\n" +
                "name\n" +
                "gender\n" +
                "birthdate\n" +
                "division\n" +
                "salary");
        System.out.println("Enter the directory to the desired file, including the csv file itself ->");
        String directory = scanner.next();
        try {
            CsvReader csv = new CsvReader(directory);
            csv.reading();
            int choice = 1;
            while (choice != 0) {
                System.out.println("What needs to be done?");
                System.out.println("1. Display all people on the screen;\n" +
                        "2. Display all divisions on the screen;\n" +
                        "3. Display people from a certain division on the screen;\n" +
                        "0. Exit.");
                while (!scanner.hasNextInt()) {
                    System.out.println("Insert integer value!");
                    scanner.next();
                }
                choice = scanner.nextInt();
                switch (choice) {
                    case 1: { // /Users/dosherak/Documents/Course3Sem1/Java/Laborator4/foreign_names.csv
                        List<Human> stuff = csv.stuff;
                        for (Human i: stuff) {
                            System.out.println(i.toString());
                            System.out.println();
                        }
                        break;
                    }
                    case 2: {
                        Set<Division> div = csv.divisions;
                        for (Division i: div) {
                            System.out.println(i.toString());
                            System.out.println();
                        }
                        break;
                    }
                    case 3: {
                        System.out.println("Enter the letter of the division -> ");
                        String div = scanner.next();
                        if (div.length() > 1) {
                            System.out.println("The division is specified by one letter!");
                        } else {
                            List<Human> stuff = csv.rightPeople(div.charAt(0));
                            for (Human i: stuff) {
                                System.out.println(i.toString());
                                System.out.println();
                            }
                            break;
                        }
                    }
                    case 0: {
                        break;
                    }
                    default: System.out.println("This numeric combination is not specified in the program!");
                }
            }
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
