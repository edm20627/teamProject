package com.internousdev.gerbera.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.gerbera.dao.ProductInfoDAO;
import com.internousdev.gerbera.dto.ProductInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class ProductDetailsAction extends ActionSupport implements SessionAware{
	private int productId;
	private Map<String,Object> session;
	public String execute(){

		//一定時間操作をしない場合セッションアウトする
		if(!session.containsKey("mCategoryList")){
			return "sessionTimeOut";
			}
		String result = ERROR;
		ProductInfoDAO productInfoDAO = new ProductInfoDAO();
		ProductInfoDTO productInfoDTO = new ProductInfoDTO();
		List<ProductInfoDTO> productInfoDtoList = new ArrayList<ProductInfoDTO>();
		productInfoDTO = productInfoDAO.getProductInfo(productId);

		//前画面の情報を引き継ぐ
				session.put("id",productInfoDTO.getId());
				session.put("productId",productInfoDTO.getProductId());
				session.put("productName",productInfoDTO.getProductName());
				session.put("productNameKana",productInfoDTO.getProductNameKana());
				session.put("imageFilePath",productInfoDTO.getImageFilePath());
				session.put("imageFileName",productInfoDTO.getImageFileName());
				session.put("price",productInfoDTO.getPrice());

				session.put("releaseCompany",productInfoDTO.getReleaseCompany());
				session.put("releaseDate",productInfoDTO.getReleaseDate());
				session.put("productDescription",productInfoDTO.getProductDescription());

		//購入個数の選択数を定める
		List<Integer> productCountList = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5));
		session.put("productCountList", productCountList);

		//商品関連商品をランダムで3つ生じさせるproductInfoDtoListを定義し、iteratorメソッドを利用する
		productInfoDtoList = productInfoDAO.getProductInfoListByCategoryId(productInfoDTO.getCategoryId(),productInfoDTO.getProductId(),0,3);
		Iterator<ProductInfoDTO> iterator = productInfoDtoList.iterator();

		//商品リストが空ならnullを代入
		if(!(iterator.hasNext())){
			productCountList = null;
		}

		//問題がなければ値を返す
		if(!productInfoDtoList.isEmpty() || productCountList==null){
			session.put("productInfoDtoList", productInfoDtoList);
			result = SUCCESS;
		}

		if(productInfoDTO.getProductName()==null){
			result = ERROR;
		}
		return result;
	}

	public int getProductId(){
		return productId;
	}

	public void setProductId(int productId){
		this.productId = productId;
	}

	public Map<String,Object> getSession(){
		return session;
	}

	public void setSession(Map<String,Object>session){
		this.session = session;
	}
}