<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.io.*,twitter4j.*" %>
<%
request.setCharacterEncoding("Shift_JIS");
String params = request.getParameter("word");
String[] paramArray = params.split("_");

%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>PAGE</title>
        <link rel=stylesheet type="text/css" href="base.css" />  
    </head>
    <body>
    	<header>twittch</header>
    	<section>
    		<h2>#hash</h2>
    		<ul style="list-style-type: none">
    		   <%
    		    String userName = (String)request.getAttribute("USER_NAME");
    		    for(String param : paramArray){
    		    	String values = (String)request.getAttribute(param);
    		    %>
	    			<li style="float:left;width:50%;"><img/><p><%=param%></p><a href="https://twitter.com/intent/tweet?button_hashtag=<%=param%>" class="twitter-hashtag-button" data-related="<%=userName%>">
	    			<%=param%></a></p><span><%=values%></span></li>
    			<%
    		    }
    			%>
    		</ul>
    		<div>
    			<button />
    			<textarea></textarea>
    		</div>
    	</section>
    	<article>
    	<h2>Live tweet</h2>
    	<p></p>
    	</article>
    <footer>このページのtweet数</footer>
    <%
    userName = (String)request.getAttribute("USER_NAME");
    for(String param : paramArray){
    	String value = (String)request.getAttribute(param);
    %>
    <%=param%>[<%=value%>]<%=userName%>
    <a href="https://twitter.com/intent/tweet?button_hashtag=<%=param%>" class="twitter-hashtag-button" data-related="<%=userName%>">
    #<%=param%></a>

    <section>
    <a class="twitter-timeline" href="https://twitter.com/search?q=%23<%=param%>" data-widget-id="434512940985618432">
    #<%=param%> に関するツイート</a>
	<script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0],p=/^http:/.test(d.location)?'http':'https';if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src=p+"://platform.twitter.com/widgets.js";fjs.parentNode.insertBefore(js,fjs);}}(document,"script","twitter-wjs");</script>
    </section>
    <%
    }
    %>

        <section>
        <a class="twitter-timeline" href="https://twitter.com/search?q=%23nowplaying" data-widget-id="434512940985618432">
        #nowplaying に関するツイート</a>
		<script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0],p=/^http:/.test(d.location)?'http':'https';if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src=p+"://platform.twitter.com/widgets.js";fjs.parentNode.insertBefore(js,fjs);}}(document,"script","twitter-wjs");</script>
        </section>
 
        </body>
</html>