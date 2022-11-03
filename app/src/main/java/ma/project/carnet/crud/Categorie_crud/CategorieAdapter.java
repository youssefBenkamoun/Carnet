package ma.project.carnet.crud.Categorie_crud;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListPopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ma.project.carnet.R;
import ma.project.carnet.classes.Categorie;
import ma.project.carnet.classes.Client;
import ma.project.carnet.classes.Produit;
import ma.project.carnet.crud.AddClientActivity;
import ma.project.carnet.crud.Produit_crud.ProduitAdapter;
import ma.project.carnet.services.CategorieService;
import ma.project.carnet.services.ClientService;
import ma.project.carnet.services.ProduitService;

public class CategorieAdapter extends RecyclerView.Adapter<CategorieAdapter.MyViewHolder> {
    List<String> args;
    List<Categorie> clients;
    private Context context;
    private ImageView red_delete;
    private RecyclerViewClickListener listener;


    CategorieAdapter(List<Categorie> clients, RecyclerViewClickListener listener){

        this.clients = clients;
        this.listener = listener;

    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.row_categorie,parent,false);

        clients = CategorieService.getCategorieService(context).findAll();
        final MyViewHolder holder = new MyViewHolder(v);
        red_delete = v.findViewById(R.id.delete_categorie);
        TextView row_id = v.findViewById(R.id.id_categorie);

        red_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TextView client_row = view.findViewById(R.id.client_row);
                int id_dlt = Integer.parseInt(row_id.getText().toString());
                Log.d("deleting",id_dlt+"");
                Categorie client2 = CategorieService.getCategorieService(context).findById(id_dlt);
                CategorieService.getCategorieService(context).delete(client2);
                clients = CategorieService.getCategorieService(context).findAll();
                notifyItemRemoved(holder.getBindingAdapterPosition());
                Toast.makeText(view.getContext(), "Client deleted successfully !!",Toast.LENGTH_LONG).show();
            }
        });




        //------------------------
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.designation.setText(String.valueOf(clients.get(position).getName()));
        holder.id_cat.setText(String.valueOf(clients.get(position).getId()));
        /*holder.prenom.setText(String.valueOf(clients.get(position).getPrenom()));
        holder.cin.setText(String.valueOf(clients.get(position).getCin()));
        holder.telephone.setText(String.valueOf(clients.get(position).getTelephone()));*/
    }

    @Override
    public int getItemCount() {
        return clients.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView designation,id_cat;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id_cat = itemView.findViewById(R.id.id_categorie);
            designation = itemView.findViewById(R.id.designation_categorie);
            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {
            listener.onClick(view, getAbsoluteAdapterPosition());
        }
    }
    public interface RecyclerViewClickListener{
        void onClick(View v, int position);
    }

}