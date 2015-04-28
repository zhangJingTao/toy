package compare;

import bean.ValueBean;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by admin on 2015/4/21.
 */
public class DateCase {
    //存储 HHmm 时间
    List<String> list = new ArrayList<String>();


    /**
     * 随机生成测试数据
     * @param amount
     */
    public void generatorData(Integer amount){
        if (amount<0) return;
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HHmm");
        for(int i=0;i<amount;i++){
           cal.add(Calendar.MINUTE,(int)(Math.random()*100));
           list.add(sdf.format(cal.getTime()));
        }
    }

    void clear(){
        list = new ArrayList<String>();
    }

    public List<String> getCaseData(){
        return list;
    }
}
