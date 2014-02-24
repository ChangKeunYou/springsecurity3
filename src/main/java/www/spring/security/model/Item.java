package www.spring.security.model;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import static javax.persistence.GenerationType.IDENTITY;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Blob;
import java.sql.Date;





import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@NamedQueries({ @NamedQuery(name = "findItemByID", query = "select * from ITEM " )})
@Entity
@Table(name="ITEM" , catalog="tutorials")
public class Item implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ITEM_ID")
	private Integer itemId;
	
	@NotEmpty
	@Column(name="ITEM_NAME")
	private String itemName;

	@NotNull
	@Min(0)
	@Column(name="PRICE")
	private Integer price;

	@NotEmpty
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name = "PICTURE")
	@Lob
	private Blob picture;
	/*
	private MultipartFile picture;
	
	public MultipartFile getPicture() {
		return picture;
	}

	public void setPicture(MultipartFile picture) {
		this.picture = picture;
	}
*/
	
	

	public Blob getPicture() {
		return picture;
	}

	public void setPicture(Blob picture) {
		this.picture = picture;
	}

	
	public String getDescription() {
		return this.description;
	}

	
	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getItemId() {
		return this.itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Integer getPrice() {
		return this.price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

}
