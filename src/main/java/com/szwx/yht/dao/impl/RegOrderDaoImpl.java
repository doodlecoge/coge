package com.szwx.yht.dao.impl;

import com.szwx.yht.bean.Hospital;
import com.szwx.yht.bean.RegOrder;
import com.szwx.yht.bean.RegPeople;
import com.szwx.yht.common.CommonDao;
import com.szwx.yht.dao.IRegOrderDAO;
import com.szwx.yht.exception.DaoException;
import com.szwx.yht.util.Page;
import com.szwx.yht.util.TimeUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Repository("regOrderDAO")
public class RegOrderDaoImpl extends  CommonDao implements IRegOrderDAO {
	
	
	private Logger log= Logger.getLogger(RegOrderDaoImpl.class);

    //hch
    public List<RegOrder> queryRegOrders(String id, String name) {
        return getHibernateTemplate().find("FROM RegOrder where identityCard=? and trueName=? order by creatTimeee desc",id, name);
    }

	public List<RegOrder> queryRegOrders(Hospital hospital, String departName,
			String doctorName, String userName, String registerName,
			Date beginTime, Date endTime,Integer status, Page page,String telPhone,Date regStratTime, Date regEndTime) throws DaoException {
		 List<RegOrder>  returnlist=new ArrayList<RegOrder>();
		 List<Object[]> list=new ArrayList<Object[]>();
		StringBuffer hql=new StringBuffer();
		String sql="select r.CODE,r.CHECK_CODE,r.CREATE_TIMEEE,r.END_TIME,r.ORDER_DESC,r.QUIT_TIME," +
		"r.STATE,r.STATE_TIME,r.TREAT_ORDER,r.TREAT_PEOPLE_TYPE,r.UPDATE_TIMEEE," +
		"r.ZHYL_ID,r.CALL_IN_ORDER,r.OPEATE_ESQ,r.REG_PEOPLE,r.REG_PIPELINED," +
		"r.TREAT_PEOPLE,r.CANCLE_ESQ,r.QUIT_TYPE,r.ISSC,r.SCWX_ID," +
		"r.IDEALREGISTER_TIME,r.ID_CARD_CODE,r.MEDICAL_NO,r.MEDICAL_TYPE,r.MOBILE," +
		"r.SEX,r.TRUE_NAME,r.CANCLENAME,r.CLINIC_FEE,r.DEPART_CODE_NO," +
		"r.DEPART_NAME,r.DOCTOR_NAME,r.EXPERTS_FEE,r.FACTORAGE_FEE,r.FOR_WORK_NO," +
		"r.HOSPITAL_CODE,r.HOSPITAL_NAME,r.OPEATENAME,r.REGTYPE,r.REGISTRY_FEE,r.WORK_DATE";
		hql.append(" from YHT_REG_ORDER r where r.CODE is not null ");
		if(hospital!=null&&!"".equals(hospital.getHospitalCode())){
			hql.append(" and r.HOSPITAL_CODE='").append(hospital.getHospitalCode()).append("' ");
		}
		if(departName!=null&&!"".equals(departName.trim())){
			hql.append(" and r.DEPART_NAME like '%").append(departName).append("%'");
		}
		if(doctorName!=null&&!"".equals(doctorName.trim())){
			hql.append(" and r.Doctor_NAME like '%").append(doctorName).append("%'");
		}
		if(userName!=null&&!"".equals(userName.trim())){
			hql.append(" and  r.opeatename like '%").append(userName).append("%'");
//			hql.append(" or  r.opeateESQ.softPhone.phoneNo like '%").append(userName).append("%' ");
//			hql.append(" or  r.opeateESQ.trueName like '%").append(userName).append("%' )");
		}
		if(registerName!=null&&!"".equals(registerName.trim())){
			hql.append(" and r.TRUE_NAME like '%").append(registerName).append("%'");
		}
		if(status!=null&&!"".equals(status)){
			hql.append(" and r.STATE=").append(status);
		}
		if(beginTime!=null){
			hql.append(" and r.WORK_DATE>=to_date('").append(TimeUtil.formatDate1(beginTime, "yyyy-MM-dd")).append("','yyyy-MM-dd')");
		}
		if(endTime!=null){
			hql.append(" and r.WORK_DATE<=to_date('").append(TimeUtil.formatDate1(endTime, "yyyy-MM-dd")).append("','yyyy-MM-dd')");
		}
		
		if(regStratTime!=null){
			hql.append(" and to_char( r.CREATE_TIMEEE,'yyyy-MM-dd')>='").append(TimeUtil.formatDate1(regStratTime, "yyyy-MM-dd")).append("' ");
		}
		if(regEndTime!=null){
			hql.append(" and to_char( r.CREATE_TIMEEE,'yyyy-MM-dd')<='").append(TimeUtil.formatDate1(regEndTime, "yyyy-MM-dd")).append("' ");
		}
		if(telPhone!=null&&!"".equals(telPhone.trim())){
			hql.append(" and r.MOBILE like '%").append(telPhone).append("%'");
		}
		hql.append(" order by r.CREATE_TIMEEE desc");
		try {
			page.setTotalCount(Integer.parseInt(this.queryListBySql("select count(code) "+hql.toString()).get(0).toString().trim()));
			list=queryListBySql(sql+hql.toString(), page.getPageNum(), page.getPageSize());
//			for (int i = 0; i < list.size(); i++) {
//				Object[] strs=list.get(i);
//				StringBuffer str=new StringBuffer();
//				for (int j = 0; j < strs.length; j++) {
//					str.append(strs[j]==null?"空":(strs[j].toString().trim().equals("")?"空":strs[j].toString().trim())).append("---");
//				}
//				System.out.println(str);
//			}
			returnlist=this.getResultList(list);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("RegOrderDaoImpl.queryRegOrders:查询出错1："+e.getMessage(),e);
		}
		return returnlist;
	}

//	private void updateRegisterInfos() throws Exception {
//		List<RegOrder> regList=new ArrayList<RegOrder>();
//		regList=this.queryList("from RegOrder");
//		for(RegOrder r:regList){
//			r=RealTimeReserve.perfectRegInfo(r);
//			this.update(r);
//		}
//		
//	}

