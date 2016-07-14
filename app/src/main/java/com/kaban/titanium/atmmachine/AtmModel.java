package com.kaban.titanium.atmmachine;


import java.lang.reflect.Array;

public class AtmModel {
    private static AtmModel ourInstance = new AtmModel();

    public static AtmModel getInstance() {
        return ourInstance;
    }

    /** Массив с кол-вом купюр каждого номинала: 200, 100, 50, 20, 10, 5, 1 */
    private int[] mBillsCountArray = {3, 2, 4, 3, 49, 23, 10};

    private AtmModel() {

    }

    public int[] getBillsCountArray() {
        return mBillsCountArray;
    }

    public void setBillsCountArray(int[] billsCountArray) {
        mBillsCountArray = billsCountArray;
    }

    /** Метод для поучения массива номиналов купюр */
    public int[] getBillsArray() {
        int[] billsArray = {200, 100, 50, 20, 10, 5, 1};
        return billsArray;
    }
}
