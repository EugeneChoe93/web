package org.big.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.big.common.DBConnection;
import org.big.dto.PqsDto;
import org.big.dto.QnaDto;

public class PqsDao {

	private static PqsDao instance;
	
	private PqsDao() {
		
	}

	public static PqsDao getInstance() {
		if (instance == null)
			instance = new PqsDao();
		return instance;
	}	
	
	public int getListCount(String items, String text) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int x = 0;

		String sql;
		
		if (items == null && text == null)
			sql = "SELECT  count(*) FROM qna";
		else
			sql = "SELECT   count(*) FROM qna WHERE " + items + " LIKE '%" + text + "%'";
		
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) 
				x = rs.getInt(1);
			
		} catch (Exception ex) {
			System.out.println("getListCount()  실행 : " + ex);
		} finally {			
			try {				
				if (rs != null) 
					rs.close();							
				if (pstmt != null) 
					pstmt.close();				
				if (conn != null) 
					conn.close();												
			} catch (Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}		
		}		
		return x;
	}
	
	public ArrayList<PqsDto> getQnaList(int page, int limit, String items, String text) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int total_record = getListCount(items, text );
		int start = (page - 1) * limit;
		int index = start + 1;

		String sql;

		if (items == null && text == null)
			sql = "SELECT * from qna ORDER BY num DESC";
		else
			sql = "SELECT  * FROM qna where " + items + " like '%" + text + "%' ORDER BY num DESC ";

		ArrayList<PqsDto> list = new ArrayList<PqsDto>();
	
		try {
			conn = DBConnection.getConnection();
			
			
			pstmt = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = pstmt.executeQuery();
			
			while (rs.absolute(index)) {
				PqsDto qna = new PqsDto();
				qna.setNum(rs.getInt("num"));
				qna.setId(rs.getString("id"));
				qna.setName(rs.getString("name"));
				qna.setSubject(rs.getString("subject"));
				qna.setContent(rs.getString("content"));
				qna.setRegist_day(rs.getString("regist_day"));
				qna.setHit(rs.getInt("hit"));
				qna.setIp(rs.getString("ip"));
				list.add(qna);
				
			
				
				if (index < (start + limit) && index <= total_record)
					index++;
				else
					break;
			}
			return list;
		} catch (Exception ex) {
			System.out.println("getQnaList()  실행  : " + ex);
		} finally {
			try {
				if (rs != null) 
					rs.close();							
				if (pstmt != null) 
					pstmt.close();				
				if (conn != null) 
					conn.close();
			} catch (Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}			
		}
		return null;
	}
	
	public String getLoginNameById(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;	

		String name=null;
		String sql = "SELECT * FROM member WHERE id = ? ";

		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) 
				name = rs.getString("name");	
			
			return name;
		} catch (Exception ex) {
			System.out.println("getQnaByNum()  실행  : " + ex);
		} finally {
			try {				
				if (rs != null) 
					rs.close();							
				if (pstmt != null) 
					pstmt.close();				
				if (conn != null) 
					conn.close();
			} catch (Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}		
		}
		return null;
	}

	public void insertQna(PqsDto qna)  {

		
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBConnection.getConnection();		

			String sql = "INSERT INTO qna VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qna.getNum());
			pstmt.setString(2, qna.getId());
			pstmt.setString(3, qna.getName());
			pstmt.setString(4, qna.getSubject());
			pstmt.setString(5, qna.getContent());
			pstmt.setString(6, qna.getRegist_day());
			pstmt.setInt(7, qna.getHit());
			pstmt.setString(8, qna.getIp());

			pstmt.executeUpdate();
		} catch (Exception ex) {
			System.out.println("insertQna()  실행  : " + ex);
		} finally {
			try {									
				if (pstmt != null) 
					pstmt.close();				
				if (conn != null) 
					conn.close();
			} catch (Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}		
		}		
	} 
	
	public void updateHit(int num) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBConnection.getConnection();

			String sql = "SELECT hit FROM qna WHERE num = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			int hit = 0;

			if (rs.next())
				hit = rs.getInt("hit") + 1;
		

			sql = "UPDATE qna SET hit=? WHERE num=?";
			pstmt = conn.prepareStatement(sql);		
			pstmt.setInt(1, hit);
			pstmt.setInt(2, num);
			pstmt.executeUpdate();
		} catch (Exception ex) {
			System.out.println("updateHit()  실행  : " + ex);
		} finally {
			try {
				if (rs != null) 
					rs.close();							
				if (pstmt != null) 
					pstmt.close();				
				if (conn != null) 
					conn.close();
			} catch (Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}			
		}
	}
	
	public PqsDto getQnaByNum(int num, int page) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PqsDto qna = null;

		updateHit(num);
		String sql = "SELECT * FROM qna WHERE num = ? ";

		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				qna = new PqsDto();
				qna.setNum(rs.getInt("num"));
				qna.setId(rs.getString("id"));
				qna.setName(rs.getString("name"));
				qna.setSubject(rs.getString("subject"));
				qna.setContent(rs.getString("content"));
				qna.setRegist_day(rs.getString("regist_day"));
				qna.setHit(rs.getInt("hit"));
				qna.setIp(rs.getString("ip"));
			}
			
			return qna;
		} catch (Exception ex) {
			System.out.println("getQnaByNum()  실행  : " + ex);
		} finally {
			try {
				if (rs != null) 
					rs.close();							
				if (pstmt != null) 
					pstmt.close();				
				if (conn != null) 
					conn.close();
			} catch (Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}		
		}
		return null;
	}
	
	public void updateQna(PqsDto qna) {

		Connection conn = null;
		PreparedStatement pstmt = null;
	
		try {
			String sql = "UPDATE qna SET name=?, subject=?, content=? WHERE num=?";

			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			conn.setAutoCommit(false);

			pstmt.setString(1, qna.getName());
			pstmt.setString(2, qna.getSubject());
			pstmt.setString(3, qna.getContent());
			pstmt.setInt(4, qna.getNum());

			pstmt.executeUpdate();			
			conn.commit();

		} catch (Exception ex) {
			System.out.println("updateQna()  실행  : " + ex);
		} finally {
			try {										
				if (pstmt != null) 
					pstmt.close();				
				if (conn != null) 
					conn.close();
			} catch (Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}		
		}
	} 
	
	public void deleteQna(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;		

		String sql = "DELETE FROM qna WHERE num=?";	

		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();

		} catch (Exception ex) {
			System.out.println("deleteQna() 실행 : " + ex);
		} finally {
			try {										
				if (pstmt != null) 
					pstmt.close();				
				if (conn != null) 
					conn.close();
			} catch (Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}		
		}
	}	
}

