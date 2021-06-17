package com.springbook.view.board;


import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.springbook.biz.board.BoardService;
import com.springbook.biz.board.BoardVO;

import java.io.File;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import org.springframework.web.bind.annotation.ResponseBody;
import com.springbook.biz.board.BoardListVO;

@Controller
@SessionAttributes("board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	// 글목록 변환처리 
	@RequestMapping("/dataTransform.do")
	@ResponseBody
	public BoardListVO dataTransform(BoardVO vo) {
		vo.setSearchCondition("TITLE");
		vo.setSearchKeyword("");
		List<BoardVO> boardList = boardService.getBoardList(vo);
		BoardListVO boardListVO = new BoardListVO();
		boardListVO.setBoardList(boardList);
		return boardListVO;
	}
	
	public String insertBoard(BoardVO vo) throws IOException {
		System.out.println("�۵�� ó��");
		// ���Ͼ��ε� ó�� 
		MultipartFile uploadFile = vo.getUploadFile();
		if(!uploadFile.isEmpty()) {
			String fileName = uploadFile.getOriginalFilename();
			uploadFile.transferTo(new File("C:/DEV2/" + fileName));
		}
		
		boardService.insertBoard(vo);
		return "getBoardList.do";

}
	// �ۼ���
	@RequestMapping("/updateBoard.do")
	public String updateBoard(@ModelAttribute("board") BoardVO vo) {
		System.out.println("�ۼ��� ó��");
		System.out.println("��ȣ :" + vo.getSeq());
		System.out.println("���� :" + vo.getTitle());
		System.out.println("�ۼ��� :" + vo.getWriter());
		System.out.println("���� :" + vo.getContent());
		System.out.println("����� :" + vo.getRegDate());
		System.out.println("��ȸ�� :" + vo.getCnt());
		boardService.updateBoard(vo);
		return "getBoardList.do";
		
	}
	

	// �ۻ���
		@RequestMapping("/deleteBoard.do")
		public String deleteBoard(BoardVO vo) {
			System.out.println("�ۻ��� ó��");
			boardService.deleteBoard(vo);
			return "getBoardList.do";	
			
		}
			
	// �� ����ȸ
			@RequestMapping("/getBoard.do")
			public String getBoard(BoardVO vo, Model model) {
				System.out.println("�� ����ȸ ó��");
				
				model.addAttribute("board", boardService.getBoard(vo)); // MODEL ��������
				return "getBoard.jsp"; // view �̸� ����
			
			}
			
			
			// �˻� ���� ��ϼ���
			@ModelAttribute("conditionMap")
			public Map<String, String> searchConditionMap() {
				Map<String, String> conditionMap = new HashMap<String, String>();
					conditionMap.put("제목", "TITLE");
					conditionMap.put("내용", "CONTENT");
					return conditionMap;
				}
			
					
			
		
			// �۸�ϰ˻�		
			@RequestMapping("/getBoardList.do")
			public String getBoardList(BoardVO vo, Model model) {
				System.out.println("�� ��ϰ˻� ó��");
				// null check
				if(vo.getSearchCondition() == null) vo.setSearchCondition("TITLE");
				if(vo.getSearchKeyword() == null) vo.setSearchKeyword("");			
				
			    model.addAttribute("boardList", boardService.getBoardList(vo)); // MODEL ��������
				return "getBoardList.jsp"; // view �̸�����
			
				
			}

   }


