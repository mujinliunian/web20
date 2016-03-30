package com.kaishengit.dao;

import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.kaishengit.entity.Product;
import com.kaishengit.util.DBHelp;

public class ProductDao {

	
	public List<Product> findAll() {
		String sql = "select * from t_product";
		return DBHelp.query(sql, new BeanListHandler<Product>(Product.class));
	}
	
	public void save(Product product) {
		String sql = "insert into t_product(prodname,prodprice,prodnum,prodaddress) values(?,?,?,?)";
		DBHelp.update(sql, product.getProdname(),product.getProdprice(),product.getProdnum(),product.getProdaddress());
	}
	
	public void del(Integer id) {
		String sql = "delete from t_product where id = ?";
		DBHelp.update(sql, id);
	}
	
	public Product findById(Integer id) {
		String sql = "select * from t_product where id = ?";
		return DBHelp.query(sql, new BeanHandler<Product>(Product.class), id);
	}
	
	public void update(Product product) {
		String sql = "UPDATE t_product SET prodname = ?,prodprice=?,prodnum=?,prodaddress=? WHERE id = ?";
		DBHelp.update(sql, product.getProdname(),product.getProdprice(),product.getProdnum(),product.getProdaddress(),product.getId());
	}
}
