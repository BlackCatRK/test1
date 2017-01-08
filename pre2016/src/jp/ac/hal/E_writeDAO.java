package jp.ac.hal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class E_writeDAO {
	
	
	public int write(int eid, String text, int uid){
		int cnt=0; //更新件数
		try(
				Connection con = DBCon.getConnection();
				PreparedStatement ps=con.prepareStatement("insert into e_write values(?,(select last_w_num+1 from event where id=?),?,?,now())");
				){
			//？を置き換え
			
			ps.setInt(1, eid);
			ps.setInt(2, eid);
			ps.setString(3, text);
			ps.setInt(4, uid);
			
			//SQLの実行
			cnt=ps.executeUpdate(); //戻り値は更新件数
			
			System.out.println(cnt);
			
			
			
		}catch(SQLException e){
			e.printStackTrace();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		try(
				Connection con = DBCon.getConnection();
				PreparedStatement ps=con.prepareStatement("update event set last_w_num=last_w_num+1, upd_date=now() where id=?;");
				){
			//？を置き換え
			
			ps.setInt(1, eid);
			
			//SQLの実行
			cnt=cnt+ps.executeUpdate(); //戻り値は更新件数
			
			System.out.println(cnt);
			
			
			
		}catch(SQLException e){
			e.printStackTrace();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		System.out.println(cnt);
		
		return cnt;
	}
	
	public int count(int eid){
		int cnt=0;
		
		try(
				Connection con = DBCon.getConnection();
				PreparedStatement ps=con.prepareStatement("select count(*) from e_write where event_id=?");
				){
			//？を置き換え
			
			ps.setInt(1, eid);
			
			//SQLの実行
			ResultSet rs=ps.executeQuery(); //戻り値は更新件数
			
			if(rs.next()){
				System.out.println("総件数を取得");
				cnt=rs.getInt("count(*)");
			}
			
			
			
		}catch(SQLException e){
			e.printStackTrace();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		return cnt;
	}
	
	
	public ArrayList<E_write> listW(int pid, int eid, int offset, int pageper){
		System.out.println("イベントスレッド書き込み内容を取得開始");
		ArrayList<E_write> ew=new ArrayList<E_write>();
		
		try(
				Connection con = DBCon.getConnection();
				PreparedStatement ps=con.prepareStatement("select e_write.write_num, e_write.text, project_user.hn, e_write.author_id, e_write.w_date from e_write inner join project_user on (project_user.user_id=e_write.author_id) where project_user.project_id=? and e_write.event_id=? order by write_num desc limit ?,?");
				){
			//？を置き換え
			
			ps.setInt(1, pid);
			ps.setInt(2, eid);
			ps.setInt(3, offset);
			ps.setInt(4, pageper);
			
			//SQLの実行
			ResultSet rs=ps.executeQuery(); //戻り値は更新件数
			
			while(rs.next()){
				System.out.println("書き込み内容を取得");
				E_write e=new E_write();
				e.setWrite_num(rs.getInt("e_write.write_num"));
				
				String ewtxt=rs.getString("e_write.text");
				String regex = "\n";
				ewtxt = ewtxt.replace(regex, "<br />");
				e.setText(ewtxt);
				
				e.setAuthor(rs.getString("project_user.hn"));
				e.setAuthor_id(rs.getInt("e_write.author_id"));
				e.setW_date(rs.getTimestamp("w_date"));
				System.out.println(e.getW_date());
				ew.add(e);
			}
			
			
			
		}catch(SQLException e){
			e.printStackTrace();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		return ew;
		
	}
}
