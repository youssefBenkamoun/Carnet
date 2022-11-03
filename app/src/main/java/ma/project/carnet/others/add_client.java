package ma.project.carnet.others;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import ma.project.carnet.R;
import ma.project.carnet.classes.Client;
import ma.project.carnet.services.ClientService;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link add_client#newInstance} factory method to
 * create an instance of this fragment.
 */
public class add_client extends Fragment {

    private EditText nom;
    private EditText prenom, cin, telephone;
    private Button insert;
    private TextView txt;
    private static String affich="";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public add_client() {
        // Required empty public constructor
    }
    void clear(){
        nom.setText("");
        prenom.setText("");
        cin.setText("");
        telephone.setText("");
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment add_client.
     */
    // TODO: Rename and change types and number of parameters
    public static add_client newInstance(String param1, String param2) {
        add_client fragment = new add_client();
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
        View view = inflater.inflate(R.layout.fragment_client, container, false);
        final ClientService es = ClientService.getClientService(this.getContext()); //new ClientService(getActivity());

        nom = view.findViewById(R.id.nom);
        prenom = view.findViewById(R.id.prenom);
        cin = view.findViewById(R.id.cin);
        telephone = view.findViewById(R.id.telephone);
        insert = view.findViewById(R.id.insert);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Insertion des étudiants
                es.create(new Client(nom.getText().toString(), prenom.getText().toString(), cin.getText().toString(),Long.parseLong(telephone.getText().toString())));
                clear();
                //Parcourir la liste des étudiants
                for(Client e : es.findAll()){
                    Log.d(e.getId()+"", e.getNom()+" "+e.getPrenom());
                    affich +=e.getId()+""+ e.getNom()+" "+e.getPrenom()+" "+e.getCin()+" "+e.getTelephone()+"\n";
                }
                txt.setText(affich);
                Log.d("youssef:", "salina");
            }
        });


        return view;
    }
}