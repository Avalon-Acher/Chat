package chat.archer.chat.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import chat.archer.chat.R;
import chat.archer.chat.util.model.ImageMsg;

/**
 * Created by 连浩逵 on 2017/6/10.
 */

public class AdapterAvatar extends RecyclerView.Adapter<AdapterAvatar.BaseViewHolder>{

    private List<ImageMsg> imageViews;
    private Context context;
    private LayoutInflater inflater;
    private static int selectedImageAvatar=0;
    private List<RelativeLayout> imageContainer=new ArrayList<>();
    private Drawable bgImageDrawable;

    public AdapterAvatar(Context context,List<ImageMsg> imageViews){
        this.context=context;
        this.imageViews=imageViews;
        this.inflater=LayoutInflater.from(context);
        selectedImageAvatar=0;
        bgImageDrawable=context.getResources().getDrawable(R.drawable.bgimage,null);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.choose_image,parent,false);
        return new BaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, final int position) {
        holder.imageView.setImageResource(imageViews.get(position).getImageID());
        imageContainer.get(selectedImageAvatar).setBackground(bgImageDrawable);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position!=selectedImageAvatar){
                    imageContainer.get(position).setBackground(bgImageDrawable);
                    imageContainer.get(selectedImageAvatar).setBackgroundColor(0);
                    selectedImageAvatar=position;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return imageViews==null?0:imageViews.size();
    }

    class BaseViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;

        BaseViewHolder(View view){
            super(view);
            imageView=(ImageView)view.findViewById(R.id.image);
            RelativeLayout layout=(RelativeLayout) itemView.findViewById(R.id.imageContainer);
            imageContainer.add(layout);
        }
    }

}
