using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

using ConsoleApp1;

namespace WpfApp1
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();
        }

        private void Button_Click(object sender, RoutedEventArgs e)
        {
            string studentName = "";
            string universityName = "";

            List<Student> studentlist = StudentList.Students();

            int studentID = 0;

            if(int.TryParse(StudentID.Text, out studentID))
            {
                foreach (Student student in studentlist)
                {
                    if(studentID.Equals(student.Id))
                    {
                        studentName = student.Name;
                        universityName = student.University;
                    }
                }
                if (studentName.Equals(""))
                {
                    StudentID.Text = "No match";
                }
                else
                {
                    StudentID.Text = studentID.ToString();
                }
            }
            else
            {
                StudentID.Text = "Invalid Student ID";
            }

            StudentName.Text = studentName;
            UniversityName.Text = universityName;


        }
    }
}
