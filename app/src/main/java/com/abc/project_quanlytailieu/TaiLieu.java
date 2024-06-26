package com.abc.project_quanlytailieu;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class TaiLieu implements Parcelable {
    private String idTl;
    private String tenTl;
    private String chuyenNganhTl;
    private String anhTl;
    private String linkTl;
    private String moTaTl;


    public TaiLieu() {

    }

    public TaiLieu( String idTl,String tenTl, String chuyenNganhTl, String anhTl, String linkTl, String moTaTl) {
        this.idTl = idTl;
        this.tenTl = tenTl;
        this.chuyenNganhTl = chuyenNganhTl;
        this.anhTl = anhTl;
        this.linkTl = linkTl;
        this.moTaTl = moTaTl;

    }

    protected TaiLieu(Parcel in) {
        idTl = in.readString();
        tenTl = in.readString();
        chuyenNganhTl = in.readString();
        anhTl = in.readString();
        linkTl = in.readString();
        moTaTl = in.readString();

    }

    public static final Creator<TaiLieu> CREATOR = new Creator<TaiLieu>() {
        @Override
        public TaiLieu createFromParcel(Parcel in) {
            return new TaiLieu(in);
        }

        @Override
        public TaiLieu[] newArray(int size) {
            return new TaiLieu[size];
        }
    };

    public String getTenTl() {
        return tenTl;
    }

    public void setTenTl(String tenTl) {
        this.tenTl = tenTl;
    }

    public String getChuyenNganhTl() {
        return chuyenNganhTl;
    }

    public void setChuyenNganhTl(String chuyenNganhTl) {
        this.chuyenNganhTl = chuyenNganhTl;
    }

    public String getAnhTl() {
        return anhTl;
    }

    public void setAnhTl(String anhTl) {
        this.anhTl = anhTl;
    }

    public String getLinkTl() {
        return linkTl;
    }

    public void setLinkTl(String linkTl) {
        this.linkTl = linkTl;
    }

    public String getMoTaTl() {
        return moTaTl;
    }

    public void setMoTaTl(String moTaTl) {
        this.moTaTl = moTaTl;
    }

    public String getIdTl() {
        return idTl;
    }

    public void setIdTl(String idTl) {
        this.idTl = idTl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(idTl);
        dest.writeString(tenTl);
        dest.writeString(chuyenNganhTl);
        dest.writeString(anhTl);
        dest.writeString(linkTl);
        dest.writeString(moTaTl);

    }
}
