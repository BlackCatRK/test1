package jp.ac.hal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ProjectDAO {
	
	
	public Project select2(int id){
		//データを格納する配列を生成
		System.out.println("プロジェクト探索を開始(object)");
		String desc="";
		String regex = "\n";
		
		Project list = new Project();
		
		//tryの()内部で宣言したconなどは処理終了後自動的にcloseされるのでcon.closeなどは不要となる。
		try(
				Connection con = DBCon.getConnection();
				PreparedStatement ps=con.prepareStatement("select * from project where id=? and closedate>now()");
				
				){
			//検索条件の指定
			ps.setInt(1, id);
			//SQLの実行・結果受け取り
			ResultSet rs=ps.executeQuery();
			//結果をオブジェクトに入れる
			if(rs.next()){
				//結果が存在した場合オブジェクトを生成
				System.out.println("プロジェクトを発見");
				//オブジェクトにデータを入れる
				System.out.println(rs.getInt("id"));
				System.out.println(rs.getString("name"));
				System.out.println(rs.getDate("startdate"));
				list.setId(rs.getInt("id"));
				list.setName(rs.getString("name"));
				list.setEventflg(rs.getBoolean("eventflg"));
				list.setBbsflg(rs.getBoolean("bbsflg"));
				desc=rs.getString("descrpt");
				desc = desc.replace(regex, "<br />");
				list.setDescrpt(desc);
				//オブジェクトをリストに追加
			}else{
				list=null;
			}
			System.out.println(list);
		}catch(SQLException e){
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//プロジェクト接続処理(旧コード)
	public ArrayList<Project> select(int id){
		System.out.println("プロジェクト検索を開始");
		//データを格納する配列を生成
		
		ArrayList<Project> list = new ArrayList<Project>();
		
		//tryの()内部で宣言したconなどは処理終了後自動的にcloseされるのでcon.closeなどは不要となる。
		try(
				Connection con = DBCon.getConnection();
				PreparedStatement ps=con.prepareStatement("select * from project where id=? and closedate>now()");
				
				){
			//検索条件の指定
			ps.setInt(1, id);
			//SQLの実行・結果受け取り
			ResultSet rs=ps.executeQuery();
			//結果をオブジェクトに入れる
			if(rs.next()){
				//結果が存在した場合オブジェクトを生成
				System.out.println("認証に成功");
				Project p=new Project();
				//オブジェクトにデータを入れる
				p.setId(rs.getInt("id"));
				p.setName(rs.getString("name"));
				//オブジェクトをリストに追加
				list.add(p);
			}
		}catch(SQLException e){
			
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//プロジェクト登録処理
	public int insert(String name, boolean eventflg, boolean bbsflg, boolean joinflg, String desc){
		System.out.println("プロジェクト生成開始");
		
		int cnt=0;
		int num=0;
		
		try(
				Connection con = DBCon.getConnection();
				PreparedStatement ps=con.prepareStatement("insert into project values(null,?,now(),\"9999-12-31 23:59:59\",?,?,?,?)");
				){
			//？を置き換え
			
			ps.setString(1, name);
			ps.setBoolean(2, eventflg);
			ps.setBoolean(3, bbsflg);			
			ps.setBoolean(4, joinflg);
			ps.setString(5, desc);
			
			//SQLの実行
			cnt=ps.executeUpdate(); //戻り値は更新件数
			
			if(cnt==1){
				PreparedStatement ps2=con.prepareStatement("select last_insert_id()");
				ResultSet rs=ps2.executeQuery();
				
				if(rs.next()){
					num=rs.getInt("last_insert_id()");
				}
			}
			
		}catch(SQLException e){
			e.printStackTrace();
			return 0;
		}catch(ClassNotFoundException e){
			e.printStackTrace();
			return 0;
		}
		
		return num;
	}
	
	//ホーム画面向けリストアップ
	public ArrayList<Project> listHome(){
		System.out.println("プロジェクトリストアップを開始");
		//データを格納する配列を生成
		
		ArrayList<Project> list = new ArrayList<Project>();
		
		//tryの()内部で宣言したconなどは処理終了後自動的にcloseされるのでcon.closeなどは不要となる。
		try(
				Connection con = DBCon.getConnection();
				PreparedStatement ps=con.prepareStatement("select * from project where closedate>now() and id!=2147483647 order by startdate desc");
				
				){
			
			//SQLの実行・結果受け取り
			ResultSet rs=ps.executeQuery();
			
			//結果をオブジェクトに入れる
			while(rs.next()){
				//結果が存在した場合オブジェクトを生成
				System.out.println("検索結果を収納");
				Project p=new Project();
				//オブジェクトにデータを入れる
				p.setId(rs.getInt("id"));
				p.setName(rs.getString("name"));
				//オブジェクトをリストに追加
				list.add(p);
			}
		}catch(SQLException e){
			
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<Project> listHomeNow(int id){
		System.out.println("参加中プロジェクトリストアップを開始");
		//データを格納する配列を生成
		
		ArrayList<Project> list = new ArrayList<Project>();
		
		//tryの()内部で宣言したconなどは処理終了後自動的にcloseされるのでcon.closeなどは不要となる。
		try(
				Connection con = DBCon.getConnection();
				PreparedStatement ps=con.prepareStatement("select id, name from project inner join project_user on (project.id=project_user.project_id) where project_user.user_id=? and closedate>now() and project_user.outdate>now() order by project_user.indate desc;");
				
				){
			
			//?の置き換え
			ps.setInt(1, id);
			
			//SQLの実行・結果受け取り
			ResultSet rs=ps.executeQuery();
			
			//結果をオブジェクトに入れる
			while(rs.next()){
				//結果が存在した場合オブジェクトを生成
				System.out.println("検索結果を収納");
				Project p=new Project();
				//オブジェクトにデータを入れる
				p.setId(rs.getInt("id"));
				p.setName(rs.getString("name"));
				//オブジェクトをリストに追加
				list.add(p);
			}
		}catch(SQLException e){
			
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<Project> listPrj(){
		System.out.println("プロジェクトリストアップを開始");
		//データを格納する配列を生成
		
		ArrayList<Project> list = new ArrayList<Project>();
		
		//tryの()内部で宣言したconなどは処理終了後自動的にcloseされるのでcon.closeなどは不要となる。
		try(
				Connection con = DBCon.getConnection();
				PreparedStatement ps=con.prepareStatement("select * from project where closedate>now() and id!=2147483647 order by startdate desc");
				
				){
			
			//SQLの実行・結果受け取り
			ResultSet rs=ps.executeQuery();
			
			//結果をオブジェクトに入れる
			while(rs.next()){
				//結果が存在した場合オブジェクトを生成
				System.out.println("検索結果を収納");
				Project p=new Project();
				//オブジェクトにデータを入れる
				p.setId(rs.getInt("id"));
				p.setName(rs.getString("name"));
				String desc=rs.getString("descrpt");
				if(desc.length()>99){
					desc=desc.substring(0, 99)+"…";
				}
				p.setDescrpt(desc);
				//オブジェクトをリストに追加
				list.add(p);
			}
		}catch(SQLException e){
			
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public int count(){
		int cnt=0;
		
		try(
				Connection con = DBCon.getConnection();
				PreparedStatement ps=con.prepareStatement("select count(*) from project where closedate>now() and id!=2147483647");
				
				){
			
			
			//SQLの実行・結果受け取り
			ResultSet rs=ps.executeQuery();
			
			//結果をオブジェクトに入れる
			if(rs.next()){
				//結果が存在した場合オブジェクトを生成
				System.out.println("検索結果を収納");
				cnt=rs.getInt("count(*)");
			}
		}catch(SQLException e){
			
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
	
	public int count2(int uid){
		int cnt=0;
		
		try(
				Connection con = DBCon.getConnection();
				PreparedStatement ps=con.prepareStatement("select count(*) from project inner join project_user on (project.id=project_user.project_id) where project_user.user_id=? and closedate>now() and id!=2147483647");
				
				){
			
			ps.setInt(1, uid);
			
			//SQLの実行・結果受け取り
			ResultSet rs=ps.executeQuery();
			
			//結果をオブジェクトに入れる
			if(rs.next()){
				//結果が存在した場合オブジェクトを生成
				System.out.println("検索結果を収納");
				cnt=rs.getInt("count(*)");
			}
		}catch(SQLException e){
			
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
	
	public ArrayList<Project> listPrj2(int offset, int limit){
		System.out.println("プロジェクトリストアップを開始");
		//データを格納する配列を生成
		
		System.out.println("limit is "+limit);
		System.out.println("offset is "+offset);
		ArrayList<Project> list = new ArrayList<Project>();
		
		//tryの()内部で宣言したconなどは処理終了後自動的にcloseされるのでcon.closeなどは不要となる。
		try(
				Connection con = DBCon.getConnection();
				PreparedStatement ps=con.prepareStatement("select * from project where closedate>now() and id!=2147483647 order by startdate desc limit ?, ?");
				
				){
			
			ps.setInt(1, offset);
			ps.setInt(2, limit);
			
			
			//SQLの実行・結果受け取り
			ResultSet rs=ps.executeQuery();
			
			//結果をオブジェクトに入れる
			while(rs.next()){
				//結果が存在した場合オブジェクトを生成
				System.out.println("検索結果を収納");
				Project p=new Project();
				//オブジェクトにデータを入れる
				p.setId(rs.getInt("id"));
				p.setName(rs.getString("name"));
				String desc=rs.getString("descrpt");
				if(desc.length()>99){
					desc=desc.substring(0, 99)+"…";
				}
				p.setDescrpt(desc);
				//オブジェクトをリストに追加
				list.add(p);
			}
		}catch(SQLException e){
			
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<Project> listPrj3(int uid, int offset, int limit){
		System.out.println("プロジェクトリストアップを開始");
		//データを格納する配列を生成
		
		System.out.println("limit is "+limit);
		System.out.println("offset is "+offset);
		ArrayList<Project> list = new ArrayList<Project>();
		
		//tryの()内部で宣言したconなどは処理終了後自動的にcloseされるのでcon.closeなどは不要となる。
		try(
				Connection con = DBCon.getConnection();
				PreparedStatement ps=con.prepareStatement("select id, name, descrpt from project inner join project_user on (project.id=project_user.project_id) where project_user.user_id=? and closedate>now() and id!=2147483647 order by startdate desc limit ?, ?");
				
				){
			
			ps.setInt(1, uid);
			ps.setInt(2, offset);
			ps.setInt(3, limit);
			
			
			//SQLの実行・結果受け取り
			ResultSet rs=ps.executeQuery();
			
			//結果をオブジェクトに入れる
			while(rs.next()){
				//結果が存在した場合オブジェクトを生成
				System.out.println("検索結果を収納");
				Project p=new Project();
				//オブジェクトにデータを入れる
				p.setId(rs.getInt("id"));
				p.setName(rs.getString("name"));
				String desc=rs.getString("descrpt");
				if(desc.length()>99){
					desc=desc.substring(0, 99)+"…";
				}
				p.setDescrpt(desc);
				//オブジェクトをリストに追加
				list.add(p);
			}
		}catch(SQLException e){
			
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return list;
	}
}
