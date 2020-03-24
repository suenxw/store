<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>菜单</title>
<link href="${pageContext.request.contextPath}/css/left.css" rel="stylesheet" type="text/css"/>
<link rel="StyleSheet" href="${pageContext.request.contextPath}/css/dtree.css" type="text/css" />
</head>
<body>
<table width="100" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="12"></td>
  </tr>
</table>
<table width="100%" border="0">
  <tr>
    <td>
<div class="dtree">

	<script type="text/javascript" src="${pageContext.request.contextPath}/js/dtree1.js"></script>
	<script type="text/javascript">
	
		d = new dTree('d');
		d.add('01',-1,'系统菜单');
		d.add('0102','01','分类管理','','','mainFrame');
		d.add('010201','0102','分类列表','${pageContext.request.contextPath}/adminCategory?method=findAll','','mainFrame');
		d.add('010202','0102','添加分类','${pageContext.request.contextPath}/adminCategory?method=addUI','','mainFrame');
		d.add('0104','01','商品管理');
		d.add('010401','0104','已上架商品列表','${pageContext.request.contextPath}/adminProduct?method=findAll','','mainFrame');
		d.add('010402','0104','添加商品','${pageContext.request.contextPath}/adminProduct?method=addUI','','mainFrame');
		/* d.add('0105','01','订单管理');
		d.add('010501','0105','订单管理','${pageContext.request.contextPath}/adminOrder_findAll.action?page=1','','mainFrame');
		 */
		document.write(d);
		
	</script>
</div>	</td>
  </tr>
</table>
</body>
</html>
