package kr.ac.hansung.cse.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class ShippingAddress implements Serializable{

	private static final long serialVersionUID = 3273994294229576271L;

//	@Id
//	@GeneratedValue(generator="myGenerator") // id를 myGenerator에 따라 생성
//	@GenericGenerator(name="myGenerator", strategy="foreign" // foreign key 값으로 할당
//		, parameters=@Parameter(value="user", name="property"))
//	private int id;
	
	@Id
	@GeneratedValue
	private int id;
	
	private String address;
	
	private String country;
	
	private String zipCode;
	
//	@OneToOne
//	@JoinColumn(name="userId") // userId에 대한 foreign key
//	private User user; // 양방향

}
