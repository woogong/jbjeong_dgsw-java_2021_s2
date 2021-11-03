package kr.hs.dgsw.java.web.servlet.phoneBook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import kr.hs.dgsw.java.web.domain.RestResult;
import kr.hs.dgsw.java.web.service.PhoneBookService;
import kr.hs.dgsw.java.web.service.PhoneBookServiceImpl;

@WebServlet("/phoneBook/delete.do")
public class PhoneBookDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");

	    RestResult result = new RestResult();

	    try {
	    	String idxList = request.getParameter("idxList");
	    	String[] idxArray = idxList.split(",");

		    PhoneBookService service = new PhoneBookServiceImpl();

	    	for (String sIdx : idxArray) {
	    		int idx = Integer.parseInt(sIdx);
	    		service.delete(idx);
	    	}
	    	
		    result.setResultCode("Success");
		} catch (Exception e) {
			e.printStackTrace();
			
			result.setResultCode("Fail");
			result.setResultMessage(e.getMessage());
		}
	    
		response.getWriter().append(new Gson().toJson(result));
	}

	
	public static class Result {
		private String resultCode;
		
		private String resultMessage;
		
		private Object data;

		public String getResultCode() {
			return resultCode;
		}

		public void setResultCode(String resultCode) {
			this.resultCode = resultCode;
		}

		public String getResultMessage() {
			return resultMessage;
		}

		public void setResultMessage(String resultMessage) {
			this.resultMessage = resultMessage;
		}

		public Object getData() {
			return data;
		}

		public void setData(Object data) {
			this.data = data;
		}
		
		
	}

}


