/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mk.ukim.finki.etnc.security.spring.controller;

import mk.ukim.finki.lovefinder.dbUtilities.dbHelper;
import mk.ukim.finki.lovefinder.interfaces.checkInInterfaces.IDbAdapter;

/**
 *
 * @author Aleksandar
 */
public class KeepAliveDaemon {
	public void prune() {
//		          IDbAdapter dbHelper=new dbHelper();
//                          dbHelper.pruneInactiveUsers(System.currentTimeMillis());
                     System.out.println("   pruning   ##########################");
	}
    
}
