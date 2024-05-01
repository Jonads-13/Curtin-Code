using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ClientGui
{
    public class Job
    {
        public int Id { get; set; }
        public string ClientPort { get; set; }
        public string PythonCode { get; set; }
    }
}
