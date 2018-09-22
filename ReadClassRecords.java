/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.hdsb.gwss.yulia.ics4u.databases;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author yulia
 */
public class ReadClassRecords {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        // TODO code application logic here
        ClassDB db = new ClassDB();
        Scanner input = new Scanner(System.in);
        long numRecords;
        int recordNumber = 1;

        db.open();

        ClassRecord c1 = new ClassRecord("Mr.Sanderson", "Chemistry", 31, 12, 82, 'u', false);
        ClassRecord c2 = new ClassRecord("Mrs.Avery", "Drama", 32, 9, 90, 'u', true);
        ClassRecord c3 = new ClassRecord("Mlle.Beltran", "Physical Education", 34, 10, 87.5, 'u', true);
        ClassRecord c4 = new ClassRecord("Mr.Muir", "Computer Science", 20, 12, 85.2, 'u', false);
        ClassRecord c5 = new ClassRecord("Ms.Kaushal", "Calculus and Vectors", 20, 12, 80, 'u', false);
        ClassRecord c6 = new ClassRecord("Mr.Chao", "Economics", 22, 12, 85, 'u', false);
        ClassRecord c7 = new ClassRecord("Ms.Fields", "Geography", 33, 9, 87.5, 'u', false);
        ClassRecord c8 = new ClassRecord("Mr.Sauve", "Computer Engineering", 20, 11, 85, 'c', false);

        ClassRecord c;
        boolean isValid = false;
        try {
            c1 = db.save(c1);
            c2 = db.save(c2);
            c3 = db.save(c3);
            c4 = db.save(c4);
            c5 = db.save(c5);
            c6 = db.save(c6);
            c7 = db.save(c7);
            c8 = db.save(c8);

        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("invalid ");
        }

        numRecords = db.getNumRecords();

        while (recordNumber != 0) {

            System.out.println("\nThere are " + numRecords + " records currently in the file.");
            System.out.println("Which record do you want [1 - " + numRecords + "] or 0 to exit?");

            do {

                try {
                    recordNumber = Integer.parseInt(input.nextLine());
                    if (recordNumber > db.getNumRecords() || recordNumber < 0) {
                        throw new Exception();
                    }
                    isValid = true;
                } catch (Exception e) {
                    System.out.println("invalid record number");
                    System.out.println("Which record do you want [1 - " + numRecords + "] or 0 to exit?");

                }

            } while (!isValid);

            isValid = false;

            System.out.println("Record number " + recordNumber + ": ");
            c = db.get(recordNumber);

            System.out.println(c.toString());

            do {

                System.out.println("Enter a new name or 'k' to keep the current name: ");
                String instructor = input.nextLine();

                while (!c.isValidTeacher(instructor)) {
                    System.out.println("This is an invalid teacher name enter a new name ");
                    instructor = input.nextLine();
                }

                if (!"k".equals(instructor)) {
                    c.setTeacher(instructor);
                }

                System.out.println("Enter a new subject or 'k' to keep the current subject ");
                String subject = input.nextLine();

                while (!c.isValidSubject(subject)) {
                    System.out.println("This is an invalid subject name enter a new name ");
                    subject = input.nextLine();
                }

                if (!"k".equals(subject)) {
                    c.setSubject(subject);
                }
                System.out.println("Enter a new class size or 'k' to keep the current size: ");
                String s = input.nextLine();

                if (!"k".equals(s)) {
                    int size = Integer.parseInt(s);

                    while (!c.isValidGrade(size)) {
                        System.out.println("This is an invalid size, enter new size ");
                        size = Integer.parseInt(input.nextLine());
                    }

                    c.setNumStu(size);

                }

                System.out.println("Enter a new grade number or 'k' to keep the current grade: ");
                String gr = input.nextLine();

                if (!"k".equals(gr)) {
                    int grade = Integer.parseInt(gr);

                    while (!c.isValidGrade(grade)) {
                        System.out.println("This is an invalid grade, enter new grade ");
                        grade = Integer.parseInt(input.nextLine());
                    }

                    c.setGrade(grade);

                }

                System.out.println("Enter a new class average or 'k' to keep the current average: ");

                String av = input.nextLine();

                if (!"k".equals(av)) {
                    int average = Integer.parseInt(av);

                    while (!c.isValidGrade(average)) {
                        System.out.println("This is an invalid average, enter new average ");
                        average = Integer.parseInt(input.nextLine());
                    }

                    c.setAverage(average);

                }

                System.out.println("Enter a new level (U,C, or N) to change levels, or 'k' to keep the current level: ");

                char uc = Character.toUpperCase(input.nextLine().charAt(0));

                if (uc != 'K' ) {

                    while (!c.isValidLevel(uc)) {
                        System.out.println("This is an invalid level. Enter U,C,or N ");
                        uc = Character.toUpperCase(input.nextLine().charAt(0));

                    }
                }
                c.setUC(uc);

                System.out.println("Enter 'k' to keep the current french status, enter any other string to change: ");

                String status = input.nextLine();

                if (!"k".equals(status)) {
                    //returns the opposite of what it was
                    c.isInFrench(!c.isInFrench());
                }

                try {
                    if (!c.isValid()) {
                        throw new Exception();
                    }
                    
                    c.setID(recordNumber);
                    db.save(c);
                    System.out.println("Record saved");
                    System.out.println(c.toString());
                    isValid = true;

                } catch (Exception e) {
                    System.out.println("Invalid data");
                }

            } while (!isValid);

        }

        db.close();

    }

}
