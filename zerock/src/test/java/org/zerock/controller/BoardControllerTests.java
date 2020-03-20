package org.zerock.controller;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.queryParam;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class) // 테스트에 사용할 클래스 명시
@WebAppConfiguration // 컨트롤러 테스트

@ContextConfiguration({
	"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})

@Log4j
public class BoardControllerTests {
	@Setter(onMethod_ = {@Autowired})
	
	private WebApplicationContext ctx;
	private MockMvc mockMvc;
	
	@Before // Before 어노테이션이 적용된 메서드는 모든 테스트 전에 매번 실행이 된다.
	public void setup() {
		// 가짜 MVC 빌드
		// 테스트 진행 시 브라우저에서 URL과 매개변수를 요청한 것 처럼 하기 위함
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	// 리스트 조회 컨트롤러 테스트
	@Test
	public void testList() throws Exception {
		
		log.info("=========================");
		log.info(
				mockMvc.perform(MockMvcRequestBuilders.get("/board/list/"))
				.andReturn()
				.getModelAndView()
				.getModelMap());
	}
	
	// 등록 컨트롤러 테스트
	@Test
	public void testRegist() throws Exception {
		log.info("=========================");
		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/regist/")
				
				.param("title", "테스트 제목")
				.param("content", "테스트 내용")
				.param("writer", "user00")
			).andReturn().getModelAndView().getViewName();
		
		log.info(resultPage);
	}
	
	// 조건 검색 컨트롤러 테스트
	@Test
	public void testGet() throws Exception {
		
		log.info("===================");
		log.info(mockMvc.perform(MockMvcRequestBuilders
			.get("/board/get/")
			.param("bno", "24"))
			.andReturn()
			.getModelAndView().getModelMap());
	}
	
	@Test
	public void testUpdate() throws Exception {
		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/modify/")

				.param("title", "테스트 제목")
				.param("content", "테스트 내용")
				.param("writer", "edit00")
				.param("bno", "24")
			).andReturn().getModelAndView().getViewName();
		
		log.info(resultPage);
	}
	
	@Test
	public void testRemove() throws Exception {
		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/remove/")
				.param("bno", "24")
			).andReturn().getModelAndView().getViewName();
		
		log.info(resultPage);
	}
}
