package nwnu.servlet;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nwnu.word.WordCount;



/**
 * Servlet implementation class WordCountServlet
 */
@WebServlet("/WordCountServlet")
public class WordCountServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    public static TreeMap<String,Integer> map;
    public static ArrayList map1;
	public static String[] str;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WordCountServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//编码格式
		PrintWriter out=response.getWriter();
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//获取前台页面的值
		String FileName=request.getParameter("FileName");		
	
		String words=request.getParameter("word");	
		
		String wordnum=request.getParameter("wordnum");	
	
		
		String wordlines=request.getParameter("wordlines");
				
		String result=request.getParameter("result");
		
		String id=request.getParameter("id");
		//创建WordCount对象
		WordCount wordCount = new WordCount();
		
        if(id.equals("0")){
    	   //文件的上传
			map=wordCount.Count(FileName);
		    out.print("<script>alert('上传成功！！');location.href='html/WordCount.jsp'</script>");		   
		}else if(id.equals("1")){
			long startTime=System.currentTimeMillis();			
			//指定单词的查找   TreeMap:map
            str=wordCount.one(words, map);
			//System.out.println(str);
            Iterator<String> it1 = map.keySet().iterator();
            //遍历
            while(it1.hasNext()){
             //键值对的形式存放
             String key = (String) it1.next();
	         Integer value = map.get(key);
            }
            long endTime=System.currentTimeMillis();
            float excTime=(float)((endTime-startTime)/1000)*1000;
            //存放至session对话中
            request.setAttribute("map", map);
            //耗时
            request.setAttribute("excTime",excTime);
            request.getRequestDispatcher("html/WordCount.jsp").forward(request, response);
		
		}else if(id.equals("2")){
			long startTime=System.currentTimeMillis();
			//高频词的统计  返回单词和个数  ArrayList:map1
			int num=Integer.parseInt(wordnum);
			map1=wordCount.two(map,num); 
			 long endTime=System.currentTimeMillis();
	         float excTime=(float)((endTime-startTime)/1000)*1000;
			request.setAttribute("map1", map1);
			request.setAttribute("excTime",excTime);
			//request.setAttribute("num", num);
		    request.getRequestDispatcher("html/WordCount.jsp").forward(request, response);
			
		}else if(id.equals("3")){
			//统计文本行数以及字符数
			long startTime=System.currentTimeMillis();
			  List<String>  lines=wordCount.statistics(FileName); 
			 for (int i = 0; i < lines.size(); i++) {
				System.out.println(lines.get(i));
			}
			 long endTime=System.currentTimeMillis();
	         float excTime=(float)((endTime-startTime)/1000)*1000;
	         request.setAttribute("excTime",excTime);
	         request.setAttribute("list", lines);
			  //页面跳转
			 request.getRequestDispatcher("html/WordCount.jsp").forward(request, response);
			
		}else if(id.equals("4")){
			long startTime=System.currentTimeMillis();
			 //写入至result.txt中
			wordCount.three(map);
			long endTime=System.currentTimeMillis();
	        float excTime=(float)((endTime-startTime)/1000)*1000;
	        request.setAttribute("excTime",excTime);
			out.print("<script>alert('存放成功');location.href='html/WordCount.jsp'</script>");	
		}
    
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
