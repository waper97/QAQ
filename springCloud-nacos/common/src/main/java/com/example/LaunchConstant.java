package com.example;

/**
 * @ClassName constant
 * @Description TODO
 * @Author wangpeng
 * @Date 2021/12/21 10:55
 */
public interface LaunchConstant {

    /**
     * nacos配置地址 dev
     */
    String NACOS_ADDR_DEV = "192.168.31.131:8848";

    /**
     * nacos配置地址 test
     */
    String NACOS_ADDR_TEST = "192.168.31.131:xxxx";

    /**
     * nacos配置地址 prod
     */
    String NACOS_ADDR_PROD = "192.168.31.131:xxxx";

    /**
     * 获取naocs配置地址
     * @param profile 环境
     * @return
     */
    static String nacosAddr (String profile) {
        switch (profile) {
            case "dev":
                return NACOS_ADDR_DEV;
            case "test":
                return NACOS_ADDR_DEV;
            case "prod":
                return NACOS_ADDR_DEV;
            default:
        }
        return NACOS_ADDR_DEV;
    }

}
