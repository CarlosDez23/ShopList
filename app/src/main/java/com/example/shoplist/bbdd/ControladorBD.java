package com.example.shoplist.bbdd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ControladorBD extends SQLiteOpenHelper {

    private static final String CREATE_TABLE_PRODUCTO =
            "CREATE TABLE PRODUCTO (" +
                    "id INTEGER," +
                    "nombre VARCHAR(255) NOT NULL, " +
                    "imagen BLOB NOT NULL);";

    private static final String CREATE_TABLE_COMPRAS =
            "CREATE TABLE COMPRA (" +
                    "id INTEGER," +
                    "fecha VARCHAR(255) NOT NULL);";

    private static final String CREATE_TABLE_LINEA_COMPRA =
            "CREATE TABLE LINEA_COMPRA (" +
                    "idcompra INTEGER NOT NULL," +
                    "idproducto INTEGER NOT NULL);";

    public ControladorBD(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_PRODUCTO);
        db.execSQL(CREATE_TABLE_COMPRAS);
        db.execSQL(CREATE_TABLE_LINEA_COMPRA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
}
