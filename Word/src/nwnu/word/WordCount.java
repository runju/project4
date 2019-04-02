
package nwnu.word;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * @author ChenRunju
 * 2019年4月1日
 */

public class WordCount {	
	//统计所有单词
	public TreeMap Count(String FileName){
		TreeMap<String,Integer> map= new TreeMap<String,Integer>();
		String line = FileName;
		File file = new File(line);
		InputStreamReader f = null;						
		try {				 
			f = new InputStreamReader(new FileInputStream(file), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		BufferedReader reader = new BufferedReader(f);//读入缓冲流													
			//一次读入一行，读入不为空时 进行单词统计				 				  
		        try {
					while((line=reader.readLine())!=null){
						line = line.toLowerCase();//忽略大小写
						String[] str = line.split("[^a-zA-Z]");//去掉特殊字符						
						for(int i = 0; i<str.length; i++){
							String word = str[i].trim();
							if(map.containsKey(word) &&  word.length() != 0){
								map.put(word, map.get(word)+1);
							}else{
								map.put(word, 1);
							}			        		
					    }
                    }
				} catch (IOException e) {
					e.printStackTrace();
				}		
		return map;
	}
	
		//指定单词的查找	，以及柱形图的显示
		 public String[] one(String s,TreeMap<String,Integer> map){		
			TreeMap<String,Integer> map1 = new TreeMap<String,Integer>();		
			String[] str=s.split(",");		
			for (int i = 0; i < str.length; i++) {
					for(Entry<String,Integer> entry: map.entrySet()) {					
					    if(str[i].equals(entry.getKey())){
					    	map1.put(entry.getKey(),entry.getValue());
					    	System.out.println(entry.getKey()+"-----"+entry.getValue());
					    	break;
					    }
				   }
			  }
			return str;		
		 }	 
		 //高频词的统计整数k
		 public  ArrayList two(TreeMap<String,Integer> map,int k){
			//获取参数k
			   ArrayList<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String,Integer>>(map.entrySet());		        
			   ArrayList<Map.Entry<String,Integer>> list1 = new ArrayList<Map.Entry<String,Integer>>(map.entrySet());		        
			   Collections.sort(list,new Comparator<Map.Entry<String,Integer>>(){  
			       public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {  
			            return o2.getValue() - o1.getValue(); 
			            }  
			        }); 
			     //输出前k个数		   
			      for(int i = 0; i<k; i++){
			    	 //list1.add(list.get(i));			    	  
			    	 System.out.println(list.get(i).getKey()+ ": " +list.get(i).getValue());  
			        } 
			      return list;
			    }	  
		 //写入至result.txt中
		 public void three(TreeMap<String,Integer> map){
				BufferedWriter bw = null;
	    		try {
	    			File file = new File("result.txt");
	    			if (!file.exists()) {
	    				file.createNewFile();
	    			}
	    			FileWriter fw = new FileWriter(file.getAbsoluteFile());
	    			bw = new BufferedWriter(fw);
	    			
	    		} catch (IOException e) {
	    			e.printStackTrace();
	    		}			
	            Iterator<String> it1 = map.keySet().iterator();
	            while(it1.hasNext())
	            {
	            	String key = (String) it1.next();
		        	Integer value = map.get(key);	        	
		        	try {
						bw.write(key+"="+value+"\n");
					} catch (IOException e) {
						e.printStackTrace();
					}
	            }
	             System.out.println("存放成功！！！");
		    }	 	 
		   //统计文本行数以及字符数
		   @SuppressWarnings("null")
		public List statistics(String FileName) throws IOException{	
			   List<String> list=null;
			  int charNum= 0 ;//字符数
			  int wordsNum= 0;//数字数
			  int lineNum = 0;//行数
			  //以流的形式读入文件
			  InputStreamReader isr = new InputStreamReader(new FileInputStream(FileName));
			  BufferedReader br = new BufferedReader(isr);
			  while( br.read()!= -1){
			  String s = br.readLine();
			  charNum+=s.length();
			  wordsNum +=s.split(" ").length;
			  lineNum ++;
			  }
			  isr.close();//关闭
			  System.out.println("字符数:"+charNum+"\n单词数:"+wordsNum+"\n行数:"+lineNum);
			  list.add("charNum");
			  list.add("wordsNum");
			  list.add("lineNum");		
			  for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i));
			}
	
			  return list;
			  }  
	     }


