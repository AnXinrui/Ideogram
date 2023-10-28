package com.axr.lxt.service.impl.user.attendance;

import com.axr.lxt.mapper.AttendanceMapper;
import com.axr.lxt.pojo.Attendance;
import com.axr.lxt.pojo.User;
import com.axr.lxt.service.impl.utils.UserDetailsImpl;
import com.axr.lxt.service.user.attendance.GetListService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetListServiceImpl implements GetListService {
    @Autowired
    private AttendanceMapper attendanceMapper;

    @Override
    public List<Attendance> getList() {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

        QueryWrapper<Attendance> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", user.getId());
        return  attendanceMapper.selectList(queryWrapper);
    }
}
