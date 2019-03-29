package Practice;

public class Fibonaci {

	public static void main(String[] args) {
		int j =1,k=0;
		System.out.println(0);
		for(int i =0;i<100;){
			
			i=k+j;
			j=k;
			k=i;
			if(i<100)
			System.out.println(i);		
		}

	}

}
