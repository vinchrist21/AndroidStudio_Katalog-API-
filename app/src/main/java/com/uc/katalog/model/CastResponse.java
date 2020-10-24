package com.uc.katalog.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CastResponse {

    @SerializedName("cast")
    private List<Cast> casts;

    public List<Cast> getCast(){
        return casts;
    }

    public void setCast(List<Cast>cast){
        this.casts = cast;
    }

}
