package f;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

//Memoクラスでカインド（テーブル）を定義するための宣言
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class User_info {
	
	// メンバ id はエンティティ（タプル）のキーである
	@PrimaryKey
	private String id;
	
	// メンバ name をデータストアに書き込む
    @Persistent 
    private String name;
    
    // メンバ pass をデータストアに書き込む
    @Persistent 
    private String pass;

    // 引数付きコンストラクタ
    public User_info(String id, String pass, String name) {
        this.id = id;
        this.pass = pass;
        this.name = name;
    }

    // ゲッタとセッタ
    public String getId() {
        return id;
    }
    public String getPass() {
        return pass;
    }
    public String getName() {
        return name;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }
    public String setName(String name) {
        return name;
    }
}