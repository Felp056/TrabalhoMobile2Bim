package com.example.trabalhomobile2bim.Telas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.trabalhomobile2bim.Controller.TelaDeLogInController;
import com.example.trabalhomobile2bim.R;

public class LogInScream extends AppCompatActivity {

    private EditText edMatricula;
    private EditText edSenha;
    private Button btnLogIn;
    private Button btnNewUser;
    private TelaDeLogInController controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginscream);
        controller = new TelaDeLogInController(this);
        edSenha = findViewById(R.id.edSenha);
        edMatricula = findViewById(R.id.edMatricula);
        btnLogIn = findViewById(R.id.btnLogIn);
        btnNewUser = findViewById(R.id.btnNewUser);

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String retorno =  controller.LogIn(Integer.parseInt(edMatricula.getText().toString()), edSenha.getText().toString());
                if(retorno == ""){
                    Intent intent = new Intent(LogInScream.this,
                            TelaDeSelecaoDeProdutos.class);
                    startActivity(intent);
                }else{
                    edMatricula.setError(retorno);
                }
            }
        });
        btnNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogInScream.this,
                        cadastro_usuario.class);
                startActivity(intent);
            }
        });

    }
}