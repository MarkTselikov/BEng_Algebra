﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Eventager.Models
{
    public class EventLocation
    {
        public int IDEventLocation { get; set; }
        public string Name { get; set; }
        public string Address { get; set; }
        public City City { get; set; }
    }
}