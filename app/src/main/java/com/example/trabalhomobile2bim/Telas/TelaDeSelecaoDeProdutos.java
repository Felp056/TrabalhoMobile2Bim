package com.example.trabalhomobile2bim.Telas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.trabalhomobile2bim.Controller.NotaController;
import com.example.trabalhomobile2bim.Controller.SelecaoDeProdutoController;
import com.example.trabalhomobile2bim.DAO.NotaDeVendaDao;
import com.example.trabalhomobile2bim.Objetos.Sapato;
import com.example.trabalhomobile2bim.R;
import com.example.trabalhomobile2bim.adapter.CustomAdapter;

import java.util.ArrayList;

public class TelaDeSelecaoDeProdutos extends AppCompatActivity {

    private TextView tvId;
    private SelecaoDeProdutoController controller;
    private NotaController controller2;
    private Button btnCarrinho;
    private Button btnNewSapato;
    private RecyclerView rvLista;
    private  TextView TvQntItensCarrinho;
    private  Button btnNewCompra;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_de_selecao_de_produtos);
        controller = new SelecaoDeProdutoController(this);
        controller2 = new NotaController(this);
        tvId = findViewById(R.id.tvIdvenda);
        tvId.setText(String.valueOf(controller.NovaVenda()));
        controller.SetIdCompra(Integer.parseInt(tvId.getText().toString()));
        btnCarrinho = findViewById(R.id.btnCarrinho);
        btnNewSapato= findViewById(R.id.btnNewSapato);
        rvLista = findViewById(R.id.rvLista);
        TvQntItensCarrinho = findViewById(R.id.TvQntItensCarrinho);
        btnNewCompra = findViewById(R.id.btnNewCompra);
        atualizarListaSapatos();
        btnNewCompra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                tvId.setText(String.valueOf(controller.NovaVenda()));
                controller.SetIdCompra(controller.NovaVenda());
            }
        });
        btnCarrinho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TelaDeSelecaoDeProdutos.this,
                        TelaDeNota.class);
                startActivity(intent);
            }
        });

        btnNewSapato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TelaDeSelecaoDeProdutos.this, produto_cadastro.class);
                startActivity(intent);
            }
        });
    }
    private void atualizarListaSapatos(){
        ArrayList<Sapato> listaSapato = controller.getAll();
        CustomAdapter adapter = new CustomAdapter(listaSapato, this);
        rvLista.setLayoutManager(new LinearLayoutManager(this));
        rvLista.setAdapter(adapter);

    }
}