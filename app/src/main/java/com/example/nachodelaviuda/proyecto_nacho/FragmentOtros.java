package com.example.nachodelaviuda.proyecto_nacho;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;


public class FragmentOtros extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private OnFragmentInteractionListener mListener;
    private RatingBar rtbar;
    public FragmentOtros() {

    }


    public static FragmentOtros newInstance(String param1, String param2) {
        FragmentOtros fragment = new FragmentOtros();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_fragment_otros, container, false);
        rtbar = (RatingBar)vista.findViewById(R.id.otroRating);
        ImageView imgBtn = (ImageView) vista.findViewById(R.id.imagenMapa);




        //Lleva a la activity del maps
        //Queda realizar mapeo segun el festival del que se provenga para las coordenadas de longitud y latitud

        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent llevarAMapa = new Intent(getContext(),MapsActivity.class);
                startActivity(llevarAMapa);
            }
        });
        return vista;
    }


    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }
}