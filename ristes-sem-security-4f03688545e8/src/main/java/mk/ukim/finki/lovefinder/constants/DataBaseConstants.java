/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mk.ukim.finki.lovefinder.constants;

/**
 *
 * @author Aleksandar
 */
public class DataBaseConstants {
    
    public static final String DB_NAME_STRING="jdbc:hsqldb:file:C:\\Users\\Aleksandar\\Documents\\NetBeansProjects\\DBS2\\db1 [aceslaf on Default schema";
//    public static final String URL = "jdbc:hsqldb:file:C:\\Users\\Aleksandar\\Documents\\NetBeansProjects\\DBS\\db1.tmp;shutdown=true";
//    public static final String URL = "jdbc:hsqldb:file:C:\\Users\\Aleksandar\\Desktop\\BAZA\\db1.tmp;shutdown=true";
    
    
//    public static final String DB_NAME_STRING="jdbc:hsqldb:file:C:\\Users\\Aleksandar\\Documents\\NetBeansProjects\\DBS2 [aceslaf on PUBLIC]";
//    public static final String URL = "jdbc:hsqldb:file:C:\\Users\\Aleksandar\\Documents\\NetBeansProjects\\DBS2\\wtdf.pdf";
    
//    
//    public static final String USER = "aceslaf";
//    public static final String PASS = "password";
//    public static final String CLASS_NAME="org.hsqldb.jdbcDriver";
    
    public static final String URL = "jdbc:mysql://194.149.138.7:3306/applegame";
    
    
    public static final String USER = "appleuser";
    public static final String PASS = "applegame";
    public static final String CLASS_NAME="com.mysql.jdbc.Driver";
    
    
    
    
    
    
    
    
    
    public static final String GENERATE_HOTSPOT_TABLE=" CREATE TABLE PUBLIC.HOTSPOT " +
                                                      " (UD INTEGER GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) NOT NULL, " +
                                                      " NAME VARCHAR(300), " +
                                                      " SSID VARCHAR(100) NOT NULL, " +
                                                      " PRIMARY KEY (UD)) ";
    
    public static final String GENERATE_IS_ACTIVE_HOTSPOT=" CREATE TABLE PUBLIC.IS_ACTIVE_HOTSPOT " +
                                                          " (ID INTEGER NOT NULL IDENTITY, " +
                                                          " USERS_ID_ID INTEGER NOT NULL, " +
                                                          " HOTSPOT_ID INTEGER NOT NULL, " +
                                                          " PRIMARY KEY (ID)) ";
    
    public static final String GENERATE_USERS_ID_TABLE=" CREATE TABLE PUBLIC.USERIDS " +
                                                          " (ID INTEGER GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) NOT NULL, " +
                                                          " MY_USER_ID INTEGER, " +
                                                          " ACTIVE BOOLEAN, " +
                                                          " LAST_LOGIN BIGINT, " +
                                                          " GOOGLE VARCHAR(500) NOT NULL, " +
                                                          " PRIMARY KEY (ID)) ";
}
