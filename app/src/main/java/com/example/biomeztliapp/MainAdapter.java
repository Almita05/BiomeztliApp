package com.example.biomeztliapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

        // Onclick en la imagen para ir a la actividad 3
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String imageUrl = model.getImagen();
                String nombre = model.getNombre();
                String descripcion = model.getDescripcion();
                String propiedades = model.getPropiedades();
                String uso = model.getUso();
                String precaucion = model.getPrecaucion();
                String ingredientes = model.getIngredientes();
                String modoPreparacion = model.getModoPreparacion();

                Intent intent;

                // Verificar en qué fragmento estamos y decidir a qué actividad dirigirse
                if (mFragment instanceof HomeFragment) {
                    intent = new Intent(v.getContext(), MainActivity3.class);
                } else if (mFragment instanceof DashboardFragment) {
                    intent = new Intent(v.getContext(), MainActivity4.class);
                } else {
                    //intent = new Intent(v.getContext(), MainActivity5.class);
                    return;
                }

                intent.putExtra("IMAGE_URL", imageUrl);
                intent.putExtra("NOMBRE", nombre);
                intent.putExtra("DESCRIPCION", descripcion);
                intent.putExtra("PROPIEDADES", propiedades);
                intent.putExtra("USO", uso);
                intent.putExtra("PRECAUCION", precaucion);
                intent.putExtra("INGREDIENTES", ingredientes);
                intent.putExtra("PREPARACION", modoPreparacion);
                v.getContext().startActivity(intent);
            }
        });


        // Clic largo en la imagen para manejar Activity4
        holder.img.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mActivity4Listener != null) {
                    // Agrega la información adicional necesaria para Activity4
                    String ingredientes = model.getIngredientes();
                    String modoPreparacion = model.getModoPreparacion();

                    mActivity4Listener.onItemClickForActivity4(ingredientes, modoPreparacion);
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

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img1);
            nombre = itemView.findViewById(R.id.nameText);
        }
    }
}
