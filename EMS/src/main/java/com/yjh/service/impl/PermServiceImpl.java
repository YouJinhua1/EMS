package com.yjh.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.yjh.domain.SysPerm;
import com.yjh.domain.SysUser;
import com.yjh.exception.ParamException;
import com.yjh.mapper.SysPermMapper;
import com.yjh.service.PermService;
import com.yjh.utils.IpUtil;
import com.yjh.utils.LevelUtils;

@Service
public class PermServiceImpl implements PermService {

	@Resource
	SysPermMapper permMapper;

	@Override
	public List<SysPerm> getPermByPermIdList(List<Integer> permIdList) {
		return permMapper.getPermByPermIdList(permIdList);
	}

	@Override
	public List<SysPerm> queryPermList() {
		return permMapper.getAllPermList();
	}

	@Override
	@Transactional
	public int savePerm(SysPerm perm, HttpServletRequest request) {
		if (checkExist(perm.getParentId(), perm.getName(), perm.getId())) {
			return -2;
		}
		// 取出父级权限
		if (perm.getParentId() != 0) {
			SysPerm parentPerm = permMapper.selectByPrimaryKey(perm.getParentId());
			if (parentPerm != null) {
				perm.setLevel(LevelUtils.calculateLevel(parentPerm.getLevel(), parentPerm.getId()));
				SysUser user = (SysUser) request.getSession().getAttribute("user");
				perm.setOperator(user.getName());
				perm.setOperateIp(IpUtil.getRemoteIp(request));
				perm.setOperateTime(new Date());
				return permMapper.insertSelective(perm);
			} else {
				return -1;
			}
		} else {
			perm.setLevel("0");
			SysUser user = (SysUser) request.getSession().getAttribute("user");
			perm.setOperator(user.getName());
			perm.setOperateIp(IpUtil.getRemoteIp(request));
			perm.setOperateTime(new Date());
			return permMapper.insertSelective(perm);
		}

	}

