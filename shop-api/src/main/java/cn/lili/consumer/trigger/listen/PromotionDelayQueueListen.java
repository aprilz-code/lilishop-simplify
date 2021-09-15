package cn.lili.consumer.trigger.listen;

import cn.hutool.json.JSONUtil;
import cn.lili.consumer.trigger.enums.DelayQueueEnums;
import cn.lili.consumer.trigger.interfaces.TimeTrigger;
import cn.lili.consumer.trigger.model.TimeTriggerMsg;
import cn.lili.consumer.trigger.AbstractDelayQueueListen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * PromotionTimeTriggerListen
 *
 * @author Chopper
 * @version v1.0
 * 2021-06-11 10:47
 */
@Component
public class PromotionDelayQueueListen extends AbstractDelayQueueListen {

    @Autowired
    private TimeTrigger timeTrigger;

    @Override
    public void invoke(String jobId) {
        timeTrigger.execute(JSONUtil.toBean(jobId, TimeTriggerMsg.class));
    }


    @Override
    public String setDelayQueueName() {
        return DelayQueueEnums.PROMOTION.name();
    }
}
