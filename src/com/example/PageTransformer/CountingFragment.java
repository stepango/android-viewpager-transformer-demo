package com.example.PageTransformer;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created with IntelliJ IDEA.
 * User: stepan.goncharov
 * Date: 26.12.12
 * Time: 14:04
 */
public class CountingFragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = new View(inflater.getContext());
        v.setBackgroundColor(Color.BLUE);
        return v;
    }
}
