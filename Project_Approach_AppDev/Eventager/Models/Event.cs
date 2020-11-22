using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Eventager.Models
{
    public class Event
    {
        public int IDEvent { get; set; }
        public string Name { get; set; }
        public string Description { get; set; }
        public DateTime EventStart { get; set; }
        public DateTime EventEnd { get; set; }
        public int CategoryID { get; set; }
        //public decimal Price { get; set; }
        public int BreakTime { get; set; }
    }
}