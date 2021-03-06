package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.School;
import java.util.List;

/**
 * 学校信息Mapper接口
 * 
 * @author ruoyi
 * @date 2019-10-11
 */
public interface SchoolMapper 
{
    /**
     * 查询学校信息
     * 
     * @param scId 学校信息ID
     * @return 学校信息
     */
    public School selectSchoolById(Long scId);

    /**
     * 查询学校信息列表
     * 
     * @param school 学校信息
     * @return 学校信息集合
     */
    public List<School> selectSchoolList(School school);

    /**
     * 新增学校信息
     * 
     * @param school 学校信息
     * @return 结果
     */
    public int insertSchool(School school);

    /**
     * 修改学校信息
     * 
     * @param school 学校信息
     * @return 结果
     */
    public int updateSchool(School school);

    /**
     * 删除学校信息
     * 
     * @param scId 学校信息ID
     * @return 结果
     */
    public int deleteSchoolById(Long scId);

    /**
     * 批量删除学校信息
     * 
     * @param scIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteSchoolByIds(String[] scIds);
}
