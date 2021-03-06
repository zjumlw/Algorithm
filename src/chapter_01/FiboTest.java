package chapter_01;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class Fibonacci{
	
	public static long F(int n){
		long result = 0;
		if(n == 0){
			return 0;
		}
		if(n == 1 || n ==2){
			return 1;
		}else{
			long[][] mat = {{1,1},{1,0}};
			//方法1------
//			long[][] matrix = getMaxtrix(mat, n % 2 == 1 ? n -1 : n -2);
//			if(n % 2 == 1) {
//				result = matrix[0][0];
//			}
//			else{
//				result = matrix[0][0] + matrix[1][0];
//			}
			//方法1------
			
			//方法2------
			long[][] matrix = getMaxtrix(mat, n - 1);
			result = matrix[0][0];
			//方法2------
			
		}
		return result;
	}

	/*
	 * 利用矩阵快速幂的方法
	 */
	public static long[][] getMaxtrix(long[][] mat, int n) {
		long[][] retMat = new long[mat.length][mat[0].length];
		for(int i = 0; i < retMat.length; i++)
			retMat[i][i] = 1;	//得到一个单位矩阵
		
		long[][] tmp = mat;
		//方法1---
//		for(; n != 0; n >>= 1){	//快速乘法,n >>= 1就是n=n/2
//			if((n & 1) == 1){	//n二进制表示时，最低位是不是1
//				retMat = matMulti(retMat, tmp);	 
//			}
//				tmp = matMulti(tmp, tmp);
//		}
		//方法1---
		
		//方法2---
		while(n > 0){
			if((n & 1) == 1)
				retMat = matMulti(retMat, tmp);
			n >>= 1;
			tmp = matMulti(tmp, tmp);
			
		}
		//方法2---
		return retMat;
	}
	
	private static long[][] matMulti(long[][] retMat, long[][] mat){
		long[][] retmat = new long[retMat.length][mat[0].length];
		for(int i = 0; i < retmat.length; i++)
			for(int j = 0; j < retmat[0].length; j++)
				for(int k = 0; k < retMat[0].length; k++)
					retmat[i][j] += retMat[i][k] * mat[k][j];
		return retmat;
	}
}

public class FiboTest {
	private	static Map cache = new ConcurrentHashMap<>(); 
	
	public static void main(String[] args) {
//		System.out.println(fibonacciArray(100));
		System.out.println(fibonacciQuick(30));
//		System.out.println(fibonacciHashMap(20));
		System.out.println(Fibonacci.F(30));
		
	}

    public static long fibonacciHashMap(int n) {  
        if (n == 0 || n == 1) return n;  
        Long result =  (Long)cache.get(n);  
        if (result == null) {  
            synchronized (cache) {  
                result = (Long) cache.get(n);  
                if (result == null) {  
//                    System.out.println("calculating fibonacciHashMap(" + n + ")");  
                    result = fibonacciHashMap(n - 2) + fibonacciHashMap(n - 1);  
                    cache.put(n, result);  
                }  
            }  
        }  
        return result;  
    }
    
	public static long fibonacciBasic(int n){
		if(n == 0) return 0;
		if(n == 1) return 1;
		return fibonacciBasic(n - 1) + fibonacciBasic(n - 2);
	}
	
	public static long fibonacciQuick(int n){
		long f = 0;
		long g = 1;
		long result = 0;
		for(int i = 0; i <= n; i++){
			result = f;
			f = f + g;
			g = f - g;
		}
		return result;
	}
	public static long fibonacciArray(int n){
		long[] fibonacci = new long[1000];
		long result = 0;
		fibonacci[0] = 0;
		fibonacci[1] = 1;
		fibonacci[2] = 1;
		for(int i =3; i <= n; i++){
			fibonacci[i] = fibonacci[i - 1] + fibonacci[i -2];
		}
		result = fibonacci[n];
		return result;
	}
}
