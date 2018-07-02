package com.soarswing.camel.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;


public class LogbackConfigLoader {  
    
    public String onStartup( ServletContext servletContext ) throws ServletException {  
    	String s = null;
        Properties prop = new Properties();  
        InputStream in = this.getClass().getResourceAsStream( "/application.properties" );  
        try {  
            prop.load(in);  
            Enumeration<Object> iter= prop.keys();  
            while(iter.hasMoreElements()){  
                String key = iter.nextElement().toString();  
                if(key.startsWith( "log" )){  
                    System.setProperty( key, prop.getProperty( key ) );  
                    System.out.println("set system logging parameter :"+key+" = "+prop.getProperty( key ));  
                }  
                return key;
            }  
           
        } catch( IOException e ) {  
            e.printStackTrace();  
        }  
        return s;
    }  
  
}  
