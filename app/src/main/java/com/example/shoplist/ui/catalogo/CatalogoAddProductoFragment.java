package com.example.shoplist.ui.catalogo;



import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.example.shoplist.R;
import com.example.shoplist.bbdd.SQLClass;
import com.example.shoplist.modelo.Producto;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import java.io.IOException;

public class CatalogoAddProductoFragment extends Fragment {

    private ImageView ivProducto;
    private TextInputLayout tvNombreProducto;
    private RelativeLayout btnAddProducto;
    private FloatingActionButton fabCamara;
    private static final int GALERIA = 1;
    private static final int CAMARA = 2;


    public static CatalogoAddProductoFragment newInstance() {
        return new CatalogoAddProductoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.catalogo_add_producto_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        llamarVistas();

    }

    private void llamarVistas() {
        ivProducto = getView().findViewById(R.id.ivNuevoProducto);
        tvNombreProducto = getView().findViewById(R.id.tvNuevoProducto);
        btnAddProducto = getView().findViewById(R.id.btnAddProducto);
        fabCamara = getView().findViewById(R.id.fabNuevoProductoCamara);
        setListeners();
    }

    private void setListeners(){
        btnAddProducto.setOnClickListener(listener);
        fabCamara.setOnClickListener(listener);
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.fabNuevoProductoCamara:
                    gestionImagen();
                    break;
                case R.id.btnAddProducto:
                    if (comprobarCampos()){
                        Producto producto = new Producto(tvNombreProducto.getEditText().getText().toString(),
                                ((BitmapDrawable)ivProducto.getDrawable()).getBitmap());
                        addProducto(producto);
                    }else{
                        Toast.makeText(getContext(), "Debes añadir una imagen y un nombre", Toast.LENGTH_LONG).show();
                    }
                    break;
                default:
                    break;
            }

        }
    };

    private boolean comprobarCampos(){
        boolean correcto = true;
       Bitmap img = ((BitmapDrawable)ivProducto.getDrawable()).getBitmap();
       if (img == null || tvNombreProducto.getEditText().getText().toString().isEmpty()){
           correcto = false;
       }
       return correcto;
    }

    private void gestionImagen() {
        AlertDialog.Builder fotoDialogo = new AlertDialog.Builder(getContext());
        fotoDialogo.setTitle("Elige un método de entrada");
        String[] fotoDialogoItems = {
                "Seleccionar fotografía de galería",
                "Capturar fotografía desde la cámara"};
        fotoDialogo.setItems(fotoDialogoItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                elegirFotoGaleria();
                                break;
                            case 1:
                                tomarFotoCamara();
                                break;
                        }
                    }
                });
        fotoDialogo.show();
    }

    private void elegirFotoGaleria(){
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, GALERIA);
    }


    private void tomarFotoCamara(){
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMARA);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALERIA) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), contentURI);
                    this.ivProducto.setImageBitmap(bitmap);
                } catch (IOException e) {
                    Toast.makeText(getContext(), "Fallo en la galería", Toast.LENGTH_LONG).show();
                }
            }
        } else if (requestCode == CAMARA) {
            Bitmap thumbnail = null;
            try {
                thumbnail = (Bitmap) data.getExtras().get("data");
                this.ivProducto.setImageBitmap(thumbnail);
            } catch (Exception e) {
                Toast.makeText(getContext(), "Fallo en la cámara", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void addProducto (Producto producto){
        SQLClass.insertarNuevoProductoCatalogo(producto, getContext());

    }
}
