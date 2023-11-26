package com.example.trabalhomobile2bim.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabalhomobile2bim.Controller.SelecaoDeProdutoController;
import com.example.trabalhomobile2bim.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trabalhomobile2bim.Objetos.Sapato;
import com.example.trabalhomobile2bim.Telas.TelaDeSelecaoDeProdutos;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private ArrayList<Sapato> listaSapato;
    private Context context;
    private SelecaoDeProdutoController controller;
    public CustomAdapter(ArrayList<Sapato> listaSapato, Context context){
        this.listaSapato = listaSapato;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.activity_adapter, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        controller= new SelecaoDeProdutoController(context);
        Sapato sapato = listaSapato.get(position);
        holder.TvNome.setText(sapato.getTipo()+" "+ sapato.getNome());
        holder.TvTamanho.setText(String.valueOf(sapato.getTamanho()));
        holder.TvValor.setText("R$ :" + sapato.getValor().toString());

        holder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String retorno = controller.AdicionarALista(sapato.getNome().toString(), sapato.getTipo().toString(),sapato.getTamanho(), sapato.getValor());
                if(retorno == ""){
                    Toast.makeText(context, "Produto Adicionado", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, retorno, Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return this.listaSapato.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView TvNome;
        TextView TvTamanho;
        ImageButton btnAdd;
        TextView TvValor;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.TvNome = itemView.findViewById(R.id.tvNome);
            this.TvTamanho = itemView.findViewById(R.id.tvTamanho);
            this.TvValor = itemView.findViewById(R.id.tvValor);
            this.btnAdd = itemView.findViewById(R.id.btnAdd);
        }

    }




}