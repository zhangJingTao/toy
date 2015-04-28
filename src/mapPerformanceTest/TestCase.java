import bean.ValueBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by admin on 2015/4/21.
 */
public class TestCase {
    List<ValueBean> list = new ArrayList<ValueBean>();

    /**
     * 随机生成测试数据
     * @param amount
     */
    void generatorData(Integer amount){
        if (amount<0) return;
        for(int i=0;i<amount;i++){
            ValueBean vb = new ValueBean((long)(Math.random()*amount), UUID.randomUUID().toString(),new Date());
            list.add(vb);
        }
    }

    void clear(){
        list = new ArrayList<ValueBean>();
    }

    List<ValueBean> getCaseData(){
        return list;
    }

}
