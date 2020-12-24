package com.example.cinemaapp.model;

import ml.huytools.lib.Annotation.JsonName;
import ml.huytools.lib.MVP.Model;
public class Ghe extends Model {
    public static final int STATUS_BOOKED = 0;
    public static final int STATUS_AVAILABLE = 1;
    public static final int STATUS_RESERVED = 2;
    @JsonName
    public String MaGhe;
    @JsonName
    public int status;
}
