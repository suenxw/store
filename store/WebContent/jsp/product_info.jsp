<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
			}
			
			.carousel-inner .item img {
				width: 100%;
				height: 300px;
			}
		</style>
	</head>

	<body>

		<%@include file="/jsp/head.jsp" %>


		<div class="container">
			<div class="row">
				<div style="background-color:#d3d3d3;width:930px;margin-bottom:10px;margin:0 auto;padding:10px;margin-bottom:10px;">
					<strong>商品详情</strong>
				</div>

				<div style="margin:0 auto;width:950px;">
					<div class="col-md-6">
						<img style="opacity: 1;width:400px;height:350px;" title="" class="medium" src="${pageContext.request.contextPath}/${bean.pimage}">
					</div>

					<div class="col-md-6">
						<div><strong>${bean.pname }</strong></div>
						<div style="border-bottom: 1px dotted #dddddd;width:350px;margin:10px 0 10px 0;">
							<div>编号：${bean.pid }</div>
						</div>

						<div style="margin:10px 0 10px 0;">商城价: <strong style="color:#f5676e;">￥：${bean.shop_price }元</strong> 市场价： <del>￥${bean.market_price }元</del>
						</div>

						<div style="padding:10px;border:1px solid #e7dbb1;width:330px;margin:15px 0 10px 0;;background-color: #fffee6;">
							<div style="margin:5px 0 10px 0;"></div>
							<form action="${pageContext.request.contextPath }/cart" id="form1" method="get">
								<!-- 提交的方法  -->
								<input type="hidden" name="method" value="add2cart">
								
								<!-- 商品的pid -->
								<input type="hidden" name="pid" value="${bean.pid }">
								
								<div style="border-bottom: 1px solid #faeac7;margin-top:20px;padding-left: 10px;">购买数量:
									<input id="quantity" name="count" value="1" maxlength="4" size="10" type="text"> </div>
	
								<div style="margin:20px 0 10px 0;;text-align: center;">
							</form>
								<a href="javascript:void(0)" onclick="subForm()">
									<input style="background: url('${pageContext.request.contextPath}/images/product.gif') no-repeat scroll 0 -600px rgba(0, 0, 0, 0);height:36px;width:127px;" value="加入购物车" type="button">
								</a> &nbsp;</div>
						</div>
					</div>
				</div>
				<div class="clear"></div>
				<div style="width:950px;margin:0 auto;">
					<div style="background-color:#d3d3d3;width:930px;padding:10px 10px;margin:10px 0 10px 0;">
						<strong>商品介绍</strong>
					</div>

					<%-- <div>
						<img src="${pageContext.request.contextPath}/${bean.pimage}">
					</div>
 --%>
					<div style="background-color:#d3d3d3;width:930px;padding:10px 10px;margin:10px 0 10px 0;">
						<strong>${bean.pdesc }</strong>
					</div>
					

			</div>
		</div>

		<div style="margin-top:50px;">
			
		</div>

	</body>
	<script type="text/javascript">
		function subForm(){
			//让指定的表单提交
			document.getElementById("form1").submit();
		}
	</script>
</html>