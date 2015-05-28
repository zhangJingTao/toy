package generator;

import java.lang.Integer;
import java.lang.String;
import java.lang.StringBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * 10进制转N进制（N<63）
 */
class TenToN {

    static String DICT = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    /**
     * 用10进制数值/base,得余数,直到10进制的值小于base,把所有余数排序颠倒,得到base进制的值
     * @param num
     * @param base
     * @return
     */
    public static String get(Long num,int base){
        List<String> result = new ArrayList<String>();
        if (num < base){//不满一轮进制的直接计算
            result.add(String.valueOf(num%base));
            return String.valueOf(DICT.substring(0,base).charAt(num.intValue()));
        }
        while (num>0){
            result.add(String.valueOf(num%base));
            num = num/base;
        }
        return listToNum(result,base);
    }

    /**
     * 将阿拉伯数字转进制内数值
     * @param result
     * @param base
     * @return
     */
    private static String listToNum(List<String> result,Integer base){
        String digit = DICT.substring(0,base);
        StringBuffer sb = new StringBuffer();
        for (int i = result.size()-1;i>-1;i--){
            sb.append(digit.charAt(Integer.valueOf(result.get(i))));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(get(218340105584896L,62));
    }
}
