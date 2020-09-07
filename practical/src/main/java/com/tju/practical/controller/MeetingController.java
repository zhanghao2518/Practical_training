package com.tju.practical.controller;

import com.tju.practical.entity.Meeting;
import com.tju.practical.service.MeetingService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Meeting)表控制层
 *
 * @author makejava
 * @since 2020-09-07 16:16:01
 */
@RestController
@RequestMapping("meeting")
public class MeetingController {
    /**
     * 服务对象
     */
    @Resource
    private MeetingService meetingService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Meeting selectOne(Integer id) {
        return this.meetingService.queryById(id);
    }

}