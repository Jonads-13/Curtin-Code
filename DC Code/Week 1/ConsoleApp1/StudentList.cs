﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApp1
{
    public class StudentList
    {
        public static List<Student> Students() 
        {
            List<Student> slist = new List<Student>();

            Student student1 = new Student();
            student1.Name = "Robert";
            student1.University = "Curtin";
            student1.Id = 101;

            Student student2 = new Student();
            student2.Name = "Mia";
            student2.University = "EWU";
            student2.Id = 102;

            Student student3 = new Student();
            student3.Name = "Adam";
            student3.University = "Murdoch";
            student3.Id = 103;

            Student student4 = new Student();
            student4.Name = "Nick";
            student4.University = "Curtin College";
            student4.Id = 104;

            Student student5 = new Student();
            student5.Name = "Jake";
            student5.University = "Curtin";
            student5.Id = 105;

            slist.Add(student1);
            slist.Add(student2);
            slist.Add(student3);
            slist.Add(student4);
            slist.Add(student5);

            return slist;


        }
    }
}
