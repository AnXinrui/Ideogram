package com.axr.lxt.service.impl.user.attendance;

import com.axr.lxt.mapper.AttendanceMapper;
import com.axr.lxt.pojo.Attendance;
import com.axr.lxt.pojo.User;
import com.axr.lxt.service.impl.utils.UserDetailsImpl;
import com.axr.lxt.service.user.attendance.AddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class AddServiceImpl implements AddService {

    @Autowired
    private AttendanceMapper attendanceMapper;

    @Override
    public Map<String, String> add(Map<String, String> data) {
        // 取出当前user
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

        String title = data.get("title");
        String content = data.get("content");
        String description = data.get("description");

//        System.out.println(title);
//        System.out.println(content);
//        System.out.println(description);

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
            map.put("error_message", "Bot描述的长度不能大于300");
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

        Date now = new Date();
        Attendance attendance = new Attendance(null, user.getId(), title,  description, content,1500, now, now);
//        Bot bot = new Bot(null, user.getId(), title,  content,description,1500, now, now);


        attendanceMapper.insert(attendance);
        map.put("error_message", "success");

        return map;
    }
}
