package id.teknologi.teknologiid.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import id.teknologi.teknologiid.utils.RecyclerInterface;

/**
 * Created by galihgasur on 10/1/17.
 */

public abstract class BaseRecyclerAdapter<Model,VH extends BaseViewHolder<Model>> extends RecyclerView.Adapter<VH> {
    protected Context context;
    protected List<Model> modelList;
    protected LayoutInflater inflater;

    public BaseRecyclerAdapter(Context context, List<Model> modelList) {
        this.context = context;
        this.modelList = modelList;
        inflater = LayoutInflater.from(context);
    }

    public BaseRecyclerAdapter(Context context, List<Model> modelList, RecyclerInterface recyclerCallback) {
        this.context = context;
        this.modelList = modelList;
        inflater = LayoutInflater.from(context);
        this.recyclerCallback = recyclerCallback;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.onBind(modelList.get(position));
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    protected View initView(int viewType, ViewGroup parent) {
        return inflater.inflate(getResLayout(viewType), parent, false);
    }

    protected abstract int getResLayout(int type);

    protected RecyclerInterface recyclerCallback;

    public void insertAndNotify(List<Model> models){
        modelList.addAll(models);
        notifyItemRangeInserted(modelList.size() - models.size(), models.size());
    }
    public void insertAndNotify(Model model){
        modelList.add(model);
        notifyItemRangeInserted(modelList.size()-1, 1);
    }
}
