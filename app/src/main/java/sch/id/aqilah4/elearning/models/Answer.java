package sch.id.aqilah4.elearning.models;

import com.google.gson.annotations.SerializedName;

public class Answer {

    @SerializedName("A")
    private String mA;
    @SerializedName("B")
    private String mB;
    @SerializedName("C")
    private String mC;
    @SerializedName("D")
    private String mD;

    public String getA() {
        return mA;
    }

    public String getB() {
        return mB;
    }

    public String getC() {
        return mC;
    }

    public String getD() {
        return mD;
    }

}