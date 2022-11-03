package ma.project.carnet.crud.Categorie_crud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

import ma.project.carnet.R;
import ma.project.carnet.classes.Produit;
import ma.project.carnet.crud.Produit_crud.ProduitAdapter;
import ma.project.carnet.services.CategorieService;
import ma.project.carnet.services.ProduitService;

public class SelectProductsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_products);

        RecyclerView recyclerView = findViewById(R.id.recyclerView_products_by_categorie);
        ProduitService produitService = ProduitService.getProduitService(this);//new ClientService(this.getContext());

        List<Produit> arr = produitService.findAll();

        ProduitAdapter produitAdapter = new ProduitAdapter(arr);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(produitAdapter);
    }
}