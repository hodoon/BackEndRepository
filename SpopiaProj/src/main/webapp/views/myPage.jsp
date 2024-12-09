<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<!DOCTYPE html>

<%-- <a href="${pageContext.request.contextPath}/auth/logout">Logout</a> --%>

<html lang="ko">
<head>
	<title>My Page</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- CDN 코드  -->
    <script src="https://kit.fontawesome.com/def66b134a.js" crossorigin="anonymous"></script>
    <title>메뉴 UI</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f5f5f5;
        }

        .menu-container {
            width: 300px;
            margin: 20px auto;
            background: #fff;
            border: 1px solid #ddd;
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0);
            overflow: hidden;
        }

        .menu-item {
            display: flex;
            align-items: center;
            margin: auto;
            border-bottom: 1px solid #eee;
            cursor: pointer;
        }

        .menu-item:hover {
            background-color: #f5f5f5;
        }

        .menu-item img {
            width: 24px;
            height: 24px;
            margin-right: 10px;
        }

        .menu-item p {
            flex: 1;
            font-size: 16px;
            color: #333;
            word-break: keep-all;
        }

        .menu-item highlight {
            color: #28a745;
        }
        
        li {
        	list-style-type: none;
        }
        
        a {
			text-decoration: none;
        }
        
        .section_title {
 			padding: 20px;
 			font-weight: 700;
 			font-size: 14px;
  			line-height: 20px;
		}
        
    </style>
</head>
<body>
    <div class="menu-container">
    <div class="section_title">나의 플랩</div>
	    <ul>
	    	<li>
			    <a href="#">
			        <div class="menu-item">
			            <img src="https://d31wz4d3hgve8q.cloudfront.net/static/img/ic_myplab_color.svg" alt="신청 내역">
			            <p>신청 내역</p>
			        </div>
		        </a>
	        </li>
	        
	        <li>
			    <a href="#">
			        <div class="menu-item">
			            <img src="https://d31wz4d3hgve8q.cloudfront.net/static/img/ic_friend.svg" alt="친구">
			            <p>친구</p>
			        </div>
		        </a>
	        </li>
	        
	        <li>
			    <a href="#">
			        <div class="menu-item">
			            <img src="https://d31wz4d3hgve8q.cloudfront.net/static/img/ic_friend_match.svg" alt="채팅">
			            <p>채팅</p>
			        </div>
		        </a>
	        </li>
	        
	        <li>
			    <a href="#">
			        <div class="menu-item">
			            <img src="https://d31wz4d3hgve8q.cloudfront.net/static/img/ic_setting_color.svg" alt="설정">
			            <p>설정</p>
			        </div>
		        </a>
	        </li>  
		</ul>     
	</div>
	
	<div class="menu-container">
	<div class="section_title">고객센터</div>
		<ul>
	        <li>
			    <a href="#">
			        <div class="menu-item">
			            <img src="https://d31wz4d3hgve8q.cloudfront.net/static/img/ic_faq_color.svg" alt="QNA">
			            <p>QNA</p>
			        </div>
		        </a>
	        </li>
	        
	        <li>
			    <a href="#">
			        <div class="menu-item">
			            <img src="https://d31wz4d3hgve8q.cloudfront.net/static/img/ic_notice_color.svg" alt="공지사항">
			            <p>공지사항</p>
			        </div>
		        </a>
	        </li>
	    </ul>
    </div>
</body>
</html>