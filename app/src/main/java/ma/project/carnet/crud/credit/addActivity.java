package ma.project.carnet.crud.credit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import ma.project.carnet.R;
import ma.project.carnet.classes.Client;
import ma.project.carnet.classes.Credit;
import ma.project.carnet.classes.Produit;
import ma.project.carnet.services.CategorieService;
import ma.project.carnet.services.ClientService;
import ma.project.carnet.services.CreditService;
import ma.project.carnet.services.ProduitService;


import android.content.Context;
import android.os.Build;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class addActivity extends AppCompatActivity {
    private Button add;
    private Spinner client;
    private Spinner product;
    private EditText prix;
    private ClientService cls;
    private CreditService cs;
    private ProduitService ps;
    private CategorieService cat;

    private ArrayList<String> clients = new ArrayList<String>();
    private ArrayList<String> products = new ArrayList<String>();

    private ArrayList<Client> clients_obj;
    private ArrayList<Produit> products_obj;

    private int id_product_string;
    private int id_client_string;
    private int id_category_string;
    private float price_String;

    private Produit cr_pro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        cs = CreditService.getCreditService(this);
        ps = ProduitService.getProduitService(this);
        cls = ClientService.getClientService(this);
        cat = CategorieService.getCategorieService(this);

        prix = findViewById(R.id.price);
        client = findViewById(R.id.client);
        product = findViewById(R.id.produit);
        add = findViewById(R.id.ajou);

        products_obj = (ArrayList<Produit>) ps.findAll();
        clients_obj = (ArrayList<Client>) cls.findAll();


        for (Client c : clients_obj) {
            clients.add(c.getId()+"- "+c.getNom());
        }

        for (Produit p: products_obj){
            products.add(p.getId()+"- "+p.getLibelle());
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>((Context) this, android.R.layout.simple_spinner_item, (List<String>) clients);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        client.setAdapter(adapter);

        adapter = new ArrayAdapter<String>((Context) this, android.R.layout.simple_spinner_item, (List<String>) products);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        product.setAdapter(adapter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Log.d(TAG, "onClickAjou: "+String.valueOf(product.getSelectedItem().toString().charAt(0)));
                id_product_string = Integer.parseInt(String.valueOf(product.getSelectedItem().toString().charAt(0)));
                id_client_string = Integer.parseInt(String.valueOf(client.getSelectedItem().toString().charAt(0)));
                cr_pro = products_obj.get(id_product_string-1);
                price_String = Float.parseFloat(String.valueOf(prix.getText()));

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    cs.create(new Credit(price_String, id_client_string, id_product_string, Date.valueOf(String.valueOf(LocalDate.now())), "Non Payé"));
                }



                //Log.d(TAG, "onClickAjou: product : "+id_product_string+" / category : "+id_category_string+" / client : "+id_client_string);
            }
        });
    }
}