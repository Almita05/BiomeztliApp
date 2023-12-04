package com.example.biomeztliapp;

import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.biomeztliapp.ui.dashboard.DashboardFragment;
import com.example.biomeztliapp.ui.home.HomeFragment;
import com.example.biomeztliapp.ui.notifications.NotificationsFragment;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;


import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainAdapter extends FirebaseRecyclerAdapter<MainModel, MainAdapter.MyViewHolder> {

    private OnItemClickListener mListener;
    private OnItemClickListenerForActivity4 mActivity4Listener;
    private Fragment mFragment; // Agregamos una referencia al fragmento

    private FirebaseRecyclerOptions<MainModel> options; // Inicializar aquí



    public MainAdapter(@NonNull FirebaseRecyclerOptions<MainModel> options, Fragment fragment) {
        super(options);
        mFragment = fragment;
        this.options = options; // Inicializar options
    }


    public interface OnItemClickListener {
        void onItemClick(String imageUrl, String nombre, String descripcion, String propiedades, String uso, String precaucion);
    }

    public interface OnItemClickListenerForActivity4 {
        void onItemClickForActivity4(String ingredientes, String modoPreparacion);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public void setOnItemClickListenerForActivity4(OnItemClickListenerForActivity4 listener) {
        mActivity4Listener = listener;
    }

    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull MainModel model) {
        holder.nombre.setText(model.getNombre());

        // Glide para cargar la imagen desde la base de datos en lugar de la URL
        Glide.with(holder.img.getContext())
                .load(Uri.parse(model.getImagen()))  // URL de la imagen desde la base de datos
                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error(com.google.firebase.database.R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.img);

        // Actualizar el estado del corazón (favorito)
        updateHeartIcon(holder.imgfv, model.getFavorito());

        // Manejar clic en el corazón (favorito)
        // Manejar clic en el corazón (favorito)
        if (shouldShowFavoritesIcon()) {
            holder.imgfv.setVisibility(View.VISIBLE);
            holder.imgfv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Cambiar el estado de favorito en el modelo de datos
                    boolean newFavoritoState = !model.getFavorito();
                    model.setFavorito(newFavoritoState);

                    // Actualizar la vista del icono del corazón
                    updateHeartIcon(holder.imgfv, newFavoritoState);

                    // Mostrar el mensaje correspondiente
                    String mensaje = newFavoritoState ? "Agregado a favoritos" : "Eliminado de favoritos";
                    Toast.makeText(v.getContext(), mensaje, Toast.LENGTH_SHORT).show();

                    // Cambiar el estado de favorito en la base de datos Firebase
                    getRef(position).child("favorito").setValue(newFavoritoState);
                }
            });
        } else {
            holder.imgfv.setVisibility(View.GONE);
        }


            // Onclick en la imagen para ir a la actividad 3
            holder.img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent;

                    // Verificar en qué fragmento estamos y decidir a qué actividad dirigirse
                    if (mFragment instanceof HomeFragment || mFragment instanceof NotificationsFragment) {
                        intent = new Intent(v.getContext(), MainActivity3.class);
                    } else if (mFragment instanceof DashboardFragment) {
                        intent = new Intent(v.getContext(), MainActivity4.class);
                    } else {
                        return; // No especificaste qué hacer en este caso
                    }

                    // Añadir extras al intent
                    intent.putExtra("IMAGE_URL", model.getImagen());
                    intent.putExtra("NOMBRE", model.getNombre());
                    intent.putExtra("DESCRIPCION", model.getDescripcion());
                    intent.putExtra("PROPIEDADES", model.getPropiedades());
                    intent.putExtra("USO", model.getUso());
                    intent.putExtra("PRECAUCION", model.getPrecaucion());
                    intent.putExtra("INGREDIENTES", model.getIngredientes());
                    intent.putExtra("PREPARACION", model.getModoPreparacion());

                    v.getContext().startActivity(intent);
                }
            });

            // Clic largo en la imagen para manejar Activity4
            holder.img.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (mActivity4Listener != null) {
                        // Agrega la información adicional necesaria para Activity4
                        mActivity4Listener.onItemClickForActivity4(model.getIngredientes(), model.getModoPreparacion());
                    }
                    return true;
                }
            });
        }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item, parent, false);
        return new MyViewHolder(view);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        CircleImageView img;
        TextView nombre;
        ImageView imgfv;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img1);
            nombre = itemView.findViewById(R.id.nameText);
            imgfv = itemView.findViewById(R.id.imgfav);
        }
    }

    // Método para actualizar la vista del icono del corazón según el estado de favorito
    private void updateHeartIcon(ImageView imgfav, boolean isFavorito) {
        int iconResource = isFavorito ? R.drawable.favorito : R.drawable.favorito_no;
        imgfav.setImageResource(iconResource);
    }

    //Método para poder ocultar el icono de favoritos
    private boolean shouldShowFavoritesIcon() {
        return !(mFragment instanceof DashboardFragment);
    }

}
