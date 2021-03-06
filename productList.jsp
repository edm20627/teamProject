<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="./css/productList.css">
	<link rel="stylesheet" href="./css/style.css">
	<title>商品一覧</title>
</head>
<body>
	<s:include value="header.jsp"/>
	<div id="contents">
		<h1>商品一覧画面</h1>
		<s:if test="!keywordsErrorMessageList.isEmpty()">
			<div class="error">
				<div class="error-message">
					<!-- propertyは属性の指定なしの場合はListの中を順番に表示する  -->
					<s:iterator value="keywordsErrorMessageList"><s:property/><br></s:iterator>
				</div>
			</div>
		</s:if>
		<s:elseif test="#session.productInfoDtoList==null">
			<div class="info">
				検索結果がありません。
			</div>
		</s:elseif>
		<s:else>
			<div id="product-list">
				<s:iterator value="#session.productInfoDtoList">
					<div class="product-list-box">
						<table>
							<tr>
								<td id="item-image-box-list"><a href='<s:url action="ProductDetailsAction"><s:param name="productId" value="%{productId}"/></s:url>'>
								<img src='<s:property value="imageFilePath"/>/<s:property value="imageFileName"/>' class="item-image-box-list"/></a></td>
							</tr>
							<tr>
								<td id="product">
									<s:property value="productName"/><br>
									（<s:property value="productNameKana"/>）<br>
									 <s:property value="price"/>円
								</td>
							</tr>
						</table>
					</div>
				</s:iterator>
			</div>
		</s:else>
	</div>
<s:include value="footer.jsp"/>
</body>
</html>