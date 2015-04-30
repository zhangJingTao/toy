import base.BaseTest;
import bean.ValueBean;

import java.util.HashMap;
import java.util.List;

/**
 * Created by ZhangJingtao on 2015/4/21.
 */
public class HashMapTest extends BaseTest{
    TestCase testCase;
    HashMap<Long,ValueBean> map = new HashMap<Long, ValueBean>();

    @Override
    public void init() {
        System.out.println("开始初始化");
        testCase = new TestCase();
        testCase.generatorData(super.getCaseAmount());
        System.out.println("结束初始化");
    }

    @Override
    public void run() {
        this.startTime = System.currentTimeMillis();
        List<ValueBean> cases = testCase.getCaseData();
        for(ValueBean vb : cases){
            ValueBean old = map.get(vb.getId());
            if (old != null){
                if (old.getCreateTime().getTime()<vb.getCreateTime().getTime()){
                    map.put(vb.getId(),vb);
                }
            }else{
                map.put(vb.getId(),vb);
            }
        }
        this.endTime = System.currentTimeMillis();
    }

    @Override
    public void result() {
         System.out.println("共耗时:"+(endTime - startTime)+"mill");
    }
}
