package chapter_01;

public class QuickPow {

	 //快速乘法 ，求a*b
	public static int qmul(int a,int b){// 根据数据范围可选择long long 
		int ans = 0;
	    while(b > 0){
	    	if((b & 1) == 1)
	    		ans += a;//按位与完成位数为1的判断
	    	b >>= 1;
	    	a <<= 1;//位运算代替/2和*2
			}
	    return ans;
	}
	
	//快速幂，求a^b
	public static int qpow(int a, int b){
		if(a == 0)
			return 0;
		int ans = 1;
		while(b > 0){
			if((b & 1) == 1)
				ans *= a;
			b >>= 1;
	    	a *= a;
		}
		return ans;
	}
	
	public static void main(String[] args) {
		System.out.println(qmul(4,15));
	}

}
