package com.dhacks.hacku;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dhacks.hacku.databinding.FragmentRegisterBinding;


/**
 * A simple {@link Fragment} subclass.
 */
public class registerFragment extends Fragment {


    public registerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentRegisterBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false);
        // return inflater.inflate(R.layout.fragment_register, container, false);
        return binding.getRoot();
    }

}
