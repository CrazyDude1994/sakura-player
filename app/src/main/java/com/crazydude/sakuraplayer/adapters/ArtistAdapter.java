package com.crazydude.sakuraplayer.adapters;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.view.ViewGroup;

import com.crazydude.sakuraplayer.gui.views.ArtistView;
import com.crazydude.sakuraplayer.models.ArtistModel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import static com.crazydude.sakuraplayer.interfaces.Callbacks.RecyclerViewClickListener;

/**
 * Created by Crazy on 27.05.2015.
 */
@EqualsAndHashCode(callSuper = false)
@Data
@Accessors(prefix = "m")
public class ArtistAdapter extends BaseCursorAdapter<ArtistModel, ArtistView> {

    private Context mContext;
    private RecyclerViewClickListener mOnRecyclerViewClickListener;

    public ArtistAdapter(Context context) {
        mContext = context;
    }

    @Override
    public BaseViewHolder<ArtistView> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseViewHolder<>(new ArtistView(parent.getContext()));
    }

    @Override
    public void onBindViewHolder(BaseViewHolder<ArtistView> holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.getView().setRippleCallback(rippleView -> {
            if (mOnRecyclerViewClickListener != null) {
                mOnRecyclerViewClickListener.onClick(rippleView, position);
            }
        });
    }
}
