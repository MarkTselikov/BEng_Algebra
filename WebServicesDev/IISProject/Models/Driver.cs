using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Runtime.Serialization;

namespace IISProject.Models
{
    [DataContract]
    public class Driver
    {
        [DataMember]
        public string FirstName { get; set; }
        [DataMember]
        public string LastName { get; set; }
        [DataMember]
        public DateTime LicenceAcquiredDate { get; set; }
        [DataMember]
        public DateTime LicenceExpirationDate { get; set; }
        [DataMember]
        public string OIB { get; set; }
        [DataMember]
        public string VehicleCategory { get; set; }

        public Driver() { }

        public Driver(string fName, string lName, string oib, 
            DateTime licenceAcq, DateTime licenceExp, string vehicleCategory)
        {
            FirstName = fName;
            LastName = lName;
            OIB = oib;
            LicenceAcquiredDate = licenceAcq;
            LicenceExpirationDate = licenceExp;
            VehicleCategory = vehicleCategory;
        }


        public override string ToString()
        {
            return $"{FirstName} {LastName} - {OIB} | {LicenceAcquiredDate} / {LicenceExpirationDate} ({VehicleCategory})";
        }
    }
}



/*
        //[DataMember (Name = "FirstName")]
        [DataMember]
        public string FName { get; set; }
        //[DataMember(Name = "LastName")]
        [DataMember]
        public string LName { get; set; }
        [DataMember]
        public string OIB { get; set; }
        //[DataMember (Name = "LicenceAcquiredDate")]
        [DataMember]
        public DateTime LicenceAcq { get; set; }
        //[DataMember(Name = "LicenceExpirationDate")]
        [DataMember]
        public DateTime LicenceExp { get; set; }
        [DataMember]
        public string VehicleCategory { get; set; }
 */
