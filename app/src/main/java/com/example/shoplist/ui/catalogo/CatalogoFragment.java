package com.example.shoplist.ui.catalogo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoplist.R;
import com.example.shoplist.adapters.CatalogoAdapter;
import com.example.shoplist.modelo.Producto;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class CatalogoFragment extends Fragment {


    //Listado de productos que va a manejar el catálogo
    private ArrayList<Producto> listaProductos = new ArrayList<>();

    //Vistas
    private RecyclerView rvCatalogoProductos;
    private FloatingActionButton fabCatalogoAddProducto;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        llamarVistas();
    }

    private void llamarVistas(){
        rvCatalogoProductos = getView().findViewById(R.id.recyclerCatalogo);
        fabCatalogoAddProducto = getView().findViewById(R.id.fabCatalogoAddProducto);
        setListeners();
        initRecyclerView();
    }

    private void setListeners(){
        fabCatalogoAddProducto.setOnClickListener(listeners);
    }

    private void initRecyclerView(){
        rvCatalogoProductos.setLayoutManager(new LinearLayoutManager(getContext()));
        //Insertamos el método para sacar la lista de la BBDD
        // listaProductos = listarProductos();
        rvCatalogoProductos.setAdapter(new CatalogoAdapter(listaProductos));
    }

    private View.OnClickListener listeners = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.fabCatalogoAddProducto:
                    //Transaccion al fragment para añadir un producto
                    break;
                default:
                    break;
            }
        }
    };

    /**
     * private ArrayList<Producto> listarProductos (){}
     */
}