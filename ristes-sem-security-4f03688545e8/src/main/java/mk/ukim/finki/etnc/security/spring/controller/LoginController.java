/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mk.ukim.finki.etnc.security.spring.controller;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import mk.ukim.finki.lovefinder.constants.GCMCOnstants;
import mk.ukim.finki.lovefinder.constants.UrlPathConstants;
import mk.ukim.finki.lovefinder.dataholders.GCMMessage;
import mk.ukim.finki.lovefinder.dataholders.Hotspot;
import mk.ukim.finki.lovefinder.dataholders.NearByUser;
import mk.ukim.finki.lovefinder.dataholders.OurGcmMessage;
import mk.ukim.finki.lovefinder.dataholders.ResponseWrapper;
import mk.ukim.finki.lovefinder.dataholders.UpdateRequest;
import mk.ukim.finki.lovefinder.dataholders.User;
import mk.ukim.finki.lovefinder.dataholders.UserAndHotspots;
import mk.ukim.finki.lovefinder.dataholders.UserIDs;
import mk.ukim.finki.lovefinder.dbUtilities.dbHelper;
import mk.ukim.finki.lovefinder.interfaces.checkInInterfaces.IDbAdapter;
import mk.ukim.finki.lovefinder.interfaces.checkInInterfaces.IFinderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Ognen
 */
@Controller
public class LoginController implements IFinderService
{
    IDbAdapter dbHelper;
    public LoginController() {
        super();
        dbHelper=new dbHelper();
    }
 
    
    @RequestMapping(value = {"/"})
    public String showLoginPage() {
        
        return "Login/login";
    }
     
     
    
    
    // returns sorted list of near by users
    //request.hotspot must not be empty
    //user.getGoogle() must not be null
    @RequestMapping(value = UrlPathConstants.FIND_NEAR_BY_FRIENDS, method = RequestMethod.POST)
    @Override
    public @ResponseBody String findNearByFriends(@RequestBody String requestString) {
        //check if the user has a valid list of hotspots;
        UpdateRequest request=new Gson().fromJson(requestString, UpdateRequest.class);
        
        if(request.getHotspots()==null){
           request.setHotspots(new ArrayList<Hotspot>());
        }
        
        List<UserAndHotspots> usersAndHotspots=dbHelper.getCloseUsers(request.getFilter(),request.getHotspots(),request.getUser(),System.currentTimeMillis());
        INearByFriendsSorter customSorter=new NearByFriendsSorter(request, usersAndHotspots);
        ArrayList<UserIDs> rsult = customSorter.sortNearByFriends();
        ResponseWrapper wraper=new ResponseWrapper();
        wraper.setNearByYsers(rsult);
        dbHelper.updateTime(request.getUser(), System.currentTimeMillis());
        String responce=new Gson().toJson(wraper);
        return responce;
    }
    
    @RequestMapping(value = UrlPathConstants.LOGIN, method = RequestMethod.POST)
    @Override
    
    //Logs in user initially, inserts user in db if not exists
    public void checkInUser(@RequestBody String requestString) {
        UpdateRequest request=new Gson().fromJson(requestString, UpdateRequest.class);
        if(request.getHotspots()==null){
            request.setHotspots(new ArrayList<Hotspot>());
        }
        
        dbHelper.insertUser(request.getUser());
        if(request.getReqType()==UpdateRequest.TYPE_CHANGE){
           dbHelper.updateUserLocation(request.getUser(), request.getHotspots(), System.currentTimeMillis());
        }
        
        dbHelper.updateTime(request.getUser(), System.currentTimeMillis());
    }
     
    
    
