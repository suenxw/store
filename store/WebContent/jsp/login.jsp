<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>登录</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" />
		<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
<!-- 引入自定义css文件 style.css -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css"/>

<style>
  body{
   margin-top:20px;
   margin:0 auto;
 }
 .carousel-inner .item img{
	 width:100%;
	 height:300px;
 }
 .container .row div{ 
	 /* position:relative;
	 float:left; */
 }
 
font {
    color: #666;
    font-size: 22px;
    font-weight: normal;
    padding-right:17px;
}
 </style>
</head>
<body>
	

		<!--
           	描述：菜单栏
           -->
		<div class="container-fluid">
			
			<div class="col-md-3" style="padding-top:20px">
				<ol class="list-inline">
					<li><a href="${pageContext.request.contextPath }/user?method=loginUI">登录</a></li>
					<li><a href="${pageContext.request.contextPath }/user?method=registUI">注册</a></li>
				</ol>
			</div>
		</div>
		<!--
           	描述：导航条
           -->
		<div class="container-fluid">
			<nav class="navbar navbar-inverse">
				<div class="container-fluid">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
							<span class="sr-only">Toggle navigation</span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
						</button>
						<a class="navbar-brand" href="${pageContext.request.contextPath }">首页</a>
					</div>

					<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
						<ul class="nav navbar-nav" id="c_ul">
							
						</ul>

					</div>
					<!-- /.navbar-collapse -->
				</div>
				<!-- /.container-fluid -->
			</nav>
		</div>

	
	
	
	
	
	
	<div class="container"  style="width:1320px;height:540px;background:#FF2C4C url('${pageContext.request.contextPath}/images/background2.jpg')">
	
	<div class="row"> 
		<div class="col-md-7">
		</div>
		
		<div class="col-md-5">
					<div style="width:440px;border:1px solid #E7E7E7;padding:20px 0 20px 30px;border-radius:5px;margin-top:60px;background:#fff;">
					<font>会员登录</font>USER LOGIN ${msg }
	
			<div>&nbsp;</div>
		<form class="form-horizontal" action="${pageContext.request.contextPath }/user" method="post">
		<input type="hidden" name="method" value="login">
	  
	 <div class="form-group">
	    <label for="username" class="col-sm-2 control-label">用户名</label>
	    <div class="col-sm-6">
	      <input type="text" class="form-control" id="username" placeholder="请输入用户名" name="username">
	    </div>
	 </div>
	 
	 <div class="form-group">
	    <label for="inputPassword3" class="col-sm-2 control-label">密码</label>
	    <div class="col-sm-6">
	      <input type="password" class="form-control" id="inputPassword3" placeholder="请输入密码" name="password">
	    </div>
	 </div>
	 
	 <%-- <div class="form-group">
	        <label for="inputPassword3" class="col-sm-2 control-label">验证码</label>
	    <div class="col-sm-3">
	      <input type="text" class="form-control" id="inputPassword3" placeholder="请输入验证码">
	    </div>
	    <div class="col-sm-3">
	      <img src="${pageContext.request.contextPath}/image/captcha1.jhtml"/>
	    </div>
	 </div>
	  --%>
	 <div class="form-group">
	    <div class="col-sm-offset-2 col-sm-10">
	      <div class="checkbox">
	        
	      </div>
	    </div>
	 </div>
	 
	 <div class="form-group">
	    <div class="col-sm-offset-2 col-sm-10">
	    <input type="submit"  width="100" value="登录" name="submit" border="0"
	    style="background: url('${pageContext.request.contextPath}/images/login.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0);
	    height:35px;width:100px;color:white;">
	    </div>
	 </div>
	</form>
	</div>			
		</div>
	</div>
	</div>	
	
		<div style="margin-top:50px;">
		
		</div>


</body>
<script type="text/javascript">
	$(function(){
		//发送ajax 查询所有分类
		$.post("${pageContext.request.contextPath}/category",{"method":"findAll"},function(obj){
			//alert(obj);
			//遍历json列表,获取每一个分类,包装成li标签,插入到ul内部
			//$.each($(obj),function(){});
			$(obj).each(function(){
				//alert(this.cname);
				$("#c_ul").append("<li><a href='${pageContext.request.contextPath}/product?method=findByPage&pageNumber=1&cid="+this.cid+"'>"+this.cname+"</a></li>");
			});
		},"json");
	})
</script>
</html>