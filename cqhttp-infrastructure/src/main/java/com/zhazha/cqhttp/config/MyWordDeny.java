package com.zhazha.cqhttp.config;

import cn.hutool.extra.spring.SpringUtil;
import com.github.houbb.sensitive.word.api.IWordDeny;
import com.zhazha.cqhttp.bean.Words;
import com.zhazha.cqhttp.repository.WordsRepository;

import java.util.List;
import java.util.stream.Collectors;

public class MyWordDeny implements IWordDeny {
	@Override
	public List<String> deny() {
		WordsRepository wordsRepository = SpringUtil.getBean(WordsRepository.class);
		return wordsRepository.list().stream().map(Words::getWord)
				.collect(Collectors.toList());
	}
}
