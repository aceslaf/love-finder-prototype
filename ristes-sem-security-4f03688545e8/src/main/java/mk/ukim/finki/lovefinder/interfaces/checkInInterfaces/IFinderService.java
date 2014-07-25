/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mk.ukim.finki.lovefinder.interfaces.checkInInterfaces;


import java.util.List;
import mk.ukim.finki.lovefinder.dataholders.ResponseWrapper;
import mk.ukim.finki.lovefinder.dataholders.UpdateRequest;
import mk.ukim.finki.lovefinder.dataholders.User;
import mk.ukim.finki.lovefinder.dataholders.UserIDs;

/**
 *
 * @author Aleksandar
 */
public interface IFinderService {
    public String findNearByFriends(String request);
    public void checkInUser(String request);
    public void updateTime(String request);
    public void pruneActiveUsers();
    public User getUser(String UserIDs);
}
