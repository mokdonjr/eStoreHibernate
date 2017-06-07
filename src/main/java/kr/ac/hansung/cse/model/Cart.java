package kr.ac.hansung.cse.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Cart implements Serializable{ // Cart�� JSON form���� ����ȭ
	// ������ ���� ��Ʈ��ũ, db�� �����Ϸ��� Serialize�� �ؾ��Ѵ�. 
	private static final long serialVersionUID = 1413783241344225820L;

	@Id
	@GeneratedValue
	private int cartId;
	
	@OneToMany(mappedBy="cart", cascade=CascadeType.ALL, fetch=FetchType.EAGER)// fetch default LAZY
	private List<CartItem> cartItems = new ArrayList<CartItem>(); // ���̺� �÷�x
	
	private double grandTotal;
}
