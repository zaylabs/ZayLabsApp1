package com.zaylabs.zaylabsapp1.DTO;

import android.support.annotation.NonNull;

public class Userid {

    public String userId;

    public <T extends Userid> T withID(@NonNull final String id){
        this.userId = id;
        return (T) this;
    }
}
