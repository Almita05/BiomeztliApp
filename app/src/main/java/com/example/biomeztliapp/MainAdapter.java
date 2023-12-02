package com.example.biomeztliapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.biomeztliapp.ui.dashboard.DashboardFragment;
import com.example.biomeztliapp.ui.home.HomeFragment;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainAdapter extends FirebaseRecyclerAdapter<MainModel, MainAdapter.MyViewHolder> {

    private OnItemClickListener mListener;
    private OnItemClickListenerForActivity4 mActivity4Listener;
    private Fragment mFragment; // Agregamos una referencia al fragmento

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

    public MainAdapter(@NonNull FirebaseRecyclerOptions<MainModel> options, Fragment fragment) {
        super(options);
        mFragment = fragment;
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

        // Verifica si imgfav está presente en el diseño antes de intentar establecer el OnClickListener
        if (holder.imgfav != null) {
            // Actualizar el estado del corazón (favorito)
            updateHeartIcon(holder.imgfav, model.getFavorito());

            // Manejar clic en el corazón (favorito)
            holder.imgfav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Cambiar el estado de favorito en el modelo de datos
                    boolean newFavoritoState = !model.getFavorito();
                    model.setFavorito(newFavoritoState);

                    // Actualizar la vista del icono del corazón
                    updateHeartIcon(holder.imgfav, newFavoritoState);

                    // Además, puedes guardar el cambio en la base de datos Firebase si es necesario
                    getRef(position).child("favorito").setValue(newFavoritoState);
                }
            });
        }

        // Resto del código...
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item, parent, false);
        return new MyViewHolder(view);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        CircleImageView img;
        ImageView imgfav; // Nuevo ImageView para el corazón (favorito) en activity_main3.xml
        TextView nombre;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img1);
            imgfav = itemView.findViewById(R.id.imgfav); // Puede ser nulo si no está presente en el diseño actual
            nombre = itemView.findViewById(R.id.nameText);
        }
    }

    // Método para actualizar la vista del icono del corazón según el estado de favorito
    private void updateHeartIcon(ImageView imgfav, boolean isFavorito) {
        if (isFavorito) {
            imgfav.setImageResource(R.drawable.favorito);
        } else {
            imgfav.setImageResource(R.drawable.favorito_no);
        }
    }
}
