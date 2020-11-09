package com.xyf.learnweb.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.xyf.learnweb.framework.web.domain.AjaxResult;

public class ErrMsgUtils {
	public static final int ERR_BASE = 0x00000000;
	public static final int ERR_CERT_BASE = 0x0C000000;
	public static final int ERR_DB_BASE = 0x0D000000;
	public static final int ERR_ENC_BASE = 0x0E000000;
	public static final int SRC_MASK = 0xFF000000;
	public static final int ERR_MASK = 0xFFFFFF;

	public static final int ERR_SUCCESS = 0;
	public static final int ERR_UNKNOWN = (ERR_BASE | 0x01);
	public static final int ERR_INTERFACE_NOT_SUPPORT  = (ERR_BASE | 0x02);
	public static final int ERR_PARAMS_NOT_SUPPORT = (ERR_BASE | 0x03);
	public static final int ERR_BAD_MEMORY = (ERR_BASE | 0x04);
	public static final int ERR_NOT_INITIALIZE = (ERR_BASE | 0x05);
	public static final int ERR_INVALID_PARAM = (ERR_BASE | 0x06);
	public static final int ERR_BAD_DATA = (ERR_BASE | 0x07);
	public static final int ERR_BAD_LEN = (ERR_BASE | 0x08);
	public static final int ERR_ACCESS_DENIED = (ERR_BASE | 0x09);
    public static final int ERR_TIMEOUT = (ERR_BASE | 0x0A);
    public static final int ERR_NEWWORK_OPERATION_FAILED = (ERR_BASE | 0x0B);
    public static final int ERR_BAD_KEY = (ERR_BASE | 0x0C);
    public static final int ERR_BAD_CERT = (ERR_BASE | 0x0D);
    public static final int ERR_CAL_FAILED = (ERR_BASE | 0x10);
    public static final int ERR_BAD_FILE = (ERR_BASE | 0x14);
    
	public static final int ERR_DB_FAIL = (ERR_DB_BASE | 0x01);
    public static final int ERR_DB_INSERT = (ERR_DB_BASE | 0x04);
    public static final int ERR_DB_UPDATE = (ERR_DB_BASE | 0x05);
    public static final int ERR_DB_DUPLICATE = (ERR_DB_BASE | 0x06);
    public static final int ERR_DB_NOTFOUND = (ERR_DB_BASE | 0x07);
    
    public static final int ERR_NOT_IN_CLUSTER = (ERR_BASE | 0xE0);
    public static final int ERR_NOT_IN_APP = (ERR_BASE | 0xE1);
    public static final int ERR_APP_NOT_FOUND = (ERR_BASE | 0xE2);
    public static final int ERR_MODULE_PUBED = (ERR_BASE | 0xE3);
    public static final int ERR_CARD_FEATRUE_PUSH = (ERR_BASE | 0xE4);
    public static final int ERR_MODULE_UNENABLED = (ERR_BASE | 0xE5);
    public static final int ERR_CLUSTER_NOT_FOUND= (ERR_BASE | 0xE6);
    public static final int ERR_APP_NOT_ENABLED= (ERR_BASE | 0xE7);
    public static final int ERR_IDTO_NOT_SUPPORT= (ERR_BASE | 0xE8);
    public static final int ERR_CLUSTER_CERT_UPDATE_OFFLINE= (ERR_BASE | 0xE9);
    public static final int ERR_CERT_DUPLICATE= (ERR_BASE | 0xEA);
    public static final int ERR_APP_NOT_SERVER= (ERR_BASE | 0xEB);

    
    public static final String getErrMsg(int code) {
    	if (ERR_ENC_BASE == (code & SRC_MASK))
    		return "密码机错误";
    	if (ERR_CERT_BASE == (code & SRC_MASK))
    		return "证书签发错误";
        switch (code) {
            case 0x00:
                return "操作执行成功";
            case 0x01:
                return "未知异常错误";
            case 0x02:
                return "不支持或未实现该接口";
            case 0x03:
                return "不支持或未实现该参数";
            case 0x04:
                return "内存错误";
            case 0x05:
                return "未完成接口初始化";
            case 0x06:
                return "接口参数无效";
            case 0x07:
                return "数据错误或不存在";
            case 0x08:
                return "长度错误";
            case 0x09:
                return "拒绝访问";
            case 0x0A:
                return "操作超时";
            case 0x0B:
                return "网络连接失败";
            case 0x0C:
                return "密钥错误或不存在";
            case 0x0D:
                return "证书错误或不存在";
            case 0x10:
            	return "密码运算失败";
            case 0x14:
                return "文件错误";
                
            /** xuwei */    
            case ERR_DB_INSERT:
            	return "插入记录出错";
            case ERR_DB_UPDATE:
                return "更新记录出错";
            case ERR_DB_DUPLICATE:
            	return "记录已存在";
            case ERR_DB_NOTFOUND:
            	return "记录不存在";
            case ERR_NOT_IN_CLUSTER:
                return "密码卡不在集群中";
            case ERR_NOT_IN_APP:
                return "模块与应用未绑定";
            case ERR_APP_NOT_FOUND:
                return "应用不存在";
            case ERR_MODULE_PUBED:
                return "证书公钥已发布";
            case ERR_CARD_FEATRUE_PUSH:
                return "推送密码卡特征值出错";
            case ERR_MODULE_UNENABLED:
                return "模块未启用";
            case ERR_CLUSTER_NOT_FOUND:
                return "集群未登记";
            case ERR_APP_NOT_ENABLED:
                return "应用未启用";
            case ERR_IDTO_NOT_SUPPORT:
                return "非密钥管理中心ID";
            case ERR_CLUSTER_CERT_UPDATE_OFFLINE:
                return "集群证书须线下更新";
            case ERR_CERT_DUPLICATE:
                return "证书重复，请检查应用类型";
            case ERR_APP_NOT_SERVER:
                return "非服务器应用，请检查应用类型";
            default:
            	return "";
        }
    }
    
    public static void setResponse(int code, JSONObject respObj) {
    	respObj.put(AjaxResult.CODE_TAG, String.format("%d", code));
    	respObj.put(AjaxResult.MSG_TAG, getErrMsg(code));
    }
    
    public static void parseException(Exception e, JSONObject respObj) {
    	try {
            e.printStackTrace();
			int code = Integer.parseInt(e.getMessage());
			ErrMsgUtils.setResponse(code, respObj);
		} catch (Exception e1) {
			ErrMsgUtils.setResponse(ErrMsgUtils.ERR_UNKNOWN, respObj);
		}
    }
}
