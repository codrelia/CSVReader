package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Reading a csv file that contains data about employees of divisions.
 *
 * @author Dasha Shevchenko
 */
public class CsvReader {
    /**
     * File directory.
     */
    public String directory;
    /**
     * List of department employees.
     */
    public List<Human> stuff;
    /**
     * The set of all divisions.
     */
    public Set<Division> divisions;
    /**
     * The number of the current line in the file.
     */
    private long numberOfLine;

    /**
     * Constructor of the class. Initializes the properties of the class.
     * @param directory The directory to the specified file.
     */
    public CsvReader(String directory) {
        this.directory = directory;
        stuff = new LinkedList<>();
        divisions = new HashSet<>();
        numberOfLine = 1;
    }

    /**
     * The method of reading the file.
     * @throws Exception If the specified directory is not a directory or a csv file.
     */
    public void reading() throws Exception {
        BufferedReader bufferedReader = null;
        File file = new File(directory);
        if (!file.isDirectory() && !file.isFile()) {
            throw new Exception("Invalid directory");
        }
        if (!getFileExtension(file).equals("csv")) {
            throw new Exception("Isn't csv file");
        }
        String line = "";
        bufferedReader = new BufferedReader(new FileReader(directory));
        bufferedReader.readLine();
        numberOfLine++;
        while ((line = bufferedReader.readLine()) != null) {
            String[] row = line.split(";");
            validValues(row);
            Division div = new Division(row[4].charAt(0));
            if (!divisions.contains(div)) {
                divisions.add(div);
            }
            stuff.add(new Human(Integer.parseInt(row[0]), row[1], row[2], div, Integer.parseInt(row[5]), row[3]));
            numberOfLine++;
        }
    }

    /**
     * Validating the values in the table.
     * @param row The current line from the file.
     * @throws Exception If there is incomplete data in the file.
     */
    private void validValues(String[] row) throws Exception {
        if (row.length != 6) {
            throw new Exception("Not enough data in the file");
        }
        validId(row[0]);
        validGender(row[2]);
        validBirthdate(row[3]);
        validDivision(row[4]);
        validSalary(row[5]);
    }

    /**
     * Checking the ID for correctness. Characters other than numbers are not allowed.
     * @param id The current ID in the row.
     * @throws Exception If the ID contains elements other than digits.
     */
    private void validId(String id) throws Exception {
        /* for (int i = 0; i < id.length(); i++) {
            if (id.charAt(i) < '0' || id.charAt(i) > '9') {
                throw new Exception("Contains incorrect data in column A line " + numberOfLine + "! Please fix it.");
            }
        } */
        try {
            Integer.parseInt(id);
        }
        catch(Exception exception) {
            throw new Exception("Contains incorrect data in column A line " + numberOfLine + "! Please fix it.");
        }
    }

    /**
     * Checking the gender for correctness.
     * @param gender The current gender in the row.
     * @throws Exception If an unknown gender is entered.
     */
    private void validGender(String gender) throws Exception {
        if (!gender.equals("Male") && !gender.equals("Female")) {
            throw new Exception("Contains incorrect data in column C line " + numberOfLine +"! Please fix it.");
        }
    }

    /**
     * Checking the birthdate for correctness.
     * @param birthdate The current birthdate in the row.
     * @throws Exception If the date is entered incorrectly.
     */
    private void validBirthdate(String birthdate) throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(birthdate);
        }
        catch(Exception exception) {
            throw new Exception("Column D of row " + numberOfLine + " contains an incorrect date of birth.");
        }
    }

    /**
     * Checking the division for correctness.
     * @param division The current division in the row.
     * @throws Exception If the division contains more than one character.
     */
    private void validDivision(String division) throws Exception {
        if (division.length() > 1) {
            throw new Exception("Column E of row " + numberOfLine +" contains an incorrect division.");
        }
    }

    /**
     * Checking the salary for correctness. Characters other than numbers are not allowed.
     * @param salary The current salary in the row.
     * @throws Exception If the salary contains elements other than digits.
     */
    private void validSalary(String salary) throws Exception {
        /*for (int i = 0; i < salary.length(); i++) {
            if (salary.charAt(i) < '0' || salary.charAt(i) > '9') {
                throw new Exception("Column F of row " + numberOfLine + " contains an incorrect division.");
            }
        }*/
        try {
            Integer.parseInt(salary);
        }
        catch (Exception exception) {
            throw new Exception("Column F of row " + numberOfLine + " contains an incorrect division.");
        }
    }

    /**
     * Checking the file extension.
     * @param file The file to which the path was specified.
     * @return The file extension, if any. If it is not present, an empty string is sent.
     */
    private String getFileExtension(File file) {
        String fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
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
        return stuff.equals(csvReader) && divisions.equals(csvReader.divisions);
    }

    /**
     * Redefining the hash code method
     * @return Hash code of this object.
     */
    @Override
    public int hashCode() {
        return stuff.size() * divisions.size();
    }
}
