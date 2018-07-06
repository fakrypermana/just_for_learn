package id.teknologi.teknologiid.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.plumillonforge.android.chipview.ChipViewAdapter;

import id.teknologi.teknologiid.R;
import id.teknologi.teknologiid.feature.Tag;

public class ChipAdapter extends ChipViewAdapter {
    public ChipAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutRes(int position) {
        Tag tag = (Tag) getChip(position);

        switch (tag.getType()) {
            default:
            case 2:
            case 3:
                return 0;
        }
    }

    @Override
    public int getBackgroundColor(int position) {
        Tag tag = (Tag) getChip(position);

        switch (tag.getType()) {
            default:
                return 0;

        }
    }

    @Override
    public int getBackgroundColorSelected(int position) {
        return 0;
    }

    @Override
    public int getBackgroundRes(int position) {
        return 0;
    }

    @Override
    public void onLayout(View view, int position) {
        Tag tag = (Tag) getChip(position);

        if (tag.getType() == 2)
            ((TextView) view.findViewById(android.R.id.text1)).setTextColor(getColor(R.color.colorAccent));
    }
}
