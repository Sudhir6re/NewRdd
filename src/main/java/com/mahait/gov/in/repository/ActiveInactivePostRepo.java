package com.mahait.gov.in.repository;

import java.util.List;

import com.mahait.gov.in.entity.OrgPostMst;
import com.mahait.gov.in.entity.OrgUserMst;

public interface ActiveInactivePostRepo {

	List getPostNameForDisplay(String ddoCode);

	OrgPostMst updatePostStatus(Long postId, Long status, OrgUserMst orgUserMst);

	List<Object[]> getddolst();

}
