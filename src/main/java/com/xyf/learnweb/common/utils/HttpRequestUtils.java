package com.xyf.learnweb.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.xyf.learnweb.common.constant.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Date;

/**
 * 
 * @author hkf
 * http请求工具类
 *
 */
public class HttpRequestUtils {
    protected static final transient Logger LOGGER = LoggerFactory.getLogger(HttpRequestUtils.class);
    private RestTemplate restTemplate;
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public String sendHttpRequestForm(String url, MultiValueMap<String, String> params) throws IOException {
        LOGGER.debug("sendHttpRequestForm:{}", params.toString()); // 打印结果页面
        HttpHeaders headers = new HttpHeaders();
        // 提交方式表单提交
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(params, headers);
        //  执行HTTP请求
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        if (response.getStatusCode().isError()) {
            return null;
        }
        return response.getBody();
    }
    
    public String sendHttpRequestJson(String url, String json) throws IOException {
        LOGGER.debug("sendHttpRequestJson:{}", json); // 打印结果页面
        if (Constants.CA_FLAG == 0) {
            JSONObject localCert = JSONObject.parseObject(Constants.LOCAL_CERT);
            localCert.put("notBefore", DateUtils.format(new Date(), DateUtils.YYYY_MM_DD_HH_MM_SS));
            LOGGER.info(localCert.toJSONString());

            return localCert.toJSONString();
        } else {
            HttpHeaders headers = new HttpHeaders();
            // 提交方式表单提交
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            HttpEntity<String> requestEntity = new HttpEntity<>(json, headers);
            //  执行HTTP请求
            ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);
            if (response.getStatusCode().isError()) {
                return null;
            }
            return response.getBody();
        }
    }
 

    public String sendHttpRequestJsonUtf8(String url, String json) throws IOException {
        LOGGER.debug("sendHttpRequestJson:{}", json); // 打印结果页面
        HttpHeaders headers = new HttpHeaders();
        // 提交方式表单提交
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(json, headers);
        //  执行HTTP请求
        ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);
        if (response.getStatusCode().isError()) {
            return null;
        }
        return response.getBody();
    }
    
}