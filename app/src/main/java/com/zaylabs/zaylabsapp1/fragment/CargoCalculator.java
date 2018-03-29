package com.zaylabs.zaylabsapp1.fragment;


import android.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zaylabs.zaylabsapp1.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CargoCalculator extends Fragment {





    public CargoCalculator() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_cargo_calculator, container, false);


        return view;
    }

    private void getCustomerInfo() {

    }

}








