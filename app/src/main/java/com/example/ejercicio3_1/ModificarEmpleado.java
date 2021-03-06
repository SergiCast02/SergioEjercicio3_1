package com.example.ejercicio3_1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ejercicio3_1.modelo.SQLiteConexion;
import com.example.ejercicio3_1.modelo.Transacciones;

public class ModificarEmpleado extends AppCompatActivity {

    EditText nombres, apellidos, anios, puesto, direccion, codigo;
    TextView botonActualizar, botonEliminar, botonRegresar;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_empleado);

        codigo = findViewById(R.id.mtxtid);
        nombres = findViewById(R.id.mtxtNombre);
        apellidos = findViewById(R.id.mtxtApellidos);
        anios = findViewById(R.id.mtxtEdad);
        puesto = findViewById(R.id.mtxtpuesto);
        direccion = findViewById(R.id.mtxtDireccion);

        botonActualizar =  findViewById(R.id.mbtnActualizar);
        botonEliminar = findViewById(R.id.mbtnEliminar);
        botonRegresar = findViewById(R.id.btnVolver);

        codigo.setText(getIntent().getStringExtra("codigo"));
        nombres.setText(getIntent().getStringExtra("nombre"));
        apellidos.setText(getIntent().getStringExtra("apellidos"));
        anios.setText(getIntent().getStringExtra("anios"));
        puesto.setText(getIntent().getStringExtra("puesto"));
        direccion.setText(getIntent().getStringExtra("direccion"));

        botonActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualizarEmpleado();
            }
        });

        botonEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setTitle("Eliminar");
                alertDialogBuilder
                        .setMessage("??Desea eliminar a "+nombres.getText()+" "+apellidos.getText()+" ?")
                        .setCancelable(false)
                        .setPositiveButton("Si",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                eliminarEmpleado();
                                startActivity(new Intent(getApplicationContext(), ListarEmpleados.class));
                                finish();
                            }
                        })
                        .setNegativeButton("No",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });


                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

        botonRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ListarEmpleados.class));
                finish();
            }
        });
    }

    private void actualizarEmpleado() {
        SQLiteConexion conexion = new SQLiteConexion(this, Transacciones.NameDatabase, null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();
        String obtenerCodigo = codigo.getText().toString();

        ContentValues valores = new ContentValues();

        valores.put(Transacciones.nombres, nombres.getText().toString());
        valores.put(Transacciones.apellidos, apellidos.getText().toString());
        valores.put(Transacciones.anios, anios.getText().toString());
        valores.put(Transacciones.puesto, puesto.getText().toString());
        valores.put(Transacciones.direccion, direccion.getText().toString());

        db.update(Transacciones.tablaEmpleado, valores , Transacciones.id +" = "+obtenerCodigo, null);
        db.close();

        Toast.makeText(getApplicationContext(), "Registro actualizado con exito"
                ,Toast.LENGTH_LONG).show();

        startActivity(new Intent(getApplicationContext(), ListarEmpleados.class));
        finish();


    }

    private void eliminarEmpleado() {
        SQLiteConexion conexion = new SQLiteConexion(this, Transacciones.NameDatabase, null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();
        String obtenerCodigo = codigo.getText().toString();

        db.delete(Transacciones.tablaEmpleado,Transacciones.id +" = "+ obtenerCodigo, null);

        Toast.makeText(getApplicationContext(), "Registro eliminado con exito, Codigo " + obtenerCodigo
                ,Toast.LENGTH_LONG).show();
        db.close();
    }

}