/*
 * This file is generated by jOOQ.
 */
package unid.jooqMono.model;


import javax.annotation.processing.Generated;

import unid.jooqMono.model.tables.AcademicMajorSubjectMapTable;
import unid.jooqMono.model.tables.AcademicMajorTable;
import unid.jooqMono.model.tables.AcademicSubjectResourceTable;
import unid.jooqMono.model.tables.AcademicSubjectTable;
import unid.jooqMono.model.tables.CityTable;
import unid.jooqMono.model.tables.CountryTable;
import unid.jooqMono.model.tables.CourseEventTable;
import unid.jooqMono.model.tables.CourseTable;
import unid.jooqMono.model.tables.CurriculumTable;
import unid.jooqMono.model.tables.EcaCourseAcademicMajorEducationLevelMapTable;
import unid.jooqMono.model.tables.EcaCourseAcademicMapTable;
import unid.jooqMono.model.tables.EcaCourseTable;
import unid.jooqMono.model.tables.EcaProfileOptionTable;
import unid.jooqMono.model.tables.EcaProfileSectionTable;
import unid.jooqMono.model.tables.EducationLevelTable;
import unid.jooqMono.model.tables.EducatorCalendarExtensionTable;
import unid.jooqMono.model.tables.EducatorCalendarTable;
import unid.jooqMono.model.tables.EducatorProfileCopy1Table;
import unid.jooqMono.model.tables.EducatorProfileExpertiseAcademicMapTable;
import unid.jooqMono.model.tables.EducatorProfileExtensionTable;
import unid.jooqMono.model.tables.EducatorProfileLanguageMapTable;
import unid.jooqMono.model.tables.EducatorProfileTable;
import unid.jooqMono.model.tables.EducatorSchoolTable;
import unid.jooqMono.model.tables.EducatorSessionNoteItemTable;
import unid.jooqMono.model.tables.EducatorSessionNoteMapTable;
import unid.jooqMono.model.tables.EducatorSessionNoteTable;
import unid.jooqMono.model.tables.EventExtensionTable;
import unid.jooqMono.model.tables.EventScheduleTimeTable;
import unid.jooqMono.model.tables.EventTable;
import unid.jooqMono.model.tables.ExpertiseAcademicMajorMapTable;
import unid.jooqMono.model.tables.ExpertiseTable;
import unid.jooqMono.model.tables.I18nTable;
import unid.jooqMono.model.tables.InterviewTopicTable;
import unid.jooqMono.model.tables.LanguageTable;
import unid.jooqMono.model.tables.OpportunityTable;
import unid.jooqMono.model.tables.PassionMajorTable;
import unid.jooqMono.model.tables.PassionSubjectAnswerTable;
import unid.jooqMono.model.tables.PassionSubjectBookTable;
import unid.jooqMono.model.tables.PassionSubjectPodcastTable;
import unid.jooqMono.model.tables.PassionSubjectTable;
import unid.jooqMono.model.tables.PassionSubjectVideoTable;
import unid.jooqMono.model.tables.PricingTable;
import unid.jooqMono.model.tables.SchoolExtensionTable;
import unid.jooqMono.model.tables.SchoolIdentityTable;
import unid.jooqMono.model.tables.SchoolTable;
import unid.jooqMono.model.tables.StudentBookingSurveyAnswerMapTable;
import unid.jooqMono.model.tables.StudentBookingSurveyMapTable;
import unid.jooqMono.model.tables.StudentEcaProfileMapTable;
import unid.jooqMono.model.tables.StudentMilestoneMapTable;
import unid.jooqMono.model.tables.StudentMilestoneOptionsTable;
import unid.jooqMono.model.tables.StudentMilestoneQuestionnaireTable;
import unid.jooqMono.model.tables.StudentMilestoneTable;
import unid.jooqMono.model.tables.StudentPaymentTransactionTable;
import unid.jooqMono.model.tables.StudentProfilePredictedGradeTable;
import unid.jooqMono.model.tables.StudentProfileQuestionnaireAnswerMapTable;
import unid.jooqMono.model.tables.StudentProfileQuestionnaireMapTable;
import unid.jooqMono.model.tables.StudentProfileSchoolReportTable;
import unid.jooqMono.model.tables.StudentProfileTable;
import unid.jooqMono.model.tables.StudentQuestionnaireAnswerTable;
import unid.jooqMono.model.tables.StudentQuestionnaireQuestionTable;
import unid.jooqMono.model.tables.StudentQuestionnaireSectionTable;
import unid.jooqMono.model.tables.StudentQuestionnaireTable;
import unid.jooqMono.model.tables.StudentSessionSurveyAnswerTable;
import unid.jooqMono.model.tables.StudentSessionSurveyQuestionTable;
import unid.jooqMono.model.tables.StudentSessionSurveyTable;
import unid.jooqMono.model.tables.StudentUploadedInterviewTable;
import unid.jooqMono.model.tables.StudentUploadedSupervisorReviewTable;
import unid.jooqMono.model.tables.StudentUploadedWritingTable;
import unid.jooqMono.model.tables.TagTable;
import unid.jooqMono.model.tables.TaggingTable;
import unid.jooqMono.model.tables.UserNotificationTable;
import unid.jooqMono.model.tables.UserTable;
import unid.jooqMono.model.tables.WritingTopicTable;
import unid.jooqMono.model.tables._AuditLogAcademicMajorSubjectMapTable;
import unid.jooqMono.model.tables._AuditLogAcademicMajorTable;
import unid.jooqMono.model.tables._AuditLogAcademicSubjectResourceTable;
import unid.jooqMono.model.tables._AuditLogAcademicSubjectTable;
import unid.jooqMono.model.tables._AuditLogCityTable;
import unid.jooqMono.model.tables._AuditLogCountryTable;
import unid.jooqMono.model.tables._AuditLogCourseEventTable;
import unid.jooqMono.model.tables._AuditLogCourseTable;
import unid.jooqMono.model.tables._AuditLogCurriculumTable;
import unid.jooqMono.model.tables._AuditLogEcaCourseAcademicMajorEducationLevelMapTable;
import unid.jooqMono.model.tables._AuditLogEcaCourseAcademicMapTable;
import unid.jooqMono.model.tables._AuditLogEcaCourseTable;
import unid.jooqMono.model.tables._AuditLogEducationLevelTable;
import unid.jooqMono.model.tables._AuditLogEducatorCalendarTable;
import unid.jooqMono.model.tables._AuditLogEducatorProfileExpertiseAcademicMapTable;
import unid.jooqMono.model.tables._AuditLogEducatorProfileLanguageMapTable;
import unid.jooqMono.model.tables._AuditLogEducatorProfileTable;
import unid.jooqMono.model.tables._AuditLogEducatorSessionNoteItemTable;
import unid.jooqMono.model.tables._AuditLogEducatorSessionNoteMapTable;
import unid.jooqMono.model.tables._AuditLogEducatorSessionNoteTable;
import unid.jooqMono.model.tables._AuditLogEventTable;
import unid.jooqMono.model.tables._AuditLogExpertiseAcademicMajorMapTable;
import unid.jooqMono.model.tables._AuditLogExpertiseTable;
import unid.jooqMono.model.tables._AuditLogI18nTable;
import unid.jooqMono.model.tables._AuditLogInterviewTopicTable;
import unid.jooqMono.model.tables._AuditLogLanguageTable;
import unid.jooqMono.model.tables._AuditLogOpportunityTable;
import unid.jooqMono.model.tables._AuditLogPricingTable;
import unid.jooqMono.model.tables._AuditLogSchoolIdentityTable;
import unid.jooqMono.model.tables._AuditLogSchoolTable;
import unid.jooqMono.model.tables._AuditLogStudentBookingSurveyAnswerMapTable;
import unid.jooqMono.model.tables._AuditLogStudentBookingSurveyMapTable;
import unid.jooqMono.model.tables._AuditLogStudentPaymentTransactionTable;
import unid.jooqMono.model.tables._AuditLogStudentProfilePredictedGradeTable;
import unid.jooqMono.model.tables._AuditLogStudentProfileQuestionnaireAnswerMapTable;
import unid.jooqMono.model.tables._AuditLogStudentProfileQuestionnaireMapTable;
import unid.jooqMono.model.tables._AuditLogStudentProfileSchoolReportTable;
import unid.jooqMono.model.tables._AuditLogStudentProfileTable;
import unid.jooqMono.model.tables._AuditLogStudentQuestionnaireAnswerTable;
import unid.jooqMono.model.tables._AuditLogStudentQuestionnaireQuestionTable;
import unid.jooqMono.model.tables._AuditLogStudentQuestionnaireSectionTable;
import unid.jooqMono.model.tables._AuditLogStudentQuestionnaireTable;
import unid.jooqMono.model.tables._AuditLogStudentSessionSurveyAnswerTable;
import unid.jooqMono.model.tables._AuditLogStudentSessionSurveyQuestionTable;
import unid.jooqMono.model.tables._AuditLogStudentSessionSurveyTable;
import unid.jooqMono.model.tables._AuditLogStudentUploadedInterviewTable;
import unid.jooqMono.model.tables._AuditLogStudentUploadedSupervisorReviewTable;
import unid.jooqMono.model.tables._AuditLogStudentUploadedWritingTable;
import unid.jooqMono.model.tables._AuditLogTagTable;
import unid.jooqMono.model.tables._AuditLogUserTable;
import unid.jooqMono.model.tables._AuditLogWritingTopicTable;


