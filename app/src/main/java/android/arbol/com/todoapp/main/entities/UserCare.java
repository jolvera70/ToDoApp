package android.arbol.com.todoapp.main.entities;

import java.util.Map;

/**
 * Created by Jorge on 02/09/2017.
 */

public class UserCare {

    private String userId;
    private String userName;
    private Map<String,Boolean> groups;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Map<String, Boolean> getGroups() {
        return groups;
    }

    public void setGroups(Map<String, Boolean> groups) {
        this.groups = groups;
    }
}
