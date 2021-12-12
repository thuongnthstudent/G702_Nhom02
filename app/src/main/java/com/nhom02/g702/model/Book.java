package com.nhom02.g702.model;

public class Book {
    private int w_id;
    private String w_ten;
    private String w_nhaxb;
    private int w_solantb;
    private double w_gia;
    private byte[] w_anh;

    public int getW_id() {
        return w_id;
    }

    public void setW_id(int w_id) {
        this.w_id = w_id;
    }

    public String getW_ten() {
        return w_ten;
    }

    public void setW_ten(String w_ten) {
        this.w_ten = w_ten;
    }

    public String getW_nhaxb() {
        return w_nhaxb;
    }

    public void setW_nhaxb(String w_nhaxb) {
        this.w_nhaxb = w_nhaxb;
    }

    public int getW_solantb() {
        return w_solantb;
    }

    public void setW_solantb(int w_solantb) {
        this.w_solantb = w_solantb;
    }

    public double getW_gia() {
        return w_gia;
    }

    public void setW_gia(double w_gia) {
        this.w_gia = w_gia;
    }

    public byte[] getW_anh() {
        return w_anh;
    }

    public void setW_anh(byte[] w_anh) {
        this.w_anh = w_anh;
    }



    public Book(int w_id, String w_ten, String w_nhaxb, int w_solantb, double w_gia, byte[] w_anh) {
        this.w_id = w_id;
        this.w_ten = w_ten;
        this.w_nhaxb = w_nhaxb;
        this.w_solantb = w_solantb;
        this.w_gia = w_gia;
        this.w_anh = w_anh;
    }

    public Book() {
    }
}
