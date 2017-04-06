package kr.ac.hansung.cse.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.ac.hansung.cse.model.Product;

@Repository
public class ProductDao {

	/* 1. JDBC Template에 DataSource를 주입 받는다. */
	private JdbcTemplate jdbcTemplateObject; // DAO객체는 JdbcTemplate객체를 활용.
	
	@Autowired
	public void setDataSource(DataSource dataSource) { // JdbcTemplate는 DataSource를 주입받은 객체.
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}
	
	/* 2. */
	public List<Product> getProducts(){ // 모든 레코드를 조회
		String sqlStatement = "select * from product";
		return jdbcTemplateObject.query(sqlStatement, new ProductMapper());
//		return jdbcTemplateObject.query(sqlStatement, new RowMapper<Product>() {
//			@Override
//			public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
//				Product product = new Product();
//				product.setId(rs.getInt("id"));
//				product.setName(rs.getString("name"));
//				product.setCategory(rs.getString("category"));
//				product.setPrice(rs.getInt("price"));
//				product.setManufacturer(rs.getString("manufacturer"));
//				product.setUnitInStock(rs.getInt("unitInStock"));
//				product.setDescription(rs.getString("description"));
//				return product;
//			}
//		});
	}

	/* 데이터베이스에 insert */ // 나중에 Hibernate로 대체
	public boolean addProduct(Product product) {
		String name = product.getName();
		String category = product.getCategory();
		int price = product.getPrice();
		String manufacturer = product.getManufacturer();
		int unitInStock = product.getUnitInStock();
		String description = product.getDescription();
		String imageFileName = product.getImageFileName(); // DB에 imageFileName만 저장
		
		String sqlStatement = "insert into product "
				+ "(name, category, price, manufacturer, unitInStock, description, imageFileName) "
				+ "values (?,?,?,?,?,?,?)";
		return jdbcTemplateObject.update(sqlStatement, new Object[] {
			name, category, price, manufacturer, unitInStock, description, imageFileName
			}) == 1; // update()메서드는 반영된 레코드 개수를 리턴
	}

	public boolean deleteProduct(int id) {
		String sqlStatement = "delete from product where id=?";
		return jdbcTemplateObject.update(sqlStatement, new Object[] {id}) == 1;
	}

	public Product getProductById(int id) {
		String sqlStatement = "select * from product where id=?";
		return jdbcTemplateObject.queryForObject(sqlStatement, new Object[] {id}, new ProductMapper()); // query는 여러 객체, queryForObject는 하나의 객체
	}

	public boolean editProduct(Product product) {
		String sqlStatement = "update product set name=?, category=?, price=?, manufacturer=?, unitInStock=?"
				+ ", description=?, imageFileName=? where id=?";
		return jdbcTemplateObject.update(sqlStatement, new Object[] {product.getName(), product.getCategory(), product.getPrice(), product.getManufacturer()
				, product.getUnitInStock(), product.getDescription(), product.getImageFileName(), product.getId() }) == 1;
	}
}
