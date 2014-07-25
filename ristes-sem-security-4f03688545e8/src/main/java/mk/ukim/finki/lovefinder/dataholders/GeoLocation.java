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
public class GeoLocation implements Serializable{
    Object longitute;
    Object latitude;

    public Object getLongitute() {
        return longitute;
    }

    public void setLongitute(Object longitute) {
        this.longitute = longitute;
    }

    public Object getLatitude() {
        return latitude;
    }

    public void setLatitude(Object latitude) {
        this.latitude = latitude;
    }
}
