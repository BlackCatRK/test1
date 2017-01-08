package jp.ac.hal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BbsWriteDAO {
	
	public int write(int bid, String text, int uid){
		int cnt=0; //更新件数
		try(
				Connection con = DBCon.getConnection();
				PreparedStatement ps=con.prepareStatement("insert into bbs_write values(?,(select lastnum+1 from bbs where id=?),?,?,now())");
				){
			//？を置き換え
			
			ps.setInt(1, bid);
			ps.setInt(2, bid);
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
				PreparedStatement ps=con.prepareStatement("update bbs set lastnum=lastnum+1, upddate=now() where id=?;");
				){
			//？を置き換え
			
			ps.setInt(1, bid);
			
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
	
	public int count(int bid){
		int cnt=0;
		
		try(
				Connection con = DBCon.getConnection();
				PreparedStatement ps=con.prepareStatement("select count(*) from bbs_write where bbs_id=?");
				){
			//？を置き換え
			
			ps.setInt(1, bid);
			
			//SQLの実行
			ResultSet rs=ps.executeQuery(); //戻り値は更新件数
			
			if(rs.next()){
				System.out.println("書き込み内容を取得");
				cnt=rs.getInt("count(*)");
			}
			
			
			
		}catch(SQLException e){
			e.printStackTrace();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		return cnt;
	}
	
	public ArrayList<BbsWrite> listW(int pid, int bid, int offset, int pageper){
		System.out.println("スレッド書き込み内容を取得開始");
		ArrayList<BbsWrite> bw=new ArrayList<BbsWrite>();
		
		try(
				Connection con = DBCon.getConnection();
				PreparedStatement ps=con.prepareStatement("select bbs_write.write_num, bbs_write.text, project_user.hn, bbs_write.author_id, bbs_write.w_date from bbs_write inner join project_user on (project_user.user_id=bbs_write.author_id) where project_user.project_id=? and bbs_write.bbs_id=? order by write_num desc limit ?,?");
				){
			//？を置き換え
			
			ps.setInt(1, pid);
			ps.setInt(2, bid);
			ps.setInt(3, offset);
			ps.setInt(4, pageper);
			
			//SQLの実行
			ResultSet rs=ps.executeQuery(); //戻り値は更新件数
			
			while(rs.next()){
				System.out.println("書き込み内容を取得");
				BbsWrite b=new BbsWrite();
				b.setWrite_num(rs.getInt("bbs_write.write_num"));
				
				String btxt=rs.getString("bbs_write.text");
				String regex = "\n";
				btxt = btxt.replace(regex, "<br />");
				b.setText(btxt);
				
				b.setAuthor(rs.getString("project_user.hn"));
				b.setAuthor_id(rs.getInt("bbs_write.author_id"));
				b.setW_date(rs.getTimestamp("w_date"));
				System.out.println(b.getW_date());
				bw.add(b);
			}
			
			
			
		}catch(SQLException e){
			e.printStackTrace();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		return bw;
		
	}
}
