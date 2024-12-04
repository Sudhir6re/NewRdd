package com.mahait.gov.in.repository;
import com.mahait.gov.in.entity.MstRoleEntity;
import com.mahait.gov.in.entity.OrgUserMst;
public interface UserInfoRepo {
	
	/*public abstract UserInfo getActiveUser(String userName);*/
	public abstract OrgUserMst getActiveUser(String userName,int appCode);
	public abstract MstRoleEntity getRoleByRoleId(int role_id);
	/*public List<Object> getActiveUser(String userName);*/
	public abstract void saveUserInfo(OrgUserMst objuserInfo);
	public abstract OrgUserMst getUserIdbyUserName(String userName) ;
	
	public abstract OrgUserMst findUser(String userName,String domain);
	
	public abstract String getNameAndDesignation(int user_id);
	public abstract OrgUserMst createDdoAst(OrgUserMst orgUserMst);
	OrgUserMst getUserByUserId(Long updatedUserId);
	
	
}