import com.zhazha.cqhttp.CqhttpBotApplication;
import com.zhazha.cqhttp.remote.msg.RMessageService;
import jakarta.annotation.Resource;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {CqhttpBotApplication.class})
class RMessageServiceTest {
    
    @Resource
    private RMessageService rMessageService;
    
    @org.junit.jupiter.api.Test
    void sendMessage() {
        // TODO: 2023/6/28 由于出现风控, 所以暂时无法测试代码
        rMessageService.sendMessage(2033445917L, "zhazha", false);
    }
}