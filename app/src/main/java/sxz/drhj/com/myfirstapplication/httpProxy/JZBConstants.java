package sxz.drhj.com.myfirstapplication.httpProxy;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;

public class JZBConstants {

    /**
     * 是否开启打印功能  (true: 开启)
     */
    public static final boolean DEBUG_MODE 						= true;/////////////// 上线要改为false ////////////////
    /**
     * 是否要切换host地址、推送appId等。  (true: 切换到测试服务器、推送)
     */
    private static final boolean DEBUG_MODE_ADVANCED 			= true;/////////////// 上线要改为false ////////////////

																/////////////// 上线要改蒲公英更新SDK PGYER_APPID ////////////////

    public static int WIDTH 	= 0;
    public static int HEIGHT 	= 0;
    /**
     * 鹰眼服务ID
     */
    public static final int EagleServiceId	= 103608;
    public static final String CacheFolder	= "jianzhibao";
    /**
     * 企业注册协议
     */
    public static final String ProtocolUrl	= "http://qiye.jianzhibao.com/mobilesource/xieyi_cpn.html";

    private static final String APIIP_Test	= "123.57.143.26";
    private static final String APIIP_Online	= "xiaolaoyiguan.com";

    private static final String APIIP = DEBUG_MODE_ADVANCED ? APIIP_Test : APIIP_Online;
    //非final的类型，方便进入demo体验版，更换host地址。
    public static String BASEURL = DEBUG_MODE_ADVANCED ? "http://" + APIIP + "/qiye/api" : "https://" + APIIP + "/api";
    //-----demo测试服务器用-----
//	public static final String APIIP_Demo	 					= "demo.jianzhibao.com";
//	public static final String APIIP 							= DEBUG_MODE_ADVANCED ? APIIP_Demo : APIIP_Online;
//	public static final String BASEURL 							= DEBUG_MODE_ADVANCED ? "http://" + APIIP + "/api" : "https://" + APIIP + "/api";
	//-----------------------
	
