package com.shtel.secure.utils;


import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * 访问http资源模板.
 * @author wangmj
 *
 */
public class ConsumeTemplate {
	
	private ConsumeTemplate() {
	    throw new IllegalAccessError("Utility class");
	}
	
	/** 
	 * 配置header方法。
	 * @param headerMap HttpHeaders中的key,value组成的map
	 * @return HttpHeaders
	 */
	public static HttpHeaders getHeaders(Map<String,String> headerMap){
		HttpHeaders headers = new HttpHeaders();
		if(headerMap != null) {
		    headers.setAll(headerMap);
		}
		headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
	}
	
	/**
	 * 通用rest请求。
	 * @param url the fully-expanded URL to connect to
	 * @param json 请求体
	 * @param method the HTTP method to execute (GET, POST, etc.) 
	 * @param clazz 返回值类型的class
	 * @return clazz类型的返回值
	 */
	public static <T> T commonRest(String url, String json, HttpMethod method, Class<T> clazz) {
        HttpEntity<String> entity = new HttpEntity<String>(json, getHeaders(null));
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        ResponseEntity<T> responseEntity = restTemplate.exchange(url, method, entity, clazz);
        return (T) responseEntity.getBody();
	}
	
	/**
	 * 通用rest请求(带上自定义的headers参数).
	 * @param url the fully-expanded URL to connect to
	 * @param json 请求体
	 * @param method the HTTP method to execute (GET, POST, etc.) 
	 * @param clazz 返回值类型的class
	 * @param headerMap headers中的参数
	 * @return
	 */
	public static <T> T commonRest(String url, String json, HttpMethod method, Class<T> clazz, Map<String, String> headerMap) {
        HttpEntity<String> entity = new HttpEntity<String>(json, getHeaders(headerMap));
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        ResponseEntity<T> responseEntity = restTemplate.exchange(url, method, entity, clazz);
        return (T) responseEntity.getBody();
	}
	
	/**
	 * GET方式请求。
	 * @param url the fully-expanded URL to connect to
	 * @param clazz 返回值类型的class
	 * @return clazz类型的返回值
	 */
	public static <T> Map<String, Object> commonGet(String url,Class<T> clazz) {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
		Map<String, Object> resultMap = new HashMap<String, Object>();
		ResponseEntity<T> responseEntity = restTemplate.getForEntity(url, clazz);
		resultMap.put("body", (T) responseEntity.getBody());
		resultMap.put("statusCode", responseEntity.getStatusCode());
		return resultMap;
	}
	
	/** 
	 * GET方式请求(带上自定义的headers参数)。
	 * @param url the fully-expanded URL to connect to
	 * @param clazz 返回值类型的class
	 * @param headerMap headers中的参数
	 * @return clazz类型的返回值
	 */
	public static <T> T commonGetWithUniqueHeader(String url,Class<T> clazz,Map<String, String> headerMap) {
		return commonRestWithUniqueHeader(url,"", HttpMethod.GET,clazz,getHeaders(headerMap));
	}
	
	public static <T> Map<String, Object> commonGet(String url, Class<T> clazz, String param) {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
		Map<String, Object> resultMap = new HashMap<String, Object>();
		ResponseEntity<T> responseEntity = restTemplate.getForEntity(url, clazz,param);
		resultMap.put("body", (T) responseEntity.getBody());
		resultMap.put("statusCode", responseEntity.getStatusCode());
		return resultMap;
	}
	
	public static <T> ResponseEntity<T> commonGet(String url, Class<T> clazz, Map<String, String> uriVariables) {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
		ResponseEntity<T> responseEntity = restTemplate.getForEntity(url, clazz,uriVariables);
		return responseEntity;
	}
	
	/**
	 * POST方式请求。
	 * @param url the fully-expanded URL to connect to
	 * @param json 请求体
	 * @param clazz 返回值类型的class
	 * @return clazz类型的返回值
	 */
	public static <T> ResponseEntity<T> commonPost(String url, String json, Class<T> clazz) {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
		HttpEntity<String> request = new HttpEntity<String>(json, getHeaders(null));
		ResponseEntity<T> responseEntity = restTemplate.postForEntity(url, request, clazz);
		return responseEntity;
	}
	
