package test.resources.com.sirion.suite.changeRequest;

import java.util.Arrays;
import java.util.List;

public class ListClass


{

		
			public static void main(String str[])
					{
								
								List<Integer> values=Arrays.asList(1,2,3,4,5,6);
									
									
								try
								{
									for(int i:values)
									{
								System.out.println(""	+values.get(i));
								}
								}
								catch(Exception e)
								{
									System.out.println(e);
								}
				
				
					}
			
	
	
}
