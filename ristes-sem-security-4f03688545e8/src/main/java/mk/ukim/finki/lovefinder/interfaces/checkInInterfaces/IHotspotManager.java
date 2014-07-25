/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mk.ukim.finki.lovefinder.interfaces.checkInInterfaces;


import java.util.List;
import mk.ukim.finki.lovefinder.dataholders.Hotspot;

/**
 *
 * @author Aleksandar
 */
public interface IHotspotManager {
     public void addAdjecentHotspots(List<Hotspot> hotspots);
     public List<Hotspot> findAdjecentHotspots(Hotspot hotspot);
     
}
