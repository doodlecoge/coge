package com.szwx.yht.bean;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 分时段排班信息实体BEAN
 * @author zhangyj
 * @date Jun 19, 2012
 */
@Entity
@Table(name="YHT_REG_PIPELINED")
@Proxy(lazy=true)
@org.hibernate.annotations.Entity(dynamicInsert=true,dynamicUpdate=true,selectBeforeUpdate=true)
public class RegPipelined implements Serializable{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -4628368783242386631L;
	@Id
	@Column(name="CODE")
	@SequenceGenerator(allocationSize=1,name="SEQ_ID_GEN",sequenceName="SEQ_REG_PIPELINED_YHT")
	@GeneratedValue(generator="SEQ_ID_GEN",strategy= GenerationType.SEQUENCE)
	private long code;							//分时段流水号
	@ManyToOne
	@JoinColumn(name="WORK_SCHEMA")
	private WorkSchema workSchema;				//排班信息
//	@ManyToOne
//	@JoinColumn(name="HOSPITAL_PIPE")
//	private HospitalPipe hospitalPipe;			//排班时间段信息
	@Column(name="MAX_REG")
	private int maxReg;							//时段内能挂的最多号数值
	@Column(name="INIT_REG")
	private int ininReg;						//初始编号
	@Column(name="SPACING_INT")
	private int spacingInt;						//间隔号
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="TAKE_VLIDITY")
	private Date TakeVlidity;					//取号有效期
	@Column(name="WORK_TYPE")
	private int workType;		                //1上午2下午
	@Column(name="STATE")
	private Integer state;						//0:有效；1：停诊；2：已满
	
/*	@Version
	private int version;						//版本号
*/	//////////////////////////GET SET/////////////////////////////////////
	
	public WorkSchema getWorkSchema() {
		return workSchema;
	}

	public void setWorkSchema(WorkSchema workSchema) {
		this.workSchema = workSchema;
	}

	public int getMaxReg() {
		return maxReg;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public void setMaxReg(int maxReg) {
		this.maxReg = maxReg;
	}

	public int getIninReg() {
		return ininReg;
	}

	public void setIninReg(int ininReg) {
		this.ininReg = ininReg;
	}

	public Date getTakeVlidity() {
		return TakeVlidity;
	}

	public void setTakeVlidity(Date takeVlidity) {
		TakeVlidity = takeVlidity;
	}

	public long getCode() {
		return code;
	}

	public void setCode(long code) {
		this.code = code;
	}

//	public HospitalPipe getHospitalPipe() {
//		return hospitalPipe;
//	}
//
//	public void setHospitalPipe(HospitalPipe hospitalPipe) {
//		this.hospitalPipe = hospitalPipe;
//	}

	public int getSpacingInt() {
		return spacingInt;
	}

	public void setSpacingInt(int spacingInt) {
		this.spacingInt = spacingInt;
	}

	public int getWorkType() {
		return workType;
	}

	public void setWorkType(int workType) {
		this.workType = workType;
	}
}
