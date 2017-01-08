package jp.ac.hal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Event_memberDAO {
	
	public int join(int eid, int uid){
		System.out.println("イベント所属処理を開始");
		int cnt=0; //更新件数
		try(
				Connection con = DBCon.getConnection();
				PreparedStatement ps=con.prepareStatement("insert into event_member values(?,?,now(),\"9999-12-31 23:59:59\")");
				){
			//？を置き換え
			
			ps.setInt(1, eid);
			ps.setInt(2, uid);
			
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
	
	public ArrayList<Project_user> listM(int eid, int pid){
		System.out.println("イベント参加者リストアップを開始");
		//データを格納する配列を生成
		
		ArrayList<Project_user> list = new ArrayList<Project_user>();
		
		//tryの()内部で宣言したconなどは処理終了後自動的にcloseされるのでcon.closeなどは不要となる。
		try(
				Connection con = DBCon.getConnection();
				PreparedStatement ps=con.prepareStatement("select project_user.hn, project_user.user_id from project_user inner join event_member on (event_member.member_id=project_user.user_id) where event_member.event_id=? and project_id=?");
				
				){
			
			
			ps.setInt(1, eid);
			ps.setInt(2, pid);
			//SQLの実行・結果受け取り
			ResultSet rs=ps.executeQuery();
			
			//結果をオブジェクトに入れる
			while(rs.next()){
				//結果が存在した場合オブジェクトを生成
				System.out.println("参加者を収納");
				Project_user p=new Project_user();
				//オブジェクトにデータを入れる
				p.setUser_id(rs.getInt("user_id"));
				p.setHn(rs.getString("hn"));
				//オブジェクトをリストに追加
				list.add(p);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("処理完了");
		return list;		
	}
	
	public boolean check(int uid,int eid){
		boolean flg=false;
		
		try(
				Connection con = DBCon.getConnection();
				PreparedStatement ps=con.prepareStatement("select * from event_member where member_id=? and event_id=?");
				){
			//？を置き換え
			
			ps.setInt(1, uid);
			ps.setInt(2, eid);
			
			//SQLの実行
			ResultSet rs=ps.executeQuery(); //戻り値は更新件数
			
			if(rs.next()){
				flg=true;
			}
			
			
			
		}catch(SQLException e){
			e.printStackTrace();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		return flg;
	}
}