	public static final String LOGIN							= "/user/oauth";
	public static final String GETJOBSLSIT						= "/offline/enterprise/jobs";
	/**获取我的工作列表**/
	public static final String GETRECENTJINAZHI					= "/job/list";
	/**获取保单**/
	public static final String GETBAODANLIST					= "/policy/typelist";
	/**获取保单企业信息*/
	public static final String BAODANPOLICYINFO					= "/policy/policyinfo";
	/**修改营业执照**/
	public static final String UPDATELICENSE					= "/enterprise/updateLicense";
	/**兼职上下架**/
	public static final String PUTAWAYORSOLDOUT					= "/enterprise/job/status";
	/**获取工资列表**/
	public static final String GETSALARYSHEETSLSIT 				= "/offline/enterprise/payrolls?job_id=";
	/**获取需要支付工资的兼职人员**/
	public static final String CREATENEEDPAYOFFUSERS 			= "/salary/create?job_id=";
	/**获取需要参保的兼职人员**/
	public static final String CREATENEEDCANBAOUSERS 			= "/policy/initcreate?job_id=";
	/**查看工资表信息**/
	public static final String SEESALARYINFO		 			= "/salary/edit?job_id=";
	/**查看保单信息**/
	public static final String SEEBAODANINFO		 			= "/policy/edit?job_id=";
	/**获取老的**/
	public static final String GETNEEDPAYOFFUSERS 				= "/offline/enterprise/payroll/logs?job_id=";
	/**提交工资表**/
	public static final String SUMMITSALARYSHEET	 			= "/salary/savecommit";
	/**提交保单**/
	public static final String SUMMITBAODAN			 			= "/policy/savecommit";
	/**保存工资表**/
	public static final String SAVESALARYSHEET	 				= "/salary/save";
	/**保存保单**/
	public static final String SAVEBADN	 						= "/policy/save";
	/**保存工资表**/
	public static final String COMMITSALARYSHEET	 			= "/salary/commit";
	/**提交工资表**/
	public static final String COMMITBAODAN			 			= "/policy/commit";
	/**真正的支付信息发送到服务器获取到charge123.57.143.26  **/
	public static final String SENDREALNEEDPAYOFFUSERS 			= "/pingpp/paycharge";
	/**告诉后台支付成功了**/
	public static final String NOTICECHARGESUCCESS				= "/offline/enterprise/payroll/paysuccess";
	/**找回密码**/
	public static final String FINDPASSWORD						= "/user/repassword";
	/**企业或者领队注册**/
	public static final String REGISTER							= "/user/register";
	/**忘记密码获取验证码**/
	public static final String FINDPASSWORDGETVERIFICATIONCODE	= "/user/verifycodemobile";
	/**关于我顶部信息*/
	public static final String ABOUTMEINFO						= "/user/userinfo";
	/**获取管理员信息*/
	public static final String MANAGERINFO						="/enterprise/highsysuser";
	/**企业信息补全**/
	public static final String ENTERPRISE						= "/enterprise";
	/**上传图片**/
	public static final String UPLOADFILE						= "/enterprise/imageUpload";
	/**获取企业积分记录**/
	public static final String JIFENNOTES						= "/enterprise/captainpaylog";
	/**语音验证码**/
	public static final String CHECKNUM							= "/user/verification-code";
	/**宝币兑换数据**/
	public static final String EXCHANGEINFO						= "/enterprise/depositlog";
	/**删除兼职**/
	public static final String DELETEJIANZHI					= "/enterprise/job/delete";
	/**申请兼职的人员数据**/
	public static final String APPLYJOBINFO						= "/job/logs";
	/**企业营业执照上传**/
	public static final String ENTERPRISEUPLOAD					= "/enterprise/updateLicense";
	/**驳回录用者**/
	public static final String BOHUI							= "/job/logs/delete";
	/**录用申请者**/
	public static final String HIRE								= "/job/logs/save";
	/**录用人才库**/
	public static final String HIRETALENTS						= "/job/logs/sparein";
	/**获取兼职详情**/
	public static final String JIANZHIDETAIL					= "/job/jobinfo";
	/**刷新兼职**/
	public static final String REFRESHJIANZHI					= "/enterprise/job";
	/**刷新兼职**/
	public static final String PUBLISHJIANZHI					= "/job";
	/**修改兼职**/
	public static final String UPDATEJIANZHI					= "/job/updatejob";
	/**修改电话号码**/
	public static final String CHANGEPHONE						= "/user/phone";
	/**修改密码**/
	public static final String CHANGPASSWORD					= "/user/";
	/**修改联系人**/
	public static final String UPDATELINK						= "/enterprise/contact";
	/**得到企业详情**/
	public static final String GETJOBINFO						= "/job/jobinfo";
	/**申请签约**/
	public static final String SIGN								= "/enterprise/contract";
	/**获取用户的银行卡信息**/
	public static final String GetBankData 						= "/allpay/v2.1/bindinfo";
	/**获取所有的因银行卡信息**/
	public static final String GetBankListData 					= "/allpay/v2.1/allbank";
	/**添加银行卡信息**/
	public static final String CreateBank 						= "/allpay/v2.1/bind";
	/**设置默认银行卡**/
	public static final String SetBakDefault 					= "/allpay/v2.1/defaultpay";
	/**删除银行卡**/
	public static final String DelBank 							= "/allpay/v2.1/unbind";
	/**提现**/
	public static final String ENCHASHMENT 						= "/wallet/v2.1/deposit";
	/**提现验证码**/
	public static final String RegistVerificationCodeUrl 		= "/user/verification-code";
	public static final String AREADTA							= "/geo/divisions";
	public static final String PUBLISHAREADTA					= "/job/jobgeography";
	/**是否可以发布兼职**/
	public static final String CANPUBLISHJINAZHI				= "/job/publishjob";
	/**userID**/
	public static final String USERID							= "userID";
	/**enterpriseID**/
	public static final String ENTERPRISEID						= "enterpriseID";
	/**sessionid**/
	public static final String SESSIONID						= "sesisonID";
	/**0:未开通自动投保 1:已开通自动投保**/
	public static final String PolicyStatus 					= "policyStatus";
	/** 是最高投保权限 false 不是最高投保权限**/
	public static final String HighAuth							= "highAuth";
	/**sessionid**/
	public static final String CanLogin							= "canlogin";
	/**用于记录用户角色**/
	public static final String ROLE								= "role";
	/**用来存放用户的cookie  姓名等信息**/
	public static final String USERCONFIG						= "userinfo";
	/**存放token**/
	public static final String TOKENCONFIG						= "tokenInfo";
	/**体验配置*/
	public static final String TESTCONFIG						= "TestInfo";
	/**配置文件*/
	public static final String JZBCONFIG						= "ConfigInfo";

