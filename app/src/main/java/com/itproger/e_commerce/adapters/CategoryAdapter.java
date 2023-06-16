package com.itproger.e_commerce.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.itproger.e_commerce.MainActivity;
import com.itproger.e_commerce.R;
import com.itproger.e_commerce.models.Category;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    Context context;
    List<Category> category;

    public CategoryAdapter(Context context, List<Category> category) {
        this.context = context;
        this.category = category;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View categoryItems = LayoutInflater.from(context).inflate(R.layout.category_item, parent, false);
        return new CategoryViewHolder(categoryItems);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.categoryTitle.setText(category.get(position).getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MainActivity.showCoursesByCategory(category.get(position).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return category.size();
    }

    public static final class CategoryViewHolder extends RecyclerView.ViewHolder{

        TextView categoryTitle;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            categoryTitle = itemView.findViewById(R.id.categoryTitle);
        }
    }
}
