package util;
/**
 * 字符串工具类
 * @author lynn
 *
 */
public class StringUtil {

	/**
	 * 判断是否字符串为空
	 * @param str
	 * @return
	 */
	// 用static的话直接访问类名就可以使用，不需要创建实例
	public static boolean isEmpty(String str){
		if(str == null || "".equals(str.trim())) { //trim()跳过前后的空格，防止判断错误
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * 判断字符串是否不为空
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		if(str != null && !"".equals(str.trim())) {
			return true;
		}else {
			return false;
		}
	}
}