	//体验用户名
	public static final String SP_TAG_TEST_USERNAME				= "testUserName";
	//体验密码
	public static final String SP_TAG_TEST_PASSWORD				= "testPassword";
	//注册时的名字
	public static final String SP_TAG_TEST_R_NAME				= "testRegisterName";
	//注册时的电话
	public static final String SP_TAG_TEST_R_PHONE				= "testRegisterPhone";
	//是否显示过发布兼职的帮助
	public static final String SP_TAG_TEST_PUBLISH_HELP			= "isShowedPublish";
	//是否显示过wifi管理的帮助
	public static final String SP_TAG_TEST_WIFI_HELP			= "isShowedWifi";
	//是否显示过排班管理的帮助
	public static final String SP_TAG_SCHEDULE_HELP				= "isShowedSchedule";
	/**当前登陆是否是demo体验版：  true体验版  false正式版*/
	public static final String SP_TAG_DEMO_LOGIN				= "isDemoLogin";
	public static final String LAST_UPDATETIME					= "lastupdatetime";
	/**验证码记录**/
	public static final String SP_JZB_HPONECODE 				= "phonecode";
    /**
     * 人才储备
     */
    public static final String SP_TAG_TALENTPOOLMODULE 			= "talentPoolModule";
    /**
     * 数据统计
     */
    public static final String SP_TAG_STATISTICMODULE 			= "statisticModule";
    /**
     * 薪酬管理
     */
    public static final String SP_TAG_SALARYMODULE 				= "salaryModule";
    /**
     * 商保管理
     */
    public static final String SP_TAG_COMMERIALINSURANCEMODULE 	= "commerialInsuranceModule";
	/**
	 * 社保管理
	 */
    public static final String SP_TAG_SOCIALSECURITYMODULE 		= "socialSecurityModule";
	/**
	 * 排班管理
	 */
    public static final String SP_TAG_SCHEDULEMODULE 			= "scheduleModule";

