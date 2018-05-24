package id.teknologi.teknologiid.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import id.teknologi.teknologiid.utils.RecyclerInterface;

/**
 * Created by galihgasur on 10/1/17.
 */

public abstract class BaseViewHolder<Model> extends RecyclerView.ViewHolder {
    public BaseViewHolder(View itemView) {
        super(itemView);
    }
    public BaseViewHolder(View itemView, RecyclerInterface recyclerInterface) {
        super(itemView);
        itemView.setOnClickListener(view -> recyclerInterface.onRecyclerItemClicked(getAdapterPosition()));
    }

    public abstract void onBind(Model model);
}