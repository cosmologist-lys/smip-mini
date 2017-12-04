package com.smip.ulities;

import org.springframework.cache.annotation.Cacheable;

/**
 * Created by kepler@gmail.com on 2017/11/28.
 */
public class Alg {
    private static final String ALG_K_ARIES = "aries";
    private static final String ALG_K_TAURUS = "taurus";
    private static final String ALG_K_GEMINI = "gemini";
    private static final String ALG_K_CANCER = "cancer";
    private static final String ALG_K_LEO = "leo";
    private static final String ALG_K_VIRGO = "virgo";
    private static final String ALG_K_LIBRA = "libra";
    private static final String ALG_K_SCORPIO = "scorpio";
    private static final String ALG_K_SAGITARRIUS = "sagittarius";
    private static final String ALG_K_CAPRICORN = "capricorn";
    private static final String ALG_K_AQUARIUS = "aquarius";
    private static final String ALG_K_PISCES = "pisces";

    @Cacheable(value = "alg_key")
    public static String getKey(String alg){
        if (!Q.notNull(alg))return null;
        String key = alg.toLowerCase().toString();
        switch (key){
            case ALG_K_ARIES:
                key += "JAN";break;
            case ALG_K_TAURUS:
                key += "FEB";break;
            case ALG_K_GEMINI:
                key += "MAR";break;
            case ALG_K_CANCER:
                key += "APR";break;
            case ALG_K_LEO:
                key += "MAY";break;
            case ALG_K_VIRGO:
                key += "JUN";break;
            case ALG_K_LIBRA:
                key += "JUL";break;
            case ALG_K_SCORPIO:
                key += "AUG";break;
            case ALG_K_SAGITARRIUS:
                key += "SEP";break;
            case ALG_K_CAPRICORN:
                key += "OCT";break;
            case ALG_K_AQUARIUS:
                key += "NOV";break;
            case ALG_K_PISCES:
                key += "DEC";break;
            default:key = null;break;
        }
        System.out.println("得到的KEY:"+key);
        return key;
    }
}

