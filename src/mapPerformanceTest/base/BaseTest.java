package base;

/**
 * Created by ZhangJingtao on 2015/4/21.
 */
public abstract class BaseTest {
    public Long startTime;
    public Long endTime;
    public Integer caseAmount;

    public abstract void init();
    public abstract void run();
    public abstract void result();

    public void setCaseAmount(Integer caseAmount) {
        this.caseAmount = caseAmount;
    }

    public Integer getCaseAmount() {
        return caseAmount;
    }
}
