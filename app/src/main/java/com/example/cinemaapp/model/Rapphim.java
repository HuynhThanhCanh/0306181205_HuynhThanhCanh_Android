package com.example.cinemaapp.model;

import org.json.JSONArray;
import  ml.huytools.lib.MVP.Model;
import ml.huytools.lib.Annotation.JsonName;
import ml.huytools.lib.MVP.ModelManager;

public class Rapphim extends Model{
    @JsonName
    public  int row;
    @JsonName
    public  int col;
    @JsonName(type = JsonName.Type.ModelManager, clazz = Ghe.class)
    public ModelManager<Ghe> listSeats;
    @JsonName
    public JSONArray configDraw;

}
