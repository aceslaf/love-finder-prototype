/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mk.ukim.finki.etnc.security.spring.controller;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import com.google.gson.Gson;
import java.io.IOException;
import java.net.MalformedURLException;
import mk.ukim.finki.lovefinder.constants.GCMCOnstants;
import mk.ukim.finki.lovefinder.dataholders.GCMMessage;
import mk.ukim.finki.lovefinder.dataholders.OurGcmMessage;
import mk.ukim.finki.lovefinder.dataholders.UserIDs;
import mk.ukim.finki.lovefinder.interfaces.checkInInterfaces.IDbAdapter;
import mk.ukim.finki.lovefinder.interfaces.checkInInterfaces.IGCMHandler;

/**
 *
 * @author Aleksandar
 */
public class GCMHAndler implements IGCMHandler{
    
 
    @Override
    public String sendMsgViaPost(String apiKey, GCMMessage msg) {
       try {
           // CARE FOR UPCAST NOT COOL
       OurGcmMessage dMsg=(OurGcmMessage) msg;
       String senderRegId = msg.getFromUID().getInstalationID();
       String recieverGoogleAcount = msg.getToUID().getGoogle();
       String recieverRegId=msg.getToUID().getInstalationID();
       
       if(recieverRegId==null){
         return "ERROR";
       }
       

       Sender sender = new Sender(GCMCOnstants.API_KEY);        
        String messageString=new Gson().toJson(msg);
        Message message = new Message.Builder().timeToLive(GCMCOnstants.TTL)
                                               .delayWhileIdle(true)
                                               .addData(GCMCOnstants.MESSAGE_KEY,messageString)
                                               .build();
        
        Result result = sender.send(message,recieverRegId , 1);
           return result.getMessageId();
      } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        return null;
    }
    
}
