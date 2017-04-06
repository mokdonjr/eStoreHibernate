package kr.ac.hansung.cse.model;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Product {
	
	private int id;
	
	/* Data Validation */
	@NotEmpty(message="product name must not be null")
	private String name;
	
	private String category;
	
	@Min(value=0, message="product price must not be less then zero")
	private int price;
	
	@NotEmpty(message="product manufacturer must not be null")
	private String manufacturer;
	
	@Min(value=0, message="product unit in stock must not be less then zero")
	private int unitInStock;
	
	private String description;
	
	/* 사진파일, 사진파일이름, 사진파일정보 포함한 객체로 
	 * Performance때문에 DB에 저장하지는 않는다. 
	 * 이미지 서버에 저장한다. (사진 이름은 DB에 저장한다) */
	private MultipartFile productImage;
	private String imageFileName;
}
