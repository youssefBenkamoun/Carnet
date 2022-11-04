package ma.project.carnet.crud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

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
    private List<Client> stars;
    private RecyclerView recyclerView;
    private CustomAdapter starAdapter = null;
    private ClientService service;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //tab=ClientService.getClientService(this).findAll();
        setContentView(R.layout.activity_client);



    }
    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem menuItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d("TAG", newText);
                if (starAdapter != null) {
                    starAdapter.getFilter().filter(newText);

                }
                return true;
            }
        });
        return true;
    }*/
}