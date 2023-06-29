package com.zhazha.cqhttp.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhazha.cqhttp.bean.Words;
import com.zhazha.cqhttp.mapper.WordsMapper;
import org.springframework.stereotype.Repository;

@Repository
public class WordsRepository extends ServiceImpl<WordsMapper, Words> {

}
