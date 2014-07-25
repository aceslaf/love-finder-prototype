/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mk.ukim.finki.lovefinder.dataholders;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Aleksandar
 */
public class UpdateRequest implements Serializable{
    public static final String TYPE_CHANGE = "CHANGE";
	public static final String TYPE_NO_CHANGE = "NO_CHANGE";
	public static final String TYPE_CHECK_IN = "CHECK_IN";
	String reqType;
	List<Hotspot> hotspots;
	GeoLocation location;
	UserIDs UserID;
	Object filter;

	public Object getFilter() {
		return filter;
	}

	public void setFilter(Object filter) {
		this.filter = filter;
	}

	public Object getReqType() {
		return reqType;
	}

	public void setReqType(String reqType) {
		this.reqType = reqType;
	}

	public List<Hotspot> getHotspots() {
		return hotspots;
	}

	public void setHotspots(List<Hotspot> hotspots) {
		this.hotspots = hotspots;
	}

	public GeoLocation getLocation() {
		return location;
	}

	public void setLocation(GeoLocation location) {
		this.location = location;
	}

	public UserIDs getUser() {
		return UserID;
	}

	public void setUser(UserIDs user) {
		this.UserID = user;
	}

}
