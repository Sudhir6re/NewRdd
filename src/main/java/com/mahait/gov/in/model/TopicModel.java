/**
 * 
 */
package com.mahait.gov.in.model;

import java.io.Serializable;

/**
 * @author Parvez
 *
 */
public class TopicModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int key;
	private String menuName;
	
//	private SubMenuModel subMenuModel;
	
//	public SubMenuModel  subMenuModel;

	
	private int SubMenukey;
	private int menuKey;
	private int roleKey;
	private String subMenuName;
	private String controllerName;
	private String linkName;
	
	
	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public int getSubMenukey() {
		return SubMenukey;
	}

	public void setSubMenukey(int subMenukey) {
		SubMenukey = subMenukey;
	}

	public int getMenuKey() {
		return menuKey;
	}

	public void setMenuKey(int menuKey) {
		this.menuKey = menuKey;
	}

	public int getRoleKey() {
		return roleKey;
	}

	public void setRoleKey(int roleKey) {
		this.roleKey = roleKey;
	}

	public String getSubMenuName() {
		return subMenuName;
	}

	public void setSubMenuName(String subMenuName) {
		this.subMenuName = subMenuName;
	}

	public String getControllerName() {
		return controllerName;
	}

	public void setControllerName(String controllerName) {
		this.controllerName = controllerName;
	}

	public String getLinkName() {
		return linkName;
	}

	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}

	/*public SubMenuModel getSubMenuModel() {
		return subMenuModel;
	}

	public void setSubMenuModel(SubMenuModel subMenuModel) {
		this.subMenuModel = subMenuModel;
	}*/

}
