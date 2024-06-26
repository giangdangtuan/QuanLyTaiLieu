package com.abc.project_quanlytailieu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Locale;

public class TaiLieuRVAdapter extends RecyclerView.Adapter<TaiLieuRVAdapter.ViewHolder> implements Filterable {

    private ArrayList<TaiLieu> listTl;
    private ArrayList<TaiLieu> listTlOld;
    private Context context;
    int lastPos = -1;
    private TaiLieuClickInterface taiLieuClickInterface;

    public TaiLieuRVAdapter(ArrayList<TaiLieu> listTl, Context context, TaiLieuClickInterface taiLieuClickInterface) {
        this.listTl = listTl;
        this.listTlOld = listTl;
        this.context = context;
        this.taiLieuClickInterface = taiLieuClickInterface;
    }

    @NonNull
    @Override
    public TaiLieuRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.tailieu_rv_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaiLieuRVAdapter.ViewHolder holder, int position) {
        TaiLieu taiLieu = listTl.get(holder.getAdapterPosition());
        holder.txtTenTl.setText(taiLieu.getTenTl());
        holder.txtChuyenNganhTl.setText(taiLieu.getChuyenNganhTl());
        Picasso picasso = Picasso.get();
        picasso.load(taiLieu.getAnhTl()).into(holder.imgTl);
//        Picasso.get().load(taiLieu.getAnhTl()).into(holder.imgTl);
        setAnimation(holder.itemView, holder.getAdapterPosition());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taiLieuClickInterface.onTaiLieuClick(holder.getAdapterPosition());
            }
        });
    }

    private void setAnimation(View itemView, int position) {
        if(position>lastPos){
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            itemView.setAnimation(animation);
            lastPos = position;
        }
    }

    @Override
    public int getItemCount() {
        return listTl.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String strTimKiem = charSequence.toString();
                if(strTimKiem.isEmpty()){
                    listTl = listTlOld;
                } else {
                    ArrayList<TaiLieu> list = new ArrayList<>();
                    for(TaiLieu tailieu : listTlOld){
                        if(tailieu.getTenTl().toLowerCase().contains(strTimKiem.toLowerCase())){
                            list.add(tailieu);
                        }
                    }
                    listTl = list;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = listTl;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                listTl = (ArrayList<TaiLieu>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtTenTl, txtChuyenNganhTl;
        private ImageView imgTl;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenTl = itemView.findViewById(R.id.txtTenTl);
            txtChuyenNganhTl = itemView.findViewById(R.id.txtChuyenNganhTl);
            imgTl = itemView.findViewById(R.id.imgTl);
        }
    }

    public interface TaiLieuClickInterface{
        void onTaiLieuClick(int position);
    }
}
