package com.axr.lxt.service.impl.user.attendance;

import com.axr.lxt.mapper.AttendanceMapper;
import com.axr.lxt.pojo.Attendance;
import com.axr.lxt.pojo.User;
import com.axr.lxt.service.impl.utils.UserDetailsImpl;
import com.axr.lxt.service.user.attendance.RemoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RemoveServiceImpl implements RemoveService {
    @Autowired
    AttendanceMapper attendanceMapper;

    @Override
    public Map<String, String> remove(Map<String, String> data) {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();
//        System.out.printf("123");
//        System.out.println(data);
        int attendance_id = Integer.parseInt(data.get("attendance_id"));

        Attendance attendance = attendanceMapper.selectById(attendance_id);

        Map<String, String> map = new HashMap<>();

        if (attendance == null) {
            map.put("error_message", "打卡不存在或已删除");
            return map;
        }

        if (!attendance.getUserId().equals(user.getId())) {
            map.put("error_message", "没有权限删除该打卡");
            return map;
        }

        attendanceMapper.deleteById(attendance_id);

        map.put("error_message", "success");
        return map;
    }



}
