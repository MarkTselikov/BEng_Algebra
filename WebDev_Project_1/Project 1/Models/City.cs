using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Project_1.Models
{
    public class City
    {
        public int IDCity { get; set; }
        public string Name { get; set; }

        public override string ToString()
        {
            return Name;
        }
    }
}