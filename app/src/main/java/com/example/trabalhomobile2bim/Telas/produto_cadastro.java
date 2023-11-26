package com.example.trabalhomobile2bim.Telas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trabalhomobile2bim.Controller.CadastroSapatoController;
import com.example.trabalhomobile2bim.Objetos.Sapato;
import com.example.trabalhomobile2bim.R;
import com.example.trabalhomobile2bim.adapter.CustomAdapter;

import java.util.ArrayList;

public class produto_cadastro extends AppCompatActivity {

    private CadastroSapatoController controller;
    private TextView tvId;
    private EditText edNome;
    private EditText edValor;
    private EditText edTamanho;
    private EditText edTipo;
    private Button btnSalvar;
    private Button btnCancelar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto_cadastro);
        controller = new CadastroSapatoController(this);
        tvId  = findViewById(R.id.tvIdText);
        edNome  = findViewById(R.id.edNome);
        edValor  = findViewById(R.id.edValor);
        edTamanho  = findViewById(R.id.edTamanho);
        edTipo = findViewById(R.id.edTipo);
        btnCancelar = findViewById(R.id.btnCancelarFinal) ;
        btnSalvar = findViewById(R.id.btnSalvar);
        tvId.setText(String.valueOf(controller.IdSapato()));
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String retorno = controller.addSapato(edNome.getText().toString(), Integer.parseInt(edTamanho.getText().toString()),Double.parseDouble(edValor.getText().toString()), edTipo.getText().toString(), Integer.parseInt(tvId.getText().toString()));
              if(retorno == "")
              {
                  Toast.makeText(produto_cadastro.this, "Novo sapato salvo com sucesso", Toast.LENGTH_SHORT).show();
              }else{
                  Toast.makeText(produto_cadastro.this, retorno, Toast.LENGTH_SHORT).show();
              }
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(produto_cadastro.this,
                        TelaDeSelecaoDeProdutos.class);
                startActivity(intent);
            }
        });
    }
}