    //Changes user active hotspots if req.type==CHANGE, updates last active time unconditionally
    // User.getGoogle() must not be null
    @RequestMapping(value = UrlPathConstants.HEART_BEAT, method = RequestMethod.POST)
    @Override
    public void updateTime(@RequestBody String requestString) {
        UpdateRequest request=new Gson().fromJson(requestString, UpdateRequest.class);
        if(request.getHotspots()==null){
            request.setHotspots(new ArrayList<Hotspot>());
        }
        if(UpdateRequest.TYPE_CHANGE.equals(request.getReqType())){
            dbHelper.updateUserLocation(request.getUser(), request.getHotspots(), System.currentTimeMillis());
        }
        
        dbHelper.updateTime(request.getUser(), System.currentTimeMillis());
        
    }
     
    
    //sets ACTIVE=false for all inactive users
    @RequestMapping(value = UrlPathConstants.PRUNE, method = RequestMethod.GET)
    @Override
    public void pruneActiveUsers() {
        dbHelper.pruneInactiveUsers(System.currentTimeMillis());
    }
  
    
    
    @Override
    public User getUser(@RequestBody String uid) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
    
   
    
    //Find reciever UID, and sends message to him
    //msg.fromUser.getGoogle() must not be null
    //msg.toUser.getGoogle() must not be null
   @RequestMapping(value = UrlPathConstants.MESSAGE, method = RequestMethod.POST) 
   public void sendPrivateMessage(@RequestBody String gcmMessageString){
           OurGcmMessage gcmMessage=new Gson().fromJson(gcmMessageString, OurGcmMessage.class);
           if(gcmMessage==null){
             return;
           }
           GCMHAndler gcmHandler=new GCMHAndler();
           UserIDs reciever=gcmMessage.getToUID();
           if(reciever==null){
             return;
           }
           
           String recieverGoogle=reciever.getGoogle();
           if(reciever.getGoogle()==null){
              return;
           }
           
           String recieverRID=dbHelper.getReqIdForAcount(recieverGoogle);
           reciever.setInstalationID(recieverRID);
           gcmMessage.setToUID(reciever);
           gcmHandler.sendMsgViaPost(GCMCOnstants.API_KEY, gcmMessage);

    
  }
    
  
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   private class NearByFriendsSorter implements INearByFriendsSorter{
        UpdateRequest request;
        List<UserAndHotspots> usersAndHotspots;

        public NearByFriendsSorter(UpdateRequest request, List<UserAndHotspots> usersAndHotspots) {
            this.request = request;
            this.usersAndHotspots = usersAndHotspots;
        }
        @Override
       public ArrayList<UserIDs> sortNearByFriends() {
                TreeSet<String> requesterHotspots=new TreeSet<String>();
                for(Hotspot reqHotspot:request.getHotspots()){        
                      requesterHotspots.add(reqHotspot.getSSID());
                }
                HashMap<UserIDs,Integer> matchesMap=new HashMap<UserIDs, Integer>();
                for(UserAndHotspots userAndHotspots:usersAndHotspots){
                    UserIDs userID=userAndHotspots.getUserID();
                    matchesMap.put(userID, 0);
                    int curUserMatches=0;
                    for(Hotspot hotspot:userAndHotspots.getHotspots()){
                        if(requesterHotspots.contains(hotspot.getSSID())){
                           curUserMatches++;
                        }
                    }
                    matchesMap.put(userID, curUserMatches);
                }
                ArrayList<NearByUser> nearbyFriends=new ArrayList<NearByUser>(matchesMap.size());
                Iterator<Map.Entry<UserIDs, Integer>> iterator = matchesMap.entrySet().iterator();
                while(iterator.hasNext()){
                    Map.Entry<UserIDs, Integer> entry = iterator.next();
                    NearByUser comparableUserWrapper=new NearByUser(entry.getKey(),entry.getValue());
                    nearbyFriends.add(comparableUserWrapper);
                }
                Collections.sort(nearbyFriends);
                Collections.reverse(nearbyFriends);
                ArrayList<UserIDs> rsult=new ArrayList<UserIDs>();
                for(NearByUser nearByYser: nearbyFriends){
                    rsult.add(nearByYser.getUserID());
                }
                return rsult;
            }
        
    }
   
}