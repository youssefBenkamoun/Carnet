package ma.project.carnet.crud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import ma.project.carnet.R;
import ma.project.carnet.classes.Client;
import ma.project.carnet.services.ClientService;

public class AddClientActivity extends AppCompatActivity {
    private EditText nom;
    private EditText prenom, cin, telephone;
    private Button insert,retour;
    private TextView txt;
    private static String affich="";

    void clear(){
        nom.setText("");
        prenom.setText("");
        cin.setText("");
        telephone.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_client);
        final ClientService es = ClientService.getClientService(this); //new ClientService(this );
        nom = findViewById(R.id.nom);
        prenom = findViewById(R.id.prenom);
        cin = findViewById(R.id.cin);
        telephone = findViewById(R.id.telephone);
        insert = findViewById(R.id.insert);


        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Insertion des étudiants
                es.create(new Client(nom.getText().toString(), prenom.getText().toString(), cin.getText().toString(),Long.parseLong(telephone.getText().toString())));
                clear();
                Toast.makeText(AddClientActivity.this, "Client inserted successfully !!",Toast.LENGTH_LONG).show();
                //Parcourir la liste des étudiants
                for(Client e : es.findAll()){
                    Log.d(e.getId()+"", e.getNom()+" "+e.getPrenom());
                    affich +=e.getId()+""+ e.getNom()+" "+e.getPrenom()+" "+e.getCin()+" "+e.getTelephone()+"\n";
                }
                //txt.setText(affich);
                Log.d("youssef:", "salina");
            }
        });


    }
}