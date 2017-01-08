package jp.ac.hal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class EventDAO {

	
	//イベント生成処理
	public int create(int pid, String name, String desc, String startdate, String limitdate){
		int cnt=0; //更新件数
		
		try(
				Connection con=DBCon.getConnection();
				PreparedStatement ps=con.prepareStatement("insert into event values(null,?,?,?,?,?,0,now())");
				){
			//？を置き換え
			
			ps.setInt(1, pid);
			ps.setString(2, name);
			ps.setString(3, desc);
			ps.setString(4, startdate);
			ps.setString(5, limitdate);
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
	
	//イベントリストアップ
	public ArrayList<Event> listH(int pid){
		System.out.println("プロジェクト内部イベントリストアップを開始");
		//データを格納する配列を生成
		
		ArrayList<Event> list = new ArrayList<Event>();
		
		//tryの()内部で宣言したconなどは処理終了後自動的にcloseされるのでcon.closeなどは不要となる。
		try(
				Connection con=DBCon.getConnection();
				PreparedStatement ps=con.prepareStatement("select * from event where project_id=?  order by startdate desc");
				
				){
			
			
			ps.setInt(1, pid);
			//SQLの実行・結果受け取り
			ResultSet rs=ps.executeQuery();
			
			//結果をオブジェクトに入れる
			while(rs.next()){
				//結果が存在した場合オブジェクトを生成
				System.out.println("検索結果を収納");
				Event p=new Event();
				//オブジェクトにデータを入れる
				p.setId(rs.getInt("id"));
				p.setName(rs.getString("name"));
				p.setStartdate(rs.getTimestamp("startdate"));
				
				Timestamp datetime = rs.getTimestamp("startdate");
				// ここが本題のDatetimeからStringへの変換！
				String dateTimeStr = new SimpleDateFormat("yyyy/MM/dd").format(datetime);
				
				p.setStdate(dateTimeStr);
				
				//オブジェクトをリストに追加
				list.add(p);
			}
		}catch(SQLException e){
			
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public Event select(int id){
		System.out.println("イベント選択を開始");
		//データを格納する配列を生成
		
		Event event = new Event();
		
		//tryの()内部で宣言したconなどは処理終了後自動的にcloseされるのでcon.closeなどは不要となる。
		try(
				Connection con=DBCon.getConnection();
				PreparedStatement ps=con.prepareStatement("select * from event where id=?");
				
				){
			
			
			ps.setInt(1, id);
			//SQLの実行・結果受け取り
			ResultSet rs=ps.executeQuery();
			
			//結果をオブジェクトに入れる
			if(rs.next()){
				//結果が存在した場合オブジェクトを生成
				System.out.println("検索結果を収納");
				//オブジェクトにデータを入れる
				event.setId(rs.getInt("id"));
				event.setName(rs.getString("name"));
				String desc=rs.getString("descrpt");
				String regex = "\n";
				desc = desc.replace(regex, "<br />");
				event.setDescrpt(desc);
				event.setStartdate(rs.getTimestamp("startdate"));
				Timestamp datetime = rs.getTimestamp("startdate");
				// ここが本題のDatetimeからStringへの変換！
				String dateTimeStr = new SimpleDateFormat("yyyy/MM/dd HH:mm").format(datetime);
				event.setStdate(dateTimeStr);
				
				Timestamp datetime2 = rs.getTimestamp("limitdate");
				// ここが本題のDatetimeからStringへの変換！
				String dateTimeStr2 = new SimpleDateFormat("yyyy/MM/dd HH:mm").format(datetime2);
				event.setLmtdate(dateTimeStr2);
				event.setLimitdate(rs.getTimestamp("limitdate"));
				
				System.out.println(event.getName());
			}
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		return event;
	}
	
	public ArrayList<Hoge> listH(String sqldate, int pid){
		System.out.println("プロジェクト内部イベントリストアップを開始");
		String sqlst=sqldate+" 00:00:00";
		String sqled=sqldate+" 23:59:59";
		System.out.println("start:"+sqlst+" "+"end:"+sqled);
		//データを格納する配列を生成
		
		ArrayList<Hoge> list = new ArrayList<Hoge>();
		
		//tryの()内部で宣言したconなどは処理終了後自動的にcloseされるのでcon.closeなどは不要となる。
		try(
				Connection con = DBCon.getConnection();
				PreparedStatement ps=con.prepareStatement("select * from event where startdate between ? and ? and project_id=?");
				
				){
			
			ps.setString(1, sqlst);
			ps.setString(2, sqled);
			ps.setInt(3, pid);
			//SQLの実行・結果受け取り
			
			System.out.println(ps);
			ResultSet rs=ps.executeQuery();
			
			//結果をオブジェクトに入れる
			while(rs.next()){
				//結果が存在した場合オブジェクトを生成
				System.out.println("検索結果を収納");
				Hoge p=new Hoge();
				//オブジェクトにデータを入れる
				p.setData1(rs.getInt("id"));
				p.setData2(rs.getString("name"));
				//オブジェクトをリストに追加
				System.out.println(p.getData2()+":"+p.getData1());
				list.add(p);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//個人ホーム画面向け最新更新イベントのリストアップ
	public ArrayList<HomeEvent> listHome(int id){
		System.out.println("ホーム向け最新イベントスレッド読み込み開始");
		//データを格納する配列を生成
		
		ArrayList<HomeEvent> list = new ArrayList<HomeEvent>();
		
		//tryの()内部で宣言したconなどは処理終了後自動的にcloseされるのでcon.closeなどは不要となる。
		try(
				Connection con = DBCon.getConnection();
				PreparedStatement ps=con.prepareStatement(
						"select event.name, event.id, event.upd_date, project.name, project.id from event "
						+ "inner join project on (project.id=event.project_id) "
						+ "inner join project_user on(project_user.project_id=event.project_id) "
						+ "where project_user.user_id=? order by event.upd_date desc limit 5;");
				){
			
			
			ps.setInt(1, id);
			//SQLの実行・結果受け取り
			ResultSet rs=ps.executeQuery();
			
			//結果をオブジェクトに入れる
			while(rs.next()){
				//結果が存在した場合オブジェクトを生成
				System.out.println("検索結果を収納");
				HomeEvent p=new HomeEvent();
				//オブジェクトにデータを入れる
				p.setEvent_id(rs.getInt("event.id"));
				p.setEvent_name(rs.getString("event.name"));
				
				// 現在日時をTimestamp型で取得(DBから取得するデータのダミーです)
				Timestamp datetime = rs.getTimestamp("event.upd_date");
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
