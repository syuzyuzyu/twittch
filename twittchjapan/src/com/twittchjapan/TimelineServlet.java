package com.twittchjapan;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

import java.io.IOException;

@SuppressWarnings("serial")
public class TimelineServlet extends HttpServlet {
	public static final String consumerKey = "rjg4B5xJCLPHWGqy3IbQ";
	public static final String consumerSecret = "5ctC7P7BzWS6MkU38bDd2tfDuliX2Cx7JlxGnUFVHWc";
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();		
		
		String params = req.getParameter("word");
		String[] paramArray = params.split("_");
		Twitter twitter = (Twitter)session.getAttribute("TwitterObj");
		if(twitter == null){
			twitter = new TwitterFactory().getInstance();
		}
		//TODO:upload comment
		twitter.setOAuthConsumer(consumerKey, consumerSecret);
		String tokenStr = (String)session.getAttribute("AccessToken");//must login 
        String tokenSecretStr = (String)session.getAttribute("AccessTokenSecret");
		//TODO:it's fiction
tokenStr = "1161692041-X02RnOww9zLyoawyfYVIkuatawDZex53x82mZNK";
tokenSecretStr = "hErTV0e9UXzAsYmL5aibm1TpqpbZQRPIDFv7cCKPUpxoD";
        AccessToken at = new AccessToken(tokenStr, tokenSecretStr);
        
        twitter.setOAuthAccessToken(at);
        
        String loginUserName = "";
        try {
        	loginUserName = twitter.getScreenName();
		} catch (IllegalStateException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (TwitterException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        Map<String,String> tree = new HashMap<String,String>();
		for(String param : paramArray){
	        Query query = new Query();
			query.setQuery("#"+param);
			query.setLang("ja");//only japanese
			query.setResultType(query.RECENT);
			query.setCount(100);//max tweet
			QueryResult result;
			try {
				result = twitter.search(query);
				tree.put(param, "");
				req.setAttribute("QUERY_RESULT", result);
				req.setAttribute(param, "" + result.getTweets().size());
				req.setAttribute("USER_NAME",loginUserName);

			} catch (TwitterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		req.setAttribute("TWEET_RESULT", tree);
		
		ServletContext context = this.getServletContext();
        RequestDispatcher dispatcher
            = context.getRequestDispatcher("/timeLine.jsp");

        dispatcher.forward(req, resp);
	}
}