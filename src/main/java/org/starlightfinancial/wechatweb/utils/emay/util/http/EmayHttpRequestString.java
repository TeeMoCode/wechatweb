package org.starlightfinancial.wechatweb.utils.emay.util.http;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * 传输数据为String的请求实体
 * 
 * @author Frank
 *
 */
public class EmayHttpRequestString extends EmayHttpRequest<String>{

	public EmayHttpRequestString(String url, String charSet, String method, Map<String, String> headers, String cookies, String params) {
		super(url, charSet, method, headers, cookies, params);
	}

	@Override
	public byte[] paramsToBytesForPost() {
		try {
			return this.getParams().getBytes(this.getCharSet());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String paramsToStringForGet() {
		return this.getParams();
	}


}
