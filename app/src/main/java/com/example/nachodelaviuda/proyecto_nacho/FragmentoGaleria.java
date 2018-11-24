package com.example.nachodelaviuda.proyecto_nacho;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nachodelaviuda.proyecto_nacho.galeria.ElementoGaleria;
import com.example.nachodelaviuda.proyecto_nacho.galeria.GaleriaAdaptador;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class FragmentoGaleria extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private DatabaseReference reference;
    private DatabaseReference mDatabaseRef;
    //private List<SubirImagen> imgList;
    private ArrayList<SubirImagen> imgList;
    private RecyclerView recyclerViewGaleria;
    private GaleriaAdaptador adapter;
    private ProgressDialog progressDialog;


    private OnFragmentInteractionListener mListener;

    public FragmentoGaleria() {
        // Required empty public constructor
    }


    public static FragmentoGaleria newInstance(String param1, String param2) {
        FragmentoGaleria fragment = new FragmentoGaleria();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imgList = new ArrayList<SubirImagen>();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("galeria" + Utilidades.nombreUbi);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Declaración de todos los elementos a usar
        final View vista = inflater.inflate(R.layout.fragment_fragmento_galeria, container, false);
        //imgList = new ArrayList<SubirImagen>();
        recyclerViewGaleria = (RecyclerView) vista.findViewById(R.id.recyclerGaleria);
        progressDialog = new ProgressDialog(getContext());
        FloatingActionButton addImagen = vista.findViewById(R.id.floatingBotonAnadirImagen);
        addImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento = new Intent(getContext(), RecogerImagenes.class);
                startActivity(intento);
            }
        });

        SubirImagen sbimg = new SubirImagen("nacho","https://firebasestorage.googleapis.com/v0/b/festivaleo-global.appspot.com/o/galeriaTomorrowland%2F1543055976126.jpg?alt=media&token=8a75e45d-66f2-4e2e-afc6-73e9fdac9869");
        imgList.add(sbimg);
        adapter = new GaleriaAdaptador(getContext(), imgList);

        //Mostramos el cuadro de diálogo con un mensaje "X"
        progressDialog.setMessage("Please wait");
        progressDialog.show();

        //Hacemos referencia a la base de datos
        //mDatabaseRef = FirebaseDatabase.getInstance().getReference("galeria" + Utilidades.nombreUbi);
        Log.i("Log1", mDatabaseRef.toString());
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //imgList = new ArrayList<SubirImagen>();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    SubirImagen img = dataSnapshot1.getValue(SubirImagen.class);
                    Log.i("Log2", img.getUrl());
                    imgList.add(img);

                }
                adapter = new GaleriaAdaptador(getContext(), imgList);
                recyclerViewGaleria.setAdapter(adapter);
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progressDialog.dismiss();
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
