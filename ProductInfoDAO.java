package com.internousdev.gerbera.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.internousdev.gerbera.dto.ProductInfoDTO;
import com.internousdev.gerbera.util.DBConnector;

public class ProductInfoDAO {

	public List<ProductInfoDTO> getProductInfoList(){
		DBConnector db=new DBConnector();
		Connection con=db.getConnection();
		List<ProductInfoDTO> productInfoDtoList=new ArrayList<ProductInfoDTO>();
		//prosuct_infoからの情報をproduct_id昇格順に並べる
		String sql="select * from product_info order by product_id asc";
		try{
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				ProductInfoDTO productInfoDto=new ProductInfoDTO();
				productInfoDto.setId(rs.getInt("id"));
				productInfoDto.setProductId(rs.getInt("product_id"));
				productInfoDto.setProductName(rs.getString("product_name"));
				productInfoDto.setProductNameKana(rs.getString("product_name_kana"));
				productInfoDto.setProductDescription(rs.getString("product_description"));
				productInfoDto.setCategoryId(rs.getInt("category_id"));
				productInfoDto.setPrice(rs.getInt("price"));
				productInfoDto.setImageFilePath(rs.getString("image_file_path"));
				productInfoDto.setImageFileName(rs.getString("image_file_name"));
				productInfoDto.setReleaseDate(rs.getDate("release_date"));
				productInfoDto.setReleaseCompany(rs.getString("release_company"));
				productInfoDto.setStatus(rs.getInt("status"));
				productInfoDto.setRegistDate(rs.getDate("regist_date"));
				productInfoDto.setUpdateDate(rs.getDate("update_date"));
				productInfoDtoList.add(productInfoDto);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			try{
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return productInfoDtoList;
	}

	public ProductInfoDTO getProductInfo(int productId){
		DBConnector db=new DBConnector();
		Connection con=db.getConnection();
		ProductInfoDTO productInfoDTO=new ProductInfoDTO();
		//product_idを指定する
		String sql="select * from product_info where product_id=?";
		try{
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, productId);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				productInfoDTO.setId(rs.getInt("id"));
				productInfoDTO.setProductId(rs.getInt("product_id"));
				productInfoDTO.setProductName(rs.getString("product_name"));
				productInfoDTO.setProductNameKana(rs.getString("product_name_kana"));
				productInfoDTO.setProductDescription(rs.getString("product_description"));
				productInfoDTO.setCategoryId(rs.getInt("category_id"));
				productInfoDTO.setPrice(rs.getInt("price"));
				productInfoDTO.setImageFilePath(rs.getString("image_file_path"));
				productInfoDTO.setImageFileName(rs.getString("image_file_name"));
				productInfoDTO.setReleaseDate(rs.getDate("release_date"));
				productInfoDTO.setReleaseCompany(rs.getString("release_company"));
				productInfoDTO.setStatus(rs.getInt("status"));
				productInfoDTO.setUpdateDate(rs.getDate("regist_date"));
				productInfoDTO.setUpdateDate(rs.getDate("update_date"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			try{
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return productInfoDTO;
	}

	public List<ProductInfoDTO> getProductInfoListByCategoryId(int categoryId,int productId,int limitOffset,int limitRowCount){
		DBConnector db=new DBConnector();
		Connection con=db.getConnection();
		List<ProductInfoDTO> productInfoDtoList=new ArrayList<ProductInfoDTO>();
		//not in と <> は同じ意味
		//rand() はランダム
		//limit [開始位置] , [取得する行数]
		//詳細画面でproduct_idが異なる商品を３商品ランダムで出力に使用
		String sql="select * from product_info where category_id=? and product_id <> ? order by rand() limit ?,?";
		try{
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1,categoryId);
			ps.setInt(2, productId);
			ps.setInt(3,limitOffset);
			ps.setInt(4, limitRowCount);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				ProductInfoDTO productInfoDTO=new ProductInfoDTO();
				productInfoDTO.setId(rs.getInt("id"));
				productInfoDTO.setProductId(rs.getInt("product_id"));
				productInfoDTO.setProductName(rs.getString("product_name"));
				productInfoDTO.setProductNameKana(rs.getString("product_name_kana"));
				productInfoDTO.setProductDescription(rs.getString("product_description"));
				productInfoDTO.setCategoryId(rs.getInt("category_id"));
				productInfoDTO.setPrice(rs.getInt("price"));
				productInfoDTO.setImageFilePath(rs.getString("image_file_path"));
				productInfoDTO.setImageFileName(rs.getString("image_file_name"));
				productInfoDTO.setReleaseDate(rs.getDate("release_date"));
				productInfoDTO.setReleaseCompany(rs.getString("release_company"));
				productInfoDTO.setStatus(rs.getInt("status"));
				productInfoDTO.setUpdateDate(rs.getDate("regist_date"));
				productInfoDTO.setUpdateDate(rs.getDate("update_date"));
				productInfoDtoList.add(productInfoDTO);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			try{
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return productInfoDtoList;
	}

	public List<ProductInfoDTO> getProductInfoListAll(String[] keywordsList){
		DBConnector db=new DBConnector();
		Connection con=db.getConnection();
		List<ProductInfoDTO> productInfoDtoList=new ArrayList<ProductInfoDTO>();
		String sql="select * from product_info where";
		boolean initializeFlag=true;
		for(String keyword : keywordsList){
			if(initializeFlag){
				//検索ワードの最初を指定
				sql += " (product_name like '%" + keyword + "%' or product_name_kana like '%" + keyword + "%')";
				initializeFlag=false;
			}else{
				//検索ワードの２つ目以降を指定
				sql += " or (product_name like '%" + keyword + "%' or product_name_kana like '%" + keyword + "%')";
			}
		}
		try{
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				ProductInfoDTO productInfoDTO=new ProductInfoDTO();
				productInfoDTO.setId(rs.getInt("id"));
				productInfoDTO.setProductId(rs.getInt("product_id"));
				productInfoDTO.setProductName(rs.getString("product_name"));
				productInfoDTO.setProductNameKana(rs.getString("product_name_kana"));
				productInfoDTO.setProductDescription(rs.getString("product_description"));
				productInfoDTO.setCategoryId(rs.getInt("category_id"));
				productInfoDTO.setPrice(rs.getInt("price"));
				productInfoDTO.setImageFilePath(rs.getString("image_file_path"));
				productInfoDTO.setImageFileName(rs.getString("image_file_name"));
				productInfoDTO.setReleaseDate(rs.getDate("release_date"));
				productInfoDTO.setReleaseCompany(rs.getString("release_company"));
				productInfoDTO.setStatus(rs.getInt("status"));
				productInfoDTO.setUpdateDate(rs.getDate("regist_date"));
				productInfoDTO.setUpdateDate(rs.getDate("update_date"));
				productInfoDtoList.add(productInfoDTO);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			try{
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return productInfoDtoList;
	}

	public List<ProductInfoDTO> getProductInfoListByKeywords(String[] keywordsList,String categoryId){
		DBConnector db=new DBConnector();
		Connection con=db.getConnection();
		List<ProductInfoDTO> productInfoDtoList=new ArrayList<ProductInfoDTO>();
		String sql="select * from product_info where";
		boolean initializeFlag=true;
		for(String keyword : keywordsList){
			if(initializeFlag){
				//カテゴリーIDの指定と検索ワードの最初を指定
				sql += " category_id=" + categoryId + " and ((product_name like '%" + keyword + "%' or product_name_kana like '%" + keyword + "%')";
				initializeFlag=false;
			}else{
				//検索ワードの２つ目以降を指定
				sql += " or (product_name like '%" + keyword + "%' or product_name_kana like '%" + keyword + "%')";
			}
		}
		sql += ")";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				ProductInfoDTO productInfoDTO=new ProductInfoDTO();
				productInfoDTO.setId(rs.getInt("id"));
				productInfoDTO.setProductId(rs.getInt("product_id"));
				productInfoDTO.setProductName(rs.getString("product_name"));
				productInfoDTO.setProductNameKana(rs.getString("product_name_kana"));
				productInfoDTO.setProductDescription(rs.getString("product_description"));
				productInfoDTO.setCategoryId(rs.getInt("category_id"));
				productInfoDTO.setPrice(rs.getInt("price"));
				productInfoDTO.setImageFilePath(rs.getString("image_file_path"));
				productInfoDTO.setImageFileName(rs.getString("image_file_name"));
				productInfoDTO.setReleaseDate(rs.getDate("release_date"));
				productInfoDTO.setReleaseCompany(rs.getString("release_company"));
				productInfoDTO.setStatus(rs.getInt("status"));
				productInfoDTO.setUpdateDate(rs.getDate("regist_date"));
				productInfoDTO.setUpdateDate(rs.getDate("update_date"));
				productInfoDtoList.add(productInfoDTO);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			try{
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return productInfoDtoList;
	}

}
