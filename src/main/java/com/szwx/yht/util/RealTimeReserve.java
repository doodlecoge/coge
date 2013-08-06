package com.szwx.yht.util;

import com.szwx.yht.bean.RegOrder;
import com.szwx.yht.bean.WSDepart;
import com.szwx.yht.bean.WSDoctor;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import java.io.IOException;
import java.io.StringReader;
import java.util.*;

public class RealTimeReserve {
		public static String reqWorkTime(String hosId,String dpId,String dtId,String workDate,Long workType) {
			return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
				"<Message>"+
				"<username>zhyl</username>"+
				"<password>zhyl</password>"+
			  	"<workInfo>"+
			  	"<hosId>"+hosId+"</hosId>"+
			  	"<dpId>"+dpId+"</dpId>"+
			  	"<dtId>"+dtId+"</dtId>"+
			    "<workDate>"+workDate+"</workDate>"+
			  	"<workType>"+workType+"</workType>"+
			  	"</workInfo>"+
			  "</Message>";
		}
		
		/**
		 * 响应时间段
		 */
		public static List rspWorkTime(String xml) {
			List list = new ArrayList();
			if (xml == null) {
				return list;
			}
			SAXBuilder builder = null;
			Document doc = null;
			try {
				builder = new SAXBuilder("org.apache.xerces.parsers.SAXParser");// 读取解析XML文件的驱动
				StringReader sr = new StringReader(xml);
				doc = builder.build(sr);
				Element rootEle = null;
				if (doc != null) {
					rootEle = doc.getRootElement();
					List workTimeList = rootEle.getChildren("workInfo");
					if (workTimeList.size() > 0) {
						for (int i=0;i<workTimeList.size();i++) {
							Element workInfo = (Element)workTimeList.get(i);
							String workTimeId = workInfo.getChildText("workTimeId");
							String startTime = workInfo.getChildText("startTime");
							String endTime = workInfo.getChildText("endTime");
							String totalNum = workInfo.getChildText("totalNum");
							String leftNum = workInfo.getChildText("leftNum");
							Object[] obj = new Object[5];
							obj[0] = workTimeId;
							obj[1] = startTime;
							obj[2] = endTime;
							obj[3] = totalNum;
							obj[4] = leftNum;
							list.add(obj);
						}
					}
				}
			} catch (JDOMException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return list;
		}
		
		
		
		public static String uploadRegInfoRequest(RegOrder regOrder) {
			WSDoctor ws=new WSDoctor();
			WSDepart dp=new WSDepart();
			try {
				ws=(WSDoctor)regOrder.getRegPipelined().getWorkSchema();
			} catch (Exception e) {
				ws=null;
			}
			try {
				dp=(WSDepart)regOrder.getRegPipelined().getWorkSchema();
			} catch (Exception e) {
				dp=null;
			}
			String s = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
			try {
				if(ws!=null){
					s = s + "<Message>" + 
					"<username>zhyl</username>" +
					"<password>zhyl</password>";
						s = s + "<regInfo>"
								+ "<Id></Id>"
								+ "<HospitalId>"+ws.getDepart().getHospital().getHospitalCode() + "</HospitalId>"
								+ "<DepartId>"+ ws.getDepart().getDepartCodeNo()+ "</DepartId>"
								+ "<DoctorId>"+ ws.getDoctorExpert().getForWorkNo()+ "</DoctorId>"
								+ "<IsExpert>"+ 1+ "</IsExpert>"
								+ "<SickName>"+regOrder.getTreatPeople().getTrueName() + "</SickName>"
								+ "<SickSex>"+ (regOrder.getTreatPeople().getSex()-1)+ "</SickSex>"
								+ "<SickBirthday>"+ cardNoToBirthday(regOrder.getTreatPeople().getIdentityCard())+ "</SickBirthday>"
								+ "<SickInsureType>"+ regOrder.getTreatPeople().getMedicalType()+ "</SickInsureType>"
								+ "<SickInsureNo>"+ regOrder.getTreatPeople().getMedicalNo()+ "</SickInsureNo>"
								+ "<SeeDoctorDate>"+ TimeUtil.formatDate1(regOrder.getRegPipelined().getWorkSchema().getWorkDate(), "yyyy-MM-dd")+ "</SeeDoctorDate>" 
								+ "<WorkType>"+regOrder.getRegPipelined().getWorkType() + "</WorkType>" 
								+ "<registryfee>"+ws.getRegistryFee()+"</registryfee>"
								+ "<chinicfee>"+ws.getClinicFee()+"</chinicfee>"
								+ "<expertsfee>"+ws.getExpertsFee()+"</expertsfee>"
								+ "<mzbh></mzbh>"
								+ "<Phone>"+ regOrder.getTreatPeople().getMobile() + "</Phone>"
								+ "<cardNo>" + regOrder.getTreatPeople().getIdentityCard()+ "</cardNo>"
								+ "<RegType>" + 0+ "</RegType>" 
								+ "<PayType>"+ 0+ "</PayType>" 
								+ "<workTimeId>"+0+ "</workTimeId>"
								+"<startTime>"+TimeUtil.formatDate1(regOrder.getStateTime(), "HH:mm")+"</startTime>"
								+"<endTime>"+TimeUtil.formatDate1(regOrder.getEndTime(), "HH:mm")+"</endTime>";
						s = s + "</regInfo>";
					s = s + "</Message>";
				}else{
					s = s + "<Message>" + 
					"<username>zhyl</username>" +
					"<password>zhyl</password>";
						s = s + "<regInfo>"
								+ "<Id></Id>"
								+ "<HospitalId>"+dp.getDepart().getHospital().getHospitalCode() + "</HospitalId>"
								+ "<DepartId>"+ dp.getDepart().getDepartCodeNo()+ "</DepartId>"
								+ "<DoctorId></DoctorId>"
								+ "<IsExpert>"+ 0+ "</IsExpert>"
								+ "<SickName>"+regOrder.getTreatPeople().getTrueName() + "</SickName>"
								+ "<SickSex>"+ (regOrder.getTreatPeople().getSex()-1)+ "</SickSex>"
								+ "<SickBirthday>"+ cardNoToBirthday(regOrder.getTreatPeople().getIdentityCard())+ "</SickBirthday>"
								+ "<SickInsureType>"+ regOrder.getTreatPeople().getMedicalType()+ "</SickInsureType>"
								+ "<SickInsureNo>"+ regOrder.getTreatPeople().getMedicalNo()+ "</SickInsureNo>"
								+ "<SeeDoctorDate>"+ TimeUtil.formatDate1(regOrder.getRegPipelined().getWorkSchema().getWorkDate(), "yyyy-MM-dd")+ "</SeeDoctorDate>" 
								+ "<WorkType>"+regOrder.getRegPipelined().getWorkType() + "</WorkType>" 
								+ "<registryfee>"+dp.getDepart().getRegistryFee()+"</registryfee>"
								+ "<chinicfee>"+dp.getDepart().getClinicFee()+"</chinicfee>"
								+ "<expertsfee>"+0+"</expertsfee>"
								+ "<mzbh></mzbh>"
								+ "<Phone>"+ regOrder.getTreatPeople().getMobile() + "</Phone>"
								+ "<cardNo>" + regOrder.getTreatPeople().getIdentityCard()+ "</cardNo>"
								+ "<RegType>" + 0+ "</RegType>" 
								+ "<PayType>"+ 0+ "</PayType>" 
								+ "<workTimeId>"+0+ "</workTimeId>"
								+"<startTime>"+TimeUtil.formatDate1(regOrder.getStateTime(), "HH:mm")+"</startTime>"
								+"<endTime>"+TimeUtil.formatDate1(regOrder.getEndTime(), "HH:mm")+"</endTime>";
						s = s + "</regInfo>";
					s = s + "</Message>";
				}
				
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return s;
	}
		
		private static String cardNoToBirthday(String cartNo){
			String year="";
			String month="";
			String day="";
			if (cartNo.length() == 18) {
				year = cartNo.substring(6, 10);
				month = cartNo.substring(10, 12);
				day = cartNo.substring(12, 14);
				
			} else {
				year = "19" + cartNo.substring(6, 8);
				month = cartNo.substring(8, 10);
				day = cartNo.substring(10, 12);
			}
				
			return year+"-"+month+"-"+day;

			
		}	
	
		public static Properties uploadRegInfoRsp(String xml) {
			Properties p = null;
			SAXBuilder builder = null;
			Document doc = null;
			try {
				builder = new SAXBuilder("org.apache.xerces.parsers.SAXParser");// 读取解析XML文件的驱动
				StringReader sr = new StringReader(xml);
				doc = builder.build(sr);
				Element rootEle = null;
				if (doc != null) {
					//<Message><code>0000<code><returnValue>"+registeredInfo.getReserveNo()+"<returnValue><regId>"+registeredInfo.getId()+"</regId><checkNo>"+registeredInfo.getCheckNo()+"</checkNo></Message>
					rootEle = doc.getRootElement();
					p = new Properties();
					String code = rootEle.getChildTextTrim("code");
					p.setProperty("code", rootEle.getChildTextTrim("code"));
					p.setProperty("returnValue", rootEle.getChildTextTrim("returnValue"));
					if ("0000".equals(code)) {
						p.setProperty("regId", rootEle.getChildTextTrim("regId"));
						p.setProperty("checkNo", rootEle.getChildTextTrim("checkNo"));
					}
				}
			} catch (JDOMException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return p;
		}
		
		public static String backRegNo(String regid){
			String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+
			 "<Message>"+
			 "<username>zhyl</username>"+
			 "<password>zhyl</password>"+
			 "<regInfo>"
			 + "<Id>"+regid+"</Id>"
			 +"</regInfo>"+
			 "</Message>";
			return xml;
		}
		
		/**
		 * 过滤儿童医院的不合年龄的病友
		 *@Title: getHospitalCode
		 *@author:gaowm
		 *@date:Sep 6, 2012 2:39:56 PM
		 *@param regOrder
		 *@return
		 */
		public static Integer getHospitalCode(RegOrder regOrder) {//0:年龄不过16周岁,1:超过16周岁，2：出错
			WSDoctor ws=new WSDoctor();
			WSDepart dp=new WSDepart();
			String p=new String();
			String n=new String();
			try {
				ws=(WSDoctor)regOrder.getRegPipelined().getWorkSchema();
			} catch (Exception e) {
				ws=null;
			}
			try {
				dp=(WSDepart)regOrder.getRegPipelined().getWorkSchema();
			} catch (Exception e) {
				dp=null;
			}
			if(ws!=null){
				p=ws.getDepart().getHospital().getHospitalCode();
				n=ws.getDepart().getDepartName();
			}else if(dp!=null){
				p=dp.getDepart().getHospital().getHospitalCode();
				n=dp.getDepart().getDepartName();
			}
			try {
				if((p!=null&&p.equals("SZET"))||(n!=null&&n.indexOf("儿") >= 0)){
					String birth=regOrder.getRegPeople().getIdentityCard().length()==15?"19"+regOrder.getRegPeople().getIdentityCard().substring(6,12):regOrder.getRegPeople().getIdentityCard().substring(6,14);
					Integer year=Integer.parseInt(birth.substring(0,4));
					Integer month=Integer.parseInt(birth.substring(4,6));
					Integer day=Integer.parseInt(birth.substring(6,8));
					Calendar cal=Calendar.getInstance();
					int dayNow = cal.get(Calendar.DATE);
					int monthNow = cal.get(Calendar.MONTH) + 1;
					int yearNow = cal.get(Calendar.YEAR);
					if(yearNow-year.intValue()<16){
						return 0;
					}else if(yearNow-year.intValue()>16){
						return 1;
					}else if(yearNow-year.intValue()==16){
						if(monthNow-month.intValue()>0){
							return 1;
						}else if(monthNow-month.intValue()<0){
							return 0;
						}else if(monthNow-month.intValue()==0){
							if(dayNow-day.intValue()>=0){
								return 1;
							}else{
								return 0;
							}
						}else{
							return 2;
						}
					}else{
						return 2;
					}
				}else{
					return 0;
				}
			} catch (Exception e) {
				e.printStackTrace();
				return 2;
			}
			
		}
		
		public static Properties uploadRegInfoBack(String xml) {
			Properties p = null;
			SAXBuilder builder = null;
			Document doc = null;
			try {
				builder = new SAXBuilder("org.apache.xerces.parsers.SAXParser");// 读取解析XML文件的驱动
				StringReader sr = new StringReader(xml);
				doc = builder.build(sr);
				Element rootEle = null;
				if (doc != null) {
					//<Message><code>0000<code><returnValue>"+registeredInfo.getReserveNo()+"<returnValue><regId>"+registeredInfo.getId()+"</regId><checkNo>"+registeredInfo.getCheckNo()+"</checkNo></Message>
					rootEle = doc.getRootElement();
					p = new Properties();
					p.setProperty("code", rootEle.getChildTextTrim("code"));
					p.setProperty("msg", rootEle.getChildTextTrim("msg"));
				}
			} catch (JDOMException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return p;
		}

		public static Integer isOverTime(RegOrder regOrder) {//0:可以挂号,1:挂的明天的号，已过3点，2：出错
			WSDoctor ws=new WSDoctor();
			WSDepart dp=new WSDepart();
			String p=new String();
			String n=new String();
			Date workDate=new Date();
			try {
				ws=(WSDoctor)regOrder.getRegPipelined().getWorkSchema();
			} catch (Exception e) {
				ws=null;
			}
			try {
				dp=(WSDepart)regOrder.getRegPipelined().getWorkSchema();
			} catch (Exception e) {
				dp=null;
			}
			if(ws!=null){
				p=ws.getDepart().getHospital().getHospitalCode();
				n=ws.getDepart().getDepartName();
				workDate=ws.getWorkDate();
			}else if(dp!=null){
				p=dp.getDepart().getHospital().getHospitalCode();
				n=dp.getDepart().getDepartName();
				workDate=dp.getWorkDate();
			}
			try {
				if((p!=null&&p.equals("SDFE"))||(p!=null&&p.equals("ZYYY"))){
					Calendar cal=Calendar.getInstance();
//					System.out.println(cal.getTime().getHours());
//					System.out.println(TimeUtil.formatDate1(cal.getTime(), "HH")+"----"+cal.get(Calendar.HOUR_OF_DAY));
					if(cal.get(Calendar.HOUR_OF_DAY)>=15){
						cal.add(Calendar.DATE, 1);
						if(TimeUtil.formatDate2(workDate, "yyyy-MM-dd").equals(TimeUtil.formatDate2(cal.getTime(), "yyyy-MM-dd"))){
							return 1;
						}else{
							return 0;
						}
					}else{
						return 0;
					}
				}else{
					return 0;
				}
			} catch (Exception e) {
				e.printStackTrace();
				return 2;
			}
			
		}
		
		public static String registerNum(Date startTime,Date endTime) {
			String str1="";
			String str2="";
			if(startTime!=null){
				str1=TimeUtil.formatDate1(startTime, "yyyy-MM-dd");
			}
			if(endTime!=null){
				str2=TimeUtil.formatDate1(endTime, "yyyy-MM-dd");
			}
			return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
				"<Message>"+
				"<username>zhyl</username>"+
				"<password>zhyl</password>"+
				 "<requestDate>"
				 + "<startTime>"+str1+"</startTime>"
				 + "<endTime>"+str2+"</endTime>"
			  	 +"</requestDate>"+
			  "</Message>";
		}
		
		public static List rspRegisterNum(String xml) {
			List list = new ArrayList();
			if (xml == null) {
				return list;
			}
			SAXBuilder builder = null;
			Document doc = null;
			try {
				builder = new SAXBuilder("org.apache.xerces.parsers.SAXParser");// 读取解析XML文件的驱动
				StringReader sr = new StringReader(xml);
				doc = builder.build(sr);
				Element rootEle = null;
				if (doc != null) {
					rootEle = doc.getRootElement();
					List workTimeList = rootEle.getChildren("regInfo");
					if (workTimeList.size() > 0) {
						for (int i=0;i<workTimeList.size();i++) {
							Element workInfo = (Element)workTimeList.get(i);
							String hosCode = workInfo.getChildText("hosCode").toString().trim();
							String hosName = workInfo.getChildText("hosName").toString().trim();
							String setnoCount = workInfo.getChildText("setnoCount").toString().trim();
							String wzyy = workInfo.getChildText("wzyy").toString().trim();
							String sjyy = workInfo.getChildText("sjyy").toString().trim();
							String dhyy = workInfo.getChildText("dhyy").toString().trim();
							String zjyy = workInfo.getChildText("zjyy").toString().trim();
							Object[] obj = new Object[7];
							obj[0] = hosCode;
							obj[1] = hosName;
							obj[2] = setnoCount;
							obj[3] = wzyy;
							obj[4] = sjyy;
							obj[5] = dhyy;
							obj[6] = zjyy;
							list.add(obj);
						}
					}
				}
			} catch (JDOMException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return list;
		}
		public static Date havaReisterTimeForPeople(RegOrder regOrder){
			WSDoctor ws=new WSDoctor();
			Date returnDate=null;
			Calendar c=Calendar.getInstance();
			try {
				ws=(WSDoctor)regOrder.getRegPipelined().getWorkSchema();
			} catch (Exception e) {
				ws=null;
			}
			if(ws!=null){
				if(!"SDFY".equals(ws.getDepart().getHospital().getHospitalCode())&&!"SDFE".equals(ws.getDepart().getHospital().getHospitalCode())&&ws.getDoctorExpert().getRegMinute()!=null&&ws.getDoctorExpert().getRegMinute()>0){
					try {
						c.setTime(regOrder.getStateTime());
						Integer timeValue=(int)(ws.getDoctorExpert().getRegMinute()*regOrder.getTreatOrder());
						if(timeValue!=null&&timeValue.intValue()>0){
							c.add(Calendar.MINUTE, timeValue.intValue());
							 //屏蔽错误的提示时间
							Integer time = c.get(Calendar.HOUR_OF_DAY);       //获取当前小时
							Integer worktype=regOrder.getRegPipelined().getWorkType();
							if((worktype.intValue()==1&&time.intValue()<=11)||(worktype.intValue()==2&&time.intValue()<=16&&time.intValue()>=13)){
								returnDate=c.getTime();
							}
						}
					} catch (Exception e) {
						returnDate=null;
					}
					
				}
				if(returnDate!=null){
					
				}
			}
			return returnDate;
		}

		public static void main(String[] args) {
			Calendar c=Calendar.getInstance();
			c.add(Calendar.HOUR_OF_DAY, 7);
			int time = c.get(Calendar.HOUR_OF_DAY);       //获取当前小时
			int min = c.get(Calendar.MINUTE);  
			System.out.println(time+"---"+min);
		}
		
		
		public static String queryRegDetials(Date startTime, Date endTime,
				String hospitalCode, String dpName,String dtName, String ghzt, String sortType, String queryType, Page page) {
			String str1="";
			String str2="";
			if(startTime!=null){
				str1=TimeUtil.formatDate1(startTime, "yyyy-MM-dd");
			}
			if(endTime!=null){
				str2=TimeUtil.formatDate1(endTime, "yyyy-MM-dd");
			}
			String  xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+
			 "<Message>"+
			 "<username>yht</username>"+
			 "<password>yht</password>"+
			 "<requestInfo>"
			 + "<hosCode>"+hospitalCode.trim()+"</hosCode>"
			 + "<dpName>"+dpName.trim()+"</dpName>"
			 + "<dtName>"+dtName.trim()+"</dtName>"
			 + "<yylx>"+queryType.trim()+"</yylx>"
			 + "<startTime>"+str1.trim()+"</startTime>"
			 + "<endTime>"+str2.trim()+"</endTime>"
			 + "<pageNum>"+page.getPageNum()+"</pageNum>"
			 + "<pageSize>"+page.getPageSize()+"</pageSize>"
			 + "<ghzt>"+ghzt.trim()+"</ghzt>"
			 + "<sortType>"+sortType.trim()+"</sortType>"
			 +"</requestInfo>"+
			 "</Message>";

			return xml;
		}

		public static Page rspRegisterDetials(String xml,Page page) {
			List list = new ArrayList();
			if (xml == null) {
				return page;
			}
			SAXBuilder builder = null;
			Document doc = null;
			try {
				builder = new SAXBuilder("org.apache.xerces.parsers.SAXParser");// 读取解析XML文件的驱动
				StringReader sr = new StringReader(xml);
				doc = builder.build(sr);
				Element rootEle = null;
				if (doc != null) {
					rootEle = doc.getRootElement();
					String pageCount= rootEle.getChildText("pageCount").toString().trim();
					if(!pageCount.equals("")&&!pageCount.equals("null")){
						page.setPageCount(Integer.parseInt(pageCount));
					}else{
						page.setPageCount(0);
					}
					String totalCount= rootEle.getChildText("totalCount").toString().trim();
					if(!totalCount.equals("")&&!totalCount.equals("null")){
						page.setTotalCount(Integer.parseInt(totalCount));
					}else{
						page.setTotalCount(0);
					}
					String pageNum= rootEle.getChildText("pageNum").toString().trim();
					if(!pageNum.equals("")&&!pageNum.equals("null")){
						page.setPageNum(Integer.parseInt(pageNum));
					}else{
						page.setPageNum(0);
					}
					List workTimeList = rootEle.getChildren("orderInfo");
					if (workTimeList.size() > 0) {
						for (int i=0;i<workTimeList.size();i++) {
							Element workInfo = (Element)workTimeList.get(i);
							String SickName = workInfo.getChildText("SickName").toString().trim();//患者姓名
							String Createdate = workInfo.getChildText("Createdate").toString().trim();//预约日期
							String RegisteredType = workInfo.getChildText("RegisteredType").toString().trim();//预约途径
							String SeeDoctorDate = workInfo.getChildText("SeeDoctorDate").toString().trim();//就诊日期
							String DepartName = workInfo.getChildText("DepartName").toString().trim();//医生姓名
							String DoctorName = workInfo.getChildText("DoctorName").toString().trim();//科室名称
							String id = workInfo.getChildText("Id").toString().trim();//流水id
							String Phone = workInfo.getChildText("Phone").toString().trim();//联系电话
							String CardNo = workInfo.getChildText("CardNo").toString().trim();//身份证号
							String iscancel = workInfo.getChildText("iscancel").toString().trim();//挂号状态
							String yhIp = workInfo.getChildText("yhIp").toString().trim();//用户ip
							String sickInsureType = workInfo.getChildText("SickInsureType").toString().trim();//医保类型
							Object[] obj = new Object[13];
							obj[0] = SickName;
							obj[1] = Createdate;
							obj[2] = RegisteredType;
							obj[3] = SeeDoctorDate;
							obj[4] = DepartName;
							obj[5] = DoctorName;
							obj[6] = Phone;
							obj[7] = CardNo;
							obj[9] = id;
							obj[10]= iscancel;
							obj[11]= yhIp;
							obj[12]= sickInsureType;
							list.add(obj);
						}
					}
				}
			} catch (JDOMException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			page.setData(list);
			return page;
		}

		public static RegOrder perfectRegInfo(RegOrder regOrder) {
			WSDoctor ws=new WSDoctor();
			WSDepart dp=new WSDepart();
			try {
				ws=(WSDoctor)regOrder.getRegPipelined().getWorkSchema();
			} catch (Exception e) {
				ws=null;
			}
			try {
				dp=(WSDepart)regOrder.getRegPipelined().getWorkSchema();
			} catch (Exception e) {
				dp=null;
			}
			
			regOrder.setIdentityCard(regOrder.getRegPeople().getIdentityCard());
			regOrder.setMedicalNo(regOrder.getRegPeople().getMedicalNo());
			regOrder.setMedicalType(regOrder.getRegPeople().getMedicalType());
			regOrder.setTrueName(regOrder.getRegPeople().getTrueName());
			regOrder.setSex(regOrder.getRegPeople().getSex());
			regOrder.setMobile(regOrder.getRegPeople().getMobile());
			regOrder.setOpeateName(regOrder.getOpeateESQ().getTrueName());
//			regOrder.setOpeateName("NOP");

			
			if(ws!=null){
				regOrder.setRegType(0);
				regOrder.setWorkDate(ws.getWorkDate());
				regOrder.setRegistryFee(ws.getRegistryFee());
				regOrder.setClinicFee(ws.getClinicFee());
				regOrder.setFactorageFee(ws.getFactorageFee());
				regOrder.setExpertsFee(ws.getExpertsFee());
				regOrder.setHospitalCode(ws.getDepart().getHospital().getHospitalCode());
				regOrder.setHospitalName(ws.getDepart().getHospital().getName());
				regOrder.setDepartCodeNo(ws.getDepart().getDepartCodeNo());
				regOrder.setDepartName(ws.getDepart().getDepartName());
				regOrder.setForWorkNo(ws.getDoctorExpert().getForWorkNo());
				regOrder.setDocName(ws.getDoctorExpert().getName());
			}else if(dp!=null){
				regOrder.setRegType(1);
				regOrder.setWorkDate(dp.getWorkDate());
				regOrder.setRegistryFee(dp.getDepart().getRegistryFee());
				regOrder.setClinicFee(dp.getDepart().getClinicFee());
				regOrder.setHospitalCode(dp.getDepart().getHospital().getHospitalCode());
				regOrder.setHospitalName(dp.getDepart().getHospital().getName());
				regOrder.setDepartCodeNo(dp.getDepart().getDepartCodeNo());
				regOrder.setDepartName(dp.getDepart().getDepartName());
			}
			
			return regOrder;
		}
		
		
		
		public static Page rspRegisterDetialsImpl(String xml,Page page) {
			List list = new ArrayList();
			if (xml == null) {
				return page;
			}
			SAXBuilder builder = null;
			Document doc = null;
			try {
				builder = new SAXBuilder("org.apache.xerces.parsers.SAXParser");// 读取解析XML文件的驱动
				StringReader sr = new StringReader(xml);
				doc = builder.build(sr);
				Element rootEle = null;
				if (doc != null) {
					rootEle = doc.getRootElement();
					String pageCount= rootEle.getChildText("pageCount").toString().trim();
					if(!pageCount.equals("")&&!pageCount.equals("null")){
						page.setPageCount(Integer.parseInt(pageCount));
					}else{
						page.setPageCount(0);
					}
					String totalCount= rootEle.getChildText("totalCount").toString().trim();
					if(!totalCount.equals("")&&!totalCount.equals("null")){
						page.setTotalCount(Integer.parseInt(totalCount));
					}else{
						page.setTotalCount(0);
					}
					String pageNum= rootEle.getChildText("pageNum").toString().trim();
					if(!pageNum.equals("")&&!pageNum.equals("null")){
						page.setPageNum(Integer.parseInt(pageNum));
					}else{
						page.setPageNum(0);
					}
					List workTimeList = rootEle.getChildren("orderInfo");
					if (workTimeList.size() > 0) {
						for (int i=0;i<workTimeList.size();i++) {
							Element workInfo = (Element)workTimeList.get(i);
							
							String id = workInfo.getChildText("Id").toString().trim();//流水id
							String sickName = workInfo.getChildText("SickName").toString().trim();//患者姓名
							String cardNo = workInfo.getChildText("CardNo").toString().trim();//身份证号
							String phone = workInfo.getChildText("Phone").toString().trim();//联系电话
							String sickInsureType = workInfo.getChildText("SickInsureType").toString().trim();//医保类型
							String sickInsureNo = workInfo.getChildText("SickInsureNo").toString().trim();//医保编号
							String sickSex = workInfo.getChildText("SickSex").toString().trim();//性别
							
//							String Createdate = workInfo.getChildText("Createdate").toString().trim();//预约日期
//							String RegisteredType = workInfo.getChildText("RegisteredType").toString().trim();//预约途径
//							String SeeDoctorDate = workInfo.getChildText("SeeDoctorDate").toString().trim();//就诊日期
//							String DepartName = workInfo.getChildText("DepartName").toString().trim();//医生姓名
//							String DoctorName = workInfo.getChildText("DoctorName").toString().trim();//科室名称
//							
//							String iscancel = workInfo.getChildText("iscancel").toString().trim();//挂号状态
//							String yhIp = workInfo.getChildText("yhIp").toString().trim();//用户ip
							
							Object[] obj = new Object[7];
							obj[0] = id;
							obj[1] = sickName;
							obj[2] = cardNo;
							obj[3] = phone;
							obj[4] = sickInsureType;
							obj[5] = sickInsureNo;
							obj[6] = sickSex;
							
							list.add(obj);
						}
					}
				}
			} catch (JDOMException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			page.setData(list);
			return page;
		}
		
		
		public static RegOrder perfectRegInfoImpl(RegOrder regOrder) {
			WSDoctor ws=new WSDoctor();
			WSDepart dp=new WSDepart();
			try {
				ws=(WSDoctor)regOrder.getRegPipelined().getWorkSchema();
			} catch (Exception e) {
				ws=null;
			}
			try {
				dp=(WSDepart)regOrder.getRegPipelined().getWorkSchema();
			} catch (Exception e) {
				dp=null;
			}
			
			regOrder.setIdentityCard(regOrder.getRegPeople().getIdentityCard());
			regOrder.setMedicalNo(regOrder.getRegPeople().getMedicalNo());
			regOrder.setMedicalType(regOrder.getRegPeople().getMedicalType());
			regOrder.setTrueName(regOrder.getRegPeople().getTrueName());
			regOrder.setSex(regOrder.getRegPeople().getSex());
			regOrder.setMobile(regOrder.getRegPeople().getMobile());
			if(regOrder.getOpeateESQ()!=null){
			regOrder.setOpeateName(regOrder.getOpeateESQ().getTrueName());
			}
			if(regOrder.getCanclePeople()!=null){
				regOrder.setCancleName(regOrder.getCanclePeople().getTrueName());
			}
			if(ws!=null){
				regOrder.setRegType(0);
				regOrder.setWorkDate(ws.getWorkDate());
				regOrder.setRegistryFee(ws.getRegistryFee());
				regOrder.setClinicFee(ws.getClinicFee());
				regOrder.setFactorageFee(ws.getFactorageFee());
				regOrder.setExpertsFee(ws.getExpertsFee());
				regOrder.setHospitalCode(ws.getDepart().getHospital().getHospitalCode());
				regOrder.setHospitalName(ws.getDepart().getHospital().getName());
				regOrder.setDepartCodeNo(ws.getDepart().getDepartCodeNo());
				regOrder.setDepartName(ws.getDepart().getDepartName());
				regOrder.setForWorkNo(ws.getDoctorExpert().getForWorkNo());
				regOrder.setDocName(ws.getDoctorExpert().getName());
			}else if(dp!=null){
				regOrder.setRegType(1);
				regOrder.setWorkDate(dp.getWorkDate());
				regOrder.setRegistryFee(dp.getDepart().getRegistryFee());
				regOrder.setClinicFee(dp.getDepart().getClinicFee());
				regOrder.setHospitalCode(dp.getDepart().getHospital().getHospitalCode());
				regOrder.setHospitalName(dp.getDepart().getHospital().getName());
				regOrder.setDepartCodeNo(dp.getDepart().getDepartCodeNo());
				regOrder.setDepartName(dp.getDepart().getDepartName());
			}
			
			return regOrder;
		}

		public static String reqDepartWorkTime(String hosId,String dpId,String workDate,Long workType) {
			return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
			"<Message>"+
			"<username>zhyl</username>"+
			"<password>zhyl</password>"+
		  	"<workInfo>"+
		  	"<hosId>"+hosId+"</hosId>"+
		  	"<dpId>"+dpId+"</dpId>"+
		    "<workDate>"+workDate+"</workDate>"+
		  	"<workType>"+workType+"</workType>"+
		  	"</workInfo>"+
		  "</Message>";
		}

		public static List rspDepartWorkTime(String xml) {
			List list = new ArrayList();
			if (xml == null) {
				return list;
			}
			SAXBuilder builder = null;
			Document doc = null;
			try {
				builder = new SAXBuilder("org.apache.xerces.parsers.SAXParser");// 读取解析XML文件的驱动
				StringReader sr = new StringReader(xml);
				doc = builder.build(sr);
				Element rootEle = null;
				if (doc != null) {
					rootEle = doc.getRootElement();
					List workTimeList = rootEle.getChildren("workInfo");
					if (workTimeList.size() > 0) {
						for (int i=0;i<workTimeList.size();i++) {
							Element workInfo = (Element)workTimeList.get(i);
							String workTimeId = workInfo.getChildText("workTimeId");
							String startTime = workInfo.getChildText("startTime");
							String endTime = workInfo.getChildText("endTime");
							String totalNum = workInfo.getChildText("totalNum");
							String leftNum = workInfo.getChildText("leftNum");
							Object[] obj = new Object[5];
							obj[0] = workTimeId;
							obj[1] = startTime;
							obj[2] = endTime;
							obj[3] = totalNum;
							obj[4] = leftNum;
							list.add(obj);
						}
					}
				}
			} catch (JDOMException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return list;
			
		}
}
