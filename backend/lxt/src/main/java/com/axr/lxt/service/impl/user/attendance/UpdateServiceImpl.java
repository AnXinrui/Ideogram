package com.axr.lxt.service.impl.user.attendance;

import com.axr.lxt.mapper.AttendanceMapper;
import com.axr.lxt.pojo.Attendance;
import com.axr.lxt.pojo.User;
import com.axr.lxt.service.impl.utils.UserDetailsImpl;
import com.axr.lxt.service.user.attendance.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class UpdateServiceImpl implements UpdateService {
    @Autowired
    private AttendanceMapper attendanceMapper;
    @Override
    public Map<String, String> update(Map<String, String> data) {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

        int attendance_id = Integer.parseInt(data.get("attendance_id"));

        String title = data.get("title");
        String content = data.get("content");
        String description = data.get("description");

        Map<String, String> map = new HashMap<>();
//        System.out.println(content);
        if (title == null || title.length() == 0) {
            System.out.println(title);
            map.put("error_message", "标题不能为空");
            return map;
        }

        if (title.length() > 100) {
            map.put("error_message", "标题长度不能大于100");
            return map;
        }

        if (description == null || description.length() == 0) {
            description = "这个用户很懒，什么也没留下~";
        }

        if (description != null && description.length() > 300) {
            map.put("error_message", "代码描述的长度不能大于300");
            return map;
        }

        if (content == null || content.length() == 0) {
            map.put("error_message", "代码不能为空");
            return map;
        }

        if (content.length() > 10000) {
            map.put("error_message", "代码长度不能超过10000");
            return map;
        }

        Attendance attendance = attendanceMapper.selectById(attendance_id);

        if (attendance == null) {
            map.put("error_message", "打卡不存在或已被删除");
            return map;
        }

        if (!attendance.getUserId().equals(user.getId())) {
            map.put("error_message", "没有权限修改该打卡");
            return map;
        }

        Attendance new_attendance = new Attendance(
                attendance.getId(),
                user.getId(),
                title,
                description,
                content,
                attendance.getRating(),
                attendance.getCreatetime(),
                new Date()
        );

        attendanceMapper.updateById(new_attendance);

        map.put("error_message", "success");

        return map;
    }
}
