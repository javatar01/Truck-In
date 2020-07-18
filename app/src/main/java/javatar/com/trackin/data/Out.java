package javatar.com.trackin.data;

public class Out {
    private String id,token;
    private boolean location_updates;

    public Out() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isLocation_updates() {
        return location_updates;
    }

    public void setLocation_updates(boolean location_updates) {
        this.location_updates = location_updates;
    }
}
