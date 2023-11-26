package com.example.trabalhomobile2bim.Telas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.trabalhomobile2bim.Controller.NotaController;
import com.example.trabalhomobile2bim.R;

public class TelaDeNota extends AppCompatActivity {

    private TextView tvExibir;
    private  TextView edDaCompra;
    private Button btnFinalizar;
    private NotaController controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_de_nota);
        controller = new NotaController(this);
        tvExibir = findViewById(R.id.tvExibir);
        edDaCompra = findViewById(R.id.edIdDaCompra);
        btnFinalizar = findViewById(R.id.btnFinalizar);
        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvExibir.setText(controller.RetornaNotaPeloId(Integer.parseInt(edDaCompra.getText().toString().trim())));
            }
        });
    }
}