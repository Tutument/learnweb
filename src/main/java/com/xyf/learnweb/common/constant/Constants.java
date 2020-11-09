package com.xyf.learnweb.common.constant;

/**
 * 通用常量信息
 * 
 * @author Lihui
 */
public class Constants
{
	 /** 
     * 模块id，应用id
     */
    public static int CA_FLAG = 0;
    public static String LOCAL_CERT = "";
    
    public static String I_MODULE_ID = "";
    public static String I_APP_ID = "";
    
	/*
	 *  muuid:CCB41DCAB5A7B5CAAD8210E6D319C2A5D53447FB274B687014C7B93592D09F91
		pri:  969c3c51040948dfeebefd3117d17930511a14c1a8c9bfa5221194d865090efc
		pub: 93f0206415b56dc5bc1c2e5765dfc9eaa2125b622a4149bbec117dda6d2584cfb836ad64dbeb8957b7ff6d309eca1b67758455a3bde82cfa24e242760926f2ac
		
		muuid:1BC4E8CA1101497DCF7CA92CB378096B
		pri:9e25bcea95239cf3c8cb9d7e9616e9061195c300afa1e9b74aaa728e136906b1
		pub: 9741e8a823aee0d87551979ed3d1b61d5d8197ef4c83da3d1215b22c3a06dfef08235cb50c64db8fe19f81ea92a9f4064e51ffde5d6528f0229cd14b345c8317

	 * */
    
    /** 
     * 所有者类型
     */
//    public static final long OWNER_TYPE_MODULE = 1;
//    public static final long OWNER_TYPE_APP = 2;
//    public static final long OWNER_TYPE_CARD = 3;
//    public static final long OWNER_TYPE_CLUSTER = 4;
 
    /** 
     * 模块状态
     */
    public static final Long MOUDLE_INACTIVATED = 0l;
    public static final Long MOUDLE_ACTIVATED = 1l;
    public static final Long MOUDLE_DISABLED = 2l;
    public static final Long MOUDLE_DISTROY = 3l;
    
    /** 
     * 证书即将到期时长
     */
    public static final long MODULE_CERT_TIME_LEFT_MAX = 30 * 24 * 3600;
    public static final long APP_CERT_TIME_LEFT_MAX = 30 * 24 * 3600;
    public static final long CARD_CERT_TIME_LEFT_MAX = 30 * 24 * 3600;
    
    /** 
     * 初始证书类型、正式证书类型
     * 模块身份认证证书、服务器证书
     */
    public static final long CERT_TYPE_ORIGIN = 1;
    public static final long CERT_TYPE_REGULAR = 2;
    public static final long CERT_TYPE_MODULE_AUTH = 1;
    public static final long CERT_TYPE_SERVER = 2;

    /**
     * UTF-8 字符集
     */
    public static final String UTF8 = "UTF-8";

    /**
     * 通用成功标识
     */
    public static final String SUCCESS = "0";

    /**
     * 通用失败标识
     */
    public static final String FAIL = "1";

    /**
     * 登录成功
     */
    public static final String LOGIN_SUCCESS = "Success";

    /**
     * 注销
     */
    public static final String LOGOUT = "Logout";

    /**
     * 登录失败
     */
    public static final String LOGIN_FAIL = "Error";

    /**
     * 当前记录起始索引
     */
    public static final String PAGE_NUM = "pageNum";

    /**
     * 每页显示记录数
     */
    public static final String PAGE_SIZE = "pageSize";

    /**
     * 排序列
     */
    public static final String ORDER_BY_COLUMN = "orderByColumn";

    /**
     * 排序的方向 "desc" 或者 "asc".
     */
    public static final String IS_ASC = "isAsc";

    /**
     * 资源映射路径 前缀
     */
    public static final String RESOURCE_PREFIX = "/profile";
}
