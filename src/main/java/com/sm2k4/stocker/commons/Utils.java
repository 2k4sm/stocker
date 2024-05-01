package com.sm2k4.stocker.commons;

public class Utils {
    public Long generateLiccno() {
        return (long) (Math.random() * 1000000000);
    }
}
