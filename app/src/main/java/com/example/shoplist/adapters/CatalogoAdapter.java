package com.example.shoplist.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoplist.R;
import com.example.shoplist.bbdd.SQLClass;
import com.example.shoplist.modelo.Producto;
import com.example.shoplist.ui.catalogo.CatalogoFragment;
import com.example.shoplist.util.Herramienta;

import java.util.ArrayList;

public class CatalogoAdapter extends RecyclerView.Adapter<CatalogoAdapter.ViewHolder> {

    private ArrayList<Producto> listaProductos = new ArrayList<>();
    private Context context;
    private CatalogoFragment catalogo;

    public CatalogoAdapter(ArrayList<Producto> listaProductos, Context context, CatalogoFragment catalogo) {
        this.listaProductos = listaProductos;
        this.context = context;
        this.catalogo = catalogo;
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
        holder.ivBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogEliminar(productoLista);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }

    private void dialogEliminar(final Producto producto){
        AlertDialog.Builder dialogo = new AlertDialog.Builder(context);
        dialogo.setTitle("¿Quieres eliminar este producto?");
        String[] opciones = {"Si", "No"};
        dialogo.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0:
                        SQLClass.borrarProducto(producto.getId(),context);
                        Herramienta.mostrarAvisto("Producto eliminado del catálogo",context);
                        catalogo.initRecyclerView();
                        break;
                    case 1:
                        Herramienta.mostrarAvisto("El producto no se eliminará",context);
                        break;
                    default:
                        break;
                }
            }
        });
        dialogo.show();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivCatalogoProductosImagen;
        private TextView tvCatalogoProductosNombre;
        private CardView cardCatalogoProducto;
        private ImageView ivBorrar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.ivCatalogoProductosImagen = itemView.findViewById(R.id.ivCatalogoProductoImagen);
            this.tvCatalogoProductosNombre = itemView.findViewById(R.id.tvCatalogoProductoNombre);
            this.ivBorrar = itemView.findViewById(R.id.ivBorrar);
            cardCatalogoProducto = itemView.findViewById(R.id.cardCatalogoProducto);
        }
    }
}
