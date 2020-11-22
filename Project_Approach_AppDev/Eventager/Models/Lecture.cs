using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Eventager.Models
{
    public class Lecture
    {
        public int IDLecture { get; set; }
        public string Name { get; set; }
        public string Description { get; set; }
        public DateTime Start { get; set; }
        public DateTime End { get; set; }
        public int EventLocationID { get; set; }
        public int LecturerID { get; set; }
        public string Hall { get; set; }
        public int Capacity { get; set; }
        public int EventID { get; set; }
    }
}