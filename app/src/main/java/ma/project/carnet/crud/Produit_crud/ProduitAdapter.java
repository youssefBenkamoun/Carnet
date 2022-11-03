package ma.project.carnet.crud.Produit_crud;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ma.project.carnet.R;
import ma.project.carnet.classes.Produit;
import ma.project.carnet.services.ProduitService;

public class ProduitAdapter extends RecyclerView.Adapter<ProduitAdapter.MyViewHolder> {
    List<String> args;
    List<Produit> clients;
    private Context context;
    private ImageView red_delete;



    public ProduitAdapter(List<Produit> clients){

        this.clients = clients;

    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.row_produit,parent,false);

        //------------------------


        final MyViewHolder holder = new MyViewHolder(v);
        red_delete = v.findViewById(R.id.delete_produit);
        TextView row_id = v.findViewById(R.id.id_produit);

        red_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TextView client_row = view.findViewById(R.id.client_row);
                int id_dlt = Integer.parseInt(row_id.getText().toString());
                Log.d("deleting",id_dlt+"");
                Produit client2 = ProduitService.getProduitService(context).findById(id_dlt);
                ProduitService.getProduitService(context).delete(client2);
                clients = ProduitService.getProduitService(context).findAll();
                notifyItemRemoved(holder.getBindingAdapterPosition());
                Toast.makeText(view.getContext(), "Client deleted successfully !!",Toast.LENGTH_LONG).show();
            }
        });


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View popup = LayoutInflater.from(inflater.getContext()).inflate(R.layout.popup_produit_edit, null, false);
                final EditText price_edit = popup.findViewById(R.id.edit_price);
                final TextView client_row = v.findViewById(R.id.id_produit);
                final TextView libelle = v.findViewById(R.id.libelle_produit);
                final TextView categorie = v.findViewById(R.id.categorie_produit);

                price_edit.setText(((TextView) v.findViewById(R.id.prix_produit)).getText().toString());

                AlertDialog dialog = new AlertDialog.Builder(popup.getContext()).setTitle("Update : ").setMessage("update the price of Product :").setView(popup).setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        int ids = Integer.parseInt(client_row.getText().toString());
                        Double new_nom = Double.parseDouble(price_edit.getText().toString());
                        String libelle_product = (String) libelle.getText().toString();
                        int catProduct = Integer.parseInt(categorie.getText().toString());
                        Produit client1 = ProduitService.getProduitService(context).findById(ids);
                        client1.setPrix(new_nom);
                        /*client1.setIdCategory(catProduct);
                        client1.setLibelle(libelle_product);*/
                        ProduitService.getProduitService(context).update(client1);
                        clients = ProduitService.getProduitService(context).findAll();
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
        holder.libelle.setText(String.valueOf(clients.get(position).getLibelle()));
        holder.cat.setText(String.valueOf(clients.get(position).getIdCategory()));
        holder.prix.setText(String.valueOf(clients.get(position).getPrix()));
        holder.id_pro.setText(String.valueOf(clients.get(position).getId()));
        /*holder.prenom.setText(String.valueOf(clients.get(position).getPrenom()));
        holder.cin.setText(String.valueOf(clients.get(position).getCin()));
        holder.telephone.setText(String.valueOf(clients.get(position).getTelephone()));*/
    }

    @Override
    public int getItemCount() {
        return clients.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView libelle,id_pro,prix,cat;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cat = itemView.findViewById(R.id.categorie_produit);
            libelle = itemView.findViewById(R.id.libelle_produit);
            prix = itemView.findViewById(R.id.prix_produit);
            id_pro = itemView.findViewById(R.id.id_produit);



        }
    }
}