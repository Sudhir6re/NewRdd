package com.mahait.gov.in.repository;

import com.mahait.gov.in.entity.MstMenuEntity;

public interface MstMenuRepo {
	
	public int saveMenu(MstMenuEntity objMenuEntity);

	public MstMenuEntity findMenuByKeyForDelete(int key);

	public void updateMenuStatus(MstMenuEntity objMenu);
	
	public MstMenuEntity findMenuByKeyForEdit(int key);

}
