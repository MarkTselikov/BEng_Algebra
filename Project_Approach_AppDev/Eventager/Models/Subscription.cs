﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Eventager.Models
{
    public class Subscription
    {
        public int IDSubscription { get; set; }
        public string Type { get; set; }
        public decimal Price { get; set; }
    }    
}