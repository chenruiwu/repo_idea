package com.lagou.controller;

import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseContentService;
import org.graalvm.compiler.core.common.type.IntegerStamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/courseContent")
public class CourseContentController {

    @Autowired
    private CourseContentService courseContentService;

    @RequestMapping("/courseContent")
    public ResponseResult findSectionAndLessonByCourseId(Integer courseId){

        /*调用service*/
        List<CourseSection> list = courseContentService.findSectionAndLessonByCourseId(courseId);

        ResponseResult responseResult = new ResponseResult(true, 200, "章节及课时查询成功", list);

        return responseResult;
    }

    /*回显章节对应的课程信息*/
    @RequestMapping("/findCourseByCourseId")
    public ResponseResult findCourseByCourseId(Integer courseId){

        Course course = courseContentService.findCourseByCourseId(courseId);

        ResponseResult responseResult = new ResponseResult(true, 200, "查询课程信息成功", course);

        return responseResult;
    }

    /*新增&更新章节信息*/
    @RequestMapping("/saveOrUpdateSection")
    public ResponseResult saveOrUpdateSection(@RequestBody CourseSection courseSection){

        /*判断是否携带了章节ID*/
        if(courseSection.getId() == null){
            //新增
            courseContentService.saveSection(courseSection);
            return new ResponseResult(true,200,"新增章节成功",null);
        }else {
            //更新
            courseContentService.updateSection(courseSection);
            return new ResponseResult(true,200,"新增章节成功",null);
        }
    }

    /*修改章节状态*/
    @RequestMapping("/updateSectionStatus")
    public ResponseResult updateSectionStatus(int id,int status){

        courseContentService.updateSectionStatus(id,status);

        /*数据响应*/
        HashMap<Object, Object> map = new HashMap<>();
        map.put("status",status);

        ResponseResult responseResult = new ResponseResult(true, 200, "修改章节状态成功", map);
        return responseResult;

    }
    /**
     * 保存&修改课时
     * */
    @RequestMapping("/saveOrUpdateLesson")
    public ResponseResult saveOrUpdateLesson(@RequestBody CourseLesson lesson){
        try {
            if(lesson.getId() == null){
                courseContentService.saveLesson(lesson);
                return new ResponseResult(true,200,"响应成功",null);
            }else{
                courseContentService.updateLesson(lesson);
                return new ResponseResult(true,200,"响应成功",null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
