package com.mahait.gov.in.service;

import java.util.List;

import com.mahait.gov.in.entity.OrgPostMst;
import com.mahait.gov.in.entity.OrgUserMst;

public interface ActiveInactivePostService {

	List getPostNameForDisplay(String ddoCode);

	OrgPostMst updatePostStatus(Long postId, Long status, OrgUserMst messages);

	List getddolst();

}
