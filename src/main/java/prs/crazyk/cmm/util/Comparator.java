package prs.crazyk.cmm.util;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
*
* @Class Name : Comparator.java
* @Description : 비교관련 로직 유틸 클래스
* @Modification Information
* @
* @     수정일     	  수정자                 수정내용
* @  ----------   	--------    ---------------------------
* @  2016.01.04     김동한       최초 생성
*
* @author 김동한
* @since 2016.01.04
* @version 1.0
* @see
*/
public class Comparator {
	
	/**
	 * 문자열이 비어있는지 여부를 확인한다.
	 * @param paramString 확인 할 대상 문자열
	 * @return
	 */
	public static boolean isEmpty(String paramString) {
		return (paramString==null || paramString.length()==0);
	}
	
	public static boolean isWhite(String paramString) {
		return (paramString==null || paramString.trim().length()==0);
	}

	/**
	 * List가 비어있는지 여부를 확인한다.
	 * @param items
	 * @return
	 */
	public static boolean isEmpty(List<?> items) {
		return (items==null || items.size()==0);
	}
	
	/**
	 * byte 배열이 비어있는지 여부를 확인한다.
	 * @param bytes
	 * @return
	 */
	public static boolean isEmpty(byte[] bytes) {
		return (bytes==null || bytes.length==0);
	}
	
	/**
	 * 배열이 비어있는지 여부를 확인한다.
	 * @param items
	 * @return
	 */
	public static boolean isEmpty(Object[] items) {
		return (items==null || items.length==0);
	}
	
	/**
	 * long wrapper 클래스 비교
	 * @param value1
	 * @param value2
	 * @return
	 */
	public static boolean isEqual(Long value1, Long value2) {
		long v1 = (value1==null) ? 0L : value1.longValue();
		long v2 = (value2==null) ? 0L : value2.longValue();
		return v1 == v2;
	}
	
	public static boolean isSameBytes(byte[] alpha, byte[] beta) {
		if(alpha.length!=beta.length) return false;
		for(int i=0 ; i<alpha.length ; i++) {
			if(alpha[i]!=beta[i]) return false;
		}
		return true;
	}
	
	public static boolean isEmpty(Map<?, ?> map){
		return (map==null || map.size()==0);
	}


	public static void main(String...args) {
		String voy = "NEMU-001-2023";
		System.out.println(voy.substring(0, voy.lastIndexOf("-")));
		System.out.println(voy.substring(voy.lastIndexOf("-") + 1));
	}
}
