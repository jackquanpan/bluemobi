package cn.bluemobi.constant;


/**
 * 系统常量类
 * 
 * @author 雷攀
 * 
 */
public class BlueMobiConstant {

    /**
     * 网站访问的域名配置
     */
    // public static final String domain = Config.get().getDomain();

    /**
     * 后台登录用户
     */
    public static final String ADMIN_USER = "adminUser";

    /**
     * 商户登录用户
     */
    public static final String BUSSINESS_USER = "bussinessUser";

    /**
     * 前台登录用户
     */
    public static final String USER = "user";

    /**
     * 项目版本号码
     */
    public static final String VERSIONS = "1.0";

    /**
     * 获取项目在磁盘上面的物理路径
     */
    public static final String WEB_SITE_ROOT_PATH = BlueMobiConstant.class.getResource("/").getPath()
            .replaceAll("%20", " ")
            .substring(0, BlueMobiConstant.class.getResource("/").getPath().replaceAll("%20", " ").indexOf("WEB-INF"));

    /**
     * 获取项目在磁盘上面的物理路径
     */
    public static final String WEB_CLASSPATH = BlueMobiConstant.class.getResource("/").getPath().replaceAll("%20", " ");

    /**
     * 图片文件格式限制
     */
    public static final String[] IMAGE_FORMAT_ARRAY = new String[] { ".gif", ".png", ".jpg", ".jpeg", ".bmp" };// 全站中引用的图片格式

    /**
     * 图片限制大小 文件的极限大小，以字节为单位，0为不限制。1MB:1*1024*1024
     */
    public static final int IMAGE_SIZE_LIMIT = 1 * 1024 * 1024;

    /**
     * 判断图片格式是否正确
     * 
     * @param type
     * @return
     */
    public static boolean checkImage(String type) {
        if (type == null) {
            return false;
        }
        type = type.toLowerCase();
        for (String s : IMAGE_FORMAT_ARRAY) {
            if (s.equals(type)) {
                return true;
            }
        }
        return false;
    }

}
