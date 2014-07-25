/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mk.ukim.finki.lovefinder.dataholders;


/**
 *
 * @author Aleksandar
 */
public class UserIDs {

        public static final String ACCOUNT_GMAIL = "gmail";
	public static final String ACCOUNT_FB = "fb";
	public static final String ACCOUNT_TWITTER = "twitter";

	private long ID;
        private boolean active;

   
	private String instalationID;
	private String deviceMacAdr;
	private String google;
	private String facebook;
	private String skype;

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getGoogle() {
		return google;
	}

	public void setGoogle(String google) {
		this.google = google;
	}

	public String getSkype() {
		return skype;
	}

	public void setSkype(String skype) {
		this.skype = skype;
	}

	public String getInstalationID() {
		return instalationID;
	}

	public long getID() {
		return ID;
	}

	public void setID(long ID) {
		this.ID = ID;
	}

	public void setInstalationID(String instalationID) {
		this.instalationID = instalationID;
	}

	public String getDeviceMacAdr() {
		return deviceMacAdr;
	}

	public void setDeviceMacAdr(String deviceMacAdr) {
		this.deviceMacAdr = deviceMacAdr;
	}
         public boolean isActive() {
            return active;
        }

        public void setActive(boolean active) {
            this.active = active;
        }


}