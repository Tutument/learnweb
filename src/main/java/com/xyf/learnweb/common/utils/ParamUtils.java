package com.xyf.learnweb.common.utils;

import com.xyf.learnweb.common.exception.BusinessException;

import java.util.regex.Pattern;

public class ParamUtils {
	public static final String MODULE_ID_PAT = "[0-9A-Z]{64}";
	
	public static final String APP_ID_PAT = "[0-9A-Z]{32}";
	
	public static final String CARD_ID_PAT = "[\\s\\S]{12}";
	public static final String CLUSTER_ID_PAT = "[0-9A-Z]{32}";
	
	public static final String LEN255_PAT = "[\\s\\S]{1,255}";
	
	public static void chkParam(final String param, final String pattern) throws BusinessException {
		Pattern p = Pattern.compile(pattern);
		if (!p.matcher(param).matches())
			throw new BusinessException("0" + ErrMsgUtils.ERR_PARAMS_NOT_SUPPORT);
	}
}
