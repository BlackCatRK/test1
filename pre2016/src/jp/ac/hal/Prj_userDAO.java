package jp.ac.hal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Prj_userDAO {
	
	public Prj_user select(int id, int uid){
		
		System.out.println("ユーザーの参加を検証");
		
		//データを格納するオブジェクトを生成
		Prj_user pu=new Prj_user();
		
		//tryの()内部で宣言したconなどは処理終了後自動的にcloseされるのでcon.closeなどは不要となる。
		try(
				Connection con = DBCon.getConnection();
				PreparedStatement ps=con.prepareStatement("select * from project_user where project_id=? and user_id=? and outdate>now()");
				
				){
			//検索条件の指定
			ps.setInt(1, id);
			ps.setInt(2, uid);
			//SQLの実行・結果受け取り
			ResultSet rs=ps.executeQuery();
			//結果をオブジェクトに入れる
			if(rs.next()){
				//結果が存在した場合オブジェクトを生成
				System.out.println("参加を確認");
				//オブジェクトにデータを入れる
				pu.setProject_id(rs.getInt("project_id"));
				pu.setUser_id(rs.getInt("user_id"));
				pu.setHn(rs.getString("hn"));
			}else{
				pu=null;
			}
			System.out.println(pu);
		}catch(SQLException e){
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}		
		return pu;
	}
	
	public Prj_user select_rj(int id, int uid){
		
		System.out.println("ユーザーの参加を検証");
		
		//データを格納するオブジェクトを生成
		Prj_user pu=new Prj_user();
		
		//tryの()内部で宣言したconなどは処理終了後自動的にcloseされるのでcon.closeなどは不要となる。
		try(
				Connection con = DBCon.getConnection();
				PreparedStatement ps=con.prepareStatement("select * from project_user where project_id=? and user_id=? and outdate!=\"9999-12-31 23:59:59\"");
				
				){
			//検索条件の指定
			ps.setInt(1, id);
			ps.setInt(2, uid);
			//SQLの実行・結果受け取り
			ResultSet rs=ps.executeQuery();
			//結果をオブジェクトに入れる
			if(rs.next()){
				//結果が存在した場合オブジェクトを生成
				System.out.println("過去の参加を確認");
				//オブジェクトにデータを入れる
				pu.setProject_id(rs.getInt("project_id"));
				pu.setUser_id(rs.getInt("user_id"));
				pu.setHn(rs.getString("hn"));
			}else{
				pu=null;
			}
			System.out.println(pu);
		}catch(SQLException e){
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}		
		return pu;
	}
	
	
	public ArrayList<Prj_user> listMember(int pid){
		System.out.println("参加メンバーリストアップを開始");
		//データを格納する配列を生成
		
		ArrayList<Prj_user> list = new ArrayList<Prj_user>();
		
		//tryの()内部で宣言したconなどは処理終了後自動的にcloseされるのでcon.closeなどは不要となる。
		try(
				Connection con = DBCon.getConnection();
				PreparedStatement ps=con.prepareStatement("select project_user.user_id, project_user.hn from project_user inner join user on project_user.user_id=user.id where project_user.project_id=? and user.outdate>=now() and project_user.outdate>now();");
				
				){
			
			//?の置き換え
			ps.setInt(1, pid);
			
			//SQLの実行・結果受け取り
			ResultSet rs=ps.executeQuery();
			
			//結果をオブジェクトに入れる
			while(rs.next()){
				//結果が存在した場合オブジェクトを生成
				System.out.println("検索結果を収納");
				Prj_user p=new Prj_user();
				//オブジェクトにデータを入れる
				p.setUser_id(rs.getInt("user_id"));
				p.setHn(rs.getString("hn"));
				//オブジェクトをリストに追加
				list.add(p);
			}
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}
	
	public int join(int prjid, int userid, boolean nameflg, boolean adrsflg, String hn, String mail, int type){
		System.out.println("プロジェクト参加処理を開始");
		
		int cnt=0;
		
		System.out.println(hn);
		
		try(
				Connection con = DBCon.getConnection();
				PreparedStatement ps=con.prepareStatement("insert into project_user values("
						+ "?,?,?,?,?,?,now(),\"9999-12-31 23:59:59\",?)");
				
				){
			
			//？を置き換え
			
			ps.setInt(1, prjid);
			ps.setInt(2, userid);
			ps.setBoolean(3, nameflg);
			ps.setBoolean(4, adrsflg);
			ps.setString(5, hn);
			ps.setString(6, mail);
			ps.setInt(7, type);			
			
			
			//SQLの実行
			cnt=ps.executeUpdate(); //戻り値は更新件数
			
			System.out.println(cnt);
			
		}catch(SQLException e){
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
	
	public int rejoin(int prjid, int userid, boolean nameflg, boolean adrsflg, String hn, String mail, int type){
		
		int cnt=0;
		
		System.out.println(hn);
		
		try(
				Connection con = DBCon.getConnection();
				PreparedStatement ps=con.prepareStatement("update project_user set nameflg=?, adrsflg=?, hn=?, mail=?, indate=now(), outdate=\"9999-12-31 23:59:59\", type=? where project_id=? and user_id=?");
				
				){
			
			//？を置き換え
			
			
			ps.setBoolean(1, nameflg);
			ps.setBoolean(2, adrsflg);
			ps.setString(3, hn);
			ps.setString(4, mail);
			ps.setInt(5, type);			
			ps.setInt(6, prjid);
			ps.setInt(7, userid);
			
			//SQLの実行
			cnt=ps.executeUpdate(); //戻り値は更新件数
			
			System.out.println(cnt);
			
		}catch(SQLException e){
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
	
	public int leave(int uid, int pid){
		System.out.println("プロジェクト退会処理を開始");
		
		int cnt=0;
		
		try(
				Connection con = DBCon.getConnection();
				PreparedStatement ps=con.prepareStatement("update project_user set outdate=now() where project_id=? and user_id=?");
				
				){
			
			//？を置き換え
			
			ps.setInt(1, pid);
			ps.setInt(2, uid);			
			
			//SQLの実行
			cnt=ps.executeUpdate(); //戻り値は更新件数
			
			System.out.println(cnt);
			
		}catch(SQLException e){
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
	
	public Member member(int pid, int mid){
		
		System.out.println("メンバーを表示");
		
		//データを格納するオブジェクトを生成
		Member m=new Member();
		
		//tryの()内部で宣言したconなどは処理終了後自動的にcloseされるのでcon.closeなどは不要となる。
		try(
				Connection con = DBCon.getConnection();
				PreparedStatement ps=con.prepareStatement("select project_user.hn, project_user.mail, project_user.indate, project_user.nameflg, project_user.adrsflg, user.name from project_user inner join user on(project_user.user_id=user.id) where project_id=? and user_id=? and project_user.outdate>now()");
				
				){
			//検索条件の指定
			ps.setInt(1, pid);
			ps.setInt(2, mid);
			//SQLの実行・結果受け取り
			ResultSet rs=ps.executeQuery();
			//結果をオブジェクトに入れる
			if(rs.next()){
				//結果が存在した場合オブジェクトを生成
				System.out.println("参加を確認");
				//オブジェクトにデータを入れる
				System.out.println(rs.getBoolean("project_user.nameflg"));
				if(rs.getBoolean("project_user.nameflg")==true){
					m.setName(rs.getString("user.name"));
				}else{
					m.setName("非公開です");
				}
				if(rs.getBoolean("project_user.adrsflg")==true){
					m.setMail(rs.getString("project_user.mail"));
				}else{
					m.setMail("非公開です");
				}
				System.out.println(m.getName());
				m.setHn(rs.getString("project_user.hn"));
				m.setIndate(rs.getDate("project_user.indate"));
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}		
		return m;
	}
	
}
