package com.cams.languages;

public class Language{
    public static String Setting="Setting";
    public static String Inbound="InBound";
    public static void setLanguage(String lang){
        if(lang.equals("Chinese")){
            Setting="设置";
            Inbound="进港";
        }
        
    }
}