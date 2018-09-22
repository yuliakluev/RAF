/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.hdsb.gwss.yulia.ics4u.databases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;


/**
 *
 * @author yulia
 */
public class ClassRecord {
    
    //access modifier?? Protected?
    private int dbID = -1; 
    static int counter; 
    private String teacher; 
    private String subject; 
    private int numStu; 
    private int grade; 
    private double average; 
    private char uOrC;
    private boolean inFrench;
    final static int LENGTH_SUBJECT = 15;
    final static int LENGTH_TEACHER =12;
    final static int RECORD_SIZE = 73; 

    public ClassRecord( String t, String s , int stu, int grade , double av, char uc , boolean fr  )  {
               
        //overridable. Is this ok?
        this.setTeacher(t);
        this.setSubject(s);
        this.setNumStu(stu);
        this.setGrade(grade); 
        this.setAverage(av); 
        this.setUC(uc);
        this.isInFrench(fr);  
        
    }
    
    //do i need an empty constructor?
    public ClassRecord() {
        this("TBD", "N/A" , -1,-1,0.0,'N', false );
        
    
    }
    //good?
    public boolean isValid(){
        if(this.isValidAverage() && this.isValidGrade() && this.isValidLevel() && this.isValidNum() && this.isValidSubject() && this.isValidTeacher()) return true; 
        return false; 
    }
    public int getID(){
        
        return this.dbID; 
        
    }
    
    public void setID( int i ){
        this.dbID = i; 
    }
    
    public int getRecordSize(){
        return RECORD_SIZE; 
    }
    
    //access modifiers?????
    public String setTeacher( String t){
        
        teacher = this.truncate( t, LENGTH_TEACHER); 
        return teacher; 
      
    }
     
    public String setSubject(String sub){
        subject = this.truncate( sub, LENGTH_SUBJECT);
        return subject; 
    }
    public int setNumStu( int stu){
        
        numStu = stu;
        return numStu; 
    }
    public int setGrade( int gr){
        
        grade = gr;
        return grade; 
    }
    public boolean isValidNum(){
        if( numStu < 0 || numStu > 40) return false; 
        return true; 
        
    }
    public boolean isValidNum( int num){
        if( num < 0 || num > 40) return false; 
        return true; 
        
    }
    public boolean isValidGrade( ){
        
        if( grade < 13 && grade > 8) return true; 
        return false; 
    }
    public boolean isValidGrade( int gr ){
        
        if( gr < 13 && gr > 8) return true; 
        return false; 
    }
    public boolean isValidAverage( ){
        
        if( average < 0 || average > 100) return false; 
        return true; 
    }
    public boolean isValidAverage( double av ){
        
        if( av < 0 || av > 100) return false; 
        return true; 
    }
    public boolean isValidTeacher(){
        if( !this.teacher.equals("") && this.teacher != null) {
            return true; 
        }
        return false; 
    } 
    public boolean isValidTeacher(String t){
        if( !t.equals("") && t != null) {
            return true; 
        }
        return false; 
    } 
    public boolean isValidSubject(){
        if(!this.subject.equals("") && this.subject != null){
            return true; 
        }
        return false; 
    }
    public boolean isValidSubject( String s){
        if(!s.equals("") && s != null){
            return true; 
        }
        return false; 
    }
    public boolean isValidLevel(){
        
        if( this.uOrC == 'U' || this.uOrC == 'C' || this.uOrC == 'N' ) return true; 
        return false; 
    }
    public boolean isValidLevel(char l){
        
        if( l == 'U' || l == 'C' || l == 'N' ) return true; 
        return false; 
    }
    

    public double setAverage( double av){
        average = av ;
        return average; 
    }
    
    public char setUC( char uc){
        Character.toUpperCase(uc);
        if( uc != 'U' || uc != 'C'){
            uOrC = 'N';
        }
        else{
            uOrC = uc; 
        }
        
        return uc; 
        
    }
    public boolean isInFrench( boolean fr){
        inFrench = fr; 
        return inFrench; 
    }

    public String getTeacher() {
        return teacher;
    }

    public String getSubject() {
        return subject;
    }

    public int getNumStu() {
        return numStu;
    }

    public int getGrade() {
        return grade;
    }

    public double getAverage() {
        return average;
    }

    public char getUC() {
        return uOrC;
    }

    public boolean isInFrench() {
        return inFrench;
    }

    @Override
    public String toString() {
         return "Teacher Name: " + this.teacher + " Subject " +this.subject+ " Number of Students in Class " +this.numStu+ " Grade: " +this.grade+ " Class Average " +this.average+ "University or College (N for neither) " +this.uOrC+ "This Course is Offered in French" +this.inFrench;
    }

    
    
    //to keep a fixed length of strings 
    private String truncate( String str , int n ){
        StringBuilder temp = new StringBuilder();

        if ( str != null ) {
            temp.append( str.trim() );
        } else {
            temp.append( "TBD" );
        }

        // trucates or pads the string
        temp.setLength( n );
        return temp.toString();
    }
    

    
}


