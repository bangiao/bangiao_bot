package com.zhazha.cqhttp.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhazha.cqhttp.bean.Group;
import com.zhazha.cqhttp.mapper.GroupMapper;
import org.springframework.stereotype.Repository;

@Repository
public class GroupRepository extends ServiceImpl<GroupMapper, Group> {

}
