/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mk.ukim.finki.lovefinder.dataholders;

/**
 *
 * @author Aleksandar
 */
public class ComparableHotSpotWraper implements Comparable<ComparableHotSpotWraper>{
    private Hotspot hotspot;

    @Override
    public int hashCode() {
        if(hotspot.getSSID()!=null){
           return hotspot.getSSID().hashCode();
        }
        if(hotspot.getName()!=null){
          return hotspot.getName().hashCode();
        }
        
        return super.hashCode();
    }
    
    @Override
    public int compareTo(ComparableHotSpotWraper t) {
        return Integer.compare(hashCode(), t.hashCode());
    }
    
}
