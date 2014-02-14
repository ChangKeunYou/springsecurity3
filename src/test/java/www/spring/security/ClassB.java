package www.spring.security;

public class ClassB extends ClassA {
	
	private String message;
	
	public ClassB(){
		
	}
	
	public ClassB(String message){
		this.message = message;
	}
	
	@Override
	public void print(){
		System.out.println("ClassB print method");
	}

	@Override
	public void test() {
		// TODO Auto-generated method stub
		System.out.println(this.message + "ClassB test Call");
	}
	
	
	
	
	
}
