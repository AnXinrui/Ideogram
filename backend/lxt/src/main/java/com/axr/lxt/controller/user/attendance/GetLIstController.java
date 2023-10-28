package com.axr.lxt.controller.user.attendance;

import com.axr.lxt.pojo.Attendance;
import com.axr.lxt.service.user.attendance.GetListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GetLIstController {
    @Autowired
    private GetListService getListService;
    @GetMapping("/user/attendance/getlist/")
    public List<Attendance> getList() {
        return getListService.getList();
    }
}
