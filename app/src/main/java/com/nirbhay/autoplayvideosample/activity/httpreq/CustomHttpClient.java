//
//  
//  
//
package com.nirbhay.autoplayvideosample.activity.httpreq;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

public class CustomHttpClient {
	public static final int HTTP_TIMEOUT = 30 * 1000; // milliseconds

	/** Single instance of our HttpClient */
	private static HttpClient mHttpClient;

	private static HttpClient getHttpClient() {
		if (mHttpClient == null) {
			mHttpClient = new DefaultHttpClient();
			final HttpParams params = mHttpClient.getParams();
			HttpConnectionParams.setConnectionTimeout(params, HTTP_TIMEOUT);
			HttpConnectionParams.setSoTimeout(params, HTTP_TIMEOUT);
			ConnManagerParams.setTimeout(params, HTTP_TIMEOUT);
		}
		return mHttpClient;

	}
/*public static String excuteHttpHeaderAuth()
{

	HttpPost post = new HttpPost(url);
	post.setHeader("Accept", "application/json");


	return
}*/


	public static String executeHttpPostHeaderAuth(String url,
                                                   ArrayList<NameValuePair> postParameters) throws Exception {
		BufferedReader in = null;
		HttpResponse response = null;
		HttpPost request = null;
		try {
			HttpClient client = getHttpClient();
			request = new HttpPost(url);

			request.setHeader("Accept", "application/json");

			UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(
					postParameters);
			request.setEntity(formEntity);
			response = client.execute(request);

			in = new BufferedReader(new InputStreamReader(response.getEntity()
					.getContent()));

			StringBuffer sb = new StringBuffer("");
			String line = "";
			String NL = System.getProperty("line.separator");
			while ((line = in.readLine()) != null) {
				sb.append(line + NL);
			}
			in.close();

			String result = sb.toString();
			return result;
		} finally {
			if (in != null) {
				try {
					in.close();

				} catch (IOException e) {
					request.abort();
					e.printStackTrace();
				}
			}
		}
	}



	public static String executeHttpPostHeaderAuthBeareaToken(String url,
                                                              ArrayList<NameValuePair> postParameters, String beareaToken) throws Exception {
		BufferedReader in = null;
		HttpResponse response = null;
		HttpPost request = null;
		try {
			HttpClient client = getHttpClient();
			request = new HttpPost(url);

			request.setHeader("Accept", "application/json");
			request.setHeader("Authorization", "Bearer "+beareaToken);

			UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(
					postParameters);
			request.setEntity(formEntity);
			response = client.execute(request);

			in = new BufferedReader(new InputStreamReader(response.getEntity()
					.getContent()));

			StringBuffer sb = new StringBuffer("");
			String line = "";
			String NL = System.getProperty("line.separator");
			while ((line = in.readLine()) != null) {
				sb.append(line + NL);
			}
			in.close();

			String result = sb.toString();
			return result;
		} finally {
			if (in != null) {
				try {
					in.close();

				} catch (IOException e) {
					request.abort();
					e.printStackTrace();
				}
			}
		}
	}

	public static String executeHttpPost(String url,
                                         ArrayList<NameValuePair> postParameters) throws Exception {
		BufferedReader in = null;
		HttpResponse response = null;
		HttpPost request = null;
		try {
			HttpClient client = getHttpClient();
			request = new HttpPost(url);
			UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(
					postParameters);
			request.setEntity(formEntity);
			response = client.execute(request);

			in = new BufferedReader(new InputStreamReader(response.getEntity()
					.getContent()));

			StringBuffer sb = new StringBuffer("");
			String line = "";
			String NL = System.getProperty("line.separator");
			while ((line = in.readLine()) != null) {
				sb.append(line + NL);
			}
			in.close();

			String result = sb.toString();
			return result;
		} finally {
			if (in != null) {
				try {
					in.close();

				} catch (IOException e) {
					request.abort();
					e.printStackTrace();
				}
			}
		}
	}


