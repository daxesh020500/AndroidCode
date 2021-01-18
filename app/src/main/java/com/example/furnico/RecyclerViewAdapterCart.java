package com.example.furnico;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapterCart extends RecyclerView.Adapter<RecyclerViewAdapterCart.ViewHolder> {

    private final List<ProductDatabase> mUserDataList;
    private final CartRecyclerInterface cartRecyclerInterface;

    public RecyclerViewAdapterCart(List<ProductDatabase> mUserDataList, CartRecyclerInterface cartRecyclerInterface) {
        this.mUserDataList = mUserDataList;
        this.cartRecyclerInterface = cartRecyclerInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_cart_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProductDatabase userData = mUserDataList.get(position);
        holder.tvCategory.setText(userData.getCategory());
        holder.tvProductName.setText(userData.getName());
        holder.tvPrice.setText(userData.getBestPrice()+"");
        holder.rootView.setOnClickListener((view -> cartRecyclerInterface.onUserClick(userData)));


    }

    @Override
    public int getItemCount() {
        return mUserDataList.size();
    }

    public interface CartRecyclerInterface{
        void onUserClick(ProductDatabase productDatabase);
        void addItem(ProductDatabase productDatabase);
        void removeItem();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvCategory;
        private final TextView tvProductName;
        private final View rootView;
        private final TextView tvPrice;
        private final Button btAddProduct;
        private final Button btRemoveProduct;

        public ViewHolder(View view) {
            super(view);
            rootView = view;
            tvCategory = view.findViewById(R.id.tv_user_cart_item_category);
            tvProductName = view.findViewById(R.id.tv_user_cart_item_product_name);
            tvPrice = view.findViewById(R.id.tv_user_cart_item_price);
            btAddProduct = view.findViewById(R.id.bt_user_cart_item_add);
            btRemoveProduct = view.findViewById(R.id.bt_user_cart_item_remove);
        }
    }
}
