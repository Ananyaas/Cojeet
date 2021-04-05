package com.example.cojeet.login;

import java.util.ArrayList;

public class States {

   public ArrayList<String> sl = new ArrayList<String>();

    public void init() {
        sl.add("Andhra Pradesh");
        sl.add("Arunachal Pradesh");
        sl.add("Assam");
        sl.add("Bihar");
        sl.add("Chhattisgarh");
        sl.add("Goa");
        sl.add("Gujarat");
        sl.add("Haryana");
        sl.add("Himachal Pradesh");
        sl.add("Jharkhand");
        sl.add("Karnataka");
        sl.add("Kerala");
        sl.add("Madhya Pradesh");
        sl.add("Maharashtra");
        sl.add("Manipur");
        sl.add("Meghalaya");
        sl.add("Mizoram");
        sl.add("Nagaland");
        sl.add("Odisha");
        sl.add("Punjab");
        sl.add("Rajasthan");
        sl.add("Sikkim");
        sl.add("Tamil Nadu");
        sl.add("Telangana");
        sl.add("Tripura");
        sl.add("Uttarakhand");
        sl.add("Uttar Pradesh");
        sl.add("West Bengal");
        sl.add("Andaman and Nicobar Islands");
        sl.add("Chandigarh");
        sl.add("Dadra and Nagar Haveli and Daman & Diu");
        sl.add("Delhi");
        sl.add("Jammu & Kashmir");
        sl.add("Ladakh");
        sl.add("Lakshadweep");
        sl.add("Puducherry");

    }

    public String cont(String addr) {
        String sta = null;
        for (String s : sl) {
            if (addr.contains(s)) {
                sta=s;
                break;
            }
            else sta=null;

        }
        return sta;

    }
}
