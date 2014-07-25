/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mk.ukim.finki.lovefinder.dataholders;

import java.io.Serializable;
import java.util.logging.Logger;

/**
 *
 * @author Aleksandar
 */
public class NearByUser implements Comparable<NearByUser>, Serializable{
    UserIDs userID;
    int matchCount;

    public NearByUser(UserIDs userID, int matchCount) {
        this.userID = userID;
        this.matchCount = matchCount;
    }

    
    
    

    public UserIDs getUserID() {
        return userID;
    }

    public void setUserID(UserIDs userID) {
        this.userID = userID;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public void setMatchCount(int matchCount) {
        this.matchCount = matchCount;
    }

    @Override
    public int compareTo(NearByUser t) {
        return Integer.compare(matchCount, t.matchCount);
    }
    
    
}
