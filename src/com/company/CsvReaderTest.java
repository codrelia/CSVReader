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
        assertEquals("Invalid directory", exception.getMessage());
    }

    @org.junit.jupiter.api.Test
    void test3() throws Exception{
        csv = new CsvReader("/Users/dosherak/Documents/Course3Sem1/Java/Laborator4/LAB/forTests/book1.csv");
        Exception exception = assertThrows(Exception.class, () -> csv.reading());
        assertEquals("Not enough data in the file", exception.getMessage());
    }

    @org.junit.jupiter.api.Test
    void test4() throws Exception{
        csv = new CsvReader("/Users/dosherak/Documents/Course3Sem1/Java/Laborator4/LAB/forTests/book2.csv");
        Exception exception = assertThrows(Exception.class, () -> csv.reading());
        assertEquals("Contains incorrect data in column A line 6! Please fix it.", exception.getMessage());
    }

    @org.junit.jupiter.api.Test
    void test5() throws Exception{
        csv = new CsvReader("/Users/dosherak/Documents/Course3Sem1/Java/Laborator4/LAB/forTests/book3.csv");
        Exception exception = assertThrows(Exception.class, () -> csv.reading());
        assertEquals("Contains incorrect data in column C line 4! Please fix it.", exception.getMessage());
    }

    @org.junit.jupiter.api.Test
    void test6() throws Exception{
        csv = new CsvReader("/Users/dosherak/Documents/Course3Sem1/Java/Laborator4/LAB/forTests/book4.csv");
        Exception exception = assertThrows(Exception.class, () -> csv.reading());
        assertEquals("Column D of row 3 contains an incorrect date of birth.", exception.getMessage());
    }

    @org.junit.jupiter.api.Test
    void test7() throws Exception{
        csv = new CsvReader("/Users/dosherak/Documents/Course3Sem1/Java/Laborator4/LAB/forTests/book5.csv");
        Exception exception = assertThrows(Exception.class, () -> csv.reading());
        assertEquals("Column D of row 5 contains an incorrect date of birth.", exception.getMessage());
    }

    @org.junit.jupiter.api.Test
    void test8() throws Exception{
        csv = new CsvReader("/Users/dosherak/Documents/Course3Sem1/Java/Laborator4/LAB/forTests/book6.csv");
        Exception exception = assertThrows(Exception.class, () -> csv.reading());
        assertEquals("Column D of row 7 contains an incorrect date of birth.", exception.getMessage());
    }

    @org.junit.jupiter.api.Test
    void test9() throws Exception{
        csv = new CsvReader("/Users/dosherak/Documents/Course3Sem1/Java/Laborator4/LAB/forTests/book7.csv");
        Exception exception = assertThrows(Exception.class, () -> csv.reading());
        assertEquals("Column D of row 8 contains an incorrect date of birth.", exception.getMessage());
    }

    @org.junit.jupiter.api.Test
    void test10() throws Exception{
        csv = new CsvReader("/Users/dosherak/Documents/Course3Sem1/Java/Laborator4/LAB/forTests/book8.csv");
        Exception exception = assertThrows(Exception.class, () -> csv.reading());
        assertEquals("Column D of row 2 contains an incorrect date of birth.", exception.getMessage());
    }

    @org.junit.jupiter.api.Test
    void test11() throws Exception{
        csv = new CsvReader("/Users/dosherak/Documents/Course3Sem1/Java/Laborator4/LAB/forTests/book9.csv");
        Exception exception = assertThrows(Exception.class, () -> csv.reading());
        assertEquals("Column E of row 6 contains an incorrect division.", exception.getMessage());
    }

    @org.junit.jupiter.api.Test
    void test12() throws Exception{
        csv = new CsvReader("/Users/dosherak/Documents/Course3Sem1/Java/Laborator4/LAB/forTests/book10.csv");
        Exception exception = assertThrows(Exception.class, () -> csv.reading());
        assertEquals("Column F of row 3 contains an incorrect division.", exception.getMessage());
    }

    @org.junit.jupiter.api.Test
    void test13() throws Exception{
        csv = new CsvReader("/Users/dosherak/Documents/Course3Sem1/Java/Laborator4/LAB/forTests/111.txt");
        Exception exception = assertThrows(Exception.class, () -> csv.reading());
        assertEquals("Isn't csv file", exception.getMessage());
    }
}