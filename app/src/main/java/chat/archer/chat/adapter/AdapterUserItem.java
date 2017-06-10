package chat.archer.chat.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import chat.archer.chat.R;
import chat.archer.chat.activity.ChatRoomActivity;
import chat.archer.chat.util.model.UserItemMsg;

/**
 * Created by 连浩逵 on 2017/6/9.
 */

public class AdapterUserItem extends RecyclerView.Adapter<AdapterUserItem.BaseViewHolder> {

    private Context context;

    private List<UserItemMsg> userItemMsgList;

    public AdapterUserItem(Context context,List<UserItemMsg> userItemMsgsList){
        this.context=context;
        this.userItemMsgList=userItemMsgsList;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseViewHolder(LayoutInflater.from(context).inflate(R.layout.item_user,parent,false));
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.ivAvatar.setImageResource(userItemMsgList.get(position).getIconID());
        holder.tvUsername.setText(userItemMsgList.get(position).getUsername());
        holder.tvSign.setText(userItemMsgList.get(position).getSign());
    }

    @Override
    public int getItemCount() {
        return (userItemMsgList==null?0:userItemMsgList.size());
    }

    class BaseViewHolder extends RecyclerView.ViewHolder{

        private ImageView ivAvatar;
        private TextView tvUsername;
        private TextView tvSign;

        BaseViewHolder(View itemView) {
            super(itemView);
            ivAvatar=(ImageView)itemView.findViewById(R.id.iv_item_avatar);
            tvUsername=(TextView)itemView.findViewById(R.id.tv_item_username);
            tvSign=(TextView)itemView.findViewById(R.id.tv_item_sign);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  Intent intent=new Intent(context, ChatRoomActivity.class);
                    intent.putExtra("username",tvUsername.getText().toString());
                    context.startActivity(intent);
                }
            });

        }
    }
}
