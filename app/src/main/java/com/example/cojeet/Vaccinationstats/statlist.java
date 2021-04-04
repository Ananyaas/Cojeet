package com.example.cojeet.Vaccinationstats;

import android.util.Log;

import com.example.cojeet.login.States;

import java.util.ArrayList;

public class statlist {
    ArrayList<s_data> arr = new ArrayList<s_data>();

    public void init() {
        States states = new States();
        states.init();
        for (String s : states.sl) {
            s_data t = new s_data();
            t.setState(s);
            t.setH_count(0);
            t.setV_count(0);
            this.arr.add(t);
        }

    }

    public void incr_v_count(String state1) {
        for (s_data s : arr) {
            if (state1.contains(s.getState())) {
                s_data t = new s_data();
                t.setState(s.getState());
                t.setH_count(s.getH_count());
                t.setV_count(s.getV_count() + 1);
                arr.set(arr.indexOf(s), t);
                //a.get(a.indexOf(s)).setV_count(s.getV_count()+1);
                // arr.set(arr.indexOf(s),t);
                //Log.e("incr_v_count: "," "+arr.indexOf(t));

                Log.e("incr_v_count: ", " " + arr.get(arr.indexOf(t)).getState() + " " + arr.get(arr.indexOf(t)).getH_count() + " " + arr.get(arr.indexOf(t)).getV_count());

            }

        }
    }

    public void incr_h_count(String state1) {
        for (s_data s : arr) {
            if (state1.contains(s.getState())) {
                s_data t = new s_data();
                t.setState(s.getState());
                t.setH_count(s.getH_count() + 1);
                t.setV_count(s.getV_count());
                arr.set(arr.indexOf(s), t);

            }
        }
    }

    /*public void getArr1(String vd) {
        incr_v_count(vd);

        for (s_data s : arr)
            Log.e("stst", s.getState() + " " + s.getV_count() + " " + s.getH_count());
    }


    public void getArr2(String hd) {
        incr_h_count(hd);

        for (s_data s : arr)
            Log.e("stst", s.getState() + " " + s.getV_count() + " " + s.getH_count());
    }*/


    public ArrayList<s_data> getArr() {
        return arr;
    }
}