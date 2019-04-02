<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>词频的统计</title>
	</head>
	
	
	  <%	
	    TreeMap<String,Integer> map=(TreeMap)request.getAttribute("map");
	    ArrayList  map1=(ArrayList)request.getAttribute("map1");
        List list=(List)request.getAttribute("list");
	  %>
  
      <script type="text/javascript" >
    	//获取文件名
    	
   function one(){
	    var fileName=document.getElementById("file").value;
	    //截取文件名
		//var strFileName = fileName.substring(fileName.lastIndexOf("\\")+1);
	    
		location.href="../WordCountServlet?FileName="+fileName+"&id=0";
    	}
        //指定单词的查找 
	function two(){
		var word = document.getElementById("word").value;
		//var wordid=document.getElementById("word");
		location.href="../WordCountServlet?word="+word+"&id=1";
	}
	function three(){
		var wordnum =  document.getElementById("wordnum").value;		
		location.href="../WordCountServlet?wordnum="+wordnum+"&id=2";		
	}
	function four(){
		//行数与字符数
		var wordlines =  document.getElementById("wordlines").value;	
		location.href="../WordCountServlet?wordlines="+wordlines+"&id=3";
	}
	function five(){
		 var result= document.getElementById("result").value; 
		 location.href="../WordCountServlet?result="+result+"&id=4";
	}	
	</script>
	<style type="text/css">
		#one{
		width: 800px;
		height: 500px;	
		background-color:antiquewhite;
		margin: 0 auto;
		}
		#two{
			margin:20px;
			padding: 20px;
		}
		#wordfile{
			margin:5px 0px 5px 0px;
		}
		#wordnum{
			margin: 5px 0px 5px 0px;
		}		
		#time{
			margin: 5px 0px 5px 0px;
		}
		#wordlines{
			margin: 5px 0px 5px 0px;
		}
		#result{
			margin: 5px 0px 5px 0px;
			
		}
		#tit{
			text-align: center;
			color: red;
			font-size: 50px;
		}	
		#tb1{
         float: right;
	     margin-top: -200px;
	     border: solid;
         }	
	</style>	
	<body>

	<div id="one"  >
			<p id="two" >
			<font id="tit" ><b>词频统计</b></font><br/><br/>
			选择文件:<input type="file" name="file" id="file"    />
		          <input type="submit" id="butt1" value="上传"  onclick="one()"/><br/><br/>	
			1.输入查找单词:<input type="text" id="word" />
			<input type="submit" id="butt" value="统计"  onclick="two()"/>
			&nbsp;&nbsp;&nbsp;&nbsp;
			耗时:<input type="text" id="time"  size="3" value="${excTime}" />ms<br/><br/>
			2.高频词统计个数:<input type="text" id="wordnum" size="5"/>
			<input type="button" id="butt" value="统计"  onclick="three()"/>
			&nbsp;&nbsp;&nbsp;&nbsp;
			耗时:<input type="text" id="time" size="3" value="${excTime}" />ms<br/><br/>
			3.统计文本行数即字符数<input type="submit" id="wordlines" value="统计行数与字符" onclick="four()" />
			&nbsp;&nbsp;&nbsp;&nbsp;
			耗时:<input type="text" id="time"  size="3"  value="${excTime}"/>ms<br/><br/>			
			4.统计所有单词并将结果存放在result.txt 文件<input type="submit" id="result" value="存放"  onclick="five()"/>			
			&nbsp;&nbsp;&nbsp;&nbsp;
			耗时:<input type="text" id="time" size="3" value="${excTime}" />ms<br/><br/>
			</p >
		</div>
		
				
		 <table border = 1px align = "center" id="tb1" >
		   <!-- 指定单词的查找 -->
			<tr>
			<td>单词</td>
			<td>数量</td>
			</tr>
			<c:forEach items="${map}" var="map" varStatus="st">	
			<tr  >
			<td>${map.key}</td>
			<td>${map.value}</td>
			</tr>
			</c:forEach>
			</table>
			
			
			<table>
			<!--行数以及字符数的统计  -->
			  <tr>
			     <td>行数</td><td>字符数</td><td>单词数</td>
			  </tr>
			 <tr>
			 <c:forEach items="${list}" var="list" varStatus="st">			 
			   <td>${list}</td>			   
			   </c:forEach>
			 </tr>
			</table>


			<table>  
			<c:forEach items="${map1}" var="map1" varStatus="st">
			<tr>
			<!--高频次数的统计  -->
			   <td>${map1}</td> 	
			</tr>
		   </c:forEach>
			</table>
			
	</body>
</html>
	