package f;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

//Memoクラスでカインド（テーブル）を定義するための宣言
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class House_info {
	
	// メンバ id はエンティティ（タプル）のキーである
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Long id;
	
	//メンバ　user_id　をデータストアに書き込む
	@Persistent
	private String user_id;
	
	//メンバ　height　をデータストアに書き込む
	@Persistent
	private double height;
	
	// メンバ width をデータストアに書き込む
    @Persistent 
    private double width;
   
    // メンバ depth をデータストアに書き込む
    @Persistent 
    private double depth;
    
    // メンバ year をデータストアに書き込む
    @Persistent 
    private int year;
    
    // メンバ length をデータストアに書き込む
    @Persistent 
    private double length;
    
    // メンバ diameter をデータストアに書き込む
    @Persistent 
    private double diameter;
    
    // メンバ conection をデータストアに書き込む
    @Persistent 
    private String conection;
    
   
    
    // メンバ city をデータストアに書き込む
    @Persistent 
    private String city;
    
    @Persistent 
    private Date date;





    
    // 引数付きコンストラクタ
    public House_info(String user_id, double height, double width, double depth, int year, double length, double diameter, String conection, String city, Date date) {
      this.user_id = user_id;
      this.height = height;
      this.width = width;
      this.depth = depth;
      this.year = year;
      this.length = length;
      this.diameter = diameter;
      this.conection = conection;
      
      this.city = city;
      this.date = date;
    }






	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserId() {
		return user_id;
	}
	public void setUserId(String user_id) {
		this.user_id = user_id;
	}

	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	public double getDepth() {
		return depth;
	}
	public void setDepth(double depth) {
		this.depth = depth;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public double getLength() {
		return length;
	}
	public void setLength(double length) {
		this.length = length;
	}
	public double getDiameter() {
		return diameter;
	}
	public void setDiameter(double diameter) {
		this.diameter = diameter;
	}
	public String getConection() {
		return conection;
	}
	public void setConection(String conection) {
		this.conection = conection;
	}

	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
    // ゲッタとセッタ
      
}