	public static final String XIAOMI_TOKEN 					= "xiaomi_token";
	/**获取代金币收入情况**/
	public static final String DAIJINBIINCOME					="/enterprise/coupon/list";
	/**获取代金币支出情况**/
	public static final String DAIJINBIEXPENSE					="/enterprise/coupon/expend";
	/**代金币使用详情**/
	public static final String DAIJINBIDETAIL 					="/enterprise/payroll/logs";
	/**委托兼职宝支付**/
	public static final String PAYOFFBYJZB						="/enterprise/payroll/jzbpay";
	/**获取签到信息**/
	public static final String SIGNINFO 						="/job/registration";
	/**获取工资列表**/
	public static final String REQUESTSALARYLIST				="/salary/list";
	/**获取保单列表**/
	public static final String REQUEBAODANLIST					="/policy/list";
	/**获取工作列表的等级**/
	public static final String REQUESTROLE						="/job/jobrolelist";
	/**删除工资表**/
	public static final String DETELESALARY 					="/salary/delete";
	/**删除参保列表**/
	public static final String DETELECAOBAO						="/policy/delete";
	/**驳回兼职**/
	public static final String REJECTSALARY						="/salary/reject";
	/**获取联系人信息**/
	public static final String REQUSETLINKMANINFO				="/salary/sysuser";
	/**更新用户基本信息**/
	public static final String UPDATEPERSONALINFO				="/user/update";
	/**提交时获取提交信息**/
	public static final String REQUESTSUBMMITINFO				="/salary/commitmsg";
	/**提交时获取提交保单信息**/
	public static final String REQUESTSUBMMITBAODANINFO			="/policy/commitmsg";
	/**获取页面url**/
	public static final String HELPURL							="/help";
	/**获取人才库信息**/
	public static final String TALENTS							="/spare/sparelist";
	/**获取人才详情**/
	public static final String TALENTSINFO						="/spare/userinfo";
	/**删除人才**/
	public static final String REMOVETALENTS					="/spare/delete";
	/**消费统计总数 **/
	public static final String CONSUMETOTAL 					= "/company/consumption/total";
	/**查询消费记录**/
	public static final String CONSUME 							= "/company/consumption/list";
	/**查询消费预付款*/
	public static final String CONSUMELIST 						= "/company/customer/consume/list";
	/**获取用工趋势数据**/
	public static final String DATACOUNTTENDENCY 				= "/job/jobline";
	/**获取兼职所有的所在城市**/
	public static final String DATACOUNTCITIES 					= "/job/jobcity";
	/**获取饼图数据**/
	public static final String DATACOUNTDUTY 					= "/job/jobcake";
	/**获取柱形图数据**/
	public static final String DATACOUNTCYLINER 				= "/job/jobcylinder";
	/**获取wifi列表**/
	public static final String WIFILIST 						= "/wifi/list";
	/**绑定wifi**/
	public static final String BINDWIFI 						= "/wifi/bind";
	/**解除绑定wifi**/
	public static final String UNBINDWIFI 						= "/wifi/unbind";
	/**解除绑定wifi**/
	public static final String IDENTIFYS 						= "/job/identifys";
	/**重新认证**/
	public static final String REIDENTIFY 						= "/job/reidentify";
	/**添加备注**/
	public static final String UPDATEREGISTRATION 				= "/job/updateregistration";
	/**企业管理员下对应的模块列表  帮助中心**/
	public static final String HELPCENTERITEM 					= "/help/modulelist";
	/**通过模块id获取问题列表  帮助中心**/
	public static final String QUESTIONLIST 					= "/help/questionlist";
	/**问题反馈**/
	public static final String FEEDBACK 						= "/help/feedback";
	/**问题搜索**/
	public static final String HELPSEARCH 						= "/help/search";
	/**打开或者关闭sms**/
	public static final String OPENORCLOSESMS 					= "/version/record/update";
	/**获取版本更新记录**/
	public static final String REQUESTVERSIONLIST 				= "/version/record/list";
	/**图形验证码*/
	public static final String ImageVerificationCodeUrl			= "/pcode";
	/**Demo体验  host地址*/
	public static final String TESTDEMOHOSTURL					= "/producttrial/status";
	/**Demo体验  注册*/
	public static final String TESTDEMOREGISTER					= "/demo/applydemomobile";
	/**排班  获取排班人员列表*/
	public static final String SCHEDULEGETUSERS					= "/scheduling/getusers";
	/**排班  创建排班*/
	public static final String SCHEDULECREATESCHEDULE 			= "/scheduling/createsheet";
	/**排班  获取排班信息*/
	public static final String SCHEDULEGETSHEET 				= "/scheduling/getsheet";
	/**排班  修改排班信息*/
	public static final String SCHEDULEUPDATESHEET 				= "/scheduling/updatesheet";
	/**排班  查看排班详情表*/
	public static final String SCHEDULEGETDETAIL				= "/scheduling/getdetail";
	/**排班  设置排班详情表*/
	public static final String SCHEDULESETDETAIL				= "/scheduling/setdetail";
	/**排班  查询班次*/
	public static final String SCHEDULESETSHIFTLIST				= "/scheduling/shiftlist";
	/**排班  添加班次*/
	public static final String SCHEDULESETADDSHIFT				= "/scheduling/addshift";
	/**排班  修改班次*/
	public static final String SCHEDULESETUPDATESHIFT			= "/scheduling/updateshift";
	/**排班  发送通知*/
	public static final String SCHEDULENOTICE					= "/scheduling/notice";
	/**排班  工时统计*/
	public static final String SCHEDULESTATISTICS				= "/scheduling/statistics";
	/**意见反馈*/
	public static final String IDEAFEEDBACK						="/help/feedback";
	/**排班  删除班次*/
	public static final String SCHEDULEDELETESHIFT				= "/scheduling/deleteshift";
	/** 用于登录时补全信息的存放所有的页面 */
	public static final Map<String, Activity> activitiesMap = new HashMap<String, Activity>();
	
    //小米推送  正式id（app_id, app_key）
    private static final String MI_PUSH_APPID_ONLINE = "2882303761517405570";
    private static final String MI_PUSH_APPKEY_ONLINE = "5281740568570";
    //小米推送 测试推送账号
    private static final String MI_PUSH_APPID_TEST = "2882303761517409949";
    private static final String MI_PUSH_APPKEY_TEST = "5761740961949";
    /**
     * 小米推送AppId
     */
    public static final String MI_PUSH_APPID = DEBUG_MODE_ADVANCED ? MI_PUSH_APPID_TEST : MI_PUSH_APPID_ONLINE;
    /**
     * 小米推送AppKey
     */
    public static final String MI_PUSH_APPKEY = DEBUG_MODE_ADVANCED ? MI_PUSH_APPKEY_TEST : MI_PUSH_APPKEY_ONLINE;


    /**
     * 获取排班sheet列表
     **/
    public static final String GRADESHEETINFORMATION = "/scheduling/sheetlist";
    /**
     * 删除排班sheet列表
     */
    public static final String DELETEGRADESHEETINFORMATION = "/scheduling/deletesheet";

}
