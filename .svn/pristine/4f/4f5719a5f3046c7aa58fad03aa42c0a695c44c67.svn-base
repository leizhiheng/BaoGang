package com.baosteel.qcsh.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by lenovo on 2015/10/1.
 */
public class DeliverCommit implements Parcelable{
    private String seller_id;//商家ID
    private String goods_ids;//商品id集合
    private int type;//配送方式
    private int siSnceSome_id;



    private String invoiceType;

    public DeliverCommit(String seller_id, String goods_ids, int type, int siSnceSome_id,String invoiceType) {
        this.seller_id = seller_id;
        this.goods_ids = goods_ids;
        this.type = type;
        this.siSnceSome_id = siSnceSome_id;
        this.invoiceType = invoiceType;
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public DeliverCommit setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
        return this;
    }

    public String getSeller_id() {
        return seller_id;
    }

    public DeliverCommit setSeller_id(String seller_id) {
        this.seller_id = seller_id;
        return this;
    }

    public String getGoods_ids() {
        return goods_ids;
    }

    public DeliverCommit setGoods_ids(String goods_ids) {
        this.goods_ids = goods_ids;
        return this;
    }

    public int getType() {
        return type;
    }

    public DeliverCommit setType(int type) {
        this.type = type;
        return this;
    }

    public int getSiSnceSome_id() {
        return siSnceSome_id;
    }

    public DeliverCommit setSiSnceSome_id(int siSnceSome_id) {
        this.siSnceSome_id = siSnceSome_id;
        return this;
    }

    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

    public static final Parcelable.Creator<DeliverCommit> CREATOR = new Parcelable.Creator<DeliverCommit>() {
        public DeliverCommit createFromParcel(Parcel in) {
            return new DeliverCommit(in);
        }

        public DeliverCommit[] newArray(int size) {
            return new DeliverCommit[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(seller_id);
        dest.writeString(invoiceType);
        dest.writeString(goods_ids);
        dest.writeInt(type);
        dest.writeInt(siSnceSome_id);

    }

    private DeliverCommit(Parcel in) {
        seller_id = in.readString();
        invoiceType = in.readString();
        goods_ids = in.readString();
        type = in.readInt();
        siSnceSome_id = in.readInt();
    }
}
