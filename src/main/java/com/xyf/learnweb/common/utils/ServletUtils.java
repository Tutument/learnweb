package com.xyf.learnweb.common.utils;

import com.alibaba.fastjson.JSONObject;
//import com.ciphergateway.crypto.CGCipher;
import com.xyf.learnweb.common.constant.Constants;
import com.xyf.learnweb.common.exception.BusinessException;
import com.xyf.learnweb.common.utils.text.Convert;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 客户端工具类
 * 
 * @author Lihui
 */
public class ServletUtils
{
    /**
     * 获取String参数
     */
    public static String getParameter(String name)
    {
        return getRequest().getParameter(name);
    }

    /**
     * 获取String参数
     */
    public static String getParameter(String name, String defaultValue)
    {
        return Convert.toStr(getRequest().getParameter(name), defaultValue);
    }

    /**
     * 获取Integer参数
     */
    public static Integer getParameterToInt(String name)
    {
        return Convert.toInt(getRequest().getParameter(name));
    }

    /**
     * 获取Integer参数
     */
    public static Integer getParameterToInt(String name, Integer defaultValue)
    {
        return Convert.toInt(getRequest().getParameter(name), defaultValue);
    }

    /**
     * 获取request
     */
    public static HttpServletRequest getRequest()
    {
        return getRequestAttributes().getRequest();
    }

    /**
     * 获取response
     */
    public static HttpServletResponse getResponse()
    {
        return getRequestAttributes().getResponse();
    }

    /**
     * 获取session
     */
    public static HttpSession getSession()
    {
        return getRequest().getSession();
    }

    public static ServletRequestAttributes getRequestAttributes()
    {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        return (ServletRequestAttributes) attributes;
    }

    /**
     * 将字符串渲染到客户端
     * 
     * @param response 渲染对象
     * @param string 待渲染的字符串
     * @return null
     */
    public static String renderString(HttpServletResponse response, String string)
    {
        try
        {
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(string);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    public static JSONObject parseRequest(HttpServletRequest request) {
    	try {
    		BufferedReader streamReader = new BufferedReader( new InputStreamReader(request.getInputStream(), "UTF-8"));
    		StringBuilder reqBuilder = new StringBuilder();
    		String inputStr;
    		while ((inputStr = streamReader.readLine()) != null)
    			reqBuilder.append(inputStr);
    		return JSONObject.parseObject(reqBuilder.toString());
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return null;
    }
    
    private static final String ENCDATA = "encdata";
    private static final String ID_FROM = "id_from";
    private static final String ID_TO = "id_to";
    
    public static boolean isIdValid(String id_from, String id_to) {
    	Pattern p = Pattern.compile(ParamUtils.MODULE_ID_PAT);
    	return p.matcher(id_from).matches() && p.matcher(id_to).matches();
    }
    
    public static JSONObject parseRequestEx(HttpServletRequest request) throws Exception {
    	try { 		
    		BufferedReader streamReader = new BufferedReader( new InputStreamReader(request.getInputStream(), "UTF-8"));
    		StringBuilder reqBuilder = new StringBuilder();
    		String inputStr;
    		while ((inputStr = streamReader.readLine()) != null)
    			reqBuilder.append(inputStr);
    		JSONObject reqObj = JSONObject.parseObject(reqBuilder.toString());

    		String id_from = reqObj.getString(ID_FROM);
    		String id_to = reqObj.getString(ID_TO);//Constants.I_MODULE_ID;
    		if (!isIdValid(id_from, id_to))
    			throw new BusinessException("0" + ErrMsgUtils.ERR_PARAMS_NOT_SUPPORT);
        	//xuwei 2.11
    		System.out.println("### I_MODULE_ID ### " + Constants.I_MODULE_ID);
        	if(!id_to.equalsIgnoreCase(Constants.I_MODULE_ID))
        		throw new BusinessException("0" + ErrMsgUtils.ERR_IDTO_NOT_SUPPORT);
        		
    		String enc = reqObj.getString(ENCDATA);
    		reqObj.remove(ENCDATA);
    		byte[] cipherData = Base64Helper.decodeBytes(enc.getBytes());
    		byte[] plainData = null;
    		try {
    			/*CGCipher oa = CGCipher.getInstance("OA");
    			plainData = oa.OA_Decrypt(id_to, id_from, cipherData);*/
                plainData = new byte[]{};
    		} catch (Exception e) {
    			e.printStackTrace();
    			throw new BusinessException("0" + ErrMsgUtils.ERR_CAL_FAILED);
    		}
    		String plain = new String(plainData);
    		JSONObject contObj = JSONObject.parseObject(plain);
    		
    		for (Map.Entry<String, Object> entry : contObj.entrySet())
    			reqObj.put(entry.getKey(), entry.getValue());
	
    		return reqObj;
    	} catch (BusinessException e) {
    		throw e;
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return null;
    }
    
    public static JSONObject genResponse(JSONObject contObj, JSONObject reqObj, int code) throws Exception {
    	JSONObject respObj = new JSONObject();

		String id_from = reqObj.getString(ID_FROM);
		String id_to = reqObj.getString(ID_TO);
		if (contObj != null) {
			byte[] plainData  = contObj.toJSONString().getBytes();
			byte[] cipherData = null;
			try {
				/*CGCipher oa = CGCipher.getInstance("OA");
				cipherData = oa.OA_Encrypt(id_to, id_from, plainData);*/
    		} catch (Exception e) {
    			e.printStackTrace();
    			throw new BusinessException("0" + ErrMsgUtils.ERR_CAL_FAILED);
    		}
    		String cipher = Base64Helper.encodeBytes(cipherData);
    		respObj.put(ENCDATA, cipher);
		}
		
		respObj.put(ID_FROM, id_to);//Constants.I_MODULE_ID;
		respObj.put(ID_TO, id_from);
		for (Map.Entry<String, Object> entry : contObj.entrySet())
			respObj.put(entry.getKey(), entry.getValue());

    	ErrMsgUtils.setResponse(code, respObj);
    	return respObj;
    }

    /**
     * 是否是Ajax异步请求
     * 
     * @param request
     */
    public static boolean isAjaxRequest(HttpServletRequest request)
    {
        String accept = request.getHeader("accept");
        if (accept != null && accept.indexOf("application/json") != -1)
        {
            return true;
        }

        String xRequestedWith = request.getHeader("X-Requested-With");
        if (xRequestedWith != null && xRequestedWith.indexOf("XMLHttpRequest") != -1)
        {
            return true;
        }

        String uri = request.getRequestURI();
        if (StringUtils.inStringIgnoreCase(uri, ".json", ".xml"))
        {
            return true;
        }

        String ajax = request.getParameter("__ajax");
        if (StringUtils.inStringIgnoreCase(ajax, "json", "xml"))
        {
            return true;
        }
        return false;
    }
}
