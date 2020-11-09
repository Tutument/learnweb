package com.cipherresource.project.system.signcert.mapper;

import com.cipherresource.project.system.signcert.domain.TSigncert;
import java.util.List;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author gwei
 * @date 2020-01-16
 */
public interface TSigncertMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public TSigncert selectTSigncertById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param tSigncert 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<TSigncert> selectTSigncertList(TSigncert tSigncert);

    /**
     * 新增【请填写功能名称】
     * 
     * @param tSigncert 【请填写功能名称】
     * @return 结果
     */
    public int insertTSigncert(TSigncert tSigncert);

    /**
     * 修改【请填写功能名称】
     * 
     * @param tSigncert 【请填写功能名称】
     * @return 结果
     */
    public int updateTSigncert(TSigncert tSigncert);

    /**
     * 删除【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteTSigncertById(Long id);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTSigncertByIds(String[] ids);
}
