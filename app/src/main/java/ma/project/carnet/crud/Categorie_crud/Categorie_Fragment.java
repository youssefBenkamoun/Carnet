package ma.project.carnet.crud.Categorie_crud;

import android.content.Intent;
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
import ma.project.carnet.crud.AddClientActivity;
import ma.project.carnet.crud.CustomAdapter;
import ma.project.carnet.services.CategorieService;
import ma.project.carnet.services.ClientService;


public class Categorie_Fragment extends Fragment {

private RecyclerView recyclerView;
private CategorieService clientService;
private List<Categorie> arr;
private CategorieAdapter categorieAdapter;
private CategorieAdapter.RecyclerViewClickListener listener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Toast.makeText(getActivity(), "welcome categorie", Toast.LENGTH_SHORT).show();
        View view = inflater.inflate(R.layout.fragment_categorie_, container, false);


        arr = CategorieService.getCategorieService(this.getContext()).findAll();

        recyclerView = view.findViewById(R.id.categorie_recycler);
        clientService = CategorieService.getCategorieService(this.getContext());//new ClientService(this.getContext());

        arr = clientService.findAll();
        setOnClickListener();
        categorieAdapter = new CategorieAdapter(arr,listener);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(categorieAdapter);



        return view;
    }

    private void setOnClickListener() {
        listener = new CategorieAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(getContext(),SelectProductsActivity.class);
                intent.putExtra("cat_id",arr.get(position).getId());
                startActivity(intent);
            }
        };
    }

    @Override
    public void onResume() {
        super.onResume();
        clientService = CategorieService.getCategorieService(this.getContext());//new ClientService(this.getContext());

        arr = clientService.findAll();
        setOnClickListener();
        categorieAdapter = new CategorieAdapter(arr,listener);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(categorieAdapter);
    }
}