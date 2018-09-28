package com.im.daeseong.lottoplayer.Database;

public class LottoTop {

    private int nNumber;
    private int nCount;

    public LottoTop(int nNumber, int nCount){
        this.nNumber = nNumber;
        this.nCount = nCount;
    }

    public int getNumber()
    {
        return nNumber;
    }

    public int getCount()
    {
        return nCount;
    }
}
