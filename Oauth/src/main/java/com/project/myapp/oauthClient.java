package com.project.myapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class oauthClient {
	private static final String AUTHCODE_URL = "https://domain.com/oauth2/authorize";
    private static final String AUTHCODE_REDIRECT_URI = "/Oauth/auth/client/redirect";
    private static final String AUTHTOKEN_URL = "https://domain.com/oauth2/token";

    public static String getRedirectAuthCodeURL(String appKey , String clientPublisherID, String ngrok) {
        String url;

        url = AUTHCODE_URL + "?" + "client_id=" + appKey + "&" + "redirect_uri=" + ngrok + AUTHCODE_REDIRECT_URI + "/" + clientPublisherID
                + "&scope=" + "FEEDBACK_READ,FEEDBACK_WRTIE" + "&response_type=code";
        return url;
    }
    
    public static String getAuthToken(String authCode , String appKey, String appSecret)throws IOException {
        HttpsURLConnection connection = null;
        String authTokenURL = AUTHTOKEN_URL + "?code=" + authCode +"&client_id="+ appKey + "&client_secret="+appSecret;
        String inputLine = null;
        try {
        	URL url=new URL(authTokenURL);
        	connection = getSecureConnection(url);
        } catch (MalformedURLException e) {//MalformoracleedURL
            e.printStackTrace();
            return "error";
        }
    try{	
    
    	BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
    	
        if(in.readLine() != null)
            inputLine = in.readLine();
        in.close();
    }
    catch(IOException e){
    e.printStackTrace();
    }
        if(inputLine.contains("error") == false) {
            System.out.println(inputLine);
            return inputLine;
        }
        return "error";   
    }

    private static HttpsURLConnection getSecureConnection(URL url) {
        TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            public void checkClientTrusted(X509Certificate[] certs,
                                           String authType) {
            }

            public void checkServerTrusted(X509Certificate[] certs,
                                           String authType) {
            }
        } };

        // Install the all-trusting trust manager
        SSLContext sc;
        try {
            sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }

        // Create all-trusting host name verifier
        HostnameVerifier allHostsValid = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };

        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
        HttpsURLConnection connection = null;
        try {
            connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
