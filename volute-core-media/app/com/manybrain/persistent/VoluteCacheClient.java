package com.manybrain.persistent;

public class VoluteCacheClient {
	
	
	public static void main(String args[]) throws Exception
	{
//		  String[] a = new String[]{"127.0.0.1:11211", "127.0.0.1:12212"};
//		    int b[] = new int[]{5, 2};
//		    MemCacheClient mcc = new MemCacheClient(a, b);
		   // set("key1","My Value");
	 System.out.println("Retrieved value: "+get("key1"));
		  
		
	}

	private static void set(String key, Object value) throws Exception {
		 String[] a = new String[]{"127.0.0.1:11211", "127.0.0.1:12212"};
		 int b[] = new int[]{5, 2};
		 MemCacheClient mcc = new MemCacheClient(a, b);
		 mcc.set(key, value);
		 // set(key,value);
		
	}
	
	private static Object get(String key) throws Exception {
		 String[] a = new String[]{"127.0.0.1:11211", "127.0.0.1:12212"};
		 int b[] = new int[]{5, 2};
		 MemCacheClient mcc = new MemCacheClient(a, b);
		  return mcc.get(key);
		
	}

}
