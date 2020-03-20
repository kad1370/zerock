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

@RunWith(SpringJUnit4ClassRunner.class) // �׽�Ʈ�� ����� Ŭ���� ���
@WebAppConfiguration // ��Ʈ�ѷ� �׽�Ʈ

@ContextConfiguration({
	"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})

@Log4j
public class BoardControllerTests {
	@Setter(onMethod_ = {@Autowired})
	
	private WebApplicationContext ctx;
	private MockMvc mockMvc;
	
	@Before // Before ������̼��� ����� �޼���� ��� �׽�Ʈ ���� �Ź� ������ �ȴ�.
	public void setup() {
		// ��¥ MVC ����
		// �׽�Ʈ ���� �� ���������� URL�� �Ű������� ��û�� �� ó�� �ϱ� ����
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	// ����Ʈ ��ȸ ��Ʈ�ѷ� �׽�Ʈ
	@Test
	public void testList() throws Exception {
		
		log.info("=========================");
		log.info(
				mockMvc.perform(MockMvcRequestBuilders.get("/board/list/"))
				.andReturn()
				.getModelAndView()
				.getModelMap());
	}
	
	// ��� ��Ʈ�ѷ� �׽�Ʈ
	@Test
	public void testRegist() throws Exception {
		log.info("=========================");
		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/regist/")
				
				.param("title", "�׽�Ʈ ����")
				.param("content", "�׽�Ʈ ����")
				.param("writer", "user00")
			).andReturn().getModelAndView().getViewName();
		
		log.info(resultPage);
	}
	
	// ���� �˻� ��Ʈ�ѷ� �׽�Ʈ
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

				.param("title", "�׽�Ʈ ����")
				.param("content", "�׽�Ʈ ����")
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
