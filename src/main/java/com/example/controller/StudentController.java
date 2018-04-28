package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.model.StudentModel;
import com.example.service.StudentService;

@Controller
public class StudentController
{
    @Autowired
    StudentService studentDAO;





    @RequestMapping("/student/add")
    public String add (Model model)
    {
        model.addAttribute("title", "Add Student");
        return "form-add";
    }


    @RequestMapping("/student/add/submit")
    public String addSubmit (
            @RequestParam(value = "npm", required = false) String npm,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "gpa", required = false) double gpa, Model model)
    {
        StudentModel student = new StudentModel (npm, name, gpa);
        studentDAO.addStudent (student);
        model.addAttribute("title","Success Add Student");
        return "success-add";
    }


    @RequestMapping("/student/view")
    public String view (Model model,
            @RequestParam(value = "npm", required = false) String npm)
    {
        StudentModel student = studentDAO.selectStudent (npm);

        if (student != null) {
            model.addAttribute ("student", student);
            model.addAttribute("title","View Student");
            return "view";
        } else {
            model.addAttribute ("npm", npm);
            model.addAttribute("title","Student Not Found");
            return "not-found";
        }
    }

    @RequestMapping("/student/search")
    public String search(Model model) {
        model.addAttribute("title", "Search mahasiswa");
        return "search";
    }


    @RequestMapping("/student/view/{npm}")
    public String viewPath (Model model,
            @PathVariable(value = "npm") String npm)
    {
        StudentModel student = studentDAO.selectStudent (npm);

        if (student != null) {
            model.addAttribute ("student", student);
            model.addAttribute("title","View Student");
            return "view";
        } else {
            model.addAttribute ("npm", npm);
            model.addAttribute("title","Student Not Found");
            return "not-found";
        }
    }


    @RequestMapping("/student/viewall")
    public String view (Model model)
    {
        List<StudentModel> students = studentDAO.selectAllStudents ();
        model.addAttribute ("students", students);
        model.addAttribute("title","View All Student");
        return "viewall";
    }

    @RequestMapping(value = "/student/data-all-student", method = RequestMethod.GET)
    public ResponseEntity<List<StudentModel>> dataAllStudent(Model model){
        List<StudentModel> students = studentDAO.selectAllStudents();
        model.addAttribute("tittle","View All Student");
        return new ResponseEntity<List<StudentModel>>(students, HttpStatus.OK);
    }

    @RequestMapping("/student/delete/{npm}")
    public String delete (Model model, @PathVariable(value = "npm") String npm)
    {
        StudentModel student = studentDAO.selectStudent(npm);
        if(student != null){
            studentDAO.deleteStudent(npm);
            model.addAttribute("title","Success Delete Student");
            return "delete";
        }else{
            model.addAttribute ("npm", npm);
            model.addAttribute("title","Student Not Found");
            return "not-found";
        }

    }

    @RequestMapping("/student/update/{npm}")
    public String update(Model model, @PathVariable(value = "npm") String npm){
        StudentModel student = studentDAO.selectStudent(npm);
        if(student != null){
            model.addAttribute("student", student);
            model.addAttribute("title","Update Student");
            return "form-update";
        }else{
            model.addAttribute ("npm", npm);
            model.addAttribute("title","Student Not Found");
            return "not-found";
        }
    }

    @RequestMapping(value = "/student/update/submit", method = RequestMethod.POST)
    public String updateSubmit(@ModelAttribute StudentModel student, Model model){
        studentDAO.updateStudent(student);
        model.addAttribute("title","Success Update Student");
        return "success-update";
    }


}
