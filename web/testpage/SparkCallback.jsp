<%--
  Created by IntelliJ IDEA.
  User: wuziqing
  Date: 2017-4-18
  Time: 23:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html class="no-js">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="">
    <meta name="keywords" content="">
    <meta name="viewport"
          content="width=device-width, initial-scale=1">
    <title>login test</title>

    <!-- Set render engine for 360 browser -->
    <meta name="renderer" content="webkit">

    <!-- No Baidu Siteapp-->
    <meta http-equiv="Cache-Control" content="no-siteapp"/>

    <link rel="icon" type="image/png" href="../assets/i/favicon.png">

    <!-- Add to homescreen for Chrome on Android -->
    <meta name="mobile-web-app-capable" content="yes">
    <link rel="icon" sizes="192x192" href="../assets/i/app-icon72x72@2x.png">

    <!-- Add to homescreen for Safari on iOS -->
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-title" content="Amaze UI"/>
    <link rel="apple-touch-icon-precomposed" href="../assets/i/app-icon72x72@2x.png">

    <!-- Tile icon for Win8 (144x144 + tile color) -->
    <meta name="msapplication-TileImage" content="../assets/i/app-icon72x72@2x.png">
    <meta name="msapplication-TileColor" content="#0e90d2">

    <link rel="stylesheet" href="../assets/css/amazeui.min.css?v=iir8iz5n">
    <link rel="stylesheet" href="../assets/css/amaze.min.css?v=iir8iz5n">
    <link rel="stylesheet" href="../assets/css/app.css?v=iir8iz5n">
    <link rel="canonical" href="http://amazeui.org/widgets/tabs/d2/0?_ver=2.x"/>

    <!-- 网站框架调用JS -->
    <script src="../assets/js/jquery.min.js?v=iir8iz5n"></script>
    <script src="../assets/js/handlebars.min.js?v=iir8iz5n"></script>
    <script src="../assets/js/amazeui.min.js?v=iir8iz5n"></script>
</head>
<body>
<header data-am-widget="header"
        class="am-header am-header-default">
    <div class="am-header-left am-header-nav">
        <a href="../index.html" class="">
            <i class="am-header-icon am-icon-home"></i>
        </a>
    </div>

    <h1 class="am-header-title">
        <a href="#title-link" class="">
            Login DEMO
        </a>
    </h1>

    <div class="am-header-right am-header-nav">
        <a href="#right-link" class="">
            <i class="am-header-icon am-icon-bars"></i>
        </a>
    </div>
</header>
<%
    HttpSession Session = request.getSession();
    String accessToken = (String) Session.getAttribute("accessToken");
    String openID = (String) Session.getAttribute("openID");
    String Oid_value =(String) Session.getAttribute("Oid_value");
%>

<div class="am-container" id="demo-view" data-backend-compiled="">
    <form class="am-form">
        <fieldset>
            <legend>Spark获取参数</legend>
            <div class="am-form-group">
                <label for="sparkAccessToken">AccessToken</label>
                <input type="email" class="" id="sparkAccessToken" placeholder="获取服务器Token" value="<%=accessToken%>">
            </div>
            <div class="am-form-group">
                <label for="openid">OpenID</label>
                <input type="email" class="" id="openid" placeholder="获取服务器OpenID" value="<%=openID%>">
            </div>
            <div class="am-form-group">
                <label for="extendID">用户扩展项</label>
                <input type="email" class="" id="extendID" placeholder="获取应用扩展项" value="<%=Oid_value%>">
            </div>
        </fieldset>
    </form>
</div>

<!--[if (gte IE 9)|!(IE)]><!-->
<script src="assets/js/jquery.min.js"></script>
<!--<![endif]-->
<!--[if lte IE 8 ]>
<script src="http://libs.baidu.com/jquery/1.11.3/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/modernizr/2.8.3/modernizr.js"></script>
<script src="assets/js/amazeui.ie8polyfill.min.js"></script>
<![endif]-->
<script src="assets/js/amazeui.min.js"></script>
</body>
</html>