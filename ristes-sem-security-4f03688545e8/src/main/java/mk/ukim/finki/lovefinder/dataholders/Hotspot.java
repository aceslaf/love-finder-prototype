/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mk.ukim.finki.lovefinder.dataholders;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Aleksandar
 */
public class Hotspot implements Serializable{
    String SSID;
	String Name;
        int ID;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

	public String getSSID() {
		return SSID;
	}

	public void setSSID(String SSID) {
		this.SSID = SSID;
	}

	public String getName() {
		return Name;
	}

	public void setName(String Name) {
		this.Name = Name;
	}

}
