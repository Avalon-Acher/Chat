package chat.archer.chat.util.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 连浩逵 on 2017/6/10.
 */

public class ChatMsg {
    private boolean myInfo;

    public boolean isMyInfo() {
        return myInfo;
    }

    public void setMyInfo(boolean myInfo) {
        this.myInfo = myInfo;
    }

    public int getIconID() {
        return iconID;
    }

    public void setIconID(int iconID) {
        this.iconID = iconID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getChatObj() {
        return chatObj;
    }

    public void setChatObj(String chatObj) {
        this.chatObj = chatObj;
    }

    private int iconID;
    private String username;
    private String content;
    private String chatObj;
    public static List<ChatMsg> chatMsgList=new ArrayList<>();

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    private String group;
}
