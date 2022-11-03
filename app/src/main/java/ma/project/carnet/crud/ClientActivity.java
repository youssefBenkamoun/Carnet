package ma.project.carnet.crud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import ma.project.carnet.R;
import ma.project.carnet.classes.Client;
import ma.project.carnet.services.ClientService;

public class ClientActivity extends AppCompatActivity {
    private FloatingActionButton addButton;
    private RecyclerView recycle_clients;
    List<Client> tab;
    ClientService clientService = ClientService.getClientService(this);//new ClientService(this);
    CustomAdapter customAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        /*
        RecyclerView recyclerView = findViewById(R.id.recycle_clients);
        clientService = ClientService.getClientService(this);

        List<Client> arr = clientService.findAll();

        customAdapter = new CustomAdapter(arr);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(customAdapter);*/

        /*recycle_clients = findViewById(R.id.recycle_clients);
        addButton = findViewById(R.id.addButton);
        Log.d("youssef1", "(String) client_id.get(0)");
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClientActivity.this, AddClientActivity.class);
                startActivity(intent);
            }
        });
        fction();
        for(Client c:clientService.findAll()){
            client_id.add(c.getId());
            client_nom.add(c.getNom());
            client_prenom.add(c.getPrenom());
            client_cin.add(c.getCin());
            client_telephone.add(c.getTelephone());
        }
        Log.d("youssef1", (String) client_id.get(0));
        Log.d("youssef1", "(String) client_id.get(0)");
        customAdapter = new CustomAdapter(this, client_id,client_nom,client_prenom,client_cin,client_telephone);
        recycle_clients.setAdapter(customAdapter);
        recycle_clients.setLayoutManager(new LinearLayoutManager(this));*/
    }
}