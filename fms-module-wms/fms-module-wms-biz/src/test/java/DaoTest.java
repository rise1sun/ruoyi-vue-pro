import cn.iocoder.yudao.module.system.api.notify.NotifyMessageSendApi;
import cn.iocoder.yudao.module.system.api.notify.dto.NotifySendSingleToUserReqDTO;
import cn.iocoder.yudao.module.wms.common.constant.MessageTemplateCode;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author jiangfeng
 * @date 2023/11/10
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DaoTest {
    @Resource
    private NotifyMessageSendApi notifySendApi;

    @Test
    public void testSendSingleMessageToAdmin() {
    Long userId = 1L; // 示例中写死，你可以改成你业务中的 userId 噢
    String templateCode = MessageTemplateCode.TASK_FAILE; // 站内信模版
    Map<String, Object> templateParams = new HashMap<>();
        templateParams.put("key1", "奥特曼");
        templateParams.put("key2", "变身");

    // 2. 发送站内信
        notifySendApi.sendSingleMessageToAdmin(new NotifySendSingleToUserReqDTO()
                .setUserId(userId).setTemplateCode(templateCode).setTemplateParams(templateParams));
}
}
