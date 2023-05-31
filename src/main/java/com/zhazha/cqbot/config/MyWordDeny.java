package com.zhazha.cqbot.config;

import cn.hutool.extra.spring.SpringUtil;
import com.github.houbb.sensitive.word.api.IWordDeny;
import com.zhazha.cqbot.bean.Words;
import com.zhazha.cqbot.service.WordsService;

import java.util.List;
import java.util.stream.Collectors;

public class MyWordDeny implements IWordDeny {
	@Override
	public List<String> deny() {
		WordsService wordsService = SpringUtil.getBean(WordsService.class);
		return wordsService.list().stream().map(Words::getWord)
				.collect(Collectors.toList());
	}
}
