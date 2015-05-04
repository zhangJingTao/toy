package generator;

import java.math.BigInteger;

/**
 * Created by admin on 2015/5/4.
 */
public class TenTo62 {
    public static String baseString(int num,int base) {
        String str = "";
        String digit = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        if(num == 0){
            return "";
        }else {
            str = baseString(num / base,base);
            return str + digit.charAt(num % base);
        }
    }

    public static String baseString(BigInteger num,int base) {
        String str = "";
        String digit = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        if(num.shortValue() == 0){
            return "";
        }else {
            BigInteger valueOf = BigInteger.valueOf(base);
            str = baseString(num.divide(valueOf),base);
            return str + digit.charAt(num.mod(valueOf).shortValue());
        }
    }

    public static void main(String[] args) {
//        System.out.println(baseString(1295,62));

        for (int i = 0; i < 9999999999999L; i++) {
            BigInteger big=new BigInteger(i+"");
            if (baseString(big,62) == "10000000"){
                System.out.println(i);
                break;
            }
        }

    }
}
