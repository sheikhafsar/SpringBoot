package sheikhafsar.in.springboot.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class RestEndpoints {

    @Value("${default.course.name}")
    private String cName;
    @Value("${default.course.chapterCount}")
    private int chapCount;

    @Autowired
    private CourseConfiguration courseConfiguration;

    @RequestMapping("/defaultCourse") //to create endpoint
    public Course getdefaultCourse(@RequestParam(value="name",defaultValue="Spring Boot",required = false)String name,
                              @RequestParam(value="chapterCount",defaultValue="2",required = false)int chapterCount){
        return new Course(cName,chapCount);
    }

    @RequestMapping("/gethierarchical") //to create endpoint
    public HashMap<String,Object> getConfigAnnotateProperties(){
        HashMap<String,Object> map = new HashMap<String,Object>();

        map.put("name",courseConfiguration.getName());
        map.put("chapterCount",courseConfiguration.getChapterCount());
        map.put("rating",courseConfiguration.getRating());
        map.put("author",courseConfiguration.getAuthor());

        return map;
    }

    @RequestMapping("/course") //to create endpoint
    public Course getEndpoint(@RequestParam(value="name",defaultValue="Spring Boot",required = false)String name,
                              @RequestParam(value="chapterCount",defaultValue="2",required = false)int chapterCount){
        return new Course(name,chapterCount);
    }

    @RequestMapping(method = RequestMethod.POST, value ="/register/course")
    public String saveCourse(@RequestBody Course course){
        return  "Your course named "+ course.getName()+"with chapter count "+course.getChapterCount()+" is saved successfully";
    }

}
