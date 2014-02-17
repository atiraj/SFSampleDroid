package com.authorwjf.http_get;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.DefaultedHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Main extends Activity implements OnClickListener {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        findViewById(R.id.my_button).setOnClickListener(this);
    }

	@Override
	public void onClick(View arg0) {
		Button b = (Button)findViewById(R.id.my_button);
		b.setClickable(false);
		new LongRunningGetIO().execute();
	}
	
	private class LongRunningGetIO extends AsyncTask <Void, Void, String> {
		
		protected String getASCIIContentFromEntity(HttpEntity entity) throws IllegalStateException, IOException {
	       InputStream in = entity.getContent();
	       StringBuilder builder = new StringBuilder();
	        /* StringBuffer out = new StringBuffer();
	         int n = 1;
	         while (n>0) {
	             byte[] b = new byte[4096];
	             n =  in.read(b);
	             if (n>0) out.append(new String(b, 0, n));
	         }
	         return out.toString();*/
	       BufferedReader reader = new BufferedReader(new InputStreamReader(in));
	        String line;
	        while ((line = reader.readLine()) != null) {
	          builder.append(line);
	        }
	        return builder.toString();
	    }
		
		protected JSONArray postArray(List<NameValuePair> nameValuePair,String url,String token)
		{
			JSONArray jsonArray=null;
			JSONObject jObj=null;
			String baseUrl="https://na15.salesforce.com";
			String strResponse=null;
			// Creating HTTP client
	        //HttpClient httpClient = createHttpClient();
			 HttpClient httpClient = new DefaultHttpClient();
			 URI uri=null;
	        // Creating HTTP Post
	       // HttpGet httpPost = new HttpGet(baseUrl + url);
			 
	        //HttpParams params=httpClient.getParams();
	        
	        //for(NameValuePair nvp:nameValuePair)
	        //{
	        	//params.setParameter(nvp.getName(), nvp.getValue());
	        	
	        //}
	        //try {
	        	try {
					 uri = new URI( baseUrl+url + "?" + URLEncodedUtils.format( nameValuePair, "utf-8" ));
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        	HttpUriRequest httpPost = new HttpGet(uri);
	        	httpPost.setHeader("Authorization", "OAuth " + token);
	            //httpPost.setParams(params);
	            
	       // } catch (UnsupportedEncodingException e) {
	            // writing error to Log
	         //   e.printStackTrace();
	        //}
	 
	        // Making HTTP Request
	        try {
	        	HttpContext localContext = new BasicHttpContext();
	            HttpResponse response = httpClient.execute(httpPost,localContext);
	            HttpEntity entity = response.getEntity();
	            strResponse = getASCIIContentFromEntity(entity);
	           // strResponse=response.toString();
	            // writing response to log
	            
	        } 
	        catch (ClientProtocolException e) {
	            // writing exception to log
	            e.printStackTrace();
	        } 
	        catch (IOException e) {
	            // writing exception to log
	            e.printStackTrace();
	 
	        }
	        catch(Exception e)
	        {
	        	e.printStackTrace();
	        }
	        
			
				try {
					jsonArray = new JSONArray(strResponse);
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//return jsonArray;
				return jsonArray;
		}
		
		protected JSONObject post(List<NameValuePair> nameValuePair,String url,String token)
		{
			JSONArray jsonArray=null;
			JSONObject jObj=null;
			String baseUrl="https://login.salesforce.com";
			String strResponse=null;
			// Creating HTTP client
	        //HttpClient httpClient = createHttpClient();
			 HttpClient httpClient = new DefaultHttpClient();
	         
	        // Creating HTTP Post
	        HttpPost httpPost = new HttpPost(baseUrl + url);
	        
	        try {
	        	httpPost.setHeader("Authorization", "OAuth " + token);
	            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
	        } catch (UnsupportedEncodingException e) {
	            // writing error to Log
	            e.printStackTrace();
	        }
	 
	        // Making HTTP Request
	        try {
	        	HttpContext localContext = new BasicHttpContext();
	            HttpResponse response = httpClient.execute(httpPost,localContext);
	            HttpEntity entity = response.getEntity();
	            strResponse = getASCIIContentFromEntity(entity);
	           // strResponse=response.toString();
	            // writing response to log
	            
	        } 
	        catch (ClientProtocolException e) {
	            // writing exception to log
	            e.printStackTrace();
	        } 
	        catch (IOException e) {
	            // writing exception to log
	            e.printStackTrace();
	 
	        }
	        catch(Exception e)
	        {
	        	e.printStackTrace();
	        }
	        
			
				try {
					//jsonArray = new JSONArray(strResponse);
					jObj=new JSONObject(strResponse);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//return jsonArray;
				return jObj;
		}
		
		protected String GetSessionId()
		{
			String strResponse=null;
			// Creating HTTP client
	        //HttpClient httpClient = createHttpClient();
				        
	        //String username="atiraj@outlook.com";
	       // String password="abcd1234LKhzbPEgd9NhixaHkXv8jpyV";
	        String username="atiraj@gmail.com";
	        String password="abcd12345H7OwcI81gI5jgdfoIJy2e3fs";
	        String grant_type="password";
	        String client_id="3MVG9A2kN3Bn17huot6JjmEy7lwZh195ZEAfMe73zOmzmBlBC0qw8TVVBbEdgljH4A9TLY.UosvkB2VuegyoJ";
	        String client_secret="4204569769270929855";

	        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(5);
	        nameValuePair.add(new BasicNameValuePair("username", username));
	        nameValuePair.add(new BasicNameValuePair("password",password ));
	        nameValuePair.add(new BasicNameValuePair("grant_type", grant_type));
	        nameValuePair.add(new BasicNameValuePair("client_id",client_id ));
	        nameValuePair.add(new BasicNameValuePair("client_secret", client_secret));
	        
	        //JSONArray jsonArray=post(nameValuePair,"/services/oauth2/token","");
	        JSONObject jObj=post(nameValuePair,"/services/oauth2/token","");
			try {
				//for (int i = 0; i < jsonArray.length(); i++) {
		          //  JSONObject jsonObject = jsonArray.getJSONObject(i);
		            strResponse=(String) jObj.get("access_token");
		        //}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        return strResponse;
		}
		
		protected String getAccounts(String token) throws UnsupportedEncodingException
		{
			String accounts="";
			List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(5);
			String url="";
			nameValuePair.add(new BasicNameValuePair("q","SELECT Name FROM Account"));
			JSONArray jsonArray=postArray(nameValuePair,"/services/data/v20.0/query",token);
			try {
				for (int i = 0; i < jsonArray.length(); i++) {
		            JSONObject jsonObject = jsonArray.getJSONObject(i);
		            accounts += " ; " + (String) jsonObject.get("Name");
		        }
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return accounts;
		}
		
		
		@Override
		protected String doInBackground(Void... params)
		{
			String strResponse=null;
			strResponse=GetSessionId();
			try {
				strResponse=getAccounts(strResponse);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        return strResponse;
		}

		protected void onPostExecute(String results) {
			if (results!=null) {
				EditText et = (EditText)findViewById(R.id.my_edit);
				et.setText(results);
			}
			Button b = (Button)findViewById(R.id.my_button);
			b.setClickable(true);
		}
    }
}