package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SchoolMapper;
import com.ruoyi.system.domain.School;
import com.ruoyi.system.service.ISchoolService;
import com.ruoyi.common.core.text.Convert;

/**
 * 学校信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2019-10-11
 */
@Service
public class SchoolServiceImpl implements ISchoolService 
{
    @Autowired
    private SchoolMapper schoolMapper;

    /**
     * 查询学校信息
     * 
     * @param scId 学校信息ID
     * @return 学校信息
     */
    @Override
    public School selectSchoolById(Long scId)
    {
        return schoolMapper.selectSchoolById(scId);
    }

    /**
     * 查询学校信息列表
     * 
     * @param school 学校信息
     * @return 学校信息
     */
    @Override
    public List<School> selectSchoolList(School school)
    {
        return schoolMapper.selectSchoolList(school);
    }

    /**
     * 新增学校信息
     * 
     * @param school 学校信息
     * @return 结果
     */
    @Override
    public int insertSchool(School school)
    {
        return schoolMapper.insertSchool(school);
    }

    /**
     * 修改学校信息
     * 
     * @param school 学校信息
     * @return 结果
     */
    @Override
    public int updateSchool(School school)
    {
        return schoolMapper.updateSchool(school);
    }

    /**
     * 删除学校信息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSchoolByIds(String ids)
    {
        return schoolMapper.deleteSchoolByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除学校信息信息
     * 
     * @param scId 学校信息ID
     * @return 结果
     */
    @Override
    public int deleteSchoolById(Long scId)
    {
        return schoolMapper.deleteSchoolById(scId);
    }
}
