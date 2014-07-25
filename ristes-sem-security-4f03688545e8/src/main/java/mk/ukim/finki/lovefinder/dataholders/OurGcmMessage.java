/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mk.ukim.finki.lovefinder.dataholders;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;
import mk.ukim.finki.etnc.security.spring.controller.Tester;

/**
 *
 * @author Aleksandar
 */
public class OurGcmMessage extends GCMMessage{
    
    String content;

    public OurGcmMessage(String content) {
        this.content = content;
    }

    public OurGcmMessage(String content, UserIDs uid) {
        this.content = content;
        this.fromUID=uid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
      //povikaj ja so porakata shto ti treba, i url: http://localhost:8080/security/+UrlPathConstants.MESSAGE
    //kade UrlPathConstants.Message=MASAZ
    public static int sendMessage(OurGcmMessage msg, String serverUrlString){
// imports:        
//        import java.io.BufferedReader;
//        import java.io.DataOutputStream;
//        import java.io.IOException;
//        import java.io.InputStreamReader;
//        import java.io.OutputStream;
//        import java.net.HttpURLConnection;
//        import java.net.MalformedURLException;
//        import java.net.URL;
//        import java.nio.charset.Charset;
//
//        import javax.net.ssl.HttpsURLConnection;
                int status=-1;
                String postBody=new Gson().toJson(msg);
                HttpURLConnection httpCon = null;
                URL serverUrl=null;
                try {
                    serverUrl=new URL(serverUrlString);
                    httpCon = (HttpURLConnection) serverUrl.openConnection();
                    httpCon.setDoOutput(true);
                    httpCon.setUseCaches(false);
                    //httpCon.setFixedLengthStreamingMode(bytes.length);
                    httpCon.setRequestMethod("POST");
                    httpCon.setRequestProperty("Content-Type","application/json");
                    OutputStream out = httpCon.getOutputStream();
                    out.write(postBody.getBytes(Charset.forName("UTF-8")));
                    out.flush();
                    out.close();
                    status= httpCon.getResponseCode();
                    
                  } catch (MalformedURLException ex) {
            Logger.getLogger(Tester.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Tester.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
                    if (httpCon != null) {
                      httpCon.disconnect();
                    }
                  }
                return status;
    
    }
    
}
