package kr.ac.hansung.cse.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class CartItem implements Serializable {

	private static final long serialVersionUID = -213127954507414254L;
	
	@Id
	@GeneratedValue
	private int cartItemId;
	
	@ManyToOne
	@JoinColumn(name="cartId") // column 이름 지정
	@JsonIgnore // 양방향이기 때문에 Cart와 CartItem에 사이클 발생하는 현상을 방지
	private Cart cart; // 양방향 일대다
	// Cart에서 CartItem 참조시 cartItemId, product, quantity, totalPrice만 참조
	
	@ManyToOne
	@JoinColumn(name="productId")
	private Product product;
	
	private int quantity;
	
	private double totalPrice;

}
