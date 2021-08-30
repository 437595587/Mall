package com.ruoyi.coupon.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.coupon.mapper.HomeSubjectMapper;
import com.ruoyi.coupon.domain.HomeSubject;
import com.ruoyi.coupon.service.IHomeSubjectService;

/**
 * 首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】Service业务层处理
 * 
 * @author xuxing
 * @date 2021-08-23
 */
@Service
public class HomeSubjectServiceImpl implements IHomeSubjectService 
{
    @Autowired
    private HomeSubjectMapper homeSubjectMapper;

    /**
     * 查询首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
     * 
     * @param id 首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】主键
     * @return 首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
     */
    @Override
    public HomeSubject selectHomeSubjectById(Long id)
    {
        return homeSubjectMapper.selectHomeSubjectById(id);
    }

    /**
     * 查询首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】列表
     * 
     * @param homeSubject 首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
     * @return 首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
     */
    @Override
    public List<HomeSubject> selectHomeSubjectList(HomeSubject homeSubject)
    {
        return homeSubjectMapper.selectHomeSubjectList(homeSubject);
    }

    /**
     * 新增首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
     * 
     * @param homeSubject 首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
     * @return 结果
     */
    @Override
    public int insertHomeSubject(HomeSubject homeSubject)
    {
        return homeSubjectMapper.insertHomeSubject(homeSubject);
    }

    /**
     * 修改首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
     * 
     * @param homeSubject 首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
     * @return 结果
     */
    @Override
    public int updateHomeSubject(HomeSubject homeSubject)
    {
        return homeSubjectMapper.updateHomeSubject(homeSubject);
    }

    /**
     * 批量删除首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
     * 
     * @param ids 需要删除的首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】主键
     * @return 结果
     */
    @Override
    public int deleteHomeSubjectByIds(Long[] ids)
    {
        return homeSubjectMapper.deleteHomeSubjectByIds(ids);
    }

    /**
     * 删除首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】信息
     * 
     * @param id 首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】主键
     * @return 结果
     */
    @Override
    public int deleteHomeSubjectById(Long id)
    {
        return homeSubjectMapper.deleteHomeSubjectById(id);
    }
}
