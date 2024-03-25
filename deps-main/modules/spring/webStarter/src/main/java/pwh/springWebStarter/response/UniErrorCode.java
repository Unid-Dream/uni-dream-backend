package pwh.springWebStarter.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.util.Assert;
import pwh.coreStarter.exception.UnifiedException;

/**
 * 错误码集合
 */
public enum UniErrorCode{
    /**
     * 错误码集合
     * 内部错误码< 客户端错误码< 第三方错误码< 业务错误码
     * 所以我们应该尽可能的把错误码的数量留给业务错误码
     * ---------------------------
     * *******新的设计**********
     * 1~99 为内部错误码（框架本身的错误）
     * 100~999 客户端错误码 （客户端异常调用之类的错误）
     * 1000~9999为第三方错误码 （代码正常，但是第三方异常）
     * 10000~99999 为业务逻辑 错误码 （无异常，代码正常流转，并返回提示给用户）
     * 由于系统内的错误码都是独一无二的，所以错误码应该放在common包集中管理
     * ---------------------------
     * 总体设计就是值越小  错误严重性越高
     * 目前10000~19999是初始系统内嵌功能使用的错误码，后续开发者可以直接使用20000以上的错误码作为业务错误码
     */
    INNTERAL_SYSTEM_ERROR(1,"系统异常"),


    // ---------------------------------- EDUCATOR -----------------------------------------------
    //当前时间段无法更改状态
    EDUCATOR_CALENDAR_TIME_SLOT_CAN_NOT_CHANGE(10601,"当前时间段无法更改"),
    SLOT_CAN_NOT_CHANGE_TO_UNAVAILABLE(10602," 当前时间段不是空闲状态,不可更改"),

    EDUCATOR_NOT_EXIST(10603," 当前Educator不存在"),
    EDUCATOR_HOURLY_RATE_IS_NULL(10604," 当前Educator, HourlyRate为空"),
    EDUCATOR_CALENDAR_NOT_EXIST(10605," 当前Educator Calendar不存在"),
    EDUCATOR_CALENDAR_START_TIME_CAN_NOT_MORE_THEN_THREE_MONTH(10606," Educator Calendar设定开始时间不能超过3个月"),

    EDUCATOR_CAN_NOT_REJECT(10607,"无法拒绝当前学生的订单"),
    EDUCATOR_CAN_NOT_ACCEPT(10608,"无法接受当前学生订单"),

    EDUCATOR_UPDATE_PROFILE_FAIL(113,"档案资料更新失败"),


    // ---------------------------------- STUDENT -----------------------------------------------
    STUDENT_PAYMENT_TRANSACTION_NOT_EXIST(10705,"当前交易订单不存在"),
    STUDENT_PAYMENT_TRANSACTION_IS_ALREADY_EXIST(10706,"当前交易订单已存在,不允许重复提交"),
    STUDENT_PAYMENT_TRANSACTION_FAIL_TO_PAY_ON_ALIPAY(10707, "支付调用失败"),

    // ---------------------------------- USER -----------------------------------------------
    USER_PROFILE_ID_NOT_EXIST(10801, "用户不存在"),

    // ---------------------------------- Course Event  -----------------------------------------------

    COURSE_EVENT_NOT_EXIST(10901, "课程不存在"),

    // ---------------------------------- Academic Major -----------------------------------------------
    ACADEMIC_MAJOR_IS_NOT_EXIST(11001, "Academic Major 不存在!!!"),
    ACADEMIC_SUBJECT_IS_NOT_EXIST(11002, "Academic Subject 不存在!!!"),
    ACADEMIC_SUBJECT_MAP_IS_NOT_EXIST(11003, "Academic Subject Map 不存在!!!"),

    // ---------------------------------- Country -----------------------------------------------
    COUNTRY_IS_NOT_EXIST(12001, "Country 不存在!!!"),
    // ---------------------------------- Language -----------------------------------------------
    LANGUAGE_IS_NOT_EXIST(13001, "Language 不存在!!!"),


    // ---------------------------------- Description -----------------------------------------------
    DESCRIPTION_IS_NOT_EXIST(14001, "Description 不存在!!!"),

    // ---------------------------------- School -----------------------------------------------
    SCHOOL_LEVEL_IS_NOT_EXIST(15001, "School Level 不存在!!!"),
    // ---------------------------------- Interview Skills  -----------------------------------------------
    INTERVIEW_SKILLS_IS_NOT_EXIST(16001, " Interview Skills 不存在!!!"),

    INTERVIEW_SKILLS_SCORE_IS_ERROR(16002, " Interview Skills Score 不存在!!!"),

    // ---------------------------------- Student Milestone -----------------------------------------------
    STUDENT_MILESTONE_QUESTION_IS_NOT_EXIST(17001, " Student Milestone Question 不存在!!!"),
    STUDENT_MILESTONE_ANSWER_IS_NOT_EXIST(17002, " Student Milestone Answer 不存在!!!"),

    ;

    private final String code;
    private final String msg;
    private final UnifiedException.I18n i18n;

    UniErrorCode(int code, String msg) {
        this.code = String.valueOf(code);
        this.msg = msg;
        this.i18n = new UnifiedException.I18n("",this.msg,"");
    }

    UniErrorCode(int code, String msg, UnifiedException.I18n i18n) {
        this.code = String.valueOf(code);
        this.msg = msg;
        this.i18n = i18n;
    }

    public String code() {
        return this.code;
    }

    public String message() {
        return this.msg;
    }

    public UnifiedException.I18n i18n(){
        return this.i18n;
    }




}
