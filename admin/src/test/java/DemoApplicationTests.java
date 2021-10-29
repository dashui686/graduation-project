import com.dream.deploy.domain.ProcessDeploy;
import com.dream.deploy.service.ProcessDeployService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.InputStream;


@SpringBootTest(classes = DemoApplicationTests.class)
public class DemoApplicationTests {

//    /**
//     * 仓库
//     */
//    @Autowired
//    private RepositoryService repositoryService;
//
//
//
//
//    /**
//     * 运行中的任务
//     */
//    @Autowired
//    private RuntimeService runtimeService;
//
//    /**
//     * 任务
//     */
//    @Autowired
//    private TaskService taskService;
//
//    /**
//     * 历史记录
//     */
//    @Autowired
//    private HistoryService historyService;


    @Test
    public void test(){
        InputStream inputStream = this
                .getClass()
                .getClassLoader()
                .getResourceAsStream(
                        "processes/myLeave.zip");
        System.out.println(inputStream);
//        System.out.println("".isEmpty());


    }

}
