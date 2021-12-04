package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * A class designed to record data about foreign employees from a csv file.
 */
public class CsvReader {
    /**
     * An array that stores information about all the people in the file
     */
    public ArrayList<Human> stuff;
    /**
     * A set that stores all the divisions from the file
     */
    public Set<Division> division;
    /**
     * The directory to the csv file
     */
    private String directory;

    /**
     * Class Constructor
     * @param directory Directory of csv file
     */
    public CsvReader(String directory) {
        this.directory = directory;
        stuff = new ArrayList<>();
        division = new HashSet<>();
    }

    /**
     * Getting the directory
     * @return Directory of csv file
     */
    public String getDirectory() {
        return directory;
    }

    /**
     * The main method. Reads a file and writes data to an array and a set if necessary.
     * @throws Exception If it's not a file or it has a non-csv extension
     */
    public void reading() throws Exception {
        long numOfLine = 2;
        BufferedReader reader = null;
        String line = "";
        try {
            reader = new BufferedReader(new FileReader(directory));
        }
        catch (Exception ex) {
            throw new Exception("This directory was not found or the file does not exist in the directory!");
        }
        String[] dirSplit = directory.split("/");
        if (!dirSplit[dirSplit.length-1].split("\\.")[1].equals("csv")) {
            throw new Exception("Reading is possible only for csv files!");
        }
        reader.readLine();
        while ((line = reader.readLine()) != null) {
            String[] row = line.split(";");
            check(row, numOfLine);
            Division div = new Division(row[4].charAt(0));
            if (!division.contains(div)) {
                division.add(div);
            }
            stuff.add(new Human(Integer.parseInt(row[0]), row[1], row[2], div, Integer.parseInt(row[5]), row[3]));
            numOfLine++;
        }
    }

    /**
     * Method for checking an array of strings with data from a file. Calls other methods
     * @param row Current row data
     * @param numOfLine Line number
     * @throws Exception If there is less data than needed
     */
    private void check(String[] row, long numOfLine) throws Exception {
        if (row.length != 6) {
            throw new Exception("Some values are missing!");
        }
        checkID(row[0], numOfLine);
        checkGender(row[2], numOfLine);
        checkBirthdate(row[3], numOfLine);
        checkDivision(row[4], numOfLine);
        checkSalary(row[5], numOfLine);
    }

    /**
     * Checking the ID from the file
     * @param id Current ID
     * @param numOfLine Number of line in csv
     * @throws Exception If extra characters are allowed in the ID column
     */
    private void checkID(String id, long numOfLine) throws Exception {
        for (int i = 0; i < id.length(); i++) {
            if (id.charAt(i) < '0' || id.charAt(i) > '9') {
                throw new Exception("In column A row " + numOfLine + " of the file contains invalid characters.");
            }
        }
    }

    /**
     * Checking the gender from the file
     * @param gender Current gender
     * @param numOfLine Number of line in csv
     * @throws Exception If the gender does not match
     */
    private void checkGender(String gender, long numOfLine) throws Exception {
        if (!gender.equals("Male") && !gender.equals("Female")) {
            throw new Exception("The gender in column C row " + numOfLine +" is incorrect!");
        }
    }

    /**
     * Checking the birthdate from the file
     * @param birthdate Current birthdate
     * @param numOfLine Number of line in csv
     * @throws Exception If the date is incorrect
     */
    private void checkBirthdate(String birthdate, long numOfLine) throws Exception {
        for (int i = 0; i < birthdate.length(); i++) {
            if ((birthdate.charAt(i) < '0' || birthdate.charAt(i) > '9') && birthdate.charAt(i) != '.') {
                throw new Exception("In column D row " + numOfLine + " of the file contains invalid characters.");
            }
        }
        int[] daysInMonth = new int[] {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        String[] birth = birthdate.split("\\.");
        if (birth.length != 3) {
            throw new Exception("Incorrect date of birth is specified in column D of row " + numOfLine);
        }
        if (birth[0].length() != 2 || birth[1].length() != 2) {
            throw new Exception("Incorrect length of the day or month in column D of the row " + numOfLine);
        }
        if (birth[1].equals("02") && Integer.parseInt(birth[2]) % 4 == 0 && Integer.parseInt(birth[0]) > 29) {
            throw new Exception("In column D of row " + numOfLine + ", the day exceeds the allowed value.");
        }
        if (daysInMonth[Integer.parseInt(birth[1]) - 1] < Integer.parseInt(birth[0]) && Integer.parseInt(birth[2]) % 4 != 0) {
            throw new Exception("In column D of row " + numOfLine + ", the day exceeds the allowed value.");
        }
    }

    /**
     * Checking the division from the file
     * @param division Current division
     * @param numOfLine Number of line in csv
     * @throws Exception If the division consists of more than one character
     */
    private void checkDivision(String division, long numOfLine) throws Exception {
        if (division.length() > 1) {
            throw new Exception("In column E of row " + numOfLine +", an incorrect subdivision is indicated. It must contain one letter or number.");
        }
    }

    /**
     * Checking the salary from the file
     * @param salary Current salary
     * @param numOfLine Number of line in csv
     * @throws Exception If there are extra characters in the salary
     */
    private void checkSalary(String salary, long numOfLine) throws Exception {
        for (int i = 0; i < salary.length(); i++) {
            if (salary.charAt(i) < '0' || salary.charAt(i) > '9') {
                throw new Exception("In column F row " + numOfLine + " of the file contains invalid characters.");
            }
        }
    }

    /**
     * A method for finding people working in the same department
     * @param title The division we need
     * @return Array with people from this division
     */
    public List<Human> rightPeople(char title) {
        List<Human> rightPeople = new ArrayList<>();
        for (Human i: stuff) {
            if (i.getDivision().getTitle() == title) {
                rightPeople.add(i);
            }
        }
        return rightPeople;
    }

    /**
     * Overriding the method for comparing objects.
     * @param o Object to compare
     * @return true if equal and false if not equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CsvReader csvReader = (CsvReader) o;
        return Objects.equals(stuff, csvReader.stuff) && Objects.equals(division, csvReader.division) && Objects.equals(directory, csvReader.directory);
    }

    /**
     * Redefining the hash code method
     * @return Hash code of this object.
     */
    @Override
    public int hashCode() {
        return stuff.size() * division.size();
    }
}
