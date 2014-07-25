/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mk.ukim.finki.lovefinder.dataholders;

/**
 *
 * @author Aleksandar
 */
public abstract class GCMMessage {
    protected UserIDs fromUID;
    protected UserIDs ToUID;

    public UserIDs getFromUID() {
        return fromUID;
    }

    public void setFromUID(UserIDs fromUID) {
        this.fromUID = fromUID;
    }

    public UserIDs getToUID() {
        return ToUID;
    }

    public void setToUID(UserIDs ToUID) {
        this.ToUID = ToUID;
    }
    

        
}
