package com.smip.ulities;

import java.text.NumberFormat;
import java.util.Random;

/**
 * this is for fun...
 */
public class Casino {

   public static int[] getTicket(int size){
       int[] odd = new int[size];
       for (int i=0;i<size;i++){
           odd[i] = new Random().nextInt(10);
       }
       return odd;
   }

   public static int gamble(int[] ticket,int size){

       int count = 0;
       boolean flg = true;
       while (flg){
           count = count+1;
           int[] odd = getTicket(size);
           if (ticket.length != odd.length){
               break;
           }
           flg = !compare(ticket,odd);
       }
       return count;
   }

   public static boolean compare(int[] n,int[] m){
       int total = n.length;
       for (int i = 0;i < n.length;i++){
           if (n[i] == m[i]) total = total -1;
       }
       if (total == 0) return true;
       return false;
   }

   public static void testGamble(){
       int[] ti = new int[]{3,5,9};
       int p = 100000;
       int total = 0;
       for (int i=0;i<p;i++){
           total = total + gamble(ti,3);
       }
       if (total>0){
           NumberFormat nt = NumberFormat.getPercentInstance();
           nt.setMinimumFractionDigits(4);
           float rate = (float)p/total;
           System.out.println("rate="+nt.format(rate));
       }
   }

    public static void main(String[] args) {
        testGamble();
        System.out.println(getRate(8));
    }

    public static double getRate(int level){
        if (level>0 && level<10){
            int ge = new Random().nextInt(10),
                    xiao1 = new Random().nextInt(10),
                    xiao2 = new Random().nextInt(10),
                    xiao3 = new Random().nextInt(10);
            return ge+xiao1*.1+xiao2*.01+xiao3*.001;
        }
        return 0;
    }


}
