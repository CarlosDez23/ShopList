package com.example.shoplist.ui.catalogo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoplist.R;
import com.example.shoplist.adapters.CatalogoAdapter;
import com.example.shoplist.bbdd.SQLClass;
import com.example.shoplist.modelo.Producto;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class CatalogoFragment extends Fragment {


    //Listado de productos que va a manejar el catálogo
    private ArrayList<Producto> listaProductos;

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
        listaProductos = SQLClass.consultaProductosCatalogo(getContext());
        if (listaProductos == null){
            listaProductos = new ArrayList<>();
        }
        rvCatalogoProductos.setAdapter(new CatalogoAdapter(listaProductos, getContext()));
    }

    private View.OnClickListener listeners = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.fabCatalogoAddProducto:
                    CatalogoAddProductoFragment fragmentAddProducto = new CatalogoAddProductoFragment();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.nav_host_fragment, fragmentAddProducto);
                    transaction.addToBackStack(null);
                    transaction.commit();
                    break;
                default:
                    break;
            }
        }
    };
}