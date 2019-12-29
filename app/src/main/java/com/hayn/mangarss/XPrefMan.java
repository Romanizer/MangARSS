package com.hayn.mangarss;

import java.util.HashMap;

class XPrefMan {


    HashMap<String,Boolean> pref;

    Boolean darkmode; // "dark"

    private static XPrefMan Instance = new XPrefMan();

    private XPrefMan(){


    }

    public XPrefMan getInstance(){
        return Instance;
    }

}
