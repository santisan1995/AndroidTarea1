package com.tecnoinfsanjose.tareaandroiduno.Vistas.Fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.tecnoinfsanjose.tareaandroiduno.DataTypes.ClienteDataType;
import com.tecnoinfsanjose.tareaandroiduno.Vistas.Activities.IActivityHome;
import com.tecnoinfsanjose.tareaandroiduno.R;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeCliente.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeCliente#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeCliente extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private Button boton_salir;
    Button boton_seleccionarFecha;
    private Button boton_siguiente;
    private int dia,mes,anio;
    private TextView texto_fecha;

    IActivityHome iActivityHome;

    public HomeCliente() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeCliente.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeCliente newInstance(String param1, String param2) {
        HomeCliente fragment = new HomeCliente();
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
        View view = inflater.inflate(R.layout.fragment_home_cliente, container, false);
        texto_fecha = view.findViewById(R.id.textView4);
        ClienteDataType c = (ClienteDataType) getActivity().getIntent().getExtras().getSerializable("cliente");
        boton_seleccionarFecha = view.findViewById(R.id.button2);
        boton_seleccionarFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                dia = c.get(Calendar.DAY_OF_MONTH);
                mes = c.get(Calendar.MONTH);
                anio = c.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        texto_fecha.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                        getActivity().getIntent().putExtra("fecha",texto_fecha.getText());
                    }
                },dia,mes,anio);
                datePickerDialog.show();
            }
        });

        boton_salir = view.findViewById(R.id.button1);
        boton_salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iActivityHome.volverActivityInicio();
            }
        });
        boton_siguiente = view.findViewById(R.id.button3);
        boton_siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iActivityHome.cambiarHomeAConfirmarCliente();
            }
        });
        return view;

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
        iActivityHome = (IActivityHome) getActivity();
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
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
