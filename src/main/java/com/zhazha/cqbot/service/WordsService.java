package com.zhazha.cqbot.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhazha.cqbot.bean.Words;
import com.zhazha.cqbot.mapper.WordsMapper;
import org.springframework.stereotype.Service;
@Service
public class WordsService extends ServiceImpl<WordsMapper, Words> {

}
