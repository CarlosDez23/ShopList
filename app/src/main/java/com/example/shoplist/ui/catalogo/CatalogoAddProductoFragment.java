package com.example.shoplist.ui.catalogo;



import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;


import com.example.shoplist.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

public class CatalogoAddProductoFragment extends Fragment {

    private ImageView ivProducto;
    private TextInputLayout tvNombreProducto;
    private RelativeLayout btnAddProducto;
    private FloatingActionButton fabCamara;


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
        ivProducto = getView().findViewById(R.id.ivCatalogoProductoImagen);
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
                    //CAMARA
                    break;
                case R.id.btnAddProducto:
                    //INSERT
                    break;
                default:
                    break;
            }

        }
    };

}
