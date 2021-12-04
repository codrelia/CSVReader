package com.company;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CsvReaderTest {
    CsvReader csv;
    @org.junit.jupiter.api.Test
    void test1() throws Exception {
        csv = new CsvReader("/Users/dosherak/Documents/Course3Sem1/Java/Laborator4/LAB/forTests/book0.csv");
        csv.reading();
        List<Human> stuff = csv.rightPeople('C');
        assertEquals("28287. Aaliyah. Gender: Female, birthdate: 25.09.1987, division: C, salary: 1100", stuff.get(0).toString());
    }

    @org.junit.jupiter.api.Test
    void test2() throws Exception{
        csv = new CsvReader("fff");
        Exception exception = assertThrows(Exception.class, () -> csv.reading());
        assertEquals("This directory was not found or the file does not exist in the directory!", exception.getMessage());
    }

    @org.junit.jupiter.api.Test
    void test3() throws Exception{
        csv = new CsvReader("/Users/dosherak/Documents/Course3Sem1/Java/Laborator4/LAB/forTests/book1.csv");
        Exception exception = assertThrows(Exception.class, () -> csv.reading());
        assertEquals("Some values are missing!", exception.getMessage());
    }

    @org.junit.jupiter.api.Test
    void test4() throws Exception{
        csv = new CsvReader("/Users/dosherak/Documents/Course3Sem1/Java/Laborator4/LAB/forTests/book2.csv");
        Exception exception = assertThrows(Exception.class, () -> csv.reading());
        assertEquals("In column A row 6 of the file contains invalid characters.", exception.getMessage());
    }

    @org.junit.jupiter.api.Test
    void test5() throws Exception{
        csv = new CsvReader("/Users/dosherak/Documents/Course3Sem1/Java/Laborator4/LAB/forTests/book3.csv");
        Exception exception = assertThrows(Exception.class, () -> csv.reading());
        assertEquals("The gender in column C row 4 is incorrect!", exception.getMessage());
    }

    @org.junit.jupiter.api.Test
    void test6() throws Exception{
        csv = new CsvReader("/Users/dosherak/Documents/Course3Sem1/Java/Laborator4/LAB/forTests/book4.csv");
        Exception exception = assertThrows(Exception.class, () -> csv.reading());
        assertEquals("In column D row 3 of the file contains invalid characters.", exception.getMessage());
    }

    @org.junit.jupiter.api.Test
    void test7() throws Exception{
        csv = new CsvReader("/Users/dosherak/Documents/Course3Sem1/Java/Laborator4/LAB/forTests/book5.csv");
        Exception exception = assertThrows(Exception.class, () -> csv.reading());
        assertEquals("Incorrect date of birth is specified in column D of row 5", exception.getMessage());
    }

    @org.junit.jupiter.api.Test
    void test8() throws Exception{
        csv = new CsvReader("/Users/dosherak/Documents/Course3Sem1/Java/Laborator4/LAB/forTests/book6.csv");
        Exception exception = assertThrows(Exception.class, () -> csv.reading());
        assertEquals("Incorrect length of the day or month in column D of the row 7", exception.getMessage());
    }

    @org.junit.jupiter.api.Test
    void test9() throws Exception{
        csv = new CsvReader("/Users/dosherak/Documents/Course3Sem1/Java/Laborator4/LAB/forTests/book7.csv");
        Exception exception = assertThrows(Exception.class, () -> csv.reading());
        assertEquals("In column D of row 8, the day exceeds the allowed value.", exception.getMessage());
    }

    @org.junit.jupiter.api.Test
    void test10() throws Exception{
        csv = new CsvReader("/Users/dosherak/Documents/Course3Sem1/Java/Laborator4/LAB/forTests/book8.csv");
        Exception exception = assertThrows(Exception.class, () -> csv.reading());
        assertEquals("In column D of row 2, the day exceeds the allowed value.", exception.getMessage());
    }

    @org.junit.jupiter.api.Test
    void test11() throws Exception{
        csv = new CsvReader("/Users/dosherak/Documents/Course3Sem1/Java/Laborator4/LAB/forTests/book9.csv");
        Exception exception = assertThrows(Exception.class, () -> csv.reading());
        assertEquals("In column E of row 6, an incorrect subdivision is indicated. It must contain one letter or number.", exception.getMessage());
    }

    @org.junit.jupiter.api.Test
    void test12() throws Exception{
        csv = new CsvReader("/Users/dosherak/Documents/Course3Sem1/Java/Laborator4/LAB/forTests/book10.csv");
        Exception exception = assertThrows(Exception.class, () -> csv.reading());
        assertEquals("In column F row 3 of the file contains invalid characters.", exception.getMessage());
    }

    @org.junit.jupiter.api.Test
    void test13() throws Exception{
        csv = new CsvReader("/Users/dosherak/Documents/Course3Sem1/Java/Laborator4/LAB/forTests/111.txt");
        Exception exception = assertThrows(Exception.class, () -> csv.reading());
        assertEquals("Reading is possible only for csv files!", exception.getMessage());
    }
}