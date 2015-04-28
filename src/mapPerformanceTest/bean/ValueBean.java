package bean;

import java.util.Date;

/**
 * Created by admin on 2015/4/21.
 */
public class ValueBean {
    Long id;
    String cardNo;
    Date createTime;

    public ValueBean(Long id,String cardNo,Date createTime){
        this.id = id;
        this.cardNo = cardNo;
        this.createTime = createTime;
    }

    public Long getId() {
        return id;
    }

    public String getCardNo() {
        return cardNo;
    }

    public Date getCreateTime() {
        return createTime;
    }
}