	/**
	 * POST方式请求。
	 * @param url the fully-expanded URL to connect to
	 * @param requestMap 请求体
	 * @param clazz 返回值类型的class
	 * @return clazz类型的返回值
	 */
	public static <T> ResponseEntity<T> commonFormPost(String url, MultiValueMap<String, String> requestMap, Class<T> clazz, Map<String, String> headerMap) {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
		HttpHeaders headers = new HttpHeaders();
		if(headerMap != null){
			headers.setAll(headerMap);
		}
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(requestMap, headers);
		ResponseEntity<T> responseEntity = restTemplate.postForEntity(url, request, clazz);
		return responseEntity;
	}
	
	/**
	 * POST方式请求(带上自定义的headers参数)。
	 * @param url the fully-expanded URL to connect to
	 * @param json 请求体
	 * @param clazz 返回值类型的class
	 * @param headerMap headers中的参数
	 * @return clazz类型的返回值
	 */
	public static <T> T commonPost(String url,String json,Class<T> clazz,Map<String, String> headerMap) {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
		HttpEntity<String> request = new HttpEntity<String>(json, getHeaders(headerMap));
		ResponseEntity<T> responseEntity = restTemplate.postForEntity(url, request, clazz);
		return (T) responseEntity.getBody();
	}
	
	/**
	 * 通用rest请求(带上自定义的headers)。
	 * @param url the fully-expanded URL to connect to
	 * @param json 请求体
	 * @param method the HTTP method to execute (GET, POST, etc.) 
	 * @param clazz 返回值类型的class
	 * @param headers 请求头部
	 * @return clazz类型的返回值
	 */
	public static <T> T commonRestWithUniqueHeader(String url, String json,
                                                   HttpMethod method, Class<T> clazz, HttpHeaders headers) {
        HttpEntity<String> entity = new HttpEntity<String>(json, headers);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        ResponseEntity<T> responseEntity = restTemplate.exchange(url, method, entity, clazz);
        return (T) responseEntity.getBody();
	}
	
	/** 
	 * GET方式请求(带上自定义的headers)。
	 * @param url the fully-expanded URL to connect to
	 * @param clazz 返回值类型的class
	 * @param headers 请求头部
	 * @return clazz类型的返回值
	 */
	public static <T> T commonGetWithUniqueHeader(String url, Class<T> clazz, HttpHeaders headers) {
		return commonRestWithUniqueHeader(url,"", HttpMethod.GET,clazz,headers);
	}
	
	/** 
	 * POST方式请求(带上自定义的headers)。
	 * @param url the fully-expanded URL to connect to
	 * @param requestString 请求体
	 * @param clazz 返回值类型的class
	 * @param headers 请求头部
	 * @return clazz类型的返回值
	 */
	public static <T> ResponseEntity<T> commonPostWithUniqueHeader(String url, String requestString, Class<T> clazz, HttpHeaders headers) {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
		HttpEntity<String> request = new HttpEntity<String>(requestString, headers);
		ResponseEntity<T> responseEntity = restTemplate.postForEntity(url, request, clazz);
		return responseEntity;
	}
	public static <T> ResponseEntity<T> commonPostWithUniqueHeader(String url, String requestString, Class<T> clazz, Map<String, String> headerMap) {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
		HttpEntity<String> request = new HttpEntity<String>(requestString, getHeaders(headerMap));
		ResponseEntity<T> responseEntity = restTemplate.postForEntity(url, request, clazz);
		return responseEntity;
	}

	public static <T> ResponseEntity<T> commonGetWithUniqueHeader(String url, Class<T> clazz,
                                                                  Map<String, String> headerMap, Map<String, String> map) {
		HttpEntity<String> entity = new HttpEntity<String>("", getHeaders(headerMap));
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        ResponseEntity<T> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, clazz, map);
        return responseEntity;
	}
}
