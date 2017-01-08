package jp.ac.hal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserDAO {
	
	public User select(String userid, String pw){
		//データを格納する配列を生成
		System.out.println("ログイン処理を開始します");
		
		User list = new User();
		
		//tryの()内部で宣言したconなどは処理終了後自動的にcloseされるのでcon.closeなどは不要となる。
		try(
				Connection con = DBCon.getConnection();
				PreparedStatement ps=con.prepareStatement("select id, userid, name, hn, mail from user where userid=? and pw=? and outdate>now()");
				
				){
			//検索条件の指定
			ps.setString(1, userid);
			ps.setString(2, pw);
			//SQLの実行・結果受け取り
			ResultSet rs=ps.executeQuery();
			//結果をオブジェクトに入れる
			if(rs.next()){
				//結果が存在した場合オブジェクトを生成
				System.out.println("認証に成功");
				
				//オブジェクトにデータを入れる
				list.setId(rs.getInt("id"));
				list.setUserid(rs.getString("userid"));
				list.setName(rs.getString("name"));
				list.setHn(rs.getString("hn"));
				list.setMail(rs.getString("mail"));
			}else{
				list=null;
			}
			System.out.println(list);
		}catch(SQLException e){
			e.printStackTrace();
			list=null;
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
			list=null;
		}
		return list;
	}
	
	public int checkId(String userid){
		int flg=0;
		System.out.println("ID重複チェック処理を開始します");
		
		try(
				Connection con = DBCon.getConnection();
				PreparedStatement ps=con.prepareStatement("select userid from user where userid like ?");
				){
			ps.setString(1, userid);
			//SQLの実行・結果受け取り
			ResultSet rs=ps.executeQuery();
			//結果をオブジェクトに入れる
			if(rs.next()){
				System.out.println("重複を検知しました");
				flg=1;
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		return flg;
		
	}
	
	//ユーザー登録処理
	public int insert(String userid, String pw, String lname, String fname, String hn, String mail){
		int cnt=0; //更新件数
		
		try(
				Connection con = DBCon.getConnection();
				PreparedStatement ps=con.prepareStatement("insert into user values(null,?,?,?,?,?,now(),?)");
				){
			
			ps.setString(1, userid);
			ps.setString(2, pw);
			ps.setString(3, lname+"　"+fname);
			ps.setString(4, hn);
			ps.setString(5, mail);
			ps.setString(6, "9999-12-31 23:59:59");			
			
			
			//SQLの実行
			cnt=ps.executeUpdate(); //戻り値は更新件数
			
			System.out.println(cnt);
			
			
			
		}catch(SQLException e){
			e.printStackTrace();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		return cnt;
	}
	
	public int delete(String userid, String pw){
		System.out.println("退会処理を開始します");
		int cnt=0; //更新件数
		
		try(
				Connection con = DBCon.getConnection();
				PreparedStatement ps=con.prepareStatement("update user set outdate=now() where userid=? and pw=?");
				){
			
			ps.setString(1, userid);
			ps.setString(2, pw);		
			
			
			//SQLの実行
			cnt=ps.executeUpdate(); //戻り値は更新件数
			
			System.out.println(cnt);
			
			
			
		}catch(SQLException e){
			e.printStackTrace();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		return cnt;
	}
	
}
