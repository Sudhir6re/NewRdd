package com.mahait.gov.in.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mahait.gov.in.common.StringHelperUtils;
import com.mahait.gov.in.entity.OrgPostMst;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.UserPostCustomVO;
import com.mahait.gov.in.repository.ActiveInactivePostRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ActiveInactivePostServiceImpl implements ActiveInactivePostService {
	
	@Autowired
	ActiveInactivePostRepo activeInactivePostRepo;

	@Override
	public List getPostNameForDisplay(String ddoCode) {
		List postNameList = activeInactivePostRepo.getPostNameForDisplay(ddoCode);

		List post = new ArrayList();
		String empFullName = "";
		String postType = "";
		String subjectName = "";
		String postLookupId = "";
		String permenantlookupId = "10001198129";
		String temparerylookupId = "10001198130";
		String statutorylookupId = "10001198155";

		if (postNameList != null) {
			for (int i = 0; i < postNameList.size(); i++) {
				UserPostCustomVO customVO = new UserPostCustomVO();

				Object rowList[] = (Object[]) postNameList.get(i);

				String postName = rowList[0].toString();
				// customVO.setPostname(postName);

				if (rowList[6] != null && !(rowList[6].toString().trim()).equals("")) {
					postLookupId = rowList[6].toString();
				}
				if (postLookupId.equals("10001198129")) {
					postName = postName.concat("P");
				} else if (postLookupId.equals("10001198130")) {
					postName = postName.concat("T");
				} else if (postLookupId.equals("10001198155")) {
					postName = postName.concat("S");
				} else {
					postName = postName;
				}
				customVO.setPostname(postName);

				long postId = Long.parseLong(rowList[1].toString());
				customVO.setPostId(postId);
				if (rowList[2] != null && !(rowList[2].toString().trim()).equals("")) {
					empFullName = rowList[2].toString();
				} else {
					empFullName = "VACANT";
				}
				customVO.setEmpFullName(empFullName);

				String dsgnName = rowList[3].toString();
				if (rowList[3] != null && !(rowList[3].toString().trim()).equals("")) {
					customVO.setDsgnname(dsgnName);
				}
				String BillNo = " ";
				String PsrNo = " ";

				if (rowList[4] != null) {
					PsrNo = rowList[4].toString();
				}
				customVO.setPsrNo(PsrNo);

				if (rowList[5] != null) {
					BillNo = rowList[5].toString();
				}
				customVO.setBillNo(BillNo);

				// logger.info("===> BillNo :: "+BillNo);
				if (rowList[7] != null && !(rowList[7].toString().trim()).equals("")) {
					postType = rowList[7].toString();
				} else {
					postType = "VACANT";
				}
				
								
				customVO.setDdoCode(rowList[8].toString());
				
				
				customVO.setActivateFlag(StringHelperUtils.isNullBigInteger(rowList[9]).longValue());
				

				customVO.setPostType(postType);

				post.add(customVO);
			}
		}

		return post;
	}
	@Override
	public OrgPostMst updatePostStatus(Long postId, Long status,OrgUserMst orgUserMst) {
		// TODO Auto-generated method stub
		return activeInactivePostRepo.updatePostStatus(postId, status,orgUserMst);
	}
	
	@Override
	public List getddolst() {
		List<Object[]> lstprop = activeInactivePostRepo.getddolst();
		List<UserPostCustomVO> lstObj = new ArrayList<>();
        if (!lstprop.isEmpty()) {
            for (Object[] objLst : lstprop) {
            	UserPostCustomVO obj = new UserPostCustomVO();
                obj.setDdoCode(StringHelperUtils.isNullString(objLst[0]));
                lstObj.add(obj);
            }
            
        }
        return lstObj;
	}

}
