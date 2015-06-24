package net.gongmingqm10.training.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.gongmingqm10.training.R;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MaterialRecyclerAdapter extends RecyclerView.Adapter<MaterialRecyclerAdapter.ViewHolder> {


    private final Context context;
    private final List<String> titles;

    public class ViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.recycler_text)
        protected TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }

        @OnClick(R.id.recycler_text)
        protected void recyclerTextClick(View view) {
            removeItem(getAdapterPosition());
        }

    }

    private void removeItem(int position) {
        titles.remove(position);
        notifyItemChanged(position);
        notifyItemRangeRemoved(position, titles.size());
    }

    public MaterialRecyclerAdapter(Context context, List<String> titles) {
        this.context = context;
        this.titles = titles;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.material_recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.textView.setText(titles.get(position));
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

}
