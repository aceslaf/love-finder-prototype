/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mk.ukim.finki.lovefinder.interfaces.checkInInterfaces;


import java.util.Date;
import java.util.List;
import mk.ukim.finki.lovefinder.dataholders.Hotspot;
import mk.ukim.finki.lovefinder.dataholders.User;
import mk.ukim.finki.lovefinder.dataholders.UserAndHotspots;
import mk.ukim.finki.lovefinder.dataholders.UserIDs;

/**
 *
 * @author Aleksandar
 */
public interface IDbAdapter {
    
    //testing
    public List<UserAndHotspots> getCloseUsers(Object filter, List<Hotspot> hotspots,UserIDs usr, long systemTime);

    
    //works
    public void updateUserLocation(UserIDs user, List<Hotspot> hotspots, long systemTime);
    
    
    //works
    public void updateTime(UserIDs user,long systemTime);
    
    
    //works
    public void pruneInactiveUsers(long currSysTime);
    
    public User getUser(UserIDs uid);
    
    public void initDbTables();

    public void insertUser(UserIDs user);
    
    public String getReqIdForAcount(String googleUserId);
    
}
