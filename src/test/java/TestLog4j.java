import org.junit.Test;
import org.apache.log4j.Logger;

/**
 * Created by WXHang on HANG at 2021/6/25 22:52
 */
public class TestLog4j {

        // apache-log4j日志
        private final Logger LOGGER = Logger.getLogger(TestLog4j.class);

        /**
         * apache-log4j 日志输出
         */
        @Test
        public void testLog4j(){
            LOGGER.info("普通信息");
            LOGGER.error("普通错误");
            LOGGER.trace("堆栈信息");
            LOGGER.fatal("致命错误");
            LOGGER.warn("警告信息");
            LOGGER.debug("调试信息");
        }


}
