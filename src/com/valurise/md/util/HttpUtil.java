package com.valurise.md.util;

import com.valurise.md.model.SdkHttpResult;

import javax.net.ssl.*;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

public class HttpUtil {

	private static SSLContext sslCtx = null;
	static {

		try {
			sslCtx = SSLContext.getInstance("TLS");
			X509TrustManager tm = new X509TrustManager() {
				public void checkClientTrusted(X509Certificate[] xcs,
						String string) throws CertificateException {
				}

				public void checkServerTrusted(X509Certificate[] xcs,
						String string) throws CertificateException {
				}

				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}
			};
			sslCtx.init(null, new TrustManager[] { tm }, null);
		} catch (Exception e) {
			e.printStackTrace();
		}

		HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {

			@Override
			public boolean verify(String arg0, SSLSession arg1) {
				// TODO Auto-generated method stub
				return true;
			}

		});

		HttpsURLConnection
				.setDefaultSSLSocketFactory(sslCtx.getSocketFactory());

	}

	// 设置body体
	public static void setBodyParameter(StringBuilder sb, HttpURLConnection conn)
			throws IOException {
		DataOutputStream out = new DataOutputStream(conn.getOutputStream());
		out.writeBytes(sb.toString());
		out.flush();
		out.close();
	}
	
	public static void setBodyParameter(String str, HttpURLConnection conn)
			throws IOException {
		DataOutputStream out = new DataOutputStream(conn.getOutputStream());
		out.write(str.getBytes("utf-8"));
		out.flush();
		out.close();
	}

    public static void setBodyParameter(Map<String, String> params, HttpURLConnection conn)
            throws IOException {
        StringBuilder sb = new StringBuilder();
        boolean firstOne = true;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (!firstOne) {
                sb.append("&");
            }
            sb.append(entry.getKey()).append("=").append(entry.getValue());
            firstOne = false;
        }
        setBodyParameter(sb,conn);
    }

	public static HttpURLConnection CreateHttpConnection(String uri, String requestMethod, String contentType)
			throws IOException, ProtocolException {


		URL url = new URL(uri);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setUseCaches(false);
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setRequestMethod(requestMethod);
		conn.setInstanceFollowRedirects(true);
		conn.setConnectTimeout(30000);
		conn.setReadTimeout(30000);
        conn.setRequestProperty("Content-Type", contentType);
		return conn;
	}

	public static byte[] readInputStream(InputStream inStream) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		byte[] data = outStream.toByteArray();
		outStream.close();
		inStream.close();
		return data;
	}

	public static SdkHttpResult returnResult(HttpURLConnection conn)
			throws Exception, IOException {
		String result;
		InputStream input = null;
		if (conn.getResponseCode() == 200) {
			input = conn.getInputStream();
		} else {
			input = conn.getErrorStream();
		}
		result = new String(readInputStream(input), "UTF-8");
		return new SdkHttpResult(conn.getResponseCode(), result);
	}
}
