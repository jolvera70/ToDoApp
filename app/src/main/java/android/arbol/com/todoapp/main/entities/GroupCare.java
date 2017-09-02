package android.arbol.com.todoapp.main.entities;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Jorge on 01/09/2017.
 */

public class GroupCare {

    private String groupId;
    private String nameGroup;
    private Map<String,Boolean> objects;
    private Map<String,Boolean> users;
    private Map<String,Map<String,String>> pagos;
    private Map<String,Map<String,String>> cobros;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getNameGroup() {
        return nameGroup;
    }

    public void setNameGroup(String nameGroup) {
        this.nameGroup = nameGroup;
    }

    public Map<String, Boolean> getObjects() {
        return objects;
    }

    public void setObjects(Map<String, Boolean> objects) {
        this.objects = objects;
    }

    public Map<String, Boolean> getUsers() {
        return users;
    }

    public void setUsers(Map<String, Boolean> users) {
        this.users = users;
    }

    public Map<String, Map<String,String>> getPagos() {
        return pagos;
    }

    public void setPagos(Map<String, Map<String,String>> pagos) {
        this.pagos = pagos;
    }

    public Map<String, Map<String,String>> getCobros() {
        return cobros;
    }

    public void setCobros(Map<String, Map<String,String>> cobros) {
        this.cobros = cobros;
    }
}
