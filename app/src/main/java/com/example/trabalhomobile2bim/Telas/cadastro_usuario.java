package com.example.trabalhomobile2bim.Telas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trabalhomobile2bim.Controller.CadastroUserController;
import com.example.trabalhomobile2bim.R;

public class cadastro_usuario extends AppCompatActivity {

    private EditText edNome;
    private EditText edSobrenome;
    private EditText edSenha;
    private TextView tvMatricula ;
    private Button btnSalvar;
    private  Button btnCancelar;
    private CadastroUserController controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);
        controller = new CadastroUserController(this);
        edSenha = findViewById(R.id.edSenha);
        edNome = findViewById(R.id.edNome);
        edSobrenome = findViewById(R.id.edSobrenome);
        tvMatricula = findViewById(R.id.tvMatriculaReal);
        btnCancelar = findViewById(R.id.btnCancelarFinal);
        btnSalvar = findViewById(R.id.btnSalvar);
        tvMatricula.setText(String.valueOf(controller.GetMatricula()));

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.CadastrarUsuario(Integer.parseInt(tvMatricula.getText().toString()), edNome.getText().toString(), edSobrenome.getText().toString(),edSenha.getText().toString());
                Toast.makeText(cadastro_usuario.this, "Novo usuario cadastrado com sucesso", Toast.LENGTH_SHORT).show();
                edNome.setText("");
                edSenha.setText("");
                edSobrenome.setText("");
                tvMatricula.setText(String.valueOf(controller.GetMatricula()));
            }
        });
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(cadastro_usuario.this,
                        LogInScream.class);
                startActivity(intent);
            }
        });
    }
}