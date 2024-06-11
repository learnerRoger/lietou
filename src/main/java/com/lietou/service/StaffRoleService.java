package com.lietou.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lietou.enums.BusinessStatusEnum;
import com.lietou.dto.Response;
import com.lietou.dto.ResponseDTO;
import com.lietou.entity.StaffRole;
import com.lietou.exception.ServiceException;
import com.lietou.mapper.StaffRoleMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author lietou
 * @Date 2023/3/1
 * @Version 1.0
 */

@Service
public class StaffRoleService extends ServiceImpl<StaffRoleMapper, StaffRole> {

    @Transactional
    public ResponseDTO setRole(Integer staffId, List<Integer> roleIds) {
        QueryWrapper<StaffRole> wrapper = new QueryWrapper<>();
        wrapper.eq("staff_id",staffId);
        List<StaffRole> list = list(wrapper);
        // 先禁用不需要的角色
        for (StaffRole staffRole : list) {
            if (roleIds.contains(staffRole.getRoleId())){
                staffRole.setStatus(1);
            }else{
                staffRole.setStatus(0);
            }
            updateById(staffRole);
        }
        // 根据条件添加或更新
        for (Integer roleId : roleIds) {
            StaffRole staffRole = new StaffRole();
            staffRole.setStaffId(staffId);
            staffRole.setRoleId(roleId);
            staffRole.setStatus(1);
            QueryWrapper<StaffRole> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("staff_id",staffId).eq("role_id",roleId);
            if(!saveOrUpdate(staffRole,queryWrapper)){
                throw new ServiceException(BusinessStatusEnum.ERROR);
            }
        }
        return Response.success();
    }

    public ResponseDTO getRole(Integer staffId) {
        List<StaffRole> list = list(new QueryWrapper<StaffRole>().eq("staff_id",staffId).eq("status",1));
        return Response.success(list);
    }
}
