/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mk.ukim.finki.lovefinder.dataholders;

import java.io.Serializable;

/**
 *
 * @author Aleksandar
 */
public class UserState implements Serializable{
    private boolean state;

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public long getLastCheckIn() {
        return lastCheckIn;
    }

    public void setLastCheckIn(long lastCheckIn) {
        this.lastCheckIn = lastCheckIn;
    }

    public UserIDs getUid() {
        return uid;
    }

    public void setUid(UserIDs uid) {
        this.uid = uid;
    }
    private long lastCheckIn;
    private UserIDs uid;   
}
