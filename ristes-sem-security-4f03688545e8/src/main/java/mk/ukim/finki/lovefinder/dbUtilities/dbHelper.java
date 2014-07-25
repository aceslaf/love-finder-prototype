/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mk.ukim.finki.lovefinder.dbUtilities;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import mk.ukim.finki.lovefinder.constants.DataBaseConstants;
import mk.ukim.finki.lovefinder.dataholders.Hotspot;
import mk.ukim.finki.lovefinder.dataholders.User;
import mk.ukim.finki.lovefinder.dataholders.UserAndHotspots;
import mk.ukim.finki.lovefinder.dataholders.UserIDs;
import mk.ukim.finki.lovefinder.interfaces.checkInInterfaces.IDbAdapter;

/**
 *
 * @author Aleksandar
 */
public class dbHelper implements IDbAdapter {

   
    private List<Hotspot> getHotspotsForUser(UserIDs userID, Connection conn) {

        List<Hotspot> lst = new ArrayList<Hotspot>();
        try {
            conn = getConection();
            Statement stmt;
            stmt = conn.createStatement();

            String sql1 = "SELECT * FROM applegame.USERIDS WHERE GOOGLE = '" + userID.getGoogle() + "' ;";

            ResultSet rs = stmt.executeQuery(sql1);
            int userRealID = 1;
            boolean suces = false;
            while (rs.next()) {
                userRealID = rs.getInt("ID");
                suces = true;
            }
            if (!suces) {
                throw new SQLException("no such user");
            }



            stmt = conn.createStatement();
            String getHotspots = " SELECT applegame.HOTSPOT.NAME AS NAME, applegame.HOTSPOT.SSID AS SSID"
                    + " FROM applegame.HOTSPOT, applegame.IS_ACTIVE_HOTSPOT"
                    + " WHERE applegame.HOTSPOT.ID = applegame.IS_ACTIVE_HOTSPOT.HOTSPOT_ID and applegame.IS_ACTIVE_HOTSPOT.USERS_ID_ID = " + userRealID + ";";
            rs = stmt.executeQuery(getHotspots);
            while (rs.next()) {
                Hotspot hs = new Hotspot();
                hs.setName(rs.getString("NAME"));
                hs.setSSID(rs.getString("SSID"));
                lst.add(hs);
            }



        } catch (SQLException ex) {
            Logger.getLogger(dbHelper.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return lst;
        }


    }

    @Override
    public List<UserAndHotspots> getCloseUsers(Object filter, List<Hotspot> hotspots, UserIDs homeUser,long systemTime) {
        Connection conn=null;
        //list of users scaned ssid
        TreeSet<String> goalSSID = new TreeSet<String>();
        for (Hotspot hts : hotspots) {
            goalSSID.add(hts.getSSID());
        }
         Map<Integer, UserIDs> IDToUsersIDs = new HashMap<Integer, UserIDs>();
         TreeSet<Integer> nearUsers = new TreeSet<Integer>();
         Map<Integer, Hotspot> IDToHotspot = new HashMap<Integer, Hotspot>();
        
        //List<UserIDs> closePeople = new ArrayList<UserIDs>();

        //result
        List<UserAndHotspots> res = new ArrayList<UserAndHotspots>();

        try {
            conn = getConection();
            Statement stmt;            
            FindCloseUsersAlgorithm finder=new FindCloseUsersAlgorithm(conn, homeUser, goalSSID, IDToUsersIDs, nearUsers, IDToHotspot);
            res=finder.doWork();
           
//            mapIDsToHotspot(conn, IDToHotspot);
//            mapIDsToUsers(conn, IDToUsersIDs);
//            mapUsefullConnections(conn, IDToHotspot, goalSSID, IDToUsersIDs, homeUser, nearUsers);
//            filterResults(nearUsers, IDToUsersIDs, conn, res);




        } catch (SQLException ex) {
            Logger.getLogger(dbHelper.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(conn!=null){
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(dbHelper.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return res;
        }
    }

    @Override
    public void updateUserLocation(UserIDs user, List<Hotspot> hotspots, long systemTime) {
         Connection con=null;
        try{
            con=getConection();
            HashSet<String> ssidSet=new HashSet<String>();
            Map<String,Hotspot> ssIdToHotspot=new HashMap<String, Hotspot>(); 
            
            for(Hotspot hs:hotspots){
               ssidSet.add(hs.getSSID());
               ssIdToHotspot.put(hs.getSSID(), hs);
            }
            
            int dID=0;
            dID=getUserIDByAcount(con, user.getGoogle());
            //delete all old hotspot connections;
            Statement stm=con.createStatement();
            String   deleteString=" DELETE FROM applegame.IS_ACTIVE_HOTSPOT "+
                                  " WHERE applegame.IS_ACTIVE_HOTSPOT.USERS_ID_ID="+dID+";";
            stm.execute(deleteString);
            stm.close();
            
            stm=con.createStatement();
            StringBuilder bracelessList=new StringBuilder();
            for(String ssid:ssidSet){
               bracelessList.append("'");
               bracelessList.append(ssid);
               bracelessList.append("'");
               bracelessList.append(",");
            }
            bracelessList.deleteCharAt(bracelessList.lastIndexOf(","));
            
            String findOld= " SELECT applegame.HOTSPOT.SSID AS SSID, applegame.HOTSPOT.ID AS ID "+
                            " FROM applegame.HOTSPOT "+
                            " WHERE applegame.HOTSPOT.SSID IN ("+bracelessList+") ;";
           ResultSet rs=stm.executeQuery(findOld);
           while(rs.next()){
                ssIdToHotspot.get(rs.getString("SSID")).setID(rs.getInt("ID"));
           }
           rs.close();
           stm.close();
           //add new hotspots if needed
           for(Hotspot hs:hotspots){
              if(hs.getID()>0){
                  continue;
              }
              
              String inserNewHotspot="INSERT INTO applegame.HOTSPOT (ID, NAME, SSID) VALUES (null,'"+hs.getSSID()+"', '"+hs.getSSID()+"');";              
              Statement s=con.createStatement();
              int succes = s.executeUpdate(inserNewHotspot);        
              s.close();
              
              hs.setID(getHotspotID(con, hs.getSSID()));
               
           }
           
           // insert new IS_ADCTIVE_CONNECTIONS;
           for(Hotspot hs:hotspots){
              Statement s=con.createStatement();
              String insertConnection="INSERT INTO applegame.IS_ACTIVE_HOTSPOT (ID, USERS_ID_ID, HOTSPOT_ID) VALUES (null, "+dID+", "+hs.getID()+");";
              int succesfull = s.executeUpdate(insertConnection);
               s.close();
           }
           
            
         
        }catch(ArrayIndexOutOfBoundsException rx){
        
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(dbHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(dbHelper.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
           if(con!=null){
               try {
                   con.close();
               } catch (SQLException ex) {
                   Logger.getLogger(dbHelper.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
        }
    }
    
    @Override
    public String getReqIdForAcount(String googleUserId){
        Connection con=null;
        String regId=null;
         try{
            con=getConection();
//            int currTime=(int)systemTime;
//            //deal with overflow in a very very bad manner
//            if(currTime<0){
//               currTime=Integer.MAX_VALUE; 
//            }
//            
            int dID=getUserIDByAcount(con, googleUserId);             
            
            String getRegIDStatement="SELECT  applegame.USERIDS.REGID AS REGID FROM applegame.USERIDS  WHERE ID ="+dID+";";
            Statement stm=con.createStatement();
            ResultSet rs=stm.executeQuery(getRegIDStatement);
            
            if(rs.next()){
              regId=rs.getString("REGID");              
            }
            rs.close();
            stm.close();
            
        }catch(ArrayIndexOutOfBoundsException rx){
        
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(dbHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(dbHelper.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
           if(con!=null){
               try {
                   con.close();
               } catch (SQLException ex) {
                   Logger.getLogger(dbHelper.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
        }
        return regId;
    }
    
    @Override
    public void updateTime(UserIDs user, long systemTime) {
             Connection con=null;
        try{
            con=getConection();
//            int currTime=(int)systemTime;
//            //deal with overflow in a very very bad manner
//            if(currTime<0){
//               currTime=Integer.MAX_VALUE; 
//            }
//            
            int dID=getUserIDByAcount(con, user.getGoogle());             
            String updateStatement="UPDATE applegame.USERIDS SET LAST_LOGIN = '"+systemTime+"',  ACTIVE = true WHERE ID ="+dID+";";
            Statement stm=con.createStatement();
            stm.execute(updateStatement);
            stm.close();
            
        }catch(ArrayIndexOutOfBoundsException rx){
        
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(dbHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(dbHelper.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
           if(con!=null){
               try {
                   con.close();
               } catch (SQLException ex) {
                   Logger.getLogger(dbHelper.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
        }
    
    }

    @Override
    public void pruneInactiveUsers(long sysTime) {
     
                Connection con=null;
        try{
            con=getConection();
            int diference=10000;
            long border=sysTime-diference;
            String pruneStatement=      "  SELECT ID  FROM applegame.USERIDS "+
                                        " WHERE applegame.USERIDS.LAST_LOGIN < '"+border+"' ;";

           Statement stmt=con.createStatement();
           ResultSet rs=stmt.executeQuery(pruneStatement);
           List<Integer> idsToBePruned=new ArrayList<Integer>();
           while(rs.next()){
               int id=rs.getInt("ID");
               idsToBePruned.add(id);
           }
           rs.close();
           stmt.close();
           for(int id : idsToBePruned){
              String updateString="UPDATE applegame.USERIDS "+
                                  "SET applegame.USERIDS.ACTIVE = false WHERE applegame.USERIDS.ID= '"+id+"' ; "; 
              Statement st=con.createStatement();
              st.execute(updateString);
              st.close();              
           }
            
        }catch(ArrayIndexOutOfBoundsException rx){
        
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(dbHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(dbHelper.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
           if(con!=null){
               try {
                   con.close();
               } catch (SQLException ex) {
                   Logger.getLogger(dbHelper.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
        }
    
    }                        

    @Override
    public User getUser(UserIDs uid) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Connection getConection() throws ClassNotFoundException, SQLException {
//        String URL = "jdbc:hsqldb:file:C:\\Users\\Aleksandar\\Documents\\NetBeansProjects\\DBS\\db1.tmp;shutdown=true";
//        String USER = "aceslaf";
//        String PASS = "password";
//        Class.forName("org.hsqldb.jdbcDriver");
        
        Class.forName(DataBaseConstants.CLASS_NAME);
        return DriverManager.getConnection(DataBaseConstants.URL, DataBaseConstants.USER, DataBaseConstants.PASS);
    }

    
    private int getHotspotID(Connection conn,String SSID) throws SQLException{
        
        String getID=" SELECT applegame.HOTSPOT.ID AS ID FROM applegame.HOTSPOT WHERE applegame.HOTSPOT.SSID = '"+SSID+"' ;";
        Statement stm=conn.createStatement();
        ResultSet rs=stm.executeQuery(getID);
        int res=-1;
        while(rs.next()){
            res = rs.getInt("ID");
        }
        rs.close();
        stm.close();
        if(res==-1){
           Logger.getLogger(dbHelper.class.getName()).log(Level.SEVERE, null, new Exception("brat ovoj hotspot vrakja ID -1"));
        }
        return res;
    }

    private int getUserIDByAcount(Connection conn,String GOOGLE) throws SQLException{
           int uid=-1;
           Statement stmt= conn.createStatement();                      
           String uidGeter=" SELECT applegame.USERIDS.ID AS ID "+
                           " FROM applegame.USERIDS "+ 
                           " WHERE applegame.USERIDS.GOOGLE= '"+GOOGLE+"' ;";
           ResultSet rs=stmt.executeQuery(uidGeter);
           while(rs.next()){
             uid=rs.getInt("ID");
           }
           rs.close();
           stmt.close();
           return  uid;
    }

    @Override
    public void initDbTables() {
                Connection con=null;
        try{
            con=getConection();
           

           Statement stmt=con.createStatement();
           stmt.execute(DataBaseConstants.GENERATE_HOTSPOT_TABLE);
           stmt.close();
           
           stmt=con.createStatement();
           stmt.execute(DataBaseConstants.GENERATE_IS_ACTIVE_HOTSPOT);
           stmt.close();
            
           
           stmt=con.createStatement();
           stmt.execute(DataBaseConstants.GENERATE_USERS_ID_TABLE);
           stmt.close();
           
        }catch(ArrayIndexOutOfBoundsException rx){
        
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(dbHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(dbHelper.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
           if(con!=null){
               try {
                   con.close();
               } catch (SQLException ex) {
                   Logger.getLogger(dbHelper.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
        }
    
    }

    @Override
    public void insertUser(UserIDs user) {
        
        
              Connection con=null;
        try{
            con=getConection();
            int uid=getUserIDByAcount(con, user.getGoogle());
            String statementString;
            if(uid>0){
               statementString=" UPDATE applegame.USERIDS "+
                               " SET ACTIVE = true, "+
                               " LAST_LOGIN = '"+System.currentTimeMillis()+"' , "+
                               " REGID = '"+ user.getInstalationID()+"' , "+
                               " GOOGLE = '"+ user.getGoogle()+"'  "+                                 
                               " WHERE ID = '"+uid+"'";
            }else{
               statementString=" INSERT INTO applegame.USERIDS (ID, MY_USER_ID, ACTIVE, LAST_LOGIN, GOOGLE,REGID) "+
                               " VALUES (null, null, TRUE,'"+System.currentTimeMillis()+"' , '"+user.getGoogle()+"','"+user.getInstalationID()+"');";
            
            }
            
           Statement stm=con.createStatement();
           stm.execute(statementString);
           stm.close();
                     
            
        }catch(ArrayIndexOutOfBoundsException rx){
        
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(dbHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(dbHelper.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
           if(con!=null){
               try {
                   con.close();
               } catch (SQLException ex) {
                   Logger.getLogger(dbHelper.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
        }
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    private class FindCloseUsersAlgorithm { 

        public FindCloseUsersAlgorithm(Connection conn, UserIDs homeUser, TreeSet<String> goalSSID, Map<Integer, UserIDs> IDToUsersIDs, TreeSet<Integer> nearUsers, Map<Integer, Hotspot> IDToHotspot) {
            this.conn = conn;
            this.homeUser = homeUser;
            this.goalSSID = goalSSID;
            this.IDToUsersIDs = IDToUsersIDs;
            this.nearUsers = nearUsers;
            this.IDToHotspot = IDToHotspot;
        }
        
        Connection conn;
        UserIDs homeUser;
        TreeSet<String> goalSSID;
        Map<Integer, UserIDs> IDToUsersIDs ;
        TreeSet<Integer> nearUsers ;
        Map<Integer, Hotspot> IDToHotspot ;
        
//    private void filterResults(TreeSet<Integer> userIDs, Map<Integer, UserIDs> IDToUsersIDs, Connection conn, List<UserAndHotspots> res) {
//        for (Integer userID : userIDs) {
//            UserAndHotspots userAndHotspots = new UserAndHotspots();
//            UserIDs uid = IDToUsersIDs.get(userID);
//            userAndHotspots.setUserID(uid);
//            userAndHotspots.setHotspots(getHotspotsForUser(uid, conn));
//            res.add(userAndHotspots);
//        }
//    }
//
//    private void mapIDsToHotspot(Connection conn, Map<Integer, Hotspot> IDToHotspot) throws SQLException {
//        Statement stmt=conn.createStatement();
//        //get all hotspots and put them in  IDtoHotspot
//        String allHotspots = " SELECT PUBLIC.HOTSPOT.ID AS ID, PUBLIC.HOTSPOT.NAME AS NAME, PUBLIC.HOTSPOT.SSID AS SSID "
//                + " FROM PUBLIC.HOTSPOT ;";
//        ResultSet rs = stmt.executeQuery(allHotspots);
//        while (rs.next()) {
//            Hotspot hs = new Hotspot();
//            hs.setSSID(rs.getString("SSID"));
//            hs.setName(rs.getString("NAME"));
//            IDToHotspot.put(rs.getInt("ID"), hs);
//        }
//        rs.close();
//        stmt.close();
//    }
//    private void mapIDsToUsers(Connection conn, Map<Integer, UserIDs> IDToUsersIDs) throws SQLException {
//        Statement stmt;
//        ResultSet rs;
//        // get all userIDs and add them in IDtoUSerIDs
//        String allUserIDs = " SELECT  PUBLIC.USERIDS.ACTIVE AS ACTIVE, PUBLIC.USERIDS.ID AS ID, PUBLIC.USERIDS.GOOGLE AS GOOGLE"
//                + " FROM PUBLIC.USERIDS ";
//        stmt = conn.createStatement();
//        rs = stmt.executeQuery(allUserIDs);
//        while (rs.next()) {
//            UserIDs usr = new UserIDs();
//            usr.setActive(rs.getBoolean("ACTIVE"));
//            usr.setID(rs.getInt("ID"));
//            usr.setGoogle(rs.getString("GOOGLE"));
//            IDToUsersIDs.put(rs.getInt("ID"), usr);
//        }
//        rs.close();
//    }
//    
//     //now get All the connections in to a map, if and only if the connections HOTSPOT_ID is in the ones we're interested in, and the coresponding user is ACTIVE
//    private void mapUsefullConnections(Connection conn, Map<Integer, Hotspot> IDToHotspot, TreeSet<String> goalSSID, Map<Integer, UserIDs> IDToUsersIDs, UserIDs homeUser, TreeSet<Integer> nearUsers) throws SQLException {
//        Statement stmt;
//        ResultSet rs;
//       
//        String allConnections = " SELECT  PUBLIC.IS_ACTIVE_HOTSPOT.USERS_ID_ID AS USERS_ID_ID, PUBLIC.IS_ACTIVE_HOTSPOT.HOTSPOT_ID AS HOTSPOT_ID"
//                + " FROM PUBLIC.IS_ACTIVE_HOTSPOT ; ";
//        stmt = conn.createStatement();
//        rs = stmt.executeQuery(allConnections);
//        while (rs.next()) {
//            Hotspot targetHotspot = IDToHotspot.get(rs.getInt("HOTSPOT_ID"));
//            if (targetHotspot == null) {
//                continue;
//            }
//            if (!goalSSID.contains(targetHotspot.getSSID())) {
//                continue;
//            }
//            UserIDs uids = IDToUsersIDs.get(rs.getInt("USERS_ID_ID"));
//            if (uids == null) {
//                continue;
//            }
//            if (!uids.isActive()) {
//                continue;
//            }
//            
//            if(uids.getGoogle().equals(homeUser.getGoogle())){                
//              continue;
//            }
//
//            Hotspot hs = new Hotspot();
//            nearUsers.add(rs.getInt("USERS_ID_ID"));
//
//        }
//        rs.close();
//        stmt.close();
//    }
        public List<UserAndHotspots> doWork() throws SQLException{
            List<UserAndHotspots> res = new ArrayList<UserAndHotspots>();
              mapIDsToHotspot();
              mapIDsToUsers();
              mapUsefullConnections();
              filterResults(res);
            return res;
        }
      private void filterResults(List<UserAndHotspots> res) {
        TreeSet<Integer> userIDs=nearUsers;  
        for (Integer userID : userIDs) {
            UserAndHotspots userAndHotspots = new UserAndHotspots();
            UserIDs uid = IDToUsersIDs.get(userID);
            userAndHotspots.setUserID(uid);
            userAndHotspots.setHotspots(getHotspotsForUser(uid, conn));
            res.add(userAndHotspots);
        }
    }

    private void mapIDsToHotspot() throws SQLException {
        Statement stmt=conn.createStatement();
        //get all hotspots and put them in  IDtoHotspot
        String allHotspots = " SELECT applegame.HOTSPOT.ID AS ID, applegame.HOTSPOT.NAME AS NAME, applegame.HOTSPOT.SSID AS SSID "
                + " FROM applegame.HOTSPOT ;";
        ResultSet rs = stmt.executeQuery(allHotspots);
        while (rs.next()) {
            Hotspot hs = new Hotspot();
            hs.setSSID(rs.getString("SSID"));
            hs.setName(rs.getString("NAME"));
            IDToHotspot.put(rs.getInt("ID"), hs);
        }
        rs.close();
        stmt.close();
    }
    private void mapIDsToUsers() throws SQLException {
        Statement stmt;
        ResultSet rs;
        // get all userIDs and add them in IDtoUSerIDs
        String allUserIDs = " SELECT  applegame.USERIDS.ACTIVE AS ACTIVE, applegame.USERIDS.ID AS ID, applegame.USERIDS.GOOGLE AS GOOGLE"
                + " FROM applegame.USERIDS ";
        stmt = conn.createStatement();
        rs = stmt.executeQuery(allUserIDs);
        while (rs.next()) {
            UserIDs usr = new UserIDs();
            usr.setActive(rs.getBoolean("ACTIVE"));
            usr.setID(rs.getInt("ID"));
            usr.setGoogle(rs.getString("GOOGLE"));
            IDToUsersIDs.put(rs.getInt("ID"), usr);
        }
        rs.close();
    }
    
     //now get All the connections in to a map, if and only if the connections HOTSPOT_ID is in the ones we're interested in, and the coresponding user is ACTIVE
    private void mapUsefullConnections() throws SQLException {
        Statement stmt;
        ResultSet rs;
       
        String allConnections = " SELECT  applegame.IS_ACTIVE_HOTSPOT.USERS_ID_ID AS USERS_ID_ID, applegame.IS_ACTIVE_HOTSPOT.HOTSPOT_ID AS HOTSPOT_ID"
                + " FROM applegame.IS_ACTIVE_HOTSPOT ; ";
        stmt = conn.createStatement();
        rs = stmt.executeQuery(allConnections);
        while (rs.next()) {
            Hotspot targetHotspot = IDToHotspot.get(rs.getInt("HOTSPOT_ID"));
            if (targetHotspot == null) {
                continue;
            }
            if (!goalSSID.contains(targetHotspot.getSSID())) {
                continue;
            }
            UserIDs uids = IDToUsersIDs.get(rs.getInt("USERS_ID_ID"));
            if (uids == null) {
                continue;
            }
            if (!uids.isActive()) {
                continue;
            }
            
            if(uids.getGoogle().equals(homeUser.getGoogle())){                
              continue;
            }

            Hotspot hs = new Hotspot();
            nearUsers.add(rs.getInt("USERS_ID_ID"));

        }
        rs.close();
        stmt.close();
    }
      
    }
}
