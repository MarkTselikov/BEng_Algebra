using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Project_1.Models
{
    public class Role
    {
        public int IDRole { get; set; }
        public string Name { get; set; }

        public override string ToString()
        {
            return Name;
        }
    }
}