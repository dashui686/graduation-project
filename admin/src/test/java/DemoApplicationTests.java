import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

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
