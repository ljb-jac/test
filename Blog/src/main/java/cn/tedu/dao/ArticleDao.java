 package cn.tedu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.tedu.entity.Article;
import cn.tedu.entity.Tag;
import cn.tedu.entity.User;
import cn.tedu.utils.DBUtils;

public class ArticleDao {

	public List<Article> getHomeList() {
		ArrayList<Article> list=new ArrayList<Article>();
		try(Connection conn=DBUtils.getConn()){
			String sql="select a.oId,a.title,a.abstract,a.commentCount,a.viewCount,a.putTop,a.created,a.updated,a.imgName,u.userName from article a join user u on u.oId=a.authorId order by a.putTop desc,a.created desc limit 0,8";
			Statement s=conn.createStatement();
			ResultSet rs=s.executeQuery(sql);
			while(rs.next()) {
				int oId=rs.getInt(1);
				String title=rs.getString(2);
				String abs=rs.getString(3);
				int commentCount=rs.getInt(4);
				int viewCount=rs.getInt(5);
				int putTop=rs.getInt(6);
				long created=rs.getLong(7);
				long updated=rs.getLong(8);
				String imgName=rs.getString(9);
				String userName=rs.getString(10);
				//封装数据
				Article a=new Article(oId, title, abs, commentCount, viewCount, putTop, created, updated, imgName, userName);
				//通过文章oId查询对应的标签信息
				TagDao dao=new TagDao();
				List<Tag> tags=dao.findByArticleId(oId);
				//				System.out.println("标签数量"+tags.size());
				//让文章对象和查询到的标签集合关联
				a.setTags(tags);

				list.add(a);		
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return list;
	}

	public List<Article> getNewList() {
		ArrayList<Article> list=new ArrayList<Article>();
		try (Connection conn = DBUtils.getConn()) {
			String sql="select oId,title from article order by created desc limit 0,5";
			Statement s=conn.createStatement();
			ResultSet rs=s.executeQuery(sql);
			while(rs.next()) {
				int oId=rs.getInt(1);
				String title=rs.getString(2);
				list.add(new Article(oId, title));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return list;
	}

	public List<Article> getCommentList() {
		ArrayList<Article> list=new ArrayList<Article>();
		try (Connection conn = DBUtils.getConn()) {
			String sql="select oId,title from article order by commentCount desc limit 0,5";
			Statement s=conn.createStatement();
			ResultSet rs=s.executeQuery(sql);
			while(rs.next()) {
				int oId=rs.getInt(1);
				String title=rs.getString(2);
				list.add(new Article(oId, title));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return list;
	}

	public List<Article> getViewList() {
		ArrayList<Article> list=new ArrayList<Article>();
		try (Connection conn = DBUtils.getConn()) {
			String sql="select oId,title from article order by viewCount desc limit 0,5";
			Statement s=conn.createStatement();
			ResultSet rs=s.executeQuery(sql);
			while(rs.next()) {
				int oId=rs.getInt(1);
				String title=rs.getString(2);
				list.add(new Article(oId, title));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return list;		
	}

	public List<Article> findAll() {
		ArrayList<Article> list=new ArrayList<Article>();
		try(Connection conn=DBUtils.getConn()){
			String sql="select a.oId,a.title,a.abstract,a.commentCount,a.viewCount,a.putTop,a.created,a.updated,a.imgName,u.userName from article a join user u on u.oId=a.authorId order by a.created desc limit 0,8";
			Statement s=conn.createStatement();
			ResultSet rs=s.executeQuery(sql);
			while(rs.next()) {
				int oId=rs.getInt(1);
				String title=rs.getString(2);
				String abs=rs.getString(3);
				int commentCount=rs.getInt(4);
				int viewCount=rs.getInt(5);
				int putTop=rs.getInt(6);
				long created=rs.getLong(7);
				long updated=rs.getLong(8);
				String imgName=rs.getString(9);
				String userName=rs.getString(10);
				//封装数据
				Article a=new Article(oId, title, abs, commentCount, viewCount, putTop, created, updated, imgName, userName);
				//通过文章oId查询对应的标签信息
				TagDao dao=new TagDao();
				List<Tag> tags=dao.findByArticleId(oId);
				//让文章对象和查询到的标签集合关联
				a.setTags(tags);

				list.add(a);		
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return list;
	}

	public List<Article> findByKeyword(String keyword) {
		ArrayList<Article> list=new ArrayList<Article>();
		try(Connection conn=DBUtils.getConn()){
			String sql="select a.oId,a.title,a.abstract,a.commentCount,a.viewCount,a.putTop,a.created,a.updated,a.imgName,u.userName from article a join user u on u.oId=a.authorId where a.title like ?";
			PreparedStatement ps=conn.prepareStatement(sql);
			//替换？
			ps.setString(1, "%"+keyword+"%");
			//执行SQL
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				int oId=rs.getInt(1);
				String title=rs.getString(2);
				String abs=rs.getString(3);
				int commentCount=rs.getInt(4);
				int viewCount=rs.getInt(5);
				int putTop=rs.getInt(6);
				long created=rs.getLong(7);
				long updated=rs.getLong(8);
				String imgName=rs.getString(9);
				String userName=rs.getString(10);
				//封装数据
				Article a=new Article(oId, title, abs, commentCount, viewCount, putTop, created, updated, imgName, userName);
				//通过文章oId查询对应的标签信息
				TagDao dao=new TagDao();
				List<Tag> tags=dao.findByArticleId(oId);
				//让文章对象和查询到的标签集合关联
				a.setTags(tags);

				list.add(a);		
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return list;
	}

	public Article findById(String id) {
		try(Connection conn=DBUtils.getConn()){
			String sql="select a.oId,a.title,a.abstract,a.commentCount,a.viewCount,a.putTop,a.created,a.updated,a.imgName,u.userName,a.content from article a join user u on u.oId=a.authorId where a.oId=?";
			PreparedStatement ps=conn.prepareStatement(sql);
			//替换？
			ps.setInt(1, Integer.parseInt(id));
			//执行SQL
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				int oId=rs.getInt(1);
				String title=rs.getString(2);
				String abs=rs.getString(3);
				int commentCount=rs.getInt(4);
				int viewCount=rs.getInt(5);
				int putTop=rs.getInt(6);
				long created=rs.getLong(7);
				long updated=rs.getLong(8);
				String imgName=rs.getString(9);
				String userName=rs.getString(10);
				String content=rs.getString(11);
				
				//封装数据
				Article a=new Article(oId, title, abs, commentCount, viewCount, putTop, created, updated, imgName, userName);
				//把查询到的正文和实体关联
				a.setContent(content);
				
				//通过文章oId查询对应的标签信息
				TagDao dao=new TagDao();
				List<Tag> tags=dao.findByArticleId(oId);
				//让文章对象和查询到的标签集合关联
				a.setTags(tags);
				//把查询到的文章对象返回
				return a;	
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return null;//查询到文章不会执行到此步
	}

	public void save(Article a, User user) {
		//把传递过来的文章对象里面的数据保存到数据库中
		//切记把created的值设为当前的系统时间
		try (Connection conn = DBUtils.getConn()) {
			String sql="insert into article (title,abstract,authorId,viewCount,commentCount,content,putTop,created,imgName) values(?,?,?,0,0,?,?,?,?)";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1,a.getTitle() );
			ps.setString(2, a.getAbs());
			//保存当前用户的id 作为作者id
			ps.setInt(3, user.getoId());
			ps.setString(4, a.getContent());
			ps.setInt(5,a.getPutTop());
			//获取当前系统时间
			ps.setLong(6, System.currentTimeMillis());
			ps.setString(7,a.getImgName());
			//执行
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		
	}


}
