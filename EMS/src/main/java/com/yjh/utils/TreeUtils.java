 package com.yjh.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.yjh.adpter.PermAdpter;
import com.yjh.adpter.PermAuthAdpter;
import com.yjh.domain.SysPerm;
import com.yjh.domain.SysRole;

/**
 * 
 * @ClassName: TreeUtils
 * @Description: 将集合中具有父子关系的对象，转成一颗树
 * @author YJH
 * @date 2018年11月19日
 *
 */
public class TreeUtils {
	/**
	 * 
	 * @Title: getTreeList
	 * @Description: TODO
	 * @param @param mList 一个装有父子关系的对象的集合
	 * @param @return
	 * @return List<PermAdpter> 返回一棵树
	 * @throws
	 */
	public static List<PermAdpter> getTree(List<SysPerm> permList) {
		//将permList转成permAdpterList
		List<PermAdpter> permAdpterList=getPermAdpter(permList);
		// 创建一个新的集合容器
		List<PermAdpter> tree = new ArrayList<PermAdpter>();
		// 遍历原集合
		for (PermAdpter node1 : permAdpterList) {
			// 定义一个标记
			boolean mark = false;
			// 遍历原集合，寻找当前节点的父节点，将该节点存入父节点中
			for (PermAdpter node2 : permAdpterList) {
				if (node1.getParentId() != 0
						&& node1.getParentId().equals(node2.getId())) {
					mark = true;
					if (node2.getNodes() == null)
						node2.setNodes(new ArrayList<PermAdpter>());
					node2.getNodes().add(node1);
					break;
				}
			}
			// 最后只有父节点才不会执行Mark=true，所以该节点就是根节点
			if (!mark) {
				tree.add(node1);
			}
		}
		return tree;
	}
	
	//perm-table专用的方法
	public static List<PermAdpter> getPermTree(List<SysPerm> permList) {
		//将permList转成permAdpterList
		List<PermAdpter> permAdpterList=getPermAdpter(permList);
		// 创建一个新的集合容器
		List<PermAdpter> tree = new ArrayList<PermAdpter>();
		// 遍历原集合
		for (PermAdpter node1 : permAdpterList) {
			// 定义一个标记
			boolean mark = false;
			// 遍历原集合，寻找当前节点的父节点，将该节点存入父节点中
			for (PermAdpter node2 : permAdpterList) {
				if (node1.getParentId() != -1
						&& node1.getParentId().equals(node2.getId())) {
					mark = true;
					if (node2.getNodes() == null)
						node2.setNodes(new ArrayList<PermAdpter>());
					node2.getNodes().add(node1);
					break;
				}
			}
			// 最后只有父节点才不会执行Mark=true，所以该节点就是根节点
			if (!mark) {
				tree.add(node1);
			}
		}
		return tree;
	}
	
	
	
	
	public static List<PermAuthAdpter> getPermAuthTree(List<SysPerm> userPermList, List<SysPerm> rolePermList,
			List<SysPerm> allPermList) {
		List<PermAuthAdpter> allPermAuthAdpterList=getPermAuthAdpter(allPermList);
        Set<Integer> userPermIdSet = userPermList.stream().map(sysPerm -> sysPerm.getId()).collect(Collectors.toSet());
        Set<Integer> rolePermIdSet = rolePermList.stream().map(sysPerm -> sysPerm.getId()).collect(Collectors.toSet());
		for(int i=0;i<allPermList.size();i++) {
			SysPerm perm=allPermList.get(i);
			if(userPermIdSet.contains(perm.getId())) {
				allPermAuthAdpterList.get(i).setHasPerm(true);
			}
			if(rolePermIdSet.contains(perm.getId())) {
				allPermAuthAdpterList.get(i).setChecked(true);
			}
		}
		return allPermAuthAdpterList;
	}
	
	
	public static List<PermAuthAdpter> getPermAuthTree(List<SysPerm> userPermList, 
			List<SysPerm> allPermList) {
		List<PermAuthAdpter> allPermAuthAdpterList=getPermAuthAdpter(allPermList);
        Set<Integer> userPermIdSet = userPermList.stream().map(sysPerm -> sysPerm.getId()).collect(Collectors.toSet());
		for(int i=0;i<allPermList.size();i++) {
			SysPerm perm=allPermList.get(i);
			if(userPermIdSet.contains(perm.getId())) {
				allPermAuthAdpterList.get(i).setHasPerm(true);
			}
		}
		return allPermAuthAdpterList;
		
		
	}

	/**
	 * 
	 * @Title: getPreorderTraversalList
	 * @Description: 先序遍历树,得到先序遍历顺序的集合
	 * @param @param mList
	 * @param @return
	 * @return List<PermAdpter>
	 * @throws
	 */
	public static List<PermAdpter> getPreorderTraversalList(
			List<PermAdpter> mList) {
		List<PermAdpter> treeList = new ArrayList<PermAdpter>();
		// 去掉根节点，集合中不需要根节点
		PermAdpter p = mList.get(0);
		addChildrenNodeToList(p, treeList);
		return treeList;
	}

	/**
	 * 
	 * @Title: addNodesNodeToList
	 * @Description: 添加子节点到集合中
	 * @param @param node
	 * @param @param list
	 * @return void
	 * @throws
	 */
	private static void addChildrenNodeToList(PermAdpter node,
			List<PermAdpter> list) {
		List<PermAdpter> tempList = null;
		if (node.getNodes() != null && node.getNodes().size() > 0) {
			// 得到根节点的子节点的集合
			tempList = node.getNodes();
			// 遍历子节点集合
			for (int i = 0; i < tempList.size(); i++) {
				list.add(tempList.get(i));
				addChildrenNodeToList(tempList.get(i), list);
			}
		} else {
			return;
		}
	}
	
	// 1. 将权限组装成adpter
	private static List<PermAdpter> getPermAdpter(List<SysPerm> permList) {
		List<PermAdpter> permAdpterList = new ArrayList<PermAdpter>();
		for (SysPerm perm : permList) {
			permAdpterList.add(PermAdpter.getAdpter(perm));
		}
		return permAdpterList;
	}
	// 1. 将权限组装成adpter
	private static List<PermAuthAdpter> getPermAuthAdpter(List<SysPerm> permList) {
		List<PermAuthAdpter> permAuthAdpter = new ArrayList<PermAuthAdpter>();
		for (SysPerm perm : permList) {
			permAuthAdpter.add(PermAuthAdpter.getPermAuthAdpter(perm));
		}
		return permAuthAdpter;
	}



	

}
