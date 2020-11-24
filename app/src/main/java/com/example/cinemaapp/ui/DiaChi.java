package com.example.cinemaapp.ui;

import java.util.HashMap;

public class DiaChi {
    static HashMap<String, String> codeHash = new HashMap<String, String>();

    static {
        quan();
    }

    public static void quan() {
        codeHash.put("1", "Quận 1");
        codeHash.put("2", "Quận 2");
        codeHash.put("3", "Quận 3");
        codeHash.put("4", "Quận 4");
        codeHash.put("5", "Quận 5");
        codeHash.put("6", "Quận 6");
        codeHash.put("7", "Quận 7");
        codeHash.put("8", "Quận 8");
        codeHash.put("9", "Quận 9");
        codeHash.put("10", "Quận 10");
        codeHash.put("11", "Quận 11");
        codeHash.put("12", "Quận 12");
        codeHash.put("GV", "Quận Gò Vấp");
        codeHash.put("BT", "Quận Bình Thạnh");
        codeHash.put("TPhu", "Quận Tân phú");
        codeHash.put("TBinh", "Quận Tân Bình");
        codeHash.put("PN", "Quận Phú Nhuận");
        codeHash.put("NB", "Quận Nhà Bè");


    }

    public static String getCode(String param) {
        return codeHash.get(param);
    }
}
