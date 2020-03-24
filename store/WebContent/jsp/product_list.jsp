<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<html>

	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>商品</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" />
		<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
		<!-- 引入自定义css文件 style.css -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css" />

		<style>
			body {
				margin-top: 20px;
				margin: 0 auto;
				width: 100%;
			}
			.carousel-inner .item img {
				width: 100%;
				height: 300px;
			}
		</style>
	</head>

	<body>
		
			<%@include file="/jsp/head.jsp" %>


		<div class="row" style="width:1210px;margin:0 auto;">
			<div class="col-md-12">
				<ol class="breadcrumb">
					<a >商品列表</a>
				</ol>
			</div>
			<c:forEach items="${pb.data }" var="p">
				<div class="col-md-2">
					<a href="${pageContext.request.contextPath }/product?method=getById&pid=${p.pid}">
						<img src="${pageContext.request.contextPath}/${p.pimage}" width="170" height="170" style="display: inline-block;">
					</a>
					<p>
						<a href="${pageContext.request.contextPath }/product?method=getById&pid=${p.pid}" style='color:2f2e2c'>${fn:substring(p.pname,0,10) }..</a>
					</p>
					
					<p>
						<font color="#FF5001">商城价：&yen;${p.shop_price }</font>
					</p>
				</div>
			</c:forEach>

		</div>

		<!--分页 -->
		<div style="width:380px;margin:0 auto;margin-top:50px;">
			<ul class="pagination" style="text-align:center; margin-top:10px;">
				
				<!-- 判断是否是第一页 -->
				<!--是第一页  -->
				<c:if test="${pb.pageNumber == 1 }">
					<li class="disabled">
						<a href="javascript:void(0)" aria-label="Previous">
							<span aria-hidden="true">&laquo;</span>
						</a>
					</li>
				</c:if>
				
				<!-- 不是第一页 -->
				<c:if test="${pb.pageNumber != 1 }">
					<li>
						<a href="${pageContext.request.contextPath }/product?method=findByPage&pageNumber=${pb.pageNumber-1}&cid=${param.cid}" aria-label="Previous">
							<span aria-hidden="true">&laquo;</span>
						</a>
					</li>
				</c:if>
				
				<!-- 展示所有页码 -->
				<c:forEach begin="1" end="${pb.totalPage }" var = "n">
					<!-- 判断是否是当前页 -->
					<c:if test="${pb.pageNumber == n }">
						<li class="active"><a href="javascript:void(0)">${n }</a></li>
					</c:if>
					<c:if test="${pb.pageNumber != n }">
						<li><a href="${pageContext.request.contextPath }/product?method=findByPage&cid=${param.cid}&pageNumber=${n}">${n }</a></li>
					</c:if>
				</c:forEach>
				
				<!-- 判断是否是最后一页 -->
				<c:if test="${pb.pageNumber == pb.totalPage }">
					<li  class="disabled">
						<a href="javascript:void(0)" aria-label="Next">
							<span aria-hidden="true">&raquo;</span>
						</a>
					</li>
				</c:if>
				<c:if test="${pb.pageNumber != pb.totalPage }">
					<li>
						<a href="${pageContext.request.contextPath }/product?method=findByPage&cid=${param.cid}&pageNumber=${pb.pageNumber+1}" aria-label="Next">
							<span aria-hidden="true">&raquo;</span>
						</a>
					</li>
				</c:if>
				
			</ul>
		</div>
		<!-- 分页结束=======================        -->

</html>