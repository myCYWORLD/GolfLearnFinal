package com.golflearn.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.golflearn.service.OpenApi;

@WebServlet("/seeksidosigu")
public class SeekSidoSigu extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");	
		PrintWriter out = response.getWriter();
		System.out.println(request.getParameter("sido"));
		System.out.println("-----------------------------------");
		String sidoVal = request.getParameter("sido");
		System.out.println(sidoVal);
		String result = "";
		OpenApi api = new OpenApi();
		List sidoList = new ArrayList();
		
		try {
			ObjectMapper mapper = new ObjectMapper(); 
			Map<String, Object> map = new HashMap<>();
			map.put("sido", api.sidoApi());
			map.put("sigungu", api.siguApi(sidoVal));
			request.setAttribute("sido", api.sidoApi());
			request.setAttribute("sigungu", api.siguApi(sidoVal));
			String jsonValue = mapper.writeValueAsString(map);
//		System.out.println("jsonValue :"+ jsonValue);
			result = mapper.writeValueAsString(map);
			out.print(result);
			
		} catch (Exception e) {
			e.printStackTrace();
			Map<String, Object> map = new HashMap<>();

			map.put("msg", e.getMessage());
			ObjectMapper mapper = new ObjectMapper();
			result = mapper.writeValueAsString(map);
//			System.out.println("result: " + result);
			out.print(result);
		}

		
		
		
		
	}

}
