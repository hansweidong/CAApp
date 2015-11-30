package htb.com.childrenapp.Core;

/**
 * Created by weidong_wu on 15/11/9.
 * 邮箱:wwdhao163@163.com
 */
public class Port {

    private static final String URL = "http://192.168.7.52:3000/api";
    public static final int ResponseJsonCode_0 = 0; //成功获得数据
    public static final int ResponseSuccessCode = 200; //响应成功
    public static final int ResponseFailureCode = 404 ;//响应失败
    public static final int ResponseJsonCode_1002 = 1002; //用户不存在
    public static final int ResponseJsonCOde_1001 = 1001; //token错误
    public static final int ResponseJsonCOde_1003 = 1003; //创建showId错误
    public static final int ResponseJsonCOde_1004 = 1004; //没有手机号码
    public static final int ResponseJsonCOde_1005 = 1005; //密码错误
    public static final int ResponseJsonCOde_1010 = 1010; //验证码错误
    public static final int ResponseJsonCOde_1007 = 1007; //手机格式不正确
    public static final int ResponseJsonCOde_1008 = 1008; //手机号已被注册
    public static final int ResponseJsonCOde_1009 = 1009; //用户名已被注册
    public static final int ResponseRegisterSuccessCode = 100;//注册成功
    /**
     * @note 登陆接口
     */
    public static final String loginUrl = URL + "/users/login?";

    /**
     * @note 注册接口
     */
    public static final String RegisterUrl = URL + "/users/register?";

    /**
     * @NOTE 手机验证码接口
     */
    public static final String VerifyPhoneUrl = URL + "/api/users/verifyPhone?";

    /**
     * @note 密码重置接口
     */
    public static final String PasswordResetUrl = URL + "/api/users/reset?";

    /**
     * @NOTE 更新个人信息接口
     */
    public static final String updateUserInfoUrl = URL + "/api/users/updateInfo?";

    /**
     * @note 注销接口
     */
    public static final String logOutUrl = URL + "/api/users/logout?";


}
