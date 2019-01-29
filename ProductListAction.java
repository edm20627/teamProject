package com.internousdev.gerbera.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.gerbera.dao.ProductInfoDAO;
import com.internousdev.gerbera.dto.ProductInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class ProductListAction extends ActionSupport implements SessionAware{


	private Map<String,Object> session;

	public String execute(){

		if(!session.containsKey("mCategoryList")) {
			return "sessionTimeOut";
		}

		ProductInfoDAO productInfoDao = new ProductInfoDAO();
		List<ProductInfoDTO> productInfoDtoList=new ArrayList<ProductInfoDTO>();
		productInfoDtoList = productInfoDao.getProductInfoList();
		session.put("productInfoDtoList", productInfoDtoList);

		String result=SUCCESS;
		return result;
	}


	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
