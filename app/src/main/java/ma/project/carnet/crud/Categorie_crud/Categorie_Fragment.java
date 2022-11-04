package ma.project.carnet.crud.Categorie_crud;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import ma.project.carnet.R;
import ma.project.carnet.classes.Categorie;
import ma.project.carnet.classes.Client;
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
    private FloatingActionButton categorieAdd;
    int a = 0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Toast.makeText(getActivity(), "welcome categorie", Toast.LENGTH_SHORT).show();
        View view = inflater.inflate(R.layout.fragment_categorie_, container, false);

        categorieAdd = view.findViewById(R.id.categorieAdd);
        categorieAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View popup = LayoutInflater.from(inflater.getContext()).inflate(R.layout.popup_produit_edit, null, false);
                final EditText nom_edit = popup.findViewById(R.id.edit_price);



                AlertDialog dialog = new AlertDialog.Builder(popup.getContext()).setTitle("Insert Category : ").setMessage("Insert the Category :").setView(popup).setPositiveButton("Insert", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String new_nom = nom_edit.getText().toString();

                        Categorie c_new = new Categorie(new_nom);
                        CategorieService.getCategorieService(getContext()).create(c_new);
                        arr = CategorieService.getCategorieService(getContext()).findAll();
                        a  =arr.size();
                        categorieAdapter = new CategorieAdapter(arr,listener);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                        recyclerView.setLayoutManager(linearLayoutManager);
                        recyclerView.setAdapter(categorieAdapter);
                        Toast.makeText(view.getContext(), "Category inserted successfully !!",Toast.LENGTH_LONG).show();
                        //notifyItemInserted(holder.getBindingAdapterPosition());

                    }
                }).setNegativeButton("Annuler", null).create();
                dialog.show();
            }
        });


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
        Log.d("onResume","started Restart !!!!!!!!!!!!!!");
        categorieAdapter.notifyItemInserted(a);
        clientService = CategorieService.getCategorieService(this.getContext());//new ClientService(this.getContext());

        arr = clientService.findAll();
        setOnClickListener();
        categorieAdapter = new CategorieAdapter(arr,listener);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(categorieAdapter);
    }
}