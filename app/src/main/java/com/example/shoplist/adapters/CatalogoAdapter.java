package com.example.shoplist.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoplist.R;
import com.example.shoplist.modelo.Producto;

import java.util.ArrayList;

public class CatalogoAdapter extends RecyclerView.Adapter<CatalogoAdapter.ViewHolder> {

    private ArrayList<Producto> listaProductos = new ArrayList<>();

    public CatalogoAdapter(ArrayList<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemListaProductos = layoutInflater.inflate(R.layout.item_catalogo,parent,false);
        ViewHolder viewHolder = new ViewHolder(itemListaProductos);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Producto productoLista = listaProductos.get(position);
        holder.tvCatalogoProductosNombre.setText(productoLista.getNombre());
        holder.ivCatalogoProductosImagen.setImageBitmap(productoLista.getImagen());

    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivCatalogoProductosImagen;
        private TextView tvCatalogoProductosNombre;
        private CardView cardCatalogoProducto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.ivCatalogoProductosImagen = itemView.findViewById(R.id.ivCatalogoProductoImagen);
            this.tvCatalogoProductosNombre = itemView.findViewById(R.id.tvCatalogoProductoNombre);
            cardCatalogoProducto = itemView.findViewById(R.id.cardCatalogoProducto);
        }
    }
}
