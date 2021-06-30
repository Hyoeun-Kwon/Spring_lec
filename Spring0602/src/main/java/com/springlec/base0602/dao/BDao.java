package com.springlec.base0602.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.springlec.base0602.dto.BDto;

public class BDao {

	DataSource dataSource;
	
	// Constructor
	public BDao() {
		try {
			
			// context.xml 과 연결해주기
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/mvc");
			
		}catch(Exception e){
			e.printStackTrace();
			
			
		}
		
	}
	
	
	
	// 초기화면 검색
	public ArrayList<BDto> list(){
		
		ArrayList<BDto> dtos = new ArrayList<BDto>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		
		try {
			//DB 와 연결
			connection = dataSource.getConnection();
			
			// Query
			String query = "SELECT bId, bName, bTitle, bContent, bDate FROM mvc_board";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			// DATA 가져오기
			while (resultSet.next()) {
				int bId = resultSet.getInt("bId");
				String bName = resultSet.getString("bName");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				Timestamp bDate = resultSet.getTimestamp("bDate");
				
				BDto dto = new BDto(bId, bName, bTitle, bContent, bDate);
				dtos.add(dto);
			
			}// while
			
			
		}catch (Exception e) {
			e.printStackTrace();
			
		}finally {
			try {
				if(resultSet != null) resultSet.close();
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
				
			}catch (Exception e) {
				e.printStackTrace();
			}
		}//finally
		
		return dtos;
	} // METHOD : list
	
	//-------------write
	public void write(String bName, String bTitle, String bContent) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		
		try {
			//DB 와 연결
			connection = dataSource.getConnection();
			
			// Query
			String query = "insert into mvc_board (bName, bTitle, bContent, bDate) values (?,?,?,now())";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, bName);
			preparedStatement.setString(2, bTitle);
			preparedStatement.setString(3, bContent);
			preparedStatement.executeUpdate();
	
			
		}catch (Exception e) {
			e.printStackTrace();
			
		}finally {
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
				
			}catch (Exception e) {
				e.printStackTrace();
			}
		}//finally
		
	}
	
	//content_view (검색해 오는 것)
	//한줄만 가져오면 되므로, 어레이 리스트는 필요없고, bean 만 필요하다
	public BDto contentView(String sbId) {
		
		BDto dto = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		
		try {
			connection = dataSource.getConnection();
			//id가 키값임
			String query = "select * from mvc_board where bId = ?"; //select 문장에 ?하는건 처음일거임 보안상 이렇게 하는게 좋다!
			
			//프리페어스테이트먼트를 쓰므로 스테이트먼트 필요없음 둘중 하나만 쓰면 됨
			preparedStatement = connection.prepareStatement(query);
			//아이디를 숫자로 받자 
			preparedStatement.setInt(1, Integer.parseInt(sbId));
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				int bId = resultSet.getInt("bId");  //위의 쿼리문에 있는 bId 컬럼이름임
				String bName = resultSet.getString("bName");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				
				dto = new BDto(bId, bName, bTitle, bContent);  
				System.out.println("데이터 로드 성공");
				
			}
			
			
		}catch(Exception e) {
			System.out.println("데이터 로드 실패");
			e.printStackTrace();
			//정상적이든, 에러가 걸리든 무조건 final로 와라 
		}finally{
			try {
				if(resultSet !=null) resultSet.close();
				if(preparedStatement !=null) preparedStatement.close();
				if(connection !=null) connection.close();
			}catch(Exception e) {
				e.printStackTrace();
				
			}
			
		}
		return dto; //내가 받은 데이터를 리턴해주면 됨
		
	
	}//content_view end
	
	//-------------modify
	
	public void modify(String bId, String bName, String bTitle, String bContent) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		
		try {
			//DB 와 연결
			connection = dataSource.getConnection();
			
			// Query
			String query = "update mvc_board set bName = ?, bTitle = ?, bContent = ? where bId = ?";
			preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setString(1, bName);
			preparedStatement.setString(2, bTitle);
			preparedStatement.setString(3, bContent);
			preparedStatement.setString(4,bId);
			preparedStatement.executeUpdate();
	
			
		}catch (Exception e) {
			e.printStackTrace();
			
		}finally {
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
				
			}catch (Exception e) {
				e.printStackTrace();
			}
		}//finally
		
	}
	
	//----------------
public void delete(String bId) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		
		try {
			connection = dataSource.getConnection();
			
			String query = "delete from mvc_board where bId= ?"; //삭제는 꼭 where 을 주기
			
			preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setString(1, bId);//쿼리문 첫번째에다가 bId를 넣어줘
			
			preparedStatement.executeUpdate();
			
		}catch(Exception e) {
			System.out.println("데이터 로드 실패");
			e.printStackTrace();
			//정상적이든, 에러가 걸리든 무조건 final로 와라 
		}finally{
			try {
				//close해줘야 데이터가 쌓이지 않음
				if(preparedStatement !=null) preparedStatement.close();
				if(connection !=null) connection.close();
			}catch(Exception e) {
				e.printStackTrace();
				
			}
			
		}

		
	}//modify 메소드 end
	
	
} // Main