import com.yaozhou.pojo.User;
import com.yaozhou.service.user.UserService;
import com.yaozhou.service.user.UserServiceImpl;
import org.junit.Test;

/**
 * Created by WXHang on HANG at 2021/6/21 17:28
 * Desc：
 */
public class TestDemo {
    @Test
    public void Test1(){
        UserService userService = new UserServiceImpl();
        User user = userService.login("admin", "1223445");
        System.out.println(user.getUserPassword());
    }
    @Test
    public void Test2(){
        UserService userService = new UserServiceImpl();
        int i = userService.pwdModify(5, "1234567879");
        System.out.println(i);
    }
}
