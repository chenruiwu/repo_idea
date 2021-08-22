package com.lagou.service;

import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;

import java.util.List;

public interface CourseContentService {

    /*根据课程id查询对应的课程信息 章节信息+课时信息*/
    public List<CourseSection> findSectionAndLessonByCourseId(Integer courseId);

    /*回显章节对应的课程信息*/
    public Course findCourseByCourseId(int courserId);

    /*新增章节信息*/
    public void saveSection(CourseSection courseSection);

    /*更新章节信息*/
    void updateSection(CourseSection courseSection);

    /*修改章节状态*/
    public void updateSectionStatus(int id, int status);

    /*保存课时*/
    public void saveLesson(CourseLesson lesson);

    void updateLesson(CourseLesson lesson);
}
