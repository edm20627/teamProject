<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="./css/style.css">
	<link rel="stylesheet" href="./css/productDetail.css">
	<title>商品詳細</title>
</head>
<body>
	<s:include value="header.jsp" />
	<div id="contents">
		<h1>商品詳細画面</h1>
		<div class="box">
			<div class="2column-container">
				<div class="left">
					<img
						src='<s:property value="%{#session.imageFilePath}"/>/<s:property value="%{#session.imageFileName}"/>'
						class="item-image-box-320" /><br>
				</div>
				<div class="right">
					<s:form action="AddCartAction">
						<table class="vertical-list-table-mini">
							<tr>
								<th scope="row"><s:label value="商品名" /></th>
								<td><s:property value="%{#session.productName}" /></td>
							</tr>
							<tr>
								<th scope="row"><s:label value="商品名ふりがな" /></th>
								<td><s:property value="%{#session.productNameKana}" /></td>
							</tr>
							<tr>
								<th scope="row"><s:label value="値段" /></th>
								<td><s:property value="%{#session.price}" />円</td>
							</tr>
							<tr>
								<th scope="row"><s:label value="購入個数" /></th>
								<td><s:select name="productCount"
										list="%{#session.productCountList}" />個</td>
							</tr>
							<tr>
								<th scope="row"><s:label value="発売会社名" /></th>
								<td><s:property value="%{#session.releaseCompany}" /></td>
							</tr>
							<tr>
								<th scope="row"><s:label value="発売年月日" /></th>
								<td><s:property value="%{#session.releaseDate}" /></td>
							</tr>
							<tr>
								<th scope="row"><s:label value="商品詳細情報" /></th>
								<td><s:property value="%{#session.productDescription}" /></td>
							</tr>
						</table>
						<div class="submit_btn_box">
							<s:submit value="カートに追加" class="submit_btn" />
						</div>
					</s:form>
				</div>
			</div>
		</div>

		<div class="product-details-recommend-box">
			<s:iterator value="#session.productInfoDtoList">
				<div class="recommend-box">
					<a class="recommend-box-link" href='<s:url action="ProductDetailsAction">
						<s:param name="productId" value="%{productId}"/>
						</s:url>'>
						<img src='<s:property value="imageFilePath"/>/<s:property value="imageFileName"/>'
						class="item-image-box-100" />
					</a><br>
					<s:property value="productName" />
					<br>
				</div>
			</s:iterator>
		</div>
	</div>
	<s:include value="footer.jsp" />
</body>
</html>