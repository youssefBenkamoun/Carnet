package ma.project.carnet.crud.credit;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.concurrent.ExecutionException;

import ma.project.carnet.R;
import ma.project.carnet.classes.Categorie;
import ma.project.carnet.classes.Client;
import ma.project.carnet.classes.Credit;
import ma.project.carnet.classes.Produit;
import ma.project.carnet.services.CategorieService;
import ma.project.carnet.services.ClientService;
import ma.project.carnet.services.CreditService;
import ma.project.carnet.services.ProduitService;


public class CreditAdapter extends RecyclerView.Adapter<CreditAdapter.CreditViewHolder> {
    private static final String TAG = "CreditAdapter";
    private List<Credit> credits;
    private Context context;
    ;

    public CreditAdapter(Context context, List<Credit> credits) {
        this.credits = credits;
        this.context = context;
    }

    @NonNull
    @Override
    public CreditViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.credit_item, viewGroup, false);
        final ImageView delete = view.findViewById(R.id.delete);
        final TextView id = view.findViewById(R.id.ids);



        CreditService cs = new CreditService(view.getContext());
        ProduitService ps = ProduitService.getProduitService(view.getContext());
        ClientService cls =ClientService.getClientService(view.getContext());
        CategorieService cat =CategorieService.getCategorieService(view.getContext());



        final CreditViewHolder holder = new CreditViewHolder(view);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                Log.d(TAG, "onDelete: "+id.getText().toString());

                cs.delete(cs.findById(Integer.parseInt(id.getText().toString())));
                credits = cs.findAll();
                notifyItemRemoved(holder.getBindingAdapterPosition());


            }
        });


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View popup = LayoutInflater.from(context).inflate(R.layout.credit_edit_item, null, false);
                final TextView idss = popup.findViewById(R.id.idsss);
                final TextView client = popup.findViewById(R.id.client);
                final TextView product = popup.findViewById(R.id.product);
                final TextView price = popup.findViewById(R.id.prix);


                idss.setText(((TextView) v.findViewById(R.id.ids)).getText().toString());

                Credit credit = cs.findById(Integer.parseInt(idss.getText().toString()));
                Client rclient = cls.findById(credit.getClient());
                Produit product1 = ps.findById(credit.getProduct());

                client.setText(rclient.getNom()+" "+rclient.getPrenom());
                product.setText(product1.getLibelle());
                price.setText(credit.getPrix()+" DH");
                AlertDialog dialog = new AlertDialog.Builder(context).setTitle("Modifier : ").setMessage("Les informatio sur le credit numero "+credit.getId()+" :").setView(popup).setPositiveButton("Payé", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        credit.setEtat("Payé");
                        cs.update(credit);
                        credits = cs.findAll();
                        notifyItemChanged(holder.getBindingAdapterPosition());
                    }
                }).setNegativeButton("Annuler", null).create();
                dialog.show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CreditViewHolder creditViewHolder, int i) {
        Log.d(TAG, "onBindView call ! " + i);
        creditViewHolder.prix.setText(credits.get(i).getPrix() + "");
        creditViewHolder.etat.setText(credits.get(i).getEtat());
        creditViewHolder.date.setText(credits.get(i).getDate() + "");
        creditViewHolder.idss.setText(credits.get(i).getId() + "");

    }

    @Override
    public int getItemCount() {
        return credits.size();
    }

    public class CreditViewHolder extends RecyclerView.ViewHolder {
        TextView idss;
        TextView prix;
        TextView etat;
        TextView date;
        ConstraintLayout parent;

        public CreditViewHolder(@NonNull View itemView) {
            super(itemView);
            idss = itemView.findViewById(R.id.ids);
            prix = itemView.findViewById(R.id.client);
            etat = itemView.findViewById(R.id.product);
            date = itemView.findViewById(R.id.prix);
            parent = itemView.findViewById(R.id.parent);
        }
    }
}

