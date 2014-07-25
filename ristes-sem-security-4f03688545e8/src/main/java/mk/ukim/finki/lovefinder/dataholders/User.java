/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mk.ukim.finki.lovefinder.dataholders;

import java.io.Serializable;
import java.util.Map;

/**
 *
 * @author Aleksandar
 */
public class User implements Serializable{
    UserIDs usrIDs;
     //age sex etc    
    Map<String,Object> userInfo;
    public UserIDs getUsrIDs() {
        return usrIDs;
    }

    public void setUsrIDs(UserIDs usrIDs) {
        this.usrIDs = usrIDs;
    }

    public Map<String, Object> getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(Map<String, Object> userInfo) {
        this.userInfo = userInfo;
    }
    
   
    
}
