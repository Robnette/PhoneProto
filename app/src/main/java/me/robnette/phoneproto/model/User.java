package me.robnette.phoneproto.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by etienne.clercboichut on 25/10/2017.
 */

public class User implements Parcelable {

    private String name;
    private String username;
    private List<String> roles;

    public User(String name, String username) {
        this.name = name;
        this.username = username;
        roles = new ArrayList<>();
        roles.add("admin");
        roles.add("user");
    }

    protected User(Parcel in) {
        name = in.readString();
        username = in.readString();
        roles = in.readArrayList(String.class.getClassLoader());
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(username);
        dest.writeList(roles);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", roles=" + roles +
                '}';
    }
}
