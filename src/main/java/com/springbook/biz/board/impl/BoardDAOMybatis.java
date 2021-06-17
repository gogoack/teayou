package com.springbook.biz.board.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.board.common.JDBCUtil;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;



@Repository
public class BoardDAOMybatis {
	@Autowired
	private SqlSessionTemplate mybatis;
	
	
	public void insertBoard(BoardVO vo) {
		System.out.println("mybatis로 insertboard() 기능처리");
		mybatis.insert("BoardDAO.insertBoard", vo);
	}
	

	public void updateBoard(BoardVO vo) {
		System.out.println("mybatis로 updateboard() 기능처리");
		mybatis.update("BoardDAO.updateBoard", vo);
	}
	
	
	public void deleteBoard(BoardVO vo) {
		System.out.println("mybatis로 deleteboard() 기능처리");
		mybatis.delete("BoardDAO.deleteBoard", vo);
	}
	

	public BoardVO getBoard(BoardVO vo) {
		System.out.println("mybatis로 geteboard() 기능처리");
		return (BoardVO) mybatis.selectOne("BoardDAO.getBoard", vo);
	}
	

	public List<BoardVO> getBoardList(BoardVO vo) {
		System.out.println("mybatis로 geteboardlist() 기능처리");
		return mybatis.selectList("BoardDAO.getBoardList", vo);
//	if(vo.getSearchCondition().equals("TITLE")) {
//		return mybatis.selectList("BoardDAO.getBoardList_T", vo);
//	}else if(vo.getSearchCondition().equals("CONTENT")) {
		
	
	//	return null;
	}
}
