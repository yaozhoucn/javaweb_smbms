import com.yaozhou.pojo.Role;
import com.yaozhou.pojo.User;
import com.yaozhou.service.role.RoleServiceImpl;
import com.yaozhou.service.user.UserService;
import com.yaozhou.service.user.UserServiceImpl;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

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
        boolean i = userService.pwdModify(5, "123456");
        System.out.println(i);
    }
    @Test
    public void Test3() throws SQLException {
        RoleServiceImpl roleService = new RoleServiceImpl();
        List<Role> roleList = roleService.getRoleList();
        for (int i = 0; i <roleList.size() ; i++) {
            System.out.println(roleList.get(i).getRoleName());
        }
    }
}
