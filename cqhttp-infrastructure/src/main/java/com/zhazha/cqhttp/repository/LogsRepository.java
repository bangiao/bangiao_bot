package com.zhazha.cqhttp.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhazha.cqhttp.bean.Logs;
import com.zhazha.cqhttp.mapper.LogsMapper;
import org.springframework.stereotype.Service;
@Service
public class LogsRepository extends ServiceImpl<LogsMapper, Logs> {

}
