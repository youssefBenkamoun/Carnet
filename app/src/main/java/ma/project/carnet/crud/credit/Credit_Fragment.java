package ma.project.carnet.crud.credit;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import ma.project.carnet.R;
import ma.project.carnet.classes.Categorie;
import ma.project.carnet.classes.Credit;
import ma.project.carnet.classes.Produit;
import ma.project.carnet.databinding.FragmentHomeBinding;
import ma.project.carnet.services.CategorieService;
import ma.project.carnet.services.ClientService;
import ma.project.carnet.services.CreditService;
import ma.project.carnet.services.ProduitService;


public class Credit_Fragment extends Fragment {

    private RecyclerView recyclerView;
    private CreditAdapter creditAdapter;

    private Credit credit;
    private Produit product;
    private Categorie category;

    private TextView id,price,etat;

    private FloatingActionButton addCredit;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_credit_, container, false);

        CreditService cs = new CreditService(this.getContext());
        ProduitService ps = ProduitService.getProduitService(this.getContext());
        ClientService cls =ClientService.getClientService(this.getContext());
        CategorieService cat =CategorieService.getCategorieService(this.getContext());







        addCredit = view.findViewById(R.id.add_credit_button);
        addCredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), addActivity.class);
                startActivity(intent);
            }
        });




        recyclerView = view.findViewById(R.id.recycle_view);
        creditAdapter = new CreditAdapter(getContext(), cs.findAll());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(creditAdapter);
        return view;
    }

}