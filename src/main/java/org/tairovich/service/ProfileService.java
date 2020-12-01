package org.tairovich.service;

import org.tairovich.database.DatabaseClass;
import org.tairovich.model.Profile;

import java.util.*;

public class ProfileService {

    private Map<String, Profile> profiles = DatabaseClass.getProfiles();

    public List<Profile> getProfiles(){
        return new ArrayList<>(profiles.values());
    }

    public Profile getProfile(String name){
        return profiles.get(name);
    }

    public Profile addProfile(Profile profile){
        profile.setId(profiles.size()+1);
        profile.setCreated(new Date());
        profiles.put(profile.getProfileName(), profile);
        return profile;
    }

    public Profile updateProfile(Profile profile){
        if (profile.getProfileName().isEmpty()) return  null;

        profiles.put(profile.getProfileName(),profile);
       return profile;
    }

    public Profile removeProfile(String profileName){
        return profiles.remove(profileName);
    }




}
