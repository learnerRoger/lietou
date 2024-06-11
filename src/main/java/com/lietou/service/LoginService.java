package com.lietou.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lietou.dto.Response;
import com.lietou.dto.ResponseDTO;
import com.lietou.entity.Staff;
import com.lietou.enums.BusinessStatusEnum;
import com.lietou.mapper.StaffMapper;
import com.lietou.util.JWTUtil;
import com.lietou.util.MD5Util;
import com.lietou.vo.StaffDeptVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author : lietou
 * @Date : 2023/1/30
 */

@Service
public class LoginService extends ServiceImpl<StaffMapper, Staff> {

    @Resource
    private StaffMapper staffMapper;

    public ResponseDTO login(Staff staff) {
        String password = MD5Util.MD55(staff.getPassword());
        StaffDeptVO staffDeptVO = this.staffMapper.findStaffInfo(staff.getCode(), password);
        if (staffDeptVO != null) {
            // 验证用户状态
            if (staffDeptVO.getStatus() == 1) {
                String token = JWTUtil.generateToken(staffDeptVO.getId(),password);
                return Response.success(staffDeptVO, token); // 返回员工信息和token
            }
            return Response.error(BusinessStatusEnum.STAFF_STATUS_ERROR);
        }
        return Response.error("用户名或密码错误!");
    }
}
