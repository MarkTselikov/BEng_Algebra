using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Eventager.Models
{
    public class LectureUser
    {
        public int IDLectureUser { get; set; }
        public User User { get; set; }
        public Lecture Lecture { get; set; }
        public int TicketsNum { get; set; }
    }
}