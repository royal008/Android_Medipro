package com.android.medipro.custom_utils;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.android.medipro.R;


/**
 * Created by Deepa on 03-Dec-17.
 */

public class FragmentBeanClass {
    AppCompatActivity context;
    Fragment fragment;
    Bundle bundle;
    int container;
    public FragmentBeanClass(AppCompatActivity context, int container)
    {
        this.context=context;
        this.container=container;
    }
    public Fragment getFragment() {
        return fragment;
    }



    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
        FragmentManager fm=context.getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.setCustomAnimations(R.anim.slide_from_right,R.anim.slide_to_left);
        ft.replace(container, fragment,fragment.getClass().getName());
        ft.addToBackStack(fragment.getClass().getName());
        ft.commit();
    }

    public void goBack() {
        FragmentManager fm=context.getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.setCustomAnimations(R.anim.slide_from_left,R.anim.slide_to_right);
        fm.popBackStack();
        ft.commit();
    }
}