	private List<RegOrder> getResultList(List<Object[]> list) {
		 List<RegOrder> returnList=new ArrayList<RegOrder>();
		for (int i = 0; i < list.size(); i++) {
			Object[] strs=list.get(i);
			RegOrder regOrder=new RegOrder();
			regOrder.setCode(Long.parseLong(strs[0].toString().trim()));
			regOrder.setCheckCode(this.getStringFromObject(strs[1]));
			regOrder.setCreatTimeee(this.getDateFromObject(strs[2]));
			regOrder.setEndTime(this.getDateFromObject(strs[3]));
			regOrder.setOrderDesc(getStringFromObject(strs[4]));
			regOrder.setQuitTimeee(getDateFromObject(strs[5]));
			regOrder.setState(getStringFromObject(strs[6]).trim().equals("")?null:Integer.parseInt(getStringFromObject(strs[6])));
			regOrder.setStateTime(getDateFromObject(strs[7]));
			regOrder.setTreatOrder(getStringFromObject(strs[8]).trim().equals("")?0:Integer.parseInt(getStringFromObject(strs[8])));
			regOrder.setUpdateTimeee(getDateFromObject(strs[10]));
			regOrder.setYLId(getStringFromObject(strs[11]).trim().equals("")?null:Long.parseLong(getStringFromObject(strs[11])));
			regOrder.setQuitType(getStringFromObject(strs[18]).trim().equals("")?null:Integer.parseInt(getStringFromObject(strs[18])));
			regOrder.setIsSC(getStringFromObject(strs[19]).trim().equals("")?null:Integer.parseInt(getStringFromObject(strs[19])));
			regOrder.setScwxId(getStringFromObject(strs[20]));
			regOrder.setIdealRegisterTime(getDateFromObject(strs[21]));
			regOrder.setIdentityCard(getStringFromObject(strs[22]));
			regOrder.setMedicalNo(getStringFromObject(strs[23]));
			regOrder.setMedicalType(getStringFromObject(strs[24]).trim().equals("")?null:Integer.parseInt(getStringFromObject(strs[24])));
			regOrder.setMobile(getStringFromObject(strs[25]));
			regOrder.setSex(getStringFromObject(strs[26]).trim().equals("")?null:Integer.parseInt(getStringFromObject(strs[26])));
			regOrder.setTrueName(getStringFromObject(strs[27]));
			regOrder.setCancleName(getStringFromObject(strs[28]));
			regOrder.setClinicFee(getStringFromObject(strs[29]).trim().equals("")?0:Float.parseFloat(getStringFromObject(strs[29])));
			regOrder.setDepartCodeNo(getStringFromObject(strs[30]));
			regOrder.setDepartName(getStringFromObject(strs[31]));
			regOrder.setDocName(getStringFromObject(strs[32]));
			regOrder.setExpertsFee(getStringFromObject(strs[33]).trim().equals("")?0:Float.parseFloat(getStringFromObject(strs[33])));
			regOrder.setFactorageFee(getStringFromObject(strs[34]).trim().equals("")?0:Float.parseFloat(getStringFromObject(strs[34])));
			regOrder.setForWorkNo(getStringFromObject(strs[35]));
			regOrder.setHospitalCode(getStringFromObject(strs[36]));
			regOrder.setHospitalName(getStringFromObject(strs[37]));
			regOrder.setOpeateName(getStringFromObject(strs[38]));
			regOrder.setRegType(getStringFromObject(strs[39]).trim().equals("")?null:Integer.parseInt(getStringFromObject(strs[39])));
			regOrder.setRegistryFee(getStringFromObject(strs[40]).trim().equals("")?0:Float.parseFloat(getStringFromObject(strs[40])));
			regOrder.setWorkDate(getDateFromObject(strs[41]));
			returnList.add(regOrder);
		}
		return returnList;
	}
	