/**
 * Convenience access to all tables in public.
 */
@Generated(
    value = {
        "https://www.jooq.org",
        "jOOQ version:3.15.12"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Tables {

    /**
     * The table <code>public._audit_log_academic_major</code>.
     */
    public static final _AuditLogAcademicMajorTable _AUDIT_LOG_ACADEMIC_MAJOR = _AuditLogAcademicMajorTable._AUDIT_LOG_ACADEMIC_MAJOR;

    /**
     * The table <code>public._audit_log_academic_major_subject_map</code>.
     */
    public static final _AuditLogAcademicMajorSubjectMapTable _AUDIT_LOG_ACADEMIC_MAJOR_SUBJECT_MAP = _AuditLogAcademicMajorSubjectMapTable._AUDIT_LOG_ACADEMIC_MAJOR_SUBJECT_MAP;

    /**
     * The table <code>public._audit_log_academic_subject</code>.
     */
    public static final _AuditLogAcademicSubjectTable _AUDIT_LOG_ACADEMIC_SUBJECT = _AuditLogAcademicSubjectTable._AUDIT_LOG_ACADEMIC_SUBJECT;

    /**
     * The table <code>public._audit_log_academic_subject_resource</code>.
     */
    public static final _AuditLogAcademicSubjectResourceTable _AUDIT_LOG_ACADEMIC_SUBJECT_RESOURCE = _AuditLogAcademicSubjectResourceTable._AUDIT_LOG_ACADEMIC_SUBJECT_RESOURCE;

    /**
     * The table <code>public._audit_log_city</code>.
     */
    public static final _AuditLogCityTable _AUDIT_LOG_CITY = _AuditLogCityTable._AUDIT_LOG_CITY;

    /**
     * The table <code>public._audit_log_country</code>.
     */
    public static final _AuditLogCountryTable _AUDIT_LOG_COUNTRY = _AuditLogCountryTable._AUDIT_LOG_COUNTRY;

    /**
     * The table <code>public._audit_log_course</code>.
     */
    public static final _AuditLogCourseTable _AUDIT_LOG_COURSE = _AuditLogCourseTable._AUDIT_LOG_COURSE;

    /**
     * The table <code>public._audit_log_course_event</code>.
     */
    public static final _AuditLogCourseEventTable _AUDIT_LOG_COURSE_EVENT = _AuditLogCourseEventTable._AUDIT_LOG_COURSE_EVENT;

    /**
     * The table <code>public._audit_log_curriculum</code>.
     */
    public static final _AuditLogCurriculumTable _AUDIT_LOG_CURRICULUM = _AuditLogCurriculumTable._AUDIT_LOG_CURRICULUM;

    /**
     * The table <code>public._audit_log_eca_course</code>.
     */
    public static final _AuditLogEcaCourseTable _AUDIT_LOG_ECA_COURSE = _AuditLogEcaCourseTable._AUDIT_LOG_ECA_COURSE;

    /**
     * The table
     * <code>public._audit_log_eca_course_academic_major_education_level_map</code>.
     */
    public static final _AuditLogEcaCourseAcademicMajorEducationLevelMapTable _AUDIT_LOG_ECA_COURSE_ACADEMIC_MAJOR_EDUCATION_LEVEL_MAP = _AuditLogEcaCourseAcademicMajorEducationLevelMapTable._AUDIT_LOG_ECA_COURSE_ACADEMIC_MAJOR_EDUCATION_LEVEL_MAP;

    /**
     * The table <code>public._audit_log_eca_course_academic_map</code>.
     */
    public static final _AuditLogEcaCourseAcademicMapTable _AUDIT_LOG_ECA_COURSE_ACADEMIC_MAP = _AuditLogEcaCourseAcademicMapTable._AUDIT_LOG_ECA_COURSE_ACADEMIC_MAP;

    /**
     * The table <code>public._audit_log_education_level</code>.
     */
    public static final _AuditLogEducationLevelTable _AUDIT_LOG_EDUCATION_LEVEL = _AuditLogEducationLevelTable._AUDIT_LOG_EDUCATION_LEVEL;

    /**
     * The table <code>public._audit_log_educator_calendar</code>.
     */
    public static final _AuditLogEducatorCalendarTable _AUDIT_LOG_EDUCATOR_CALENDAR = _AuditLogEducatorCalendarTable._AUDIT_LOG_EDUCATOR_CALENDAR;

    /**
     * The table <code>public._audit_log_educator_profile</code>.
     */
    public static final _AuditLogEducatorProfileTable _AUDIT_LOG_EDUCATOR_PROFILE = _AuditLogEducatorProfileTable._AUDIT_LOG_EDUCATOR_PROFILE;

    /**
     * The table
     * <code>public._audit_log_educator_profile_expertise_academic_map</code>.
     */
    public static final _AuditLogEducatorProfileExpertiseAcademicMapTable _AUDIT_LOG_EDUCATOR_PROFILE_EXPERTISE_ACADEMIC_MAP = _AuditLogEducatorProfileExpertiseAcademicMapTable._AUDIT_LOG_EDUCATOR_PROFILE_EXPERTISE_ACADEMIC_MAP;

    /**
     * The table <code>public._audit_log_educator_profile_language_map</code>.
     */
    public static final _AuditLogEducatorProfileLanguageMapTable _AUDIT_LOG_EDUCATOR_PROFILE_LANGUAGE_MAP = _AuditLogEducatorProfileLanguageMapTable._AUDIT_LOG_EDUCATOR_PROFILE_LANGUAGE_MAP;

    /**
     * The table <code>public._audit_log_educator_session_note</code>.
     */
    public static final _AuditLogEducatorSessionNoteTable _AUDIT_LOG_EDUCATOR_SESSION_NOTE = _AuditLogEducatorSessionNoteTable._AUDIT_LOG_EDUCATOR_SESSION_NOTE;

    /**
     * The table <code>public._audit_log_educator_session_note_item</code>.
     */
    public static final _AuditLogEducatorSessionNoteItemTable _AUDIT_LOG_EDUCATOR_SESSION_NOTE_ITEM = _AuditLogEducatorSessionNoteItemTable._AUDIT_LOG_EDUCATOR_SESSION_NOTE_ITEM;

    /**
     * The table <code>public._audit_log_educator_session_note_map</code>.
     */
    public static final _AuditLogEducatorSessionNoteMapTable _AUDIT_LOG_EDUCATOR_SESSION_NOTE_MAP = _AuditLogEducatorSessionNoteMapTable._AUDIT_LOG_EDUCATOR_SESSION_NOTE_MAP;

    /**
     * The table <code>public._audit_log_event</code>.
     */
    public static final _AuditLogEventTable _AUDIT_LOG_EVENT = _AuditLogEventTable._AUDIT_LOG_EVENT;

    /**
     * The table <code>public._audit_log_expertise</code>.
     */
    public static final _AuditLogExpertiseTable _AUDIT_LOG_EXPERTISE = _AuditLogExpertiseTable._AUDIT_LOG_EXPERTISE;

    /**
     * The table <code>public._audit_log_expertise_academic_major_map</code>.
     */
    public static final _AuditLogExpertiseAcademicMajorMapTable _AUDIT_LOG_EXPERTISE_ACADEMIC_MAJOR_MAP = _AuditLogExpertiseAcademicMajorMapTable._AUDIT_LOG_EXPERTISE_ACADEMIC_MAJOR_MAP;

    /**
     * The table <code>public._audit_log_i18n</code>.
     */
    public static final _AuditLogI18nTable _AUDIT_LOG_I18N = _AuditLogI18nTable._AUDIT_LOG_I18N;

    /**
     * The table <code>public._audit_log_interview_topic</code>.
     */
    public static final _AuditLogInterviewTopicTable _AUDIT_LOG_INTERVIEW_TOPIC = _AuditLogInterviewTopicTable._AUDIT_LOG_INTERVIEW_TOPIC;

    /**
     * The table <code>public._audit_log_language</code>.
     */
    public static final _AuditLogLanguageTable _AUDIT_LOG_LANGUAGE = _AuditLogLanguageTable._AUDIT_LOG_LANGUAGE;

    /**
     * The table <code>public._audit_log_opportunity</code>.
     */
    public static final _AuditLogOpportunityTable _AUDIT_LOG_OPPORTUNITY = _AuditLogOpportunityTable._AUDIT_LOG_OPPORTUNITY;

    /**
     * The table <code>public._audit_log_pricing</code>.
     */
    public static final _AuditLogPricingTable _AUDIT_LOG_PRICING = _AuditLogPricingTable._AUDIT_LOG_PRICING;

    /**
     * The table <code>public._audit_log_school</code>.
     */
    public static final _AuditLogSchoolTable _AUDIT_LOG_SCHOOL = _AuditLogSchoolTable._AUDIT_LOG_SCHOOL;

    /**
     * The table <code>public._audit_log_school_identity</code>.
     */
    public static final _AuditLogSchoolIdentityTable _AUDIT_LOG_SCHOOL_IDENTITY = _AuditLogSchoolIdentityTable._AUDIT_LOG_SCHOOL_IDENTITY;

    /**
     * The table
     * <code>public._audit_log_student_booking_survey_answer_map</code>.
     */
    public static final _AuditLogStudentBookingSurveyAnswerMapTable _AUDIT_LOG_STUDENT_BOOKING_SURVEY_ANSWER_MAP = _AuditLogStudentBookingSurveyAnswerMapTable._AUDIT_LOG_STUDENT_BOOKING_SURVEY_ANSWER_MAP;

    /**
     * The table <code>public._audit_log_student_booking_survey_map</code>.
     */
    public static final _AuditLogStudentBookingSurveyMapTable _AUDIT_LOG_STUDENT_BOOKING_SURVEY_MAP = _AuditLogStudentBookingSurveyMapTable._AUDIT_LOG_STUDENT_BOOKING_SURVEY_MAP;

    /**
     * The table <code>public._audit_log_student_payment_transaction</code>.
     */
    public static final _AuditLogStudentPaymentTransactionTable _AUDIT_LOG_STUDENT_PAYMENT_TRANSACTION = _AuditLogStudentPaymentTransactionTable._AUDIT_LOG_STUDENT_PAYMENT_TRANSACTION;

    /**
     * The table <code>public._audit_log_student_profile</code>.
     */
    public static final _AuditLogStudentProfileTable _AUDIT_LOG_STUDENT_PROFILE = _AuditLogStudentProfileTable._AUDIT_LOG_STUDENT_PROFILE;

    /**
     * The table <code>public._audit_log_student_profile_predicted_grade</code>.
     */
    public static final _AuditLogStudentProfilePredictedGradeTable _AUDIT_LOG_STUDENT_PROFILE_PREDICTED_GRADE = _AuditLogStudentProfilePredictedGradeTable._AUDIT_LOG_STUDENT_PROFILE_PREDICTED_GRADE;

    /**
     * The table
     * <code>public._audit_log_student_profile_questionnaire_answer_map</code>.
     */
    public static final _AuditLogStudentProfileQuestionnaireAnswerMapTable _AUDIT_LOG_STUDENT_PROFILE_QUESTIONNAIRE_ANSWER_MAP = _AuditLogStudentProfileQuestionnaireAnswerMapTable._AUDIT_LOG_STUDENT_PROFILE_QUESTIONNAIRE_ANSWER_MAP;

    /**
     * The table
     * <code>public._audit_log_student_profile_questionnaire_map</code>.
     */
    public static final _AuditLogStudentProfileQuestionnaireMapTable _AUDIT_LOG_STUDENT_PROFILE_QUESTIONNAIRE_MAP = _AuditLogStudentProfileQuestionnaireMapTable._AUDIT_LOG_STUDENT_PROFILE_QUESTIONNAIRE_MAP;

    /**
     * The table <code>public._audit_log_student_profile_school_report</code>.
     */
    public static final _AuditLogStudentProfileSchoolReportTable _AUDIT_LOG_STUDENT_PROFILE_SCHOOL_REPORT = _AuditLogStudentProfileSchoolReportTable._AUDIT_LOG_STUDENT_PROFILE_SCHOOL_REPORT;

    /**
     * The table <code>public._audit_log_student_questionnaire</code>.
     */
    public static final _AuditLogStudentQuestionnaireTable _AUDIT_LOG_STUDENT_QUESTIONNAIRE = _AuditLogStudentQuestionnaireTable._AUDIT_LOG_STUDENT_QUESTIONNAIRE;

    /**
     * The table <code>public._audit_log_student_questionnaire_answer</code>.
     */
    public static final _AuditLogStudentQuestionnaireAnswerTable _AUDIT_LOG_STUDENT_QUESTIONNAIRE_ANSWER = _AuditLogStudentQuestionnaireAnswerTable._AUDIT_LOG_STUDENT_QUESTIONNAIRE_ANSWER;

    /**
     * The table <code>public._audit_log_student_questionnaire_question</code>.
     */
    public static final _AuditLogStudentQuestionnaireQuestionTable _AUDIT_LOG_STUDENT_QUESTIONNAIRE_QUESTION = _AuditLogStudentQuestionnaireQuestionTable._AUDIT_LOG_STUDENT_QUESTIONNAIRE_QUESTION;

    /**
     * The table <code>public._audit_log_student_questionnaire_section</code>.
     */
    public static final _AuditLogStudentQuestionnaireSectionTable _AUDIT_LOG_STUDENT_QUESTIONNAIRE_SECTION = _AuditLogStudentQuestionnaireSectionTable._AUDIT_LOG_STUDENT_QUESTIONNAIRE_SECTION;

    /**
     * The table <code>public._audit_log_student_session_survey</code>.
     */
    public static final _AuditLogStudentSessionSurveyTable _AUDIT_LOG_STUDENT_SESSION_SURVEY = _AuditLogStudentSessionSurveyTable._AUDIT_LOG_STUDENT_SESSION_SURVEY;

    /**
     * The table <code>public._audit_log_student_session_survey_answer</code>.
     */
    public static final _AuditLogStudentSessionSurveyAnswerTable _AUDIT_LOG_STUDENT_SESSION_SURVEY_ANSWER = _AuditLogStudentSessionSurveyAnswerTable._AUDIT_LOG_STUDENT_SESSION_SURVEY_ANSWER;

    /**
     * The table <code>public._audit_log_student_session_survey_question</code>.
     */
    public static final _AuditLogStudentSessionSurveyQuestionTable _AUDIT_LOG_STUDENT_SESSION_SURVEY_QUESTION = _AuditLogStudentSessionSurveyQuestionTable._AUDIT_LOG_STUDENT_SESSION_SURVEY_QUESTION;

    /**
     * The table <code>public._audit_log_student_uploaded_interview</code>.
     */
    public static final _AuditLogStudentUploadedInterviewTable _AUDIT_LOG_STUDENT_UPLOADED_INTERVIEW = _AuditLogStudentUploadedInterviewTable._AUDIT_LOG_STUDENT_UPLOADED_INTERVIEW;

    /**
     * The table
     * <code>public._audit_log_student_uploaded_supervisor_review</code>.
     */
    public static final _AuditLogStudentUploadedSupervisorReviewTable _AUDIT_LOG_STUDENT_UPLOADED_SUPERVISOR_REVIEW = _AuditLogStudentUploadedSupervisorReviewTable._AUDIT_LOG_STUDENT_UPLOADED_SUPERVISOR_REVIEW;

    /**
     * The table <code>public._audit_log_student_uploaded_writing</code>.
     */
    public static final _AuditLogStudentUploadedWritingTable _AUDIT_LOG_STUDENT_UPLOADED_WRITING = _AuditLogStudentUploadedWritingTable._AUDIT_LOG_STUDENT_UPLOADED_WRITING;

    /**
     * The table <code>public._audit_log_tag</code>.
     */
    public static final _AuditLogTagTable _AUDIT_LOG_TAG = _AuditLogTagTable._AUDIT_LOG_TAG;

    /**
     * The table <code>public._audit_log_user</code>.
     */
    public static final _AuditLogUserTable _AUDIT_LOG_USER = _AuditLogUserTable._AUDIT_LOG_USER;

    /**
     * The table <code>public._audit_log_writing_topic</code>.
     */
    public static final _AuditLogWritingTopicTable _AUDIT_LOG_WRITING_TOPIC = _AuditLogWritingTopicTable._AUDIT_LOG_WRITING_TOPIC;

    /**
     * The table <code>public.academic_major</code>.
     */
    public static final AcademicMajorTable ACADEMIC_MAJOR = AcademicMajorTable.ACADEMIC_MAJOR;

    /**
     * The table <code>public.academic_major_subject_map</code>.
     */
    public static final AcademicMajorSubjectMapTable ACADEMIC_MAJOR_SUBJECT_MAP = AcademicMajorSubjectMapTable.ACADEMIC_MAJOR_SUBJECT_MAP;

    /**
     * The table <code>public.academic_subject</code>.
     */
    public static final AcademicSubjectTable ACADEMIC_SUBJECT = AcademicSubjectTable.ACADEMIC_SUBJECT;

    /**
     * The table <code>public.academic_subject_resource</code>.
     */
    public static final AcademicSubjectResourceTable ACADEMIC_SUBJECT_RESOURCE = AcademicSubjectResourceTable.ACADEMIC_SUBJECT_RESOURCE;

    /**
     * The table <code>public.city</code>.
     */
    public static final CityTable CITY = CityTable.CITY;

    /**
     * The table <code>public.country</code>.
     */
    public static final CountryTable COUNTRY = CountryTable.COUNTRY;

    /**
     * The table <code>public.course</code>.
     */
    public static final CourseTable COURSE = CourseTable.COURSE;

    /**
     * The table <code>public.course_event</code>.
     */
    public static final CourseEventTable COURSE_EVENT = CourseEventTable.COURSE_EVENT;

    /**
     * The table <code>public.curriculum</code>.
     */
    public static final CurriculumTable CURRICULUM = CurriculumTable.CURRICULUM;

    /**
     * The table <code>public.eca_course</code>.
     */
    public static final EcaCourseTable ECA_COURSE = EcaCourseTable.ECA_COURSE;

    /**
     * The table
     * <code>public.eca_course_academic_major_education_level_map</code>.
     */
    public static final EcaCourseAcademicMajorEducationLevelMapTable ECA_COURSE_ACADEMIC_MAJOR_EDUCATION_LEVEL_MAP = EcaCourseAcademicMajorEducationLevelMapTable.ECA_COURSE_ACADEMIC_MAJOR_EDUCATION_LEVEL_MAP;

    /**
     * The table <code>public.eca_course_academic_map</code>.
     */
    public static final EcaCourseAcademicMapTable ECA_COURSE_ACADEMIC_MAP = EcaCourseAcademicMapTable.ECA_COURSE_ACADEMIC_MAP;

    /**
     * The table <code>public.eca_profile_option</code>.
     */
    public static final EcaProfileOptionTable ECA_PROFILE_OPTION = EcaProfileOptionTable.ECA_PROFILE_OPTION;

    /**
     * The table <code>public.eca_profile_section</code>.
     */
    public static final EcaProfileSectionTable ECA_PROFILE_SECTION = EcaProfileSectionTable.ECA_PROFILE_SECTION;

    /**
     * The table <code>public.education_level</code>.
     */
    public static final EducationLevelTable EDUCATION_LEVEL = EducationLevelTable.EDUCATION_LEVEL;

    /**
     * The table <code>public.educator_calendar</code>.
     */
    public static final EducatorCalendarTable EDUCATOR_CALENDAR = EducatorCalendarTable.EDUCATOR_CALENDAR;

    /**
     * The table <code>public.educator_calendar_extension</code>.
     */
    public static final EducatorCalendarExtensionTable EDUCATOR_CALENDAR_EXTENSION = EducatorCalendarExtensionTable.EDUCATOR_CALENDAR_EXTENSION;

    /**
     * The table <code>public.educator_profile</code>.
     */
    public static final EducatorProfileTable EDUCATOR_PROFILE = EducatorProfileTable.EDUCATOR_PROFILE;

    /**
     * The table <code>public.educator_profile_copy1</code>.
     */
    public static final EducatorProfileCopy1Table EDUCATOR_PROFILE_COPY1 = EducatorProfileCopy1Table.EDUCATOR_PROFILE_COPY1;

    /**
     * The table <code>public.educator_profile_expertise_academic_map</code>.
     */
    public static final EducatorProfileExpertiseAcademicMapTable EDUCATOR_PROFILE_EXPERTISE_ACADEMIC_MAP = EducatorProfileExpertiseAcademicMapTable.EDUCATOR_PROFILE_EXPERTISE_ACADEMIC_MAP;

    /**
     * The table <code>public.educator_profile_extension</code>.
     */
    public static final EducatorProfileExtensionTable EDUCATOR_PROFILE_EXTENSION = EducatorProfileExtensionTable.EDUCATOR_PROFILE_EXTENSION;

    /**
     * The table <code>public.educator_profile_language_map</code>.
     */
    public static final EducatorProfileLanguageMapTable EDUCATOR_PROFILE_LANGUAGE_MAP = EducatorProfileLanguageMapTable.EDUCATOR_PROFILE_LANGUAGE_MAP;

    /**
     * The table <code>public.educator_school</code>.
     */
    public static final EducatorSchoolTable EDUCATOR_SCHOOL = EducatorSchoolTable.EDUCATOR_SCHOOL;

    /**
     * The table <code>public.educator_session_note</code>.
     */
    public static final EducatorSessionNoteTable EDUCATOR_SESSION_NOTE = EducatorSessionNoteTable.EDUCATOR_SESSION_NOTE;

    /**
     * The table <code>public.educator_session_note_item</code>.
     */
    public static final EducatorSessionNoteItemTable EDUCATOR_SESSION_NOTE_ITEM = EducatorSessionNoteItemTable.EDUCATOR_SESSION_NOTE_ITEM;

    /**
     * The table <code>public.educator_session_note_map</code>.
     */
    public static final EducatorSessionNoteMapTable EDUCATOR_SESSION_NOTE_MAP = EducatorSessionNoteMapTable.EDUCATOR_SESSION_NOTE_MAP;

    /**
     * The table <code>public.event</code>.
     */
    public static final EventTable EVENT = EventTable.EVENT;

    /**
     * The table <code>public.event_extension</code>.
     */
    public static final EventExtensionTable EVENT_EXTENSION = EventExtensionTable.EVENT_EXTENSION;

    /**
     * The table <code>public.event_schedule_time</code>.
     */
    public static final EventScheduleTimeTable EVENT_SCHEDULE_TIME = EventScheduleTimeTable.EVENT_SCHEDULE_TIME;

    /**
     * The table <code>public.expertise</code>.
     */
    public static final ExpertiseTable EXPERTISE = ExpertiseTable.EXPERTISE;

    /**
     * The table <code>public.expertise_academic_major_map</code>.
     */
    public static final ExpertiseAcademicMajorMapTable EXPERTISE_ACADEMIC_MAJOR_MAP = ExpertiseAcademicMajorMapTable.EXPERTISE_ACADEMIC_MAJOR_MAP;

    /**
     * The table <code>public.i18n</code>.
     */
    public static final I18nTable I18N = I18nTable.I18N;

    /**
     * The table <code>public.interview_topic</code>.
     */
    public static final InterviewTopicTable INTERVIEW_TOPIC = InterviewTopicTable.INTERVIEW_TOPIC;

    /**
     * The table <code>public.language</code>.
     */
    public static final LanguageTable LANGUAGE = LanguageTable.LANGUAGE;

    /**
     * The table <code>public.opportunity</code>.
     */
    public static final OpportunityTable OPPORTUNITY = OpportunityTable.OPPORTUNITY;

    /**
     * The table <code>public.passion_major</code>.
     */
    public static final PassionMajorTable PASSION_MAJOR = PassionMajorTable.PASSION_MAJOR;

    /**
     * The table <code>public.passion_subject</code>.
     */
    public static final PassionSubjectTable PASSION_SUBJECT = PassionSubjectTable.PASSION_SUBJECT;

    /**
     * The table <code>public.passion_subject_answer</code>.
     */
    public static final PassionSubjectAnswerTable PASSION_SUBJECT_ANSWER = PassionSubjectAnswerTable.PASSION_SUBJECT_ANSWER;

    /**
     * The table <code>public.passion_subject_book</code>.
     */
    public static final PassionSubjectBookTable PASSION_SUBJECT_BOOK = PassionSubjectBookTable.PASSION_SUBJECT_BOOK;

    /**
     * The table <code>public.passion_subject_podcast</code>.
     */
    public static final PassionSubjectPodcastTable PASSION_SUBJECT_PODCAST = PassionSubjectPodcastTable.PASSION_SUBJECT_PODCAST;

    /**
     * The table <code>public.passion_subject_video</code>.
     */
    public static final PassionSubjectVideoTable PASSION_SUBJECT_VIDEO = PassionSubjectVideoTable.PASSION_SUBJECT_VIDEO;

    /**
     * The table <code>public.pricing</code>.
     */
    public static final PricingTable PRICING = PricingTable.PRICING;

    /**
     * The table <code>public.school</code>.
     */
    public static final SchoolTable SCHOOL = SchoolTable.SCHOOL;

    /**
     * The table <code>public.school_extension</code>.
     */
    public static final SchoolExtensionTable SCHOOL_EXTENSION = SchoolExtensionTable.SCHOOL_EXTENSION;

    /**
     * The table <code>public.school_identity</code>.
     */
    public static final SchoolIdentityTable SCHOOL_IDENTITY = SchoolIdentityTable.SCHOOL_IDENTITY;

    /**
     * The table <code>public.student_booking_survey_answer_map</code>.
     */
    public static final StudentBookingSurveyAnswerMapTable STUDENT_BOOKING_SURVEY_ANSWER_MAP = StudentBookingSurveyAnswerMapTable.STUDENT_BOOKING_SURVEY_ANSWER_MAP;

    /**
     * The table <code>public.student_booking_survey_map</code>.
     */
    public static final StudentBookingSurveyMapTable STUDENT_BOOKING_SURVEY_MAP = StudentBookingSurveyMapTable.STUDENT_BOOKING_SURVEY_MAP;

    /**
     * The table <code>public.student_eca_profile_map</code>.
     */
    public static final StudentEcaProfileMapTable STUDENT_ECA_PROFILE_MAP = StudentEcaProfileMapTable.STUDENT_ECA_PROFILE_MAP;

    /**
     * The table <code>public.student_milestone</code>.
     */
    public static final StudentMilestoneTable STUDENT_MILESTONE = StudentMilestoneTable.STUDENT_MILESTONE;

    /**
     * The table <code>public.student_milestone_map</code>.
     */
    public static final StudentMilestoneMapTable STUDENT_MILESTONE_MAP = StudentMilestoneMapTable.STUDENT_MILESTONE_MAP;

    /**
     * The table <code>public.student_milestone_options</code>.
     */
    public static final StudentMilestoneOptionsTable STUDENT_MILESTONE_OPTIONS = StudentMilestoneOptionsTable.STUDENT_MILESTONE_OPTIONS;

    /**
     * The table <code>public.student_milestone_questionnaire</code>.
     */
    public static final StudentMilestoneQuestionnaireTable STUDENT_MILESTONE_QUESTIONNAIRE = StudentMilestoneQuestionnaireTable.STUDENT_MILESTONE_QUESTIONNAIRE;

    /**
     * The table <code>public.student_payment_transaction</code>.
     */
    public static final StudentPaymentTransactionTable STUDENT_PAYMENT_TRANSACTION = StudentPaymentTransactionTable.STUDENT_PAYMENT_TRANSACTION;

    /**
     * The table <code>public.student_profile</code>.
     */
    public static final StudentProfileTable STUDENT_PROFILE = StudentProfileTable.STUDENT_PROFILE;

    /**
     * The table <code>public.student_profile_predicted_grade</code>.
     */
    public static final StudentProfilePredictedGradeTable STUDENT_PROFILE_PREDICTED_GRADE = StudentProfilePredictedGradeTable.STUDENT_PROFILE_PREDICTED_GRADE;

    /**
     * The table <code>public.student_profile_questionnaire_answer_map</code>.
     */
    public static final StudentProfileQuestionnaireAnswerMapTable STUDENT_PROFILE_QUESTIONNAIRE_ANSWER_MAP = StudentProfileQuestionnaireAnswerMapTable.STUDENT_PROFILE_QUESTIONNAIRE_ANSWER_MAP;

    /**
     * The table <code>public.student_profile_questionnaire_map</code>.
     */
    public static final StudentProfileQuestionnaireMapTable STUDENT_PROFILE_QUESTIONNAIRE_MAP = StudentProfileQuestionnaireMapTable.STUDENT_PROFILE_QUESTIONNAIRE_MAP;

    /**
     * The table <code>public.student_profile_school_report</code>.
     */
    public static final StudentProfileSchoolReportTable STUDENT_PROFILE_SCHOOL_REPORT = StudentProfileSchoolReportTable.STUDENT_PROFILE_SCHOOL_REPORT;

    /**
     * The table <code>public.student_questionnaire</code>.
     */
    public static final StudentQuestionnaireTable STUDENT_QUESTIONNAIRE = StudentQuestionnaireTable.STUDENT_QUESTIONNAIRE;

    /**
     * The table <code>public.student_questionnaire_answer</code>.
     */
    public static final StudentQuestionnaireAnswerTable STUDENT_QUESTIONNAIRE_ANSWER = StudentQuestionnaireAnswerTable.STUDENT_QUESTIONNAIRE_ANSWER;

    /**
     * The table <code>public.student_questionnaire_question</code>.
     */
    public static final StudentQuestionnaireQuestionTable STUDENT_QUESTIONNAIRE_QUESTION = StudentQuestionnaireQuestionTable.STUDENT_QUESTIONNAIRE_QUESTION;

    /**
     * The table <code>public.student_questionnaire_section</code>.
     */
    public static final StudentQuestionnaireSectionTable STUDENT_QUESTIONNAIRE_SECTION = StudentQuestionnaireSectionTable.STUDENT_QUESTIONNAIRE_SECTION;

    /**
     * The table <code>public.student_session_survey</code>.
     */
    public static final StudentSessionSurveyTable STUDENT_SESSION_SURVEY = StudentSessionSurveyTable.STUDENT_SESSION_SURVEY;

    /**
     * The table <code>public.student_session_survey_answer</code>.
     */
    public static final StudentSessionSurveyAnswerTable STUDENT_SESSION_SURVEY_ANSWER = StudentSessionSurveyAnswerTable.STUDENT_SESSION_SURVEY_ANSWER;

    /**
     * The table <code>public.student_session_survey_question</code>.
     */
    public static final StudentSessionSurveyQuestionTable STUDENT_SESSION_SURVEY_QUESTION = StudentSessionSurveyQuestionTable.STUDENT_SESSION_SURVEY_QUESTION;

    /**
     * The table <code>public.student_uploaded_interview</code>.
     */
    public static final StudentUploadedInterviewTable STUDENT_UPLOADED_INTERVIEW = StudentUploadedInterviewTable.STUDENT_UPLOADED_INTERVIEW;

    /**
     * The table <code>public.student_uploaded_supervisor_review</code>.
     */
    public static final StudentUploadedSupervisorReviewTable STUDENT_UPLOADED_SUPERVISOR_REVIEW = StudentUploadedSupervisorReviewTable.STUDENT_UPLOADED_SUPERVISOR_REVIEW;

    /**
     * The table <code>public.student_uploaded_writing</code>.
     */
    public static final StudentUploadedWritingTable STUDENT_UPLOADED_WRITING = StudentUploadedWritingTable.STUDENT_UPLOADED_WRITING;

    /**
     * The table <code>public.tag</code>.
     */
    public static final TagTable TAG = TagTable.TAG;

    /**
     * The table <code>public.tagging</code>.
     */
    public static final TaggingTable TAGGING = TaggingTable.TAGGING;

    /**
     * The table <code>public.user</code>.
     */
    public static final UserTable USER = UserTable.USER;

    /**
     * The table <code>public.user_notification</code>.
     */
    public static final UserNotificationTable USER_NOTIFICATION = UserNotificationTable.USER_NOTIFICATION;

    /**
     * The table <code>public.writing_topic</code>.
     */
    public static final WritingTopicTable WRITING_TOPIC = WritingTopicTable.WRITING_TOPIC;
}
