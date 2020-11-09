package com.xyf.learnweb.framework.config;

import com.xyf.learnweb.common.constant.Constants;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.*;
import java.util.Properties;

//@Configuration
public class LibLoadingConfig {
	static {
        try {
            System.loadLibrary("CGCipherJNI");         
            Properties properties = new Properties();
            
            properties = PropertiesLoaderUtils.loadProperties(new ClassPathResource("application.properties"));
            System.out.println("######  caflag=" + new String(properties.getProperty("caflag")));
            Constants.CA_FLAG = Integer.valueOf(properties.getProperty("caflag"));
            Constants.LOCAL_CERT = properties.getProperty("localcert");      
            
            String csConfPath = System.getenv("CS_CONFIG_PATH");
            System.out.println("######  csConfPath=" + csConfPath);
            if(csConfPath.endsWith("/"))            
                csConfPath = csConfPath.substring(0, csConfPath.length()-1);
            System.out.println("######  csConfPath2=" + csConfPath);
            InputStream inputStream = new BufferedInputStream(new FileInputStream(new File(csConfPath + "/module.config")));
            properties.load(new InputStreamReader(inputStream));
            Constants.I_MODULE_ID = properties.getProperty("module_id");
            Constants.I_APP_ID = properties.getProperty("app_id");
            System.out.println("######  I_MODULE_ID=" + Constants.I_MODULE_ID);
            System.out.println("######  I_APP_ID=" + Constants.I_APP_ID);

        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
