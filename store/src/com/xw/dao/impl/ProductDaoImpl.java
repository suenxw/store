package com.xw.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.xw.constant.Constant;
import com.xw.dao.ProductDao;
import com.xw.domain.PageBean;
import com.xw.domain.Product;
import com.xw.utils.DataSourceUtils;

public class ProductDaoImpl implements ProductDao {

	@Override
	/**
	 * 查询热门
	 */
	public List<Product> findHot() throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product where is_hot = ? and pflag = ? order by pdate desc limit 12";
		return qr.query(sql, new BeanListHandler<>(Product.class), Constant.PRODUCT_IS_HOT,Constant.PRODUCT_IS_UP);
	}

	@Override
	/**
	 * 查询最新
	 */
	public List<Product> findNew() throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product where pflag = ? order by pdate desc limit 12";
		return qr.query(sql, new BeanListHandler<>(Product.class),Constant.PRODUCT_IS_UP);
	}

	@Override
	/**
	 *查询单个商品
	 */
	public Product getById(String pid) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product where pid = ? limit 1";
		return qr.query(sql, new BeanHandler<>(Product.class), pid);
		
	}

	@Override
	/**
	 * 查询当前页数据
	 */
	public List<Product> findByPage(PageBean<Product> pb, String cid) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product where cid = ? and pflag = ? order by pdate desc limit ?,?";
		return qr.query(sql, new BeanListHandler<>(Product.class), cid,Constant.PRODUCT_IS_UP,pb.getStartIndex(),pb.getPageSize());
	}

	@Override
	/**
	 * 获取总记录数
	 */
	public int getTotalRecord(String cid) throws Exception {
		return ((Long)new QueryRunner(DataSourceUtils.getDataSource()).query("select count(*) from product where cid = ? and pflag = ?", new ScalarHandler(), cid,Constant.PRODUCT_IS_UP)).intValue();
	}

	@Override
	/**
	 * 后台展示上架商品
	 */
	public List<Product> findAll() throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product where pflag = ? order by pdate desc";
		return qr.query(sql, new BeanListHandler<>(Product.class), Constant.PRODUCT_IS_UP);
	}

	@Override
	/**
	 * 保存商品
	 */
	public void save(Product p) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		/*
		 * `pid` varchar(32) NOT NULL,
			  `pname` varchar(50) DEFAULT NULL,
			  `market_price` double DEFAULT NULL,
			  
			  `shop_price` double DEFAULT NULL,
			  `pimage` varchar(200) DEFAULT NULL,
			  `pdate` date DEFAULT NULL,
			  
			  `is_hot` int(11) DEFAULT NULL,
			  `pdesc` varchar(255) DEFAULT NULL,
			  `pflag` int(11) DEFAULT NULL,
			  
			  `cid` varchar(32) DEFAULT NULL,
		 */
		String sql="insert into product values(?,?,?,?,?,?,?,?,?,?);";
		qr.update(sql, p.getPid(),p.getPname(),p.getMarket_price(),
				p.getShop_price(),p.getPimage(),p.getPdate(),
				p.getIs_hot(),p.getPflag(),
				p.getCategory().getCid(),p.getPdesc());
	}

	@Override
	/**
	 * 删除商品
	 */
	public void delete(Product p) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="delete from product where pid = ?;";
		qr.update(sql,p.getPid());
	}

}
