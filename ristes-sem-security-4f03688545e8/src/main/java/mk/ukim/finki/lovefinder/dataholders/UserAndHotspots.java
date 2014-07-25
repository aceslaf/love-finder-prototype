/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mk.ukim.finki.lovefinder.dataholders;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Aleksandar
 */
public class UserAndHotspots implements Serializable{
    List<Hotspot> hotspots;
    GeoLocation location;
    UserIDs UserID;
    
    public List<Hotspot> getHotspots() {
        return hotspots;
    }

    public void setHotspots(List<Hotspot> hotspots) {
        this.hotspots = hotspots;
    }

    public GeoLocation getLocation() {
        return location;
    }

    public void setLocation(GeoLocation location) {
        this.location = location;
    }

    public UserIDs getUserID() {
        return UserID;
    }

    public void setUserID(UserIDs UserID) {
        this.UserID = UserID;
    }
   
}
