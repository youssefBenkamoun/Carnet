package ma.project.carnet.crud;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import ma.project.carnet.R;
import ma.project.carnet.classes.Client;
import ma.project.carnet.services.ClientService;


public class fragment_client extends Fragment {
    private List<Client> arr;
    private List<Client> clients;
    private CustomAdapter customAdapter;
    private RecyclerView recyclerView;
    private ClientService clientService;
    private FloatingActionButton addButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.recycle_view, container, false);
        addButton = view.findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddClientActivity.class);
                startActivity(intent);
            }
        });
        recyclerView = view.findViewById(R.id.recycle_clients);
        clientService = ClientService.getClientService(this.getContext());//new ClientService(this.getContext());

        arr = clientService.findAll();

        customAdapter = new CustomAdapter(arr);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(customAdapter);
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        Log.d("yyyyy","on Resume !!!!!!! ");
        clientService = ClientService.getClientService(this.getContext());//new ClientService(this.getContext());

        arr = clientService.findAll();
        //Log.d("yyyyy",arr.get(0).getPrenom()+" ");

        customAdapter = new CustomAdapter(arr);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(customAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("yyyyy","on Start !!!!!!! ");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("yyyyy","on Pause !!!!!!! ");
    }

}