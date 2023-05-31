package com.zhazha.cqbot.utils;

import cn.hutool.core.util.StrUtil;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CQCodeUtils {
	
	/**
	 * 去掉cq表达式
	 *
	 * @param cqCode
	 * @return
	 */
	public static @Nullable String getText(String cqCode) {
		if (StrUtil.isBlank(cqCode)) {
			return null;
		}
		String pattern = "(\\[[^\\]]*\\])|(\\r\\n)|(\\n)";
		return cqCode.replaceAll(pattern, "");
	}
	
	/**
	 * 拿到 cqCode code 中的汉字
	 *
	 * @param cqCode
	 * @return
	 */
	public static List<String> getChinese(String cqCode) {
		ArrayList<String> stringArrayList = new ArrayList<>();
		Pattern chinesePattern = Pattern.compile("[\u4e00-\u9fa5]+");
		Matcher chineseMatcher = chinesePattern.matcher(cqCode);
		while (chineseMatcher.find()) {
			String chinese = chineseMatcher.group();
			stringArrayList.add(chinese);
		}
		return stringArrayList;
	}
	
	/**
	 * 获得 cq code 中的 at 的所有人
	 * <p>
	 * 注意, 已经排除了 all 的情况
	 *
	 * @param cqCode
	 * @return
	 */
	public static List<String> getAt(String cqCode) {
		ArrayList<String> stringArrayList = new ArrayList<>();
		Pattern qqPattern = Pattern.compile("(?<=qq=)(\\d+|all)");
		Matcher qqMatcher = qqPattern.matcher(cqCode);
		while (qqMatcher.find()) {
			String qq = qqMatcher.group();
			if (StrUtil.equalsIgnoreCase(qq, "all")) {
				continue;
			}
			stringArrayList.add(qq);
		}
		return stringArrayList;
	}
	
}