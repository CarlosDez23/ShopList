package com.example.shoplist.bbdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.shoplist.modelo.Producto;
import com.example.shoplist.util.Herramienta;

import java.util.ArrayList;

public class SQLClass {

    private static final String NOMBRE_BD = "BBDD";
    private static final int VERSION_BD = 1;

    /**
     * Método para insertar un producto en la tabla productos
     * @param producto
     * @param context
     */
    public static void insertarNuevoProductoCatalogo(Producto producto,Context context){
        ControladorBD controlador = new ControladorBD(context, NOMBRE_BD, null,VERSION_BD);
        SQLiteDatabase bd = controlador.getWritableDatabase();
        ContentValues contenido = new ContentValues();
        contenido.put("nombre",producto.getNombre());
        contenido.put("imagen", Herramienta.bitmapToBase64(producto.getImagen()));
        bd.insert("PRODUCTO", null, contenido);
        bd.close();
        controlador.close();
    }

    /**
     * Método para borrar un producto de la tabla productos
     * @param idProducto
     * @param context
     */
    public static void borrarProducto(int idProducto, Context context){
        ControladorBD controladorBD = new ControladorBD(context,NOMBRE_BD,null,VERSION_BD);
        SQLiteDatabase bd = controladorBD.getWritableDatabase();
        bd.delete("PRODUCTO", "id="+idProducto, null);
        bd.close();
        controladorBD.close();
    }

    /**
     * Método para listar todos los productos que se encuentran en la tabla
     * @param context
     * @return
     */
    public static ArrayList<Producto> consultaProductosCatalogo(Context context){
        ControladorBD controladorBD = new ControladorBD(context, NOMBRE_BD, null, VERSION_BD);
        SQLiteDatabase db = controladorBD.getReadableDatabase();
        ArrayList<Producto> listaProductos = null;
        Producto producto;
        String[] campos = new String[]{"id", "nombre", "imagen"};
        Cursor c = db.query("PRODUCTO", campos, null, null,null,null,null);
        if (c.moveToFirst()) {
            listaProductos = new ArrayList<>();
            do {
                producto = new Producto(c.getInt(0), c.getString(1), Herramienta.base64ToBitmap(c.getString(2)));
                listaProductos.add(producto);
            } while (c.moveToNext());
        }
        db.close();
        controladorBD.close();
        return listaProductos;
    }
}
