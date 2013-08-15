/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thang.tools.util;

import java.util.prefs.Preferences;

/**
 *
 * @author Gandilong
 */
public class RegUtils {
    
    private static final Preferences reg=Preferences.systemRoot().node("/interflow");
    
    
    public static void set(String key,String value){
        reg.put(key, value);
    }
    
    public static String get(String key){
        return reg.get(key,null);
    }
    
    public static String get(String key,String defaultValue){
        return reg.get(key,defaultValue);
    }
    
}