	@Transactional
	public int ChangePerm(SysPerm perm, HttpServletRequest request) {
		// 查出原来的数据
		SysPerm oldPerm = permMapper.selectByPrimaryKey(perm.getId());
		// 待更新的资源不存在
		if (oldPerm == null)
			return -1;// 待更新的资源不存在
		if (perm.getParentId() != 0) {
			if (oldPerm.getParentId() != perm.getParentId()) {// 更新后层级改变了
				// 新的父资源
				SysPerm newParentPerm = permMapper.selectByPrimaryKey(perm.getParentId());
				if (newParentPerm != null) {
					// 判断新的父资源下是否有同名称的
					boolean flag=checkExist(perm.getParentId(), perm.getName(), perm.getId());
	
					if (flag) {
						return -2;// 新的父资源下有同名称的资源
					} else {// 新的父资源下没有同名称的
							// 查出更新前它的子资源
						List<SysPerm> subPermList = permMapper
								.getChildPermListByLevel(oldPerm.getLevel() + "." + oldPerm.getId());
						// 判断它是否有子资源
						if (subPermList == null||!(subPermList.size()>0)) {
							// 没有子资源，直接更新就行
							SysUser user = (SysUser) request.getSession().getAttribute("user");
							perm.setOperator(user.getName());
							perm.setOperateIp(IpUtil.getRemoteIp(request));
							perm.setOperateTime(new Date());
							perm.setLevel(LevelUtils.calculateLevel(newParentPerm.getLevel(), newParentPerm.getId()));
							int x = permMapper.updateByPrimaryKeySelective(perm);
							return x;
						} else {
							SysUser user = (SysUser) request.getSession().getAttribute("user");
							perm.setOperator(user.getName());
							perm.setOperateIp(IpUtil.getRemoteIp(request));
							perm.setOperateTime(new Date());
							perm.setLevel(LevelUtils.calculateLevel(newParentPerm.getLevel(), newParentPerm.getId()));
							// 计算子资源的新前缀
							String newSubPermLevelPrex = LevelUtils.calculateLevel(perm.getLevel(), perm.getId());
							for (SysPerm subPerm : subPermList) {
								// 取出原来子资源的层级
								String oldSubPermLevel = subPerm.getLevel();
								// 计算出待更新资源的子资源的前缀
								String oldSubPermLevelPrefix = LevelUtils.calculateLevel(oldPerm.getLevel(),
										oldPerm.getId());
								;
								// 计算子资源的level的后缀
								String oldSubPermLevelSuffix = oldSubPermLevel
										.substring(oldSubPermLevelPrefix.length());
								String newSubPermLevel = newSubPermLevelPrex + oldSubPermLevelSuffix;
								subPerm.setLevel(newSubPermLevel);
								// permMapper.updateByPrimaryKeySelective(subPerm);
							}
							// 更新所有子部门的level
							permMapper.batchUpdateLevel(subPermList);
							int x = permMapper.updateByPrimaryKeySelective(perm);
							return x;
						}
					}
				} else {
					return -3;// 新的父资源不存在
				}
			} else {// 更新后层级没改变
					// 直接更新就行了
				SysUser user = (SysUser) request.getSession().getAttribute("user");
				perm.setOperator(user.getName());
				perm.setOperateIp(IpUtil.getRemoteIp(request));
				perm.setOperateTime(new Date());
				int x = permMapper.updateByPrimaryKeySelective(perm);
				return x;
			}
		} else {
			if (oldPerm.getParentId() != perm.getParentId()) {// 更新后层级改变了
				// 判断新的父资源下是否有同名称的
				if (checkExist(perm.getParentId(), perm.getName(), perm.getId()))
					return -2;// 新的父资源下有同名称的资源
				// 查出更新前它的子资源
				List<SysPerm> subPermList = permMapper
						.getChildPermListByLevel(oldPerm.getLevel() + "." + oldPerm.getId());
				// 判断它是否有子资源
				if (subPermList != null&&subPermList.size()>0) {
					SysUser user = (SysUser) request.getSession().getAttribute("user");
					perm.setOperator(user.getName());
					perm.setOperateIp(IpUtil.getRemoteIp(request));
					perm.setOperateTime(new Date());
					perm.setLevel("0");
					// 计算子资源的新前缀
					String newSubPermLevelPrex = LevelUtils.calculateLevel(perm.getLevel(), perm.getId());
					for (SysPerm subPerm : subPermList) {
						// 取出原来子资源的层级
						String oldSubPermLevel = subPerm.getLevel();
						// 计算出待更新资源的子资源的前缀
						String oldSubPermLevelPrefix = LevelUtils.calculateLevel(oldPerm.getLevel(), oldPerm.getId());
						;
						// 计算子资源的level的后缀
						String oldSubPermLevelSuffix = oldSubPermLevel.substring(oldSubPermLevelPrefix.length());
						String newSubPermLevel = newSubPermLevelPrex + oldSubPermLevelSuffix;
						subPerm.setLevel(newSubPermLevel);
						// permMapper.updateByPrimaryKeySelective(subPerm);
					}
					// 更新所有子部门的level
					permMapper.batchUpdateLevel(subPermList);
					int x = permMapper.updateByPrimaryKeySelective(perm);
					return x;
				} else {
					
					// 没有子资源，直接更新就行
					SysUser user = (SysUser) request.getSession().getAttribute("user");
					perm.setOperator(user.getName());
					perm.setOperateIp(IpUtil.getRemoteIp(request));
					perm.setOperateTime(new Date());
					perm.setLevel("0");
					int x = permMapper.updateByPrimaryKeySelective(perm);
					return x;
				}

			} else {// 更新后层级没改变
					// 直接更新就行了
				SysUser user = (SysUser) request.getSession().getAttribute("user");
				perm.setOperator(user.getName());
				perm.setOperateIp(IpUtil.getRemoteIp(request));
				perm.setOperateTime(new Date());
				int x = permMapper.updateByPrimaryKeySelective(perm);
				return x;
			}
		}
	}

	/**
	 * 
	 */
	@Override
	@Transactional
	public int deletePerm(int id, HttpServletRequest request) {
		int x = permMapper.selectByParentId(id);
		if (x > 0) {
			return -1;
		} else {
			x = permMapper.deleteByPrimaryKey(id);
			if (x > 0) {
				return 1;
			} else {
				return x;
			}
		}
	}

	// 判断是否重复了即：在同一层级下不能出现名称相同的资源。
	private boolean checkExist(Integer parentId, String permName, Integer id) {
		int x=permMapper.countByNameAndParentId(parentId, permName, id);
		// 如果大于零，说明存在
		return x > 0;
	}

}
