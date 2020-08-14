package com.example.shoplist.bbdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.shoplist.modelo.Producto;
import com.example.shoplist.util.Herramienta;

public class SQLClass {

    private static final String NOMBRE_BD = "BBDD";
    private static final int VERSION_BD = 1;


    public static void insertarNuevoProductoCatalogo(Producto producto,Context context){
        ControladorBD controlador = new ControladorBD(context, NOMBRE_BD, null,VERSION_BD);
        SQLiteDatabase bd = controlador.getWritableDatabase();
        ContentValues contenido = new ContentValues();
        contenido.put("id", producto.getId());
        contenido.put("nombre",producto.getNombre());
        contenido.put("imagen", Herramienta.bitmapToBase64(producto.getImagen()));
        bd.insert("PRODUCTO", null, contenido);
        bd.close();
        controlador.close();

    }
}
