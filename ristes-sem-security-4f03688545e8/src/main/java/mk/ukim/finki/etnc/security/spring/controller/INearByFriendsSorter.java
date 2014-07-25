/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mk.ukim.finki.etnc.security.spring.controller;

import java.util.ArrayList;
import mk.ukim.finki.lovefinder.dataholders.UserIDs;

/**
 *
 * @author Aleksandar
 */
public interface INearByFriendsSorter {

    ArrayList<UserIDs> sortNearByFriends();
    
}
