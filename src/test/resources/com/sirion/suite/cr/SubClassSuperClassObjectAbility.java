package test.resources.com.sirion.suite.cr;






public class SubClassSuperClassObjectAbility  {

	
	public void funtionSuper1()
	{
		System.out.println("Function 1 of super class");
	}
	
	
	public static void main(String[] args) 
	
	
	{
		SubClassSuperClassObjectAbility obj1=new SubClassSuperClassObjectAbility();
		SubClass obj2=new SubClass();
		//obj2=new SubClassSuperClassObjectAbility();
		obj1=new SubClass();
		obj2.function1();
		obj2.function2();
		obj2.funtionSuper1();
		obj1.funtionSuper1();
		obj2.funtionSub1();
		//obj1.funtionSub1();
	}

}
	 interface Hello
	{
		
		public void function1();
		public void function2();
		
		
	}



  class SubClass extends SubClassSuperClassObjectAbility implements Hello
{

	public void funtionSub1()
	{
		System.out.println("Function 1 of sub class");
	}
	
	
	public static void main(String str[])
	{
		SubClassSuperClassObjectAbility obj1=new SubClassSuperClassObjectAbility();
		SubClass obj2=new SubClass();
		//obj2=new SubClassSuperClassObjectAbility();
		obj1=new SubClass();
		obj2.funtionSub1();
		//obj1.funtionSub1();
		obj2.function1();
		obj2.function2();
		obj2.funtionSuper1();
		obj1.funtionSuper1();
		obj2.funtionSub1();
	}

	@Override
	public void function1() {
		System.out.println("Implement function 1 of interface");
		
	}

	@Override
	public void function2() {
		System.out.println("Implement function 2 of interface");
		
	}
	
}
	
	

