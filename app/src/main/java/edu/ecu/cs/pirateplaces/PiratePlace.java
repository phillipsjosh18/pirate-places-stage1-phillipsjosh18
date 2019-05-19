package edu.ecu.cs.pirateplaces;

import java.io.Serializable;
import java.util.Date;

public class PiratePlace implements java.io.Serializable {

    private int mTextResId;
    private Date mDate;

    public PiratePlace(int textResId, Date date) {
        mTextResId = textResId;
        mDate = date;
    }

    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }
}
