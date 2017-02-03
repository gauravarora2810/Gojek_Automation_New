package test.resources.com.sirion.suite.changeRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class CollectionClass {
	
	static String str;

	public static void main(String[] args) 
	
	
	{
		
		
		System.out.println("Please Input Your Value");
//		Scanner sc=new Scanner(System.in);
//		String str=sc.next();
//		System.out.println(str);
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
			 try {
				str=br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 System.out.println(str);
		if (str.equalsIgnoreCase("Gaurav"))
		{
			throw new ArithmeticException();
		}
		
		List<Integer> obj=new ArrayList();		
		Random r=new Random();
				for(int i=1; i<=100; i++)
				{
					obj.add(r.nextInt(1000));
				}
				
				Comparator<Integer> obj1=new Comparator<Integer>() {
//					
					@Override
					public int compare(Integer arg0, Integer arg1) {
						// TODO Auto-generated method stub
						int diff=0;
//						
						if(arg0%10>arg1%10)
							diff=1;
//						
						else if(arg0%10<arg1%10)
							diff=-1;
//						
//						
						return diff;
					}
				};
				
//				// insert elements in a structure(i.e collection)
//				obj.add(1);
//				obj.add(2);
//				//obj.add("Gaurav");
//				//obj.add(5.5);
//				
//				
//				// remove elements from a structure(i.e collection)
//				obj.remove(2);
//				
				Collections.sort(obj,obj1);
				
				
				for(Object o:obj)
				{
					System.out.println(o);
				}
						
	}

}
