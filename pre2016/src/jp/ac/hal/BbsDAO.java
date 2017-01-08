package jp.ac.hal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class BbsDAO {
	/*
	 * 接続オブジェクトを生成して返す
	 * @return：接続オブジェクト
	 * @throws ClassNotFoundException
	 * @throws SQL Exception
	 */
	
	//掲示板生成処理
	public int create(int pid, String title, String desc){
		int cnt=0; //更新件数
		
		try(
				Connection con = DBCon.getConnection();
				PreparedStatement ps=con.prepareStatement("insert into bbs values(null,?,?,now(),\"9999-12-31 23:59:59\",0,now(),?)");
				){
			//？を置き換え
			
			ps.setInt(1, pid);
			ps.setString(2, title);
			ps.setString(3, desc);	
			
			
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
	
	//掲示板リストアップ
	public ArrayList<Bbs> listH(int pid){
		System.out.println("プロジェクト内部掲示板リストアップを開始");
		//データを格納する配列を生成
		
		ArrayList<Bbs> list = new ArrayList<Bbs>();
		
		//tryの()内部で宣言したconなどは処理終了後自動的にcloseされるのでcon.closeなどは不要となる。
		try(
				Connection con = DBCon.getConnection();
				PreparedStatement ps=con.prepareStatement("select * from bbs where closedate>now() and project_id=? order by upddate desc");
				
				){
			
			
			ps.setInt(1, pid);
			//SQLの実行・結果受け取り
			ResultSet rs=ps.executeQuery();
			
			//結果をオブジェクトに入れる
			while(rs.next()){
				//結果が存在した場合オブジェクトを生成
				System.out.println("検索結果を収納");
				Bbs p=new Bbs();
				//オブジェクトにデータを入れる
				p.setId(rs.getInt("id"));
				p.setTitle(rs.getString("title"));
				// 現在日時をTimestamp型で取得(DBから取得するデータのダミーです)
				Timestamp datetime = rs.getTimestamp("bbs.upddate");
				// ここが本題のDatetimeからStringへの変換！
				String dateTimeStr = new SimpleDateFormat("yyyy/MM/dd HH:mm").format(datetime);
				
				p.setUpddate(dateTimeStr);
				//オブジェクトをリストに追加
				list.add(p);
			}
		}catch(SQLException e){
			
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public Bbs select(int pid, int bid){
		System.out.println("BBS抽出を開始");
		//データを格納する配列を生成
		
		Bbs bbs = new Bbs();
		System.out.println("pid="+pid);
		System.out.println("bid="+bid);
		
		//tryの()内部で宣言したconなどは処理終了後自動的にcloseされるのでcon.closeなどは不要となる。
		try(
				Connection con = DBCon.getConnection();
				PreparedStatement ps=con.prepareStatement("select * from bbs where closedate>now() and project_id=? and id=?");
				
				){
			
			
			ps.setInt(1, pid);
			ps.setInt(2, bid);
			//SQLの実行・結果受け取り
			ResultSet rs=ps.executeQuery();
			
			//結果をオブジェクトに入れる
			if(rs.next()){
				//結果が存在した場合オブジェクトを生成
				System.out.println("検索結果を収納");
				//オブジェクトにデータを入れる
				bbs.setId(rs.getInt("id"));
				bbs.setTitle(rs.getString("title"));
				bbs.setDescrpt(rs.getString("dscrpt"));
			}
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		return bbs;
	}
	
	
	//個人ホーム画面向け最新更新BBSのリストアップ
	public ArrayList<HomeBBS> listHome(int id){
		System.out.println("ホーム向け最新BBSスレッド読み込み開始");
		//データを格納する配列を生成
		
		ArrayList<HomeBBS> list = new ArrayList<HomeBBS>();
		
		//tryの()内部で宣言したconなどは処理終了後自動的にcloseされるのでcon.closeなどは不要となる。
		try(
				Connection con = DBCon.getConnection();
				PreparedStatement ps=con.prepareStatement(
						"select bbs.title, bbs.id, bbs.upddate, project.name, project.id from bbs "
						+ "inner join project on (project.id=bbs.project_id) "
						+ "inner join project_user on (project_user.project_id=bbs.project_id) "
						+ "where project_user.user_id=? order by bbs.upddate desc limit 5;");
				){
			
			
			ps.setInt(1, id);
			//SQLの実行・結果受け取り
			ResultSet rs=ps.executeQuery();
			
			//結果をオブジェクトに入れる
			while(rs.next()){
				//結果が存在した場合オブジェクトを生成
				System.out.println("検索結果を収納");
				HomeBBS p=new HomeBBS();
				//オブジェクトにデータを入れる
				p.setBbs_id(rs.getInt("bbs.id"));
				p.setBbs_title(rs.getString("bbs.title"));
				
				// 現在日時をTimestamp型で取得(DBから取得するデータのダミーです)
				Timestamp datetime = rs.getTimestamp("bbs.upddate");
				// ここが本題のDatetimeからStringへの変換！
				String dateTimeStr = new SimpleDateFormat("yyyy/MM/dd HH:mm").format(datetime);
				
				p.setUpddate(dateTimeStr);
				p.setPrj_name(rs.getString("project.name"));
				p.setPrj_id(rs.getInt("project.id"));
				//オブジェクトをリストに追加
				list.add(p);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return list;
	}
}
