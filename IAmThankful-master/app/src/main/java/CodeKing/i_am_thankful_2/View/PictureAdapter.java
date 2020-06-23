package CodeKing.i_am_thankful_2.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import CodeKing.i_am_thankful_2.R;

public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.PictureViewHolder> {
    /**
     *
     */
    private Context mContext;
    /**
     *
     */
    private ArrayList<PictureItem> mPictureList;

    /**
     * @param context
     * @param pictureList
     */
    public PictureAdapter (Context context, ArrayList <PictureItem> pictureList) {
        mContext = context;
        mPictureList = pictureList;
    }

    /**
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public PictureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.picture_item, parent, false);
        return new PictureViewHolder(v);
    }

    /**
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull PictureViewHolder holder, int position) {
        PictureItem currentItem = mPictureList.get(position);
        String imageUrl = currentItem.getmImageUrl();
        Picasso.with(mContext).load(imageUrl).fit().centerInside().into(holder.mImageView);
    }

    /**
     * @return picture list
     */
    @Override
    public int getItemCount() {
        return mPictureList.size();
    }

    public class PictureViewHolder extends RecyclerView.ViewHolder{
        /**
         *
         */
        public ImageView mImageView;


        /**
         * @param itemView
         */
        public PictureViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_view);
        }
    }
}