	public static String urlincoding(String param) {
		String resp = "";
		try {

			HttpClient Client = new DefaultHttpClient();
			URL url1 = new URL(param);
			URI uri = new URI(url1.getProtocol(), url1.getUserInfo(),
					url1.getHost(), url1.getPort(), url1.getPath(),
					url1.getQuery(), url1.getRef());
			url1 = uri.toURL();
			HttpGet httpget = new HttpGet(url1.toString());
			String s = url1.toString();
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			resp = Client.execute(httpget, responseHandler);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp;
	}

	public static String executeHttpGet(String url) throws Exception {
		BufferedReader in = null;
		try {
			HttpClient client = getHttpClient();
			if (url.contains(" ")) {
				url = url.replace("", "%20");
			}
			HttpGet request = new HttpGet();
			request.setURI(new URI(url));
			HttpResponse response = client.execute(request);
			in = new BufferedReader(new InputStreamReader(response.getEntity()
					.getContent()));

			StringBuffer sb = new StringBuffer("");
			String line = "";
			String NL = System.getProperty("line.separator");
			while ((line = in.readLine()) != null) {
				sb.append(line + NL);
			}
			in.close();
			String result = sb.toString();
			return result;
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static String executeHttpGetHeaderAuth(String url) throws Exception {
		BufferedReader in = null;
		try {
			HttpClient client = getHttpClient();
			if (url.contains(" ")) {
				url = url.replace("", "%20");
			}
			HttpGet request = new HttpGet();
			request.setURI(new URI(url));
			request.setHeader("Accept", "application/json");
			HttpResponse response = client.execute(request);
			in = new BufferedReader(new InputStreamReader(response.getEntity()
					.getContent()));

			StringBuffer sb = new StringBuffer("");
			String line = "";
			String NL = System.getProperty("line.separator");
			while ((line = in.readLine()) != null) {
				sb.append(line + NL);
			}
			in.close();
			String result = sb.toString();
			return result;
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public static String executeHttpGetHeaderAuthToken(String url, String tokenbearea) throws Exception {
		BufferedReader in = null;
		try {
			HttpClient client = getHttpClient();
			if (url.contains(" ")) {
				url = url.replace("", "%20");
			}
			HttpGet request = new HttpGet();
			request.setURI(new URI(url));
			request.setHeader("Accept", "application/json");
			request.setHeader("Authorization", "Bearer "+tokenbearea);
			HttpResponse response = client.execute(request);
			in = new BufferedReader(new InputStreamReader(response.getEntity()
					.getContent()));

			StringBuffer sb = new StringBuffer("");
			String line = "";
			String NL = System.getProperty("line.separator");
			while ((line = in.readLine()) != null) {
				sb.append(line + NL);
			}
			in.close();
			String result = sb.toString();
			return result;
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static String executeHttpDelete(String url,
                                           ArrayList<NameValuePair> postParameters) throws Exception,
            IOException {
		// TODO Auto-generated method stub
		BufferedReader in = null;
		try {
			HttpClient client = getHttpClient();
			HttpPost request = new HttpPost(url);
			UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(
					postParameters);
			request.setEntity(formEntity);
			HttpResponse response = client.execute(request);

			in = new BufferedReader(new InputStreamReader(response.getEntity()
					.getContent()));

			StringBuffer sb = new StringBuffer("");
			String line = "";
			String NL = System.getProperty("line.separator");
			while ((line = in.readLine()) != null) {
				sb.append(line + NL);
			}
			in.close();

			String result = sb.toString();
			return result;
		} finally {
			if (in != null) {
				try {
					in.close();

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public static String excuteGetValuepair(String parmUrl,
                                            ArrayList<NameValuePair> getParameters) {
		String url = parmUrl;
		BufferedReader in = null;
		String result = "";
		try {
			HttpClient httpClient = new DefaultHttpClient();
			String paramsString = URLEncodedUtils
					.format(getParameters, "UTF-8");
			HttpGet httpGet = new HttpGet(url + "?" + paramsString);

			HttpResponse response = httpClient.execute(httpGet);
			in = new BufferedReader(new InputStreamReader(response.getEntity()
					.getContent()));

			StringBuffer sb = new StringBuffer("");
			String line = "";
			String NL = System.getProperty("line.separator");
			while ((line = in.readLine()) != null) {
				sb.append(line + NL);
			}
			in.close();
			result = sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}


}
