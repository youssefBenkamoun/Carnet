package ma.project.carnet.crud;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ma.project.carnet.R;
import ma.project.carnet.classes.Client;
import ma.project.carnet.services.ClientService;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    List<String> args;
    List<Client> clients;
    private Context context;
    private ImageView red_delete;
    private ArrayList client_id,client_nom,client_prenom,client_cin,client_telephone;



    //Context context,ArrayList client_id,ArrayList client_nom,ArrayList client_prenom,ArrayList client_cin,ArrayList client_telephone
    CustomAdapter(List<Client> clients){
        /*this.context = context;
        this.client_id = client_id;
        this.client_nom = client_nom;
        this.client_prenom = client_prenom;
        this.client_cin = client_cin;
        this.client_telephone = client_telephone;*/
        this.clients = clients;

    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.row,parent,false);

        //------------------------


        final MyViewHolder holder = new MyViewHolder(v);

        red_delete = v.findViewById(R.id.imageView2);
        TextView row_id = v.findViewById(R.id.client_row);

        red_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TextView client_row = view.findViewById(R.id.client_row);
                int id_dlt = Integer.parseInt(row_id.getText().toString());
                Log.d("deleting",id_dlt+"");
                Client client2 = ClientService.getClientService(context).findById(id_dlt);
                ClientService.getClientService(context).delete(client2);
                clients = ClientService.getClientService(context).findAll();
                notifyItemRemoved(holder.getBindingAdapterPosition());
                Toast.makeText(view.getContext(), "Client deleted successfully !!",Toast.LENGTH_LONG).show();
            }
        });


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View popup = LayoutInflater.from(inflater.getContext()).inflate(R.layout.popup_edit, null, false);
                final EditText nom_edit = popup.findViewById(R.id.nom_edit);
                final EditText prenom_edit = popup.findViewById(R.id.prenom_edit);
                final EditText cin_edit = popup.findViewById(R.id.cin_edit);
                final EditText telephone_edit = popup.findViewById(R.id.telephone_edit);
                final TextView client_row = v.findViewById(R.id.client_row);

                nom_edit.setText(((TextView) v.findViewById(R.id.nom)).getText().toString());
                prenom_edit.setText(((TextView) v.findViewById(R.id.prenom)).getText().toString());
                cin_edit.setText(((TextView) v.findViewById(R.id.cin)).getText().toString());
                telephone_edit.setText(((TextView) v.findViewById(R.id.telephone)).getText().toString());
                AlertDialog dialog = new AlertDialog.Builder(popup.getContext()).setTitle("Update : ").setMessage("update the client data :").setView(popup).setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        int ids = Integer.parseInt(client_row.getText().toString());
                        String new_nom = nom_edit.getText().toString();
                        String new_prenom = prenom_edit.getText().toString();
                        String new_cin = cin_edit.getText().toString();
                        Long new_telephone =Long.parseLong(telephone_edit.getText().toString());

                        Client client1 = ClientService.getClientService(context).findById(ids);
                        client1.setNom(new_nom);
                        client1.setPrenom(new_prenom);
                        client1.setCin(new_cin);
                        client1.setTelephone(new_telephone);
                        ClientService.getClientService(context).update(client1);
                        clients = ClientService.getClientService(context).findAll();
                        notifyItemChanged(holder.getBindingAdapterPosition());
                        Toast.makeText(v.getContext(), "Client updated successfully !!",Toast.LENGTH_LONG).show();
                        //notifyItemInserted(holder.getBindingAdapterPosition());

                    }
                }).setNegativeButton("Annuler", null).create();
                dialog.show();
            }
        });

        //------------------------
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.nom.setText(String.valueOf(clients.get(position).getNom()));
        holder.id.setText(String.valueOf(clients.get(position).getId()));
        holder.prenom.setText(String.valueOf(clients.get(position).getPrenom()));
        holder.cin.setText(String.valueOf(clients.get(position).getCin()));
        holder.telephone.setText(String.valueOf(clients.get(position).getTelephone()));
    }

    @Override
    public int getItemCount() {
        return clients.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nom,prenom,cin,telephone,id;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nom = itemView.findViewById(R.id.nom);
            prenom = itemView.findViewById(R.id.prenom);
            cin = itemView.findViewById(R.id.cin);
            id = itemView.findViewById(R.id.client_row);
            telephone = itemView.findViewById(R.id.telephone);


        }
    }
}
