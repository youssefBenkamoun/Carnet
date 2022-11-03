package ma.project.carnet.crud.Produit_crud;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import ma.project.carnet.R;
import ma.project.carnet.classes.Categorie;
import ma.project.carnet.classes.Produit;
import ma.project.carnet.crud.Categorie_crud.CategorieAdapter;
import ma.project.carnet.services.CategorieService;
import ma.project.carnet.services.ProduitService;


public class Produit_Fragment extends Fragment {

    private RecyclerView recyclerView;
    private ProduitService produitService;
    private List<Produit> arr;
    private ProduitAdapter produitAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Toast.makeText(getActivity(), "welcome categorie", Toast.LENGTH_SHORT).show();
        View view = inflater.inflate(R.layout.fragment_produit_, container, false);


        Produit c = new Produit("fino",110.0,1);
        ProduitService.getProduitService(this.getContext()).create(c);
        arr = ProduitService.getProduitService(this.getContext()).findAll();

        recyclerView = view.findViewById(R.id.recycler_produit);
        produitService = ProduitService.getProduitService(this.getContext());

        arr = produitService.findAll();

        produitAdapter = new ProduitAdapter(arr);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(produitAdapter);
        return view;
    }
}