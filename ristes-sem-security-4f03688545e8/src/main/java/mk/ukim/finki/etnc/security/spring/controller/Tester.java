/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mk.ukim.finki.etnc.security.spring.controller;

import com.google.gson.Gson;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import mk.ukim.finki.lovefinder.dataholders.Hotspot;
import mk.ukim.finki.lovefinder.dataholders.NearByUser;
import mk.ukim.finki.lovefinder.dataholders.OurGcmMessage;
import mk.ukim.finki.lovefinder.dataholders.ResponseWrapper;
import mk.ukim.finki.lovefinder.dataholders.UpdateRequest;
import mk.ukim.finki.lovefinder.dataholders.User;
import mk.ukim.finki.lovefinder.dataholders.UserAndHotspots;
import mk.ukim.finki.lovefinder.dataholders.UserIDs;
import mk.ukim.finki.lovefinder.dbUtilities.dbHelper;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
 
import javax.net.ssl.HttpsURLConnection;
import mk.ukim.finki.lovefinder.constants.GCMCOnstants;
import mk.ukim.finki.lovefinder.constants.UrlPathConstants;

/**
 *
 * @author Aleksandar
 */
public class Tester {

    public static void main(String[] args) {
//        Connection conn = null;
//        Statement stmt=null,stmt2=null,stmt1 = null;
//        try {
//            String URL = "jdbc:hsqldb:file:C:\\Users\\Aleksandar\\Documents\\NetBeansProjects\\DBS\\db1.tmp;shutdown=true";
//            String USER = "aceslaf";
//            String PASS = "password";
//            Class.forName("org.hsqldb.jdbcDriver");
//            conn = DriverManager.getConnection(URL, USER, PASS);
//            stmt = conn.createStatement();
////            String sql = "CREATE TABLE PUBLIC.OUR_USERS" +
////                         "(ID INTEGER GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) NOT NULL," +
////                         "INFO VARCHAR(1000)," +
////                         "PRIMARY KEY (ID))";
////            stmt.executeQuery(sql);
//            
//            stmt = conn.createStatement();
////            String sql1 = "INSERT INTO \"OUR_USERS\" (\"USERS_ID_KEY\", \"USERS_INFO_JASON\") VALUES (3, 'testis');";
//            String sql1 = "INSERT INTO PUBLIC.OUR_USERS (USERS_ID_KEY, USER_INFO_JASON) VALUES (3, 'testis')";
//            stmt.execute(sql1);
//            
//           
//            System.out.println("Database created successfully...");
//        } catch (SQLException se) {
//            //Handle errors for JDBC
//            se.printStackTrace();
//        } catch (Exception e) {
//            //Handle errors for Class.forName
//            e.printStackTrace();
//        } finally {
//            //finally block used to close resources
//            try {
//                if (stmt != null) {
//                    stmt.close();
//                }
//            } catch (SQLException se2) {
//            }// nothing we can do
//            try {
//                if (conn != null) {
//                    conn.close();
//                }
//            } catch (SQLException se) {
//                se.printStackTrace();
//
//            }
//        }
        
        
        
        
        
        
        
        
        
        
        
        
        
//        dbHelper dbHelper=new dbHelper();
//       
//        

       
//        System.out.println("    " + new Date());
//        helper.updateTime(user, System.currentTimeMillis());
//        System.out.println("    " + new Date());
//        

//        
//        
//        System.out.println("    " + new Date());
//        helper.pruneInactiveUsers(System.currentTimeMillis());
//        System.out.println("    " + new Date());
 
        
        
        
        
 //        System.out.println("####################################################");
//        System.out.println("#                                                  #");
//        System.out.println("#                 COFIE BREAK                      #");
//        System.out.println("#                                                  #");
//        System.out.println("####################################################");
//        System.out.println("####################################################");
//        System.out.println("#                                                  #");
//        System.out.println("#                 find users test                  #");
//        System.out.println("#                                                  #");
//        System.out.println("####################################################");
        
//         UpdateRequest request=new UpdateRequest();
//         request.setUser(user);
//         request.setHotspots(hsList);
//         
//        
//        if(request.getHotspots()==null){
//           request.setHotspots(new ArrayList<Hotspot>());
//        }
//        
//        List<UserAndHotspots> usersAndHotspots=dbHelper.getCloseUsers(request.getFilter(),request.getHotspots(),request.getUser(),System.currentTimeMillis());
//       
//        TreeSet<String> requesterHotspots=new TreeSet<String>();
//        for(Hotspot reqHotspot:request.getHotspots()){        
//              requesterHotspots.add(reqHotspot.getSSID());
//        }
//        
//        HashMap<UserIDs,Integer> matchesMap=new HashMap<UserIDs, Integer>();
//        for(UserAndHotspots userAndHotspots:usersAndHotspots){
//            UserIDs userID=userAndHotspots.getUserID();
//            matchesMap.put(userID, 0);
//            int curUserMatches=0;
//            for(Hotspot hotspot:userAndHotspots.getHotspots()){
//                if(requesterHotspots.contains(hotspot.getSSID())){
//                   curUserMatches++;
//                }
//            }
//            matchesMap.put(userID, curUserMatches);
//        }
//        
//        
//        ArrayList<NearByUser> nearbyFriends=new ArrayList<NearByUser>(matchesMap.size());
//        Iterator<Map.Entry<UserIDs, Integer>> iterator = matchesMap.entrySet().iterator();
//        while(iterator.hasNext()){
//            Map.Entry<UserIDs, Integer> entry = iterator.next();
//            NearByUser comparableUserWrapper=new NearByUser(entry.getKey(),entry.getValue());
//            nearbyFriends.add(comparableUserWrapper);
//        }
//        
//        Collections.sort(nearbyFriends);
//        Collections.reverse(nearbyFriends);
//        
//        ArrayList<UserIDs> rsult=new ArrayList<UserIDs>();
//        for(NearByUser nearByYser: nearbyFriends){
//            rsult.add(nearByYser.getUserID());
//        }
//        ResponseWrapper wraper=new ResponseWrapper();
//        wraper.setNearByYsers(rsult);
//        dbHelper.updateTime(request.getUser(), System.currentTimeMillis());
        
        
        
        
//        System.out.println("####################################################");
//        System.out.println("#                                                  #");
//        System.out.println("#                 COFIE BREAK                      #");
//        System.out.println("#                                                  #");
//        System.out.println("####################################################");
        //        System.out.println("####################################################");
//        System.out.println("#                                                  #");
//        System.out.println("#                insert/updateUser test            #");
//        System.out.println("#                                                  #");
//        System.out.println("####################################################");
        
        
//        UserIDs thirdUses=new UserIDs();
//        thirdUses.setGoogle("dddddddddddddddddddAAAAdddAAAdAAAAAdddd");
//        dbHelper.insertUser(thirdUses);
//        
        
        
        
        //        System.out.println("####################################################");
//        System.out.println("#                                                  #");
//        System.out.println("#                 COFIE BREAK                      #");
//        System.out.println("#                                                  #");
//        System.out.println("####################################################");
        //        System.out.println("####################################################");
//        System.out.println("#                                                  #");
//        System.out.println("#                make new db                       #");
//        System.out.println("#                                                  #");
//        System.out.println("####################################################");
        
      
//        dbHelper.pruneInactiveUsers(System.currentTimeMillis());
        
//        UserIDs ids=new UserIDs();
//        ids.setGoogle("adddopeosidus");
//        ids.setInstalationID("dddddd))))");
//        new dbHelper().insertUser(ids);
        
       
//       OurGcmMessage gcm=new Gson().fromJson("{\"content\":\"probna poraka\",\"ToUID\":{\"google\":\"karatekid\",\"instalationID\":\"APA91bETs55Mms9UfK6xkysw4ZgpBSc0FrvgXhVPAUTHAAzBQI36Dd2EVVJhSCNFE_aJYr9UETEzm6BWX4quA9HRGSqhLiEUWWUK42tvnVmGsA6ZnIyR5IHxbQA-BelGWxFI_8wTLQnYQv5et4CQBE00Uc7oJA9BlQ\",\"ID\":0},\"fromUID\":{\"google\":\"karatekid\",\"instalationID\":\"APA91bETs55Mms9UfK6xkysw4ZgpBSc0FrvgXhVPAUTHAAzBQI36Dd2EVVJhSCNFE_aJYr9UETEzm6BWX4quA9HRGSqhLiEUWWUK42tvnVmGsA6ZnIyR5IHxbQA-BelGWxFI_8wTLQnYQv5et4CQBE00Uc7oJA9BlQ\",\"ID\":0}}", OurGcmMessage.class);
//           if(gcm==null){
//             return;
//           }
     
//           GCMHAndler gcmHandler=new GCMHAndler();
//           OurGcmMessage msg=new OurGcmMessage("Ogi, ako go gledash ova pushi kur .!.    8=====D");
//           UserIDs uid=new UserIDs();
//                   uid.setGoogle("karateManija");
//                   uid.setInstalationID("APA91bHMHT4W73BIVW5pYcq-cZpsQxdr8KJ4W2Ysuh47qjW3MLdYcTMAFg1DKsF1wFKKs8duNf0_bLMhoSeifzDcGWTs4U29OyJzacbayN8vyrc6GDjPGJ-tMDDPz977swHB6Ags56cWX1v8FpiqDSJ-feDfbWdFQA");
//           msg.setFromUID(uid);
//           msg.setToUID(uid);
//          String msgAsString=new Gson().toJson(msg);
//           gcmHandler.sendMsgViaPost(GCMCOnstants.API_KEY, msg);
        
//    
        
        
      //  LoginController ctrl=new LoginController();
        //ctrl.checkInUser("{\"UserID\":{\"skype\":\"karate_manija\",\"facebook\":\"karate_manija@hotmail.com\",\"google\":\"karate.manija@gmail.com\",\"instalationID\":\"APA91bFTkbA7dSPGZ7KAbk78PjycgEtBOd8TEHbeAVNPyRA_4tfcTvRIstbx2iR-u4zOGvdlabfzX42FVYnRXFGJMinLUHcqqMe-583z9_QpNzu8N9Qm5KaTAb_vmhP4aBFi7rwCZckJEjUObArZr2CbMegQViABlg\",\"ID\":0},\"reqType\":\"NO_CHANGE\"}");
       // ctrl.findNearByFriends(null);
      
   //     ctrl.updateTime("{\"UserID\":{\"skype\":\"karate_manija\",\"facebook\":\"karate_manija@hotmail.com\",\"google\":\"karate.manija@gmail.com\",\"instalationID\":\"APA91bGglEqVacTUXcAuBvIZ3J7tU9d8jknkmZc1a2QPcq2DQOGEUl1rW6PJCLB9qRDPBJ-AY7l2fByWGhdppIHWjTe_SdHw1V97bX0h5Nh02nEIZGBGpuJDxhsSBISI56JSQ1Gp57OmWv9kjaWUbsTrcO4UuUabjg\",\"ID\":0},\"reqType\":\"NO_CHANGE\"}");
       // ctrl.updateTime("{\"UserID\":{\"skype\":\"karate_manija\",\"facebook\":\"karate_manija@hotmail.com\",\"google\":\"karate.manija@gmail.com\",\"instalationID\":\"APA91bGglEqVacTUXcAuBvIZ3J7tU9d8jknkmZc1a2QPcq2DQOGEUl1rW6PJCLB9qRDPBJ-AY7l2fByWGhdppIHWjTe_SdHw1V97bX0h5Nh02nEIZGBGpuJDxhsSBISI56JSQ1Gp57OmWv9kjaWUbsTrcO4UuUabjg\",\"ID\":0},\"hotspots\":[{\"SSID\":\"54:89:98:28:53:d1\",\"Name\":\"Karate-Kid\",\"ID\":0},{\"SSID\":\"64:70:02:e1:5b:44\",\"Name\":\"Konstantin\",\"ID\":0},{\"SSID\":\"08:19:a6:1c:a8:1e\",\"Name\":\"Ugrinovska\",\"ID\":0},{\"SSID\":\"70:54:f5:cc:17:15\",\"Name\":\"Mihajlovski\",\"ID\":0},{\"SSID\":\"00:24:fe:90:0c:59\",\"Name\":\"LILJANA\",\"ID\":0},{\"SSID\":\"64:66:b3:77:c1:a0\",\"Name\":\"TP-LINK_77C1A0\",\"ID\":0},{\"SSID\":\"04:f9:38:65:8f:8c\",\"Name\":\"HUAWEI-P62Y\",\"ID\":0}],\"reqType\":\"CHANGE\"}");
       // ctrl.sendPrivateMessage("{\"content\":\"vjfsfhd\",\"ToUID\":{\"google\":\"karate.manija@gmail.com\",\"ID\":0},\"fromUID\":{\"skype\":\"karate_manija\",\"facebook\":\"karate_manija@hotmail.com\",\"google\":\"karate.manija@gmail.com\",\"instalationID\":\"APA91bGglEqVacTUXcAuBvIZ3J7tU9d8jknkmZc1a2QPcq2DQOGEUl1rW6PJCLB9qRDPBJ-AY7l2fByWGhdppIHWjTe_SdHw1V97bX0h5Nh02nEIZGBGpuJDxhsSBISI56JSQ1Gp57OmWv9kjaWUbsTrcO4UuUabjg\",\"ID\":0}}");
//         UserIDs ids=new UserIDs();
//        ids.setGoogle("adddopeosidus");
//        ids.setInstalationID("APA91bGglEqVacTUXcAuBvIZ3J7tU9d8jknkmZc1a2QPcq2DQOGEUl1rW6PJCLB9qRDPBJ-AY7l2fByWGhdppIHWjTe_SdHw1V97bX0h5Nh02nEIZGBGpuJDxhsSBISI56JSQ1Gp57OmWv9kjaWUbsTrcO4UuUabjg");
//        OurGcmMessage gcmMassage=new OurGcmMessage("ogi pushi kur .!.  8===D 2 pat");
//        gcmMassage.setFromUID(ids);
//        gcmMassage.setToUID(ids);
        
    //   new GCMHAndler().sendMsgViaPost(GCMCOnstants.API_KEY, gcmMassage );
//        dbHelper dbHelper1 =new dbHelper();
//                UserIDs user=new UserIDs();
//        user.setGoogle("ddddda");
//        user.setInstalationID("aaaa");
//        List<Hotspot> hsList=new ArrayList<Hotspot>();
//        Hotspot hs;
//
//        
////        hs=new Hotspot();
////        hs.setSSID("7");
////        hsList.add(hs);  
//         hs=new Hotspot();
//        hs.setSSID("8");
//        hsList.add(hs); 
//        hs=new Hotspot();
//        hs.setSSID("9");
//        hsList.add(hs);      
//        hs=new Hotspot();
//        hs.setSSID("10");
//        hsList.add(hs);      
//        hs=new Hotspot();
//        hs.setSSID("11");
//        
//        hsList.add(hs);
//        dbHelper1.insertUser(user);
//        //dbHelper1.updateTime(user, System.currentTimeMillis());
//        UpdateRequest req=new UpdateRequest();
//        req.setUser(user);
//        req.setHotspots(hsList);
//        dbHelper1.updateUserLocation(user, hsList, System.currentTimeMillis());
//          dbHelper1.getCloseUsers(null, hsList, user, System.currentTimeMillis());
//        System.out.println(System.currentTimeMillis());
        LoginController ctrl=new LoginController();
        ctrl.findNearByFriends("{\"UserID\":{\"google\":\"ime.prezime.majmunce@gmail.com\",\"instalationID\":\"APA91bHMHT4W73BIVW5pYcq-cZpsQxdr8KJ4W2Ysuh47qjW3MLdYcTMAFg1DKsF1wFKKs8duNf0_bLMhoSeifzDcGWTs4U29OyJzacbayN8vyrc6GDjPGJ-tMDDPz977swHB6Ags56cWX1v8FpiqDSJ-feDfbWdFQA\",\"ID\":0},\"hotspots\":[{\"SSID\":\"54:89:98:28:53:d1\",\"Name\":\"Karate-Kid\",\"ID\":0},{\"SSID\":\"08:19:a6:1c:a8:1e\",\"Name\":\"Ugrinovska\",\"ID\":0},{\"SSID\":\"64:70:02:e1:5b:44\",\"Name\":\"Konstantin\",\"ID\":0},{\"SSID\":\"00:24:fe:90:0c:59\",\"Name\":\"LILJANA\",\"ID\":0},{\"SSID\":\"54:89:98:29:0b:b8\",\"Name\":\"Kalina\",\"ID\":0}],\"reqType\":\"CHANGE\"}");
    }
  
}
