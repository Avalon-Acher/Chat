package chat.archer.chat.util.model;

/**
 * Created by 连浩逵 on 2017/6/9.
 */

public class MomentMsg {
    private int iconID;
    private String username;
    private String moment;
    private int good;

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

    public String getMoment() {
        return moment;
    }

    public void setMoment(String moment) {
        this.moment = moment;
    }

    public int getGood() {
        return good;
    }

    public void setGood(int good) {
        this.good = good;
    }
}
