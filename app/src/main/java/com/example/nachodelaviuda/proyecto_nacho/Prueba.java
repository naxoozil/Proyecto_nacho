package com.example.nachodelaviuda.proyecto_nacho;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Prueba.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Prueba#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Prueba extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    ListView listadoPrueba;
    String[][] datos = {
            {"boom",         "Christopher Nolan",  "2:49", "9", "Interstellar (Interestelar en Hispanoamérica) es una película épica de ciencia ficción estadounidense de 2014, dirigida por Christopher Nolan y protagonizada por Matthew McConaughey, Anne Hathaway, Jessica Chastain, Michael Caine y Matt Damon. La película presenta a un equipo de astronautas que viaja a través de un agujero de gusano en busca de un nuevo hogar para la humanidad. Los hermanos Christopher y Jonathan Nolan escribieron el guion, que tuvo su origen en un borrador que Jonathan desarrolló en 2007. Christopher Nolan produjo la película junto a su esposa Emma Thomas mediante su compañía productora Syncopy, y con Lynda Obst a través de Lynda Obst Productions. El físico teórico Kip Thorne, cuyo trabajo inspiró la película, fue productor ejecutivo y participó como consultor científico. Warner Bros., Paramount Pictures y Legendary Pictures cofinanciaron la película."},
            {"umf",          "James Mangold",      "2:17", "7", "Logan(Logan: Wolverine en Hispanoamérica) es una película estadounidense de 2017 y la última de la trilogía de Wolverine, basada en el personaje de Wolverine, de Marvel Comics, y producida por la 20th Century Fox. Se estrenó el 3 de marzo de 2017, protagonizada por Hugh Jackman y Patrick Stewart siendo esta sus últimas apariciones como Wolverine y el Profesor X en la franquicia de X-Men."},
            {"dreambeach",   "Baltasar Kormákur",  "2:01", "8", "Everest es una película estadounidense estrenada el 18 de septiembre de 2015, dirigida por Baltasar Kormákur y escrita por Justin Isbell y William Nicholson. La cinta, que tiene como protagonistas a Jason Clarke, Josh Brolin, John Hawkes, Robin Wright, Michael Kelly, Keira Knightley, Emily Watson, Sam Worthington y Jake Gyllenhaal, narra la tragedia ocurrida en el monte Everest el 10 de mayo de 1996, en la que ocho alpinistas fallecieron debido a una tormenta."},
            {"defcon",       "Guillermo del Toro", "2:12", "7", "Pacific Rim (Titanes del Pacífico en Hispanoamérica) es una película estadounidense de ciencia ficción del 2013 dirigida por Guillermo del Toro, escrita por Del Toro y Travis Beacham, y protagonizada por Charlie Hunnam, Idris Elba, Rinko Kikuchi, Charlie Day, Robert Kazinsky, Max Martini, y Ron Perlman. La película está ambientada en la década de 2020, cuando la Tierra es atacada por kaijus, monstruos colosales que han surgido a partir de un portal interdimensional en el fondo del Océano Pacífico, llamado \"El Abismo\". Para luchar contra los monstruos, la humanidad se une para crear a los Jaegers: gigantescas máquinas humanoides, cada una controlada por dos pilotos cuyas mentes están unidas por un puente neural (similares a los personajes llamados Headmasters de Transformers o a las unidades EVA (mecha) de Neon Genesis Evangelion). Centrándose en los días posteriores de la guerra, la historia sigue a Raleigh Becket, un piloto jaeger llamado de su retiro, que se asociará con la piloto novata Mako Mori en un último esfuerzo para derrotar a los kaijus."},
            {"Tomorrowland", "Alex Garland",       "1:48", "9", "Ex Machina es una película de ciencia ficción británica de 2015, escrita y dirigida por Alex Garland, siendo su primera película como director. Está protagonizada por Domhnall Gleeson, Alicia Vikander, Oscar Isaac y Sonoya Mizuno. Ex Machina cuenta la historia de Caleb, un programador de la empresa Bluebook, quien es invitado por Nathan, el Presidente de la compañía para la cual él trabaja, con el fin de realizar la prueba de Turing a un androide con inteligencia artificial. La película ha recibido principalmente críticas positivas de los expertos. La cinta ganó el Óscar a los mejores efectos visuales."},
    };

    int[] datosImg = {R.drawable.boom,
            R.drawable.umf,
            R.drawable.dreambeach,
            R.drawable.defcon,
            R.drawable.tomorrowland};

    public Prueba() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Prueba.
     */
    // TODO: Rename and change types and number of parameters
    public static Prueba newInstance(String param1, String param2) {
        Prueba fragment = new Prueba();
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
        View vista = inflater.inflate(R.layout.fragment_prueba, container, false);

        listadoPrueba = (ListView) vista.findViewById(R.id.listaPrueba);
        listadoPrueba.getContext();

        listadoPrueba.setAdapter(new NuevoAdaptador(this.getContext(), datos, datosImg));
        listadoPrueba.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*Intent visorDetalles = new Intent(view.getContext(), NuevoDetallesPelicula.class);
                visorDetalles.putExtra("TIT", datos[position][0]);
                visorDetalles.putExtra("DET", datos[position][4]);
                startActivity(visorDetalles);*/
                Intent visorDetalles = new Intent(view.getContext(), GestorDeTabs.class);
                startActivity(visorDetalles);
            }
        });


        return vista;
    }


    // TODO: Rename method, update argument and hook method into UI event
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