	private Date getDateFromObject(Object obj){
		String str=obj==null?"":(obj.toString().trim().equals("")?"":obj.toString());
		if(str.length()>19){
			return TimeUtil.formatDate3(str.trim().substring(0, 19), "yyyy-MM-dd hh:mm:ss");
		}else if(str.trim().length()<19&&str.trim().length()>10){
			return TimeUtil.formatDate3(str.trim().substring(0, 10), "yyyy-MM-dd");
		}else if(str.trim().length()==10){
			return TimeUtil.formatDate3(str.trim(), "yyyy-MM-dd");
		}else{
			return null;
		}

	}
	private String getStringFromObject(Object obj){
		return obj==null?"":obj.toString().trim();
	}

	public List<RegPeople> queryBlackPeople(String telPhone,
			String registerName, Page page, Integer isBlack) throws DaoException {
		List<RegPeople> list=new ArrayList<RegPeople>();
		StringBuffer hql=new StringBuffer();
		hql.append(" from RegPeople r where 1=1  ");//r.breakPromiseCount>=3
		if(isBlack!=null){
			if(isBlack.intValue()==0){
				hql.append(" and r.breakPromiseCount>=3 ");
			}else if(isBlack.intValue()==1){
				hql.append(" and r.breakPromiseCount<3 ");
			}
		}
		if(telPhone!=null&&!telPhone.equals("")){
			hql.append(" and r.mobile like '%").append(telPhone).append("%'");
		}
		if(registerName!=null&&!"".equals(registerName.trim())){
			hql.append(" and r.trueName like '%").append(registerName).append("%'");
		}
		hql.append(" order by r.id ");
		try {
			page.setTotalCount(queryList(hql.toString()).size());
			list=queryList(hql.toString(), page.getPageNum(), page.getPageSize());
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("RegOrderDaoImpl.queryBlackPeople:查询出错1："+e.getMessage(),e);
		}
		return list;
	}

