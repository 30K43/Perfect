package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.FileInfoMapper;
import com.ruoyi.system.domain.FileInfo;
import com.ruoyi.system.service.IFileInfoService;
import com.ruoyi.common.core.text.Convert;

/**
 * 文件信息Service业务层处理
 * 
 * @author LH
 * @date 2019-10-13
 */
@Service
public class FileInfoServiceImpl implements IFileInfoService 
{
    @Autowired
    private FileInfoMapper fileInfoMapper;

    /**
     * 查询文件信息
     * 
     * @param fileId 文件信息ID
     * @return 文件信息
     */
    @Override
    public FileInfo selectFileInfoById(Long fileId)
    {
        return fileInfoMapper.selectFileInfoById(fileId);
    }

    /**
     * 查询文件信息列表
     * 
     * @param fileInfo 文件信息
     * @return 文件信息
     */
    @Override
    public List<FileInfo> selectFileInfoList(FileInfo fileInfo)
    {
        return fileInfoMapper.selectFileInfoList(fileInfo);
    }

    /**
     * 新增文件信息
     * 
     * @param fileInfo 文件信息
     * @return 结果
     */
    @Override
    public int insertFileInfo(FileInfo fileInfo)
    {
        return fileInfoMapper.insertFileInfo(fileInfo);
    }

    /**
     * 修改文件信息
     * 
     * @param fileInfo 文件信息
     * @return 结果
     */
    @Override
    public int updateFileInfo(FileInfo fileInfo)
    {
        return fileInfoMapper.updateFileInfo(fileInfo);
    }

    /**
     * 删除文件信息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteFileInfoByIds(String ids)
    {
        return fileInfoMapper.deleteFileInfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除文件信息信息
     * 
     * @param fileId 文件信息ID
     * @return 结果
     */
    @Override
    public int deleteFileInfoById(Long fileId)
    {
        return fileInfoMapper.deleteFileInfoById(fileId);
    }
}
