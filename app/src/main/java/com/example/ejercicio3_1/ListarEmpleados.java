package com.example.ejercicio3_1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ejercicio3_1.modelo.Empleados;
import com.example.ejercicio3_1.modelo.SQLiteConexion;
import com.example.ejercicio3_1.modelo.Transacciones;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ListarEmpleados extends AppCompatActivity {
    SQLiteConexion conexion;
    ListView lista;
    ArrayList <Empleados> listaEmpleado;
    ArrayList <String> ArregloPersona;
    TextView botonVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_empleados);

        lista = findViewById(R.id.listaEmpleados);
        botonVolver = findViewById(R.id.btnatras);
        conexion = new SQLiteConexion(this, Transacciones.NameDatabase,null,1);
        obtenerlistaEmpleado();
        
        ArrayAdapter adp = new ArrayAdapter(this, android.R.layout.simple_list_item_checked,ArregloPersona);
        lista.setAdapter(adp);

       lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               ObtenerEmpleado(i);
           }
       });

       botonVolver.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               finish();
           }
       });
    }

    private void ObtenerEmpleado(int id) {
        Empleados empleado = listaEmpleado.get(id);

        Intent intent = new Intent(getApplicationContext(),ModificarEmpleado.class);

        intent.putExtra("codigo", empleado.getId()+"");
        intent.putExtra("nombre", empleado.getNombre());
        intent.putExtra("apellidos", empleado.getApellidos());
        intent.putExtra("anios", empleado.getEdad());
        intent.putExtra("puesto", empleado.getPuesto());
        intent.putExtra("direccion", empleado.getDireccion());

        startActivity(intent);
        finish();
    }


    private void obtenerlistaEmpleado() {
        SQLiteDatabase db = conexion.getReadableDatabase();
        Empleados lista_empleado = null;
        listaEmpleado = new ArrayList<Empleados>();
        Cursor cursor = db.rawQuery("SELECT * FROM "+ Transacciones.tablaEmpleado, null);
        
        while (cursor.moveToNext())
        {
            lista_empleado = new Empleados();
            lista_empleado.setId(cursor.getInt(0));
            lista_empleado.setNombre(cursor.getString(1));
            lista_empleado.setApellidos(cursor.getString(2));
            lista_empleado.setEdad(cursor.getString(3));
            lista_empleado.setDireccion(cursor.getString(4));
            lista_empleado.setPuesto(cursor.getString(5));
            listaEmpleado.add(lista_empleado);
        }
        cursor.close();
        
        llenarlista();
    }
    private void llenarlista()
    {
        ArregloPersona = new ArrayList<String>();
        for (int i = 0; i< listaEmpleado.size(); i++)
        {
            ArregloPersona.add(listaEmpleado.get(i).getId()+" "+
                    listaEmpleado.get(i).getNombre()+" "+
                    listaEmpleado.get(i).getApellidos()+" | "+
                    listaEmpleado.get(i).getEdad()+" | "+
                    listaEmpleado.get(i).getDireccion()+" | "+
                    listaEmpleado.get(i).getPuesto());
        }
    }
}