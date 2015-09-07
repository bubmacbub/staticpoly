package com.volute.util;

import java.io.IOException;
import java.util.Arrays;

import com.manybrain.persistent.MemCacheClient;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import play.api.Play;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author amadoudiallo
 */
public class VoluteCacheClient {
	private MemCacheClient cc;
    

    private VoluteCacheClient() {
    	
    	Config conf = ConfigFactory.load();
    	String cacheServers = conf.getString("volute.cache.servers");
    	String weightServers = conf.getString("volute.cache.weights");
    	if(cacheServers==null || weightServers==null)
    	{
    		throw new RuntimeException("Cannot find the [volute.cache.servers] or [volute.cache.weights] is missing in the app.conf");
    	}
    	
    	String [] servers= cacheServers.split(",");
    	int[] weights= Arrays.stream( weightServers.split(", ")).mapToInt(Integer::parseInt).toArray();
    	if(servers.length != weights.length)
    	{
    		throw new RuntimeException("Number of elements in [volute.cache.servers] should match size of [volute.cache.weights] in the app.conf");
        	
    	}
    	try {
			cc=new MemCacheClient(servers, weights);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
    }
    
    public void setValue(String key, Object o) throws IOException
    {
    	cc.set(key, o);
    	
    }
    
    public Object getValue(String key)
    {
    	return cc.get(key);
    	
    }
    
    public static VoluteCacheClient getInstance() {
        return VoluteCacheClientHolder.INSTANCE;
    }
    
    private static class VoluteCacheClientHolder {

        private static final VoluteCacheClient INSTANCE = new VoluteCacheClient();
    }
}