	public void updateBlackPeople() {
		Calendar cal = Calendar.getInstance();
		String nowDate= TimeUtil.formatDate1(cal.getTime(), "yyyy-MM-dd");
		cal.set(Calendar.YEAR, new Date().getYear());
		cal.set(Calendar.MONTH,  1);// �£���
		cal.set(Calendar.DAY_OF_MONTH, 1);
		String nowStartTime= TimeUtil.formatDate1(cal.getTime(), "yyyy-MM-dd");
		try {
			List<RegPeople> list=(List<RegPeople>)queryList(" from RegPeople");
			for (RegPeople r:list) {
				int num=this.getRegBlackNumByRegPeople(r,nowDate,nowStartTime);
				r.setBreakPromiseCount(num);
				update(r);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("更新黑名单信息出错："+e.getMessage());
		}
	}

	private int getRegBlackNumByRegPeople(RegPeople r,String nowDate,String StartTime) throws Exception {
		StringBuffer hql=new StringBuffer();
		hql.append(" from RegOrder r where r.state=3 and r.regPeople.id=").append(r.getId());
		if(r.getCancelDate()!=null&&r.getCancelDate().getYear()>=new Date().getYear()){
			hql.append(" and r.creatTimeee>to_date('").append(TimeUtil.formatDate1(r.getCancelDate(), "yyyy-MM-dd")).append("','yyyy-MM-dd') ");
		}else{
			hql.append(" and r.creatTimeee>to_date('").append(StartTime).append("','yyyy-MM-dd') ");
		}
		hql.append(" and r.creatTimeee<to_date('").append(nowDate).append("','yyyy-MM-dd') ");
		List list=queryList(hql.toString());
		return list.size();
	}

	public List<RegOrder> getRegOrdersForBlack(RegPeople r)
			throws DaoException {
		StringBuffer hql=new StringBuffer();
		Calendar cal = Calendar.getInstance();
		String nowDate= TimeUtil.formatDate1(cal.getTime(), "yyyy-MM-dd");
		cal.set(Calendar.YEAR, new Date().getYear());
		cal.set(Calendar.MONTH,  1);//
		cal.set(Calendar.DAY_OF_MONTH, 1);
		String startTime= TimeUtil.formatDate1(cal.getTime(), "yyyy-MM-dd");
		hql.append(" from RegOrder r where r.state=3 and r.regPeople.id=").append(r.getId());
		if(r.getCancelDate()!=null&&r.getCancelDate().getYear()>=new Date().getYear()){
			hql.append(" and r.creatTimeee>to_date('").append(TimeUtil.formatDate1(r.getCancelDate(), "yyyy-MM-dd")).append("','yyyy-MM-dd') ");
		}else{
			hql.append(" and r.creatTimeee>to_date('").append(startTime).append("','yyyy-MM-dd') ");
		}
		hql.append(" and r.creatTimeee<to_date('").append(nowDate).append("','yyyy-MM-dd') ");
		List<RegOrder> list=new ArrayList<RegOrder>();
		try {
			list = queryList(hql.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

    public int queryDaoQuitNum(Long code) throws DaoException {
        Calendar calendar1=Calendar.getInstance();
        Calendar calendar2=Calendar.getInstance();
        calendar2.add(Calendar.DAY_OF_YEAR, -7);
//		System.out.println(TimeUtil.formatDate1(calendar1.getTime(), "yyyy-MM-dd")+"---------------"+TimeUtil.formatDate1(calendar2.getTime(), "yyyy-MM-dd"));
        String hql="select count(t.regPeople) from RegOrder t where t.state=2 and  t.regPeople.id=(select r.regPeople.id from RegOrder r where r.code="+code+") ";
        hql+=" and to_char(t.quitTimeee,'yyyy-MM-dd')>'"+ TimeUtil.formatDate1(calendar2.getTime(), "yyyy-MM-dd")+"'";
        hql+=" and to_char(t.quitTimeee,'yyyy-MM-dd')<='"+ TimeUtil.formatDate1(calendar1.getTime(), "yyyy-MM-dd")+"'";
        try {
            return Integer.parseInt(queryList(hql.toString()).get(0).toString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }



    //hch
    public int queryDaoQuitNum(String pid) throws DaoException {
        Calendar calendar1=Calendar.getInstance();
        Calendar calendar2=Calendar.getInstance();
        calendar2.add(Calendar.DAY_OF_YEAR, -7);
//		System.out.println(TimeUtil.formatDate1(calendar1.getTime(), "yyyy-MM-dd")+"---------------"+TimeUtil.formatDate1(calendar2.getTime(), "yyyy-MM-dd"));

        String hql="select count(t.regPeople) from RegOrder t where t.state=2 and  t.regPeople.identityCard='" + pid + "' ";
        hql+=" and to_char(t.quitTimeee,'yyyy-MM-dd')>'"+ TimeUtil.formatDate1(calendar2.getTime(), "yyyy-MM-dd")+"'";
        hql+=" and to_char(t.quitTimeee,'yyyy-MM-dd')<='"+ TimeUtil.formatDate1(calendar1.getTime(), "yyyy-MM-dd")+"'";

        try {
            return Integer.parseInt(queryList(hql.toString()).get(0).toString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

}
