/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mk.ukim.finki.lovefinder.interfaces.checkInInterfaces;

import mk.ukim.finki.lovefinder.dataholders.GCMMessage;

/**
 *
 * @author Aleksandar
 */
public interface IGCMHandler {
   public String sendMsgViaPost(String apiKey, GCMMessage msg);
}
