package test.resources.com.sirion.suite.cr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Scanner;

//import com.sun.imageio.plugins.common.InputStreamAdapter;



class TryCatchwithThrowandThrows
{

static int i=0;
static int j=0;
static int k=0;

static int ar[]=new int[4]	;	// Array with size 4

static Scanner sc=null;
static BufferedReader br=null;
public static void main(String str[]) throws  SQLException, IOException


		{
	
// Difference between BufferReader and Scanner Class of Inputting data	
	// BufferReader always takes data in string, Thus user needs to comvert it into what is required but in case of scanner class, 
	// Scanner class has all the methods that allow user to take data in any form (Integer, float, string etc) 
					
					br=new BufferedReader(new InputStreamReader(System.in));
					 sc=new Scanner(System.in);
					
					 System.out.println("Enter value of i");
					 i=Integer.parseInt(br.readLine());
					 System.out.println("Enter value of j");
					j=sc.nextInt();	// Compiler would give an exception on this line and say user to 										   keep this code line into try catch block or surround it by throws keyword
					
					
					
					k=i+j;  // 
					System.out.println("Value of k is"+k );
					try
					{
						if(k<=0)
						{
							throw new ArithmeticException();
						}
			
					}
					
				catch(ArithmeticException e)
					{
					
							System.out.println(" Value of K should not be less than or equal to 0 " + e);
	
					}

//			finally
//				{
//
//						System.out.println("This block must execute ");				
//
//				}


		}


}
