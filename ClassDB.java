/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.hdsb.gwss.yulia.ics4u.databases;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yulia
 */
public class ClassDB {

    private static RandomAccessFile raf;

    public ClassDB() {
    }

    public static void open() {
        try {
            raf = new RandomAccessFile("classinfo.dat", "rw");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ClassDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void close() {
        try {
            raf.close();
        } catch (IOException ex) {
            Logger.getLogger(ClassDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public long getNumRecords() {
        try {
            return raf.length() / ClassRecord.RECORD_SIZE;
        } catch (IOException ex) {
            Logger.getLogger(ClassDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1; 
        
    }
    //why was it static?
    public static ClassRecord get( int dbID ) throws IOException {
        
        
        // READ in ALL FIELD INTO  
        ClassRecord c = new ClassRecord();
        try {
            raf.seek(  (dbID - 1) * ClassRecord.RECORD_SIZE );
        } catch (IOException ex) {
            Logger.getLogger(ClassDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        char teacherName[] = new char[ClassRecord.LENGTH_TEACHER];
        for( int i=0; i < ClassRecord.LENGTH_TEACHER; i++ ) {
                teacherName[i] = raf.readChar();
        }
        
        c.setTeacher(new String( teacherName )); 
        
        char subject[] = new char[ClassRecord.LENGTH_SUBJECT];
        for( int i=0; i < c.LENGTH_SUBJECT; i++ ) {
            
            try {
                subject[i] = raf.readChar();
            } catch (IOException ex) {
                Logger.getLogger(ClassDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        c.setSubject(new String( subject )) ; 
        try {
            c.setNumStu(raf.readInt());
            c.setGrade(raf.readInt());
            c.setAverage(raf.readDouble());
            c.setUC(raf.readChar());
            c.isInFrench(raf.readBoolean());
        } catch (IOException ex) {
            Logger.getLogger(ClassDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
  
        return c;
        
    }
    
    public static ClassRecord save(ClassRecord c) throws Exception {

        // ADD or UPDATE
        if (!c.isValid()) {

            throw new Exception( "Class Record failed isValid(), not being saved.");
        } else {
            if (c.getID() == -1) {
                raf.seek(raf.length());
                c.setID((int) (raf.length() / ClassRecord.RECORD_SIZE) + 1);
            } else {
                // VALID ID
                raf.seek((c.getID() - 1) * ClassRecord.RECORD_SIZE);

            }
            raf.writeChars(c.getTeacher());
            raf.writeChars(c.getSubject());
            raf.writeInt(c.getNumStu());
            raf.writeInt(c.getGrade());
            raf.writeDouble(c.getAverage());
            raf.writeChar(c.getUC());
            raf.writeBoolean(c.isInFrench());

            return c;
        }

    }
}
