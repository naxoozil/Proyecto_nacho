package com.example.nachodelaviuda.proyecto_nacho;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

import static android.support.constraint.Constraints.TAG;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentoGeneral.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentoGeneral#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentoGeneral extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "descripcion";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private FirebaseDatabase database;
    private DatabaseReference myRef;

    private OnFragmentInteractionListener mListener;

    public FragmentoGeneral() {
        // Required empty public constructor
    }


    public static FragmentoGeneral newInstance(String param1, String param2) {
        FragmentoGeneral fragment = new FragmentoGeneral();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View vista = inflater.inflate(R.layout.fragment_fragmento_general, container, false);
        final TextView txt = (TextView) vista.findViewById(R.id.fragGeneralNombreFestival);
        final TextView txtdesc = (TextView) vista.findViewById(R.id.generalDescripcion);
        ImageView  imagen = (ImageView) vista.findViewById(R.id.fragGeneral);
        Bundle datos = this.getActivity().getIntent().getExtras();
        txt.setText(datos.getString("nombre"));
        txtdesc.setText(datos.getString("descripcion"));
        Glide.with(getContext()).load(datos.getString("img")).into(imagen);




        /*database = FirebaseDatabase.getInstance();
        myRef = database.getReference("espana").child(Utilidades.proveniencia);
        Log.i("General = ", myRef.toString());
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                General general = dataSnapshot.getValue(General.class);
                txt.setText(general.getTitulo());
                txtdesc.setText(general.getDescripcion());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                General general = dataSnapshot.getValue(General.class);
                txt.setText(general.getTitulo());
                txtdesc.setText(general.getDescripcion());
                Log.i("General = ", dataSnapshot.toString());

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });*/


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
