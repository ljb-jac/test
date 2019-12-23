package cn.tedu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.tedu.entity.Tag;
import cn.tedu.utils.DBUtils;

public class TagDao {

	public List<Tag> findByArticleId(int oId) {
		ArrayList<Tag> list=new ArrayList<Tag>();
		try (Connection conn = DBUtils.getConn()) {
			String sql="select t.title from tag t join tag_article ta on ta.tag_oId=t.oId where ta.article_oId=?";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, oId);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				String title=rs.getString(1);
				//数据封装到对象中并把对象添加到集合
				list.add(new Tag(0,title,0));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return list;
	}

	public List<Tag> getList() {
		ArrayList<Tag> list=new ArrayList<Tag>();
		try (Connection conn = DBUtils.getConn()) {
			String sql="select oId,title,referenceCount from tag order by referenceCount desc limit 0,5";
			Statement s=conn.createStatement();
			ResultSet rs=s.executeQuery(sql);
			while(rs.next()) {
				int oId=rs.getInt(1);
				String title =rs.getString(2);
				int referenceCount =rs.getInt(3);
				list.add(new Tag(oId, title, referenceCount));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return list;
	}
	
}
