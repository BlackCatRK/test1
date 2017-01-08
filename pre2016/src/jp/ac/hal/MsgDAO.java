package jp.ac.hal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MsgDAO {
	/*
	 * 接続オブジェクトを生成して返す
	 * @return：接続オブジェクト
	 * @throws ClassNotFoundException
	 * @throws SQL Exception
	 */
	
	
	public ArrayList<Msg> list(int toid) {
		// TODO 自動生成されたメソッド・スタブ
		//データを格納する配列を生成
		System.out.println("msg読み込み");
		
		ArrayList<Msg> list = new ArrayList<Msg>();
		
		//tryの()内部で宣言したconなどは処理終了後自動的にcloseされるのでcon.closeなどは不要となる。
		try(
				Connection con = DBCon.getConnection();
				PreparedStatement ps=con.prepareStatement("select * from message where toid=? and delflg=0");
				
				){
			//検索条件の指定
			ps.setInt(1, toid);
			//SQLの実行・結果受け取り
			ResultSet rs=ps.executeQuery();
			//結果をオブジェクトに入れる
			while(rs.next()){
				//結果が存在した場合オブジェクトを生成
				Msg msg=new Msg();
				
				System.out.println("認証に成功");
				
				//オブジェクトにデータを入れる
				msg.setFromid(rs.getInt(""));
				msg.setToid(rs.getInt(""));
				msg.setProjectid(rs.getInt(""));
				msg.setTitle(rs.getString(""));
				msg.setText(rs.getString(""));
				msg.setSenddate(rs.getDate(""));
				
				list.add(msg);
			}
			System.out.println(list);
		}catch(SQLException e){
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	

	//msg開封
	public Msg read(int fromid,int toid, int projectid){
		//データを格納する配列を生成
		System.out.println("msg読み込み");
		
		Msg msg = new Msg();
		
		//tryの()内部で宣言したconなどは処理終了後自動的にcloseされるのでcon.closeなどは不要となる。
		try(
				Connection con = DBCon.getConnection();
				PreparedStatement ps=con.prepareStatement("select * from message where fromid=? and toid=? and projectid=? and deletedate>now()");
				
				){
			//検索条件の指定
			ps.setInt(1, fromid);
			ps.setInt(2, toid);
			ps.setInt(3, projectid);
			//SQLの実行・結果受け取り
			ResultSet rs=ps.executeQuery();
			//結果をオブジェクトに入れる
			if(rs.next()){
				//結果が存在した場合オブジェクトを生成
				System.out.println("認証に成功");
				
				//オブジェクトにデータを入れる
				msg.setFromid(rs.getInt(""));
				msg.setToid(rs.getInt(""));;
				msg.setProjectid(rs.getInt(""));;
				msg.setTitle(rs.getString(""));;
				msg.setText(rs.getString(""));;
				msg.setSenddate(rs.getDate(""));;
				
				
			}else{
				msg=null;
			}
			System.out.println(msg);
		}catch(SQLException e){
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return msg;
	}
	
	
	//ユーザー登録処理
	public int insert(String userid, String pw, String lname, String fname, String adrs, String tel, String hn, String mail){
		int cnt=0; //更新件数
		
		try(
				Connection con = DBCon.getConnection();
				PreparedStatement ps=con.prepareStatement("insert into user values(null,?,?,?,?,?,?,?,now(),?)");
				){
			
			ps.setString(1, userid);
			ps.setString(2, pw);
			ps.setString(3, lname+"　"+fname);
			ps.setString(4, adrs);
			ps.setString(5, tel);
			ps.setString(6, hn);
			ps.setString(7, mail);
			ps.setString(8, "9999-12-31 23:59:59");			
			
			
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

