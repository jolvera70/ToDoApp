package android.arbol.com.todoapp.main.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * Created by Jorge on 01/09/2017.
 */

public class ObjectCare {
    private String uid;
    private String name;
    private AddressObjectCare address;
    private Map<String,Boolean> images;
    private Map<String,Boolean> groups;


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AddressObjectCare getAddress() {
        return address;
    }

    public void setAddress(AddressObjectCare address) {
        this.address = address;
    }

    public Map<String,Boolean> getImages() {
        return images;
    }

    public void setImages(Map<String,Boolean> images) {
        this.images = images;
    }

    public Map<String,Boolean> getGroups() {
        return groups;
    }

    public void setGroups(Map<String,Boolean> groups) {
        this.groups = groups;
    }
